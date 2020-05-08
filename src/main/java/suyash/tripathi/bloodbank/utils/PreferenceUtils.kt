package suyash.tripathi.bloodbank.utils


import android.content.Context
import androidx.preference.PreferenceManager
import suyash.tripathi.bloodbank.R


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class PreferenceUtils {

    fun isNotificationsEnabled(context: Context): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.getBoolean(context.getString(R.string.notification_key), true)
    }

    companion object {

        fun getLanguageCode(context: Context): String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(context.getString(R.string.language_key), context.getString(R.string.arabic_language_value))
        }
    }
}
