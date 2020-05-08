package suyash.tripathi.bloodbank.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import suyash.tripathi.bloodbank.models.Shoutout
import suyash.tripathi.bloodbank.repository.ShoutoutRepository

class ShoutoutsViewModel : ViewModel(), KoinComponent {
    private val mRepository: ShoutoutRepository by inject()

    val shoutouts: LiveData<List<Shoutout>>?
        get() = mRepository.shoutouts

    fun createShoutout(shoutout: Map<String, Any>) {
        mRepository.createShoutout(shoutout)
    }

}
