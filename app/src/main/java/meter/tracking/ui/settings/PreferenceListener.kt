package meter.tracking.ui.settings

import android.content.Context
import androidx.preference.Preference
import meter.tracking.ui.settings.SettingsKeys.NOTIFICATION_SETTINGS

/**
 * Listen preference update to deal with notification setup/cleanup
 * @see SettingsKeys for available keys
 */
class PreferenceListener : Preference.OnPreferenceChangeListener {

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        when (preference.key) {
            NOTIFICATION_SETTINGS -> {
                val value = preference.summary
                if ("true" == value) {
                    this.activateNotification(preference.context)
                }
            }
        }

        return true
    }

    private fun activateNotification(context: Context?) {
        TODO("not implemented")
    }
}