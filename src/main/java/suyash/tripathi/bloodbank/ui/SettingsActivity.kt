package suyash.tripathi.bloodbank.ui


import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.android.inject
import suyash.tripathi.bloodbank.R
import suyash.tripathi.bloodbank.databinding.ActivitySettingsBinding
import suyash.tripathi.bloodbank.utils.ConfigHelper
import suyash.tripathi.bloodbank.utils.Constants

class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private val mFirebaseMessaging by inject<FirebaseMessaging>()
    private var mBinding: ActivitySettingsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)
        registerPreferenceListener()
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(mBinding!!.toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
            actionBar.title = getString(R.string.settings_action_bar_label)
        }
        mBinding!!.toolbar.setNavigationIcon(R.drawable.ic_nav_back)
    }

    private fun registerPreferenceListener() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == getString(R.string.notification_key)) {
            val isNotificationSelected = sharedPreferences.getBoolean(key, true)
            if (isNotificationSelected) {
                mFirebaseMessaging.subscribeToTopic(Constants.SHOUTOUTS_TOPIC_NAME)
            } else {
                mFirebaseMessaging.unsubscribeFromTopic(Constants.SHOUTOUTS_TOPIC_NAME)
            }
        } else if (key == getString(R.string.language_key)) {
            val language = sharedPreferences.getString(key, getString(R.string.arabic_language_value))
            ConfigHelper.setLanguage(resources, language!!)
            recreate()

        }
    }
}
