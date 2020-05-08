package suyash.tripathi.bloodbank.ui


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import suyash.tripathi.bloodbank.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }
}
