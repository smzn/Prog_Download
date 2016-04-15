package com.example.mizuno.prog_download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mizuno on 2016/04/15.
 */
public class DownLoadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // ダウンロードが完了したら結果用アクティビティを表示
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            Toast.makeText(context, "Download Complete ID : " + id, Toast.LENGTH_LONG).show();
            if (id != -1) {
                Log.d("StringData", "StringData");
                try {
                    Toast.makeText(context, "ダウンロードに成功",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(context, "アプリが存在しません。",Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // ダウンロードマネージャの通知領域をクリックした場合はメッセージ表示のみ
            Toast.makeText(context, intent.getAction(), Toast.LENGTH_LONG).show();
        }

    }
}
