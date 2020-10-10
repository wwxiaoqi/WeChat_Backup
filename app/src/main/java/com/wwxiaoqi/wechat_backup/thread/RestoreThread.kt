package com.wwxiaoqi.wechat_backup.thread

import android.app.ProgressDialog
import android.content.Context
import com.wwxiaoqi.wechat_backup.R
import com.wwxiaoqi.wechat_backup.utils.ShellUtils
import java.io.File

class RestoreThread(private val context: Context, private val fileDir: File) : Thread() {
  private val progressDialog: ProgressDialog = ProgressDialog(context)
  override fun run() {
    try {
      sleep(3000)
      Thread {
        val cmd = arrayOf("su", "source $fileDir/recovery.sh")
        ShellUtils.execCmd(cmd, true)
      }.start()
      progressDialog.setMessage(context.getString(R.string.restore_success_prompt))
      sleep(3000)
      progressDialog.dismiss()
    } catch (e: InterruptedException) {
      e.printStackTrace()
    }
  }

  init {
    progressDialog.setMessage(context.getString(R.string.restore_prompt))
    progressDialog.setCancelable(false)
    progressDialog.show()
  }
}