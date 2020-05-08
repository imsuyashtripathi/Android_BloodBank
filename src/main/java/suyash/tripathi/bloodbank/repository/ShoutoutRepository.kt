package suyash.tripathi.bloodbank.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import org.greenrobot.eventbus.EventBus
import org.koin.core.KoinComponent
import org.koin.core.inject
import suyash.tripathi.bloodbank.eventbus.ShoutoutCreationEvent
import suyash.tripathi.bloodbank.models.Shoutout
import suyash.tripathi.bloodbank.utils.Constants
import java.util.*

class ShoutoutRepository : KoinComponent {
    private val mFirestore by inject<FirebaseFirestore>()
    var shoutouts: MutableLiveData<List<Shoutout>>? = null
        private set


    init {
        if (shoutouts == null) {
            shoutouts = MutableLiveData()
            loadShoutouts()
        }
    }

    fun createShoutout(shoutout: Map<String, Any>) {
        mFirestore.collection(Constants.SHOUTOUTS_COLLECTION_NAME)
                .document()
                .set(shoutout)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        postEvent("success")
                    } else {
                        postEvent("fail")
                        logger(task.exception.toString())
                    }
                }
    }

    private fun postEvent(event: String) {
        EventBus.getDefault().post(ShoutoutCreationEvent(event))
    }

    private fun loadShoutouts() {
        mFirestore.collection(Constants.SHOUTOUTS_COLLECTION_NAME)
                .orderBy(Constants.SHOUTOUT_TIME, Query.Direction.DESCENDING)
                .addSnapshotListener { queryDocumentSnapshots, e ->
                    if (e != null) logger("listening failed!")
                    else {
                        val shoutoutsList = ArrayList<Shoutout>()
                        if (queryDocumentSnapshots != null) {
                            for (doc in queryDocumentSnapshots) {
                                shoutoutsList.add(doc.toObject(Shoutout::class.java))
                            }
                        }
                        shoutouts!!.setValue(shoutoutsList)
                    }
                }
    }

    private fun logger(s: String) {
        Log.d(TAG, "logger: $s")
    }

    companion object {
        private const val TAG = "ShoutoutRepository"
    }


}
