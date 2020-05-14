package meter.tracking.ui.metrics.main.export

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import java.io.File


/**
 * This [AsyncTask] send a mail to the address given in parameter with a csv file containing an export
 * of metrics
 * Return a instance of [ExportTaskResult]. The [Bundle] inside this pojo can be used to send mail
 * and the [File] have to be deleted after mail sending
 * @param dir [File] : the output Directory  where to put the attached file
 */
class ExportAsyncTask(private val dir: File, private val callback: (result: ExportTaskResult) -> Unit) : AsyncTask<String, Int, ExportTaskResult>() {


    override fun doInBackground(vararg params: String): ExportTaskResult {

        val exportFile = getExportFile()
        val bundle = Bundle()
        bundle.putString(Intent.EXTRA_SUBJECT, "")
        bundle.putStringArray(Intent.EXTRA_EMAIL, params)
        bundle.putParcelable(Intent.EXTRA_STREAM, Uri.fromFile(exportFile))
        return ExportTaskResult(bundle, exportFile)
    }

    override fun onPostExecute(result: ExportTaskResult?) {
        super.onPostExecute(result)
        if (result != null)
            callback.invoke(result)
    }

    private fun getExportFile(): File {
        return File.createTempFile("export", null, dir)
    }
}