package com.wwxiaoqi.wechat_backup.thread;

import android.app.ProgressDialog;
import android.content.Context;
import com.wwxiaoqi.wechat_backup.R;
import com.wwxiaoqi.wechat_backup.utils.ShellUtils;
import java.io.File;

public class RestoreThread extends Thread {
  private File fileDir;
  private Context context;
  private ProgressDialog progressDialog;

  public RestoreThread(Context context, File fileDir) {
    this.fileDir = fileDir;
    this.context = context;
    progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(context.getString(R.string.restore_prompt));
    progressDialog.setCancelable(false);
    progressDialog.show();
  }

  @Override
  public void run() {
    try {
      sleep(3000);
      new Thread(() -> {
        String[] cmd = { "su", "source " + fileDir + "/recovery.sh" };
        ShellUtils.execCmd(cmd, true);
      }).start();
      progressDialog.setMessage(context.getString(R.string.restore_success_prompt));
      sleep(3000);
      progressDialog.dismiss();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
