package meter.tracking.ui.settings

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import meter.tracking.R


class SettingsActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private val mTAG = "SettingsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)
            val preferenceListener = PreferenceListener()
            val notificationEnabled = findPreference<SwitchPreferenceCompat>(SettingsKeys.NOTIFICATION_SETTINGS)
            if (notificationEnabled != null) {
                notificationEnabled.onPreferenceChangeListener = preferenceListener
            }

            val timePicker = findPreference<Preference>(SettingsKeys.NOTIFICATION_TIME)
            if (timePicker != null) {
                timePicker.summary = PreferenceManager.getDefaultSharedPreferences(this.context)
                        .getString(SettingsKeys.NOTIFICATION_TIME, "8:00")
                timePicker.setOnPreferenceClickListener { (SettingsActivity::showDateDialog); true }
                timePicker.onPreferenceChangeListener = preferenceListener
            }
        }
    }

    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        if (!PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putString(SettingsKeys.NOTIFICATION_TIME, "$hour:$minute").commit()) {
            Log.e(mTAG, "Failed to save preference: ${SettingsKeys.NOTIFICATION_TIME} value=$hour:$minute")
        }
    }

    fun showDateDialog() {
        val time = PreferenceManager.getDefaultSharedPreferences(this).getString(SettingsKeys.NOTIFICATION_TIME, "8:0")
        val split = time!!.split(':')
        TimePickerDialog(this, this, split[0].toInt(), split[1].toInt(), true).show()
    }
}