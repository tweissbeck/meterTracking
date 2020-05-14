package meter.tracking.ui.metrics.main.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import meter.tracking.R
import java.io.File


/**
 * This create a [DialogFragment] with a single form attribute: email and 2 buttons
 * On 'send report' action, use the provided email and launch task that will export counters
 * On 'cancel', hide the dialog
 * @param defaultEmail a default mail to put on field email
 * @param sendMail a callback called on positive button of the dialog is pressed. Take the mail in parameter
 */

class ExportCountersDialog(private val defaultEmail: String?, private val sendMail: (String, File) -> Unit) : DialogFragment() {

    lateinit var email: AutoCompleteTextView

    companion object {
        const val TAG: String = "ExportCountersDialog"
        const val SHARED_PREF_EXPORT_MAILS = "export_mails"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            this.email = it.findViewById(R.id.export_counters_dialog_email)
            if (this.defaultEmail != null) {
                this.email.setText(defaultEmail)
            }

            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

            val emails: Array<String> = if (sharedPref != null) {
                val set = HashSet<String>()
                sharedPref.getStringSet(SHARED_PREF_EXPORT_MAILS, set)!!.toTypedArray()
            } else {
                arrayOf()
            }

            ArrayAdapter<String>(it.baseContext, android.R.layout.simple_list_item_1, emails).also { adapter ->
                email.setAdapter(adapter)
            }

            builder.setView(inflater.inflate(R.layout.export_counters_dialog, null))
            builder.setMessage(R.string.export_counters_dialog_message)
                    .setPositiveButton(R.string.export_counter_dialog_positive_action) { dialog, id ->
                        val mailAddress = this.email.editableText.toString()
                        // save used mail into shared pref, don't need to test if this mail is already
                        // in shared pref as its a set
                        sharedPref?.edit()?.putStringSet(
                                SHARED_PREF_EXPORT_MAILS,
                                sharedPref.getStringSet(
                                        SHARED_PREF_EXPORT_MAILS,
                                        emptySet()
                                )!!.plus(mailAddress))

                        // Use call back to notify parent a mail has been selected & export required
                        this.sendMail(mailAddress, context?.cacheDir
                                ?: throw java.lang.IllegalStateException("Context is null"))
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}