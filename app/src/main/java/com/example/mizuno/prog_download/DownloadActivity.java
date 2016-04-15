package com.example.mizuno.prog_download;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

public class DownloadActivity extends AppCompatActivity {

    DownloadManager downloadManager;
    String filePath = "/cakephp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        for(int i=0;i<10;i++) {
            //URIを生成する
            Uri.Builder uriBuilder = new Uri.Builder();
            uriBuilder = uriCreate(uriBuilder, "http", "j11000.sangi01.net",String.valueOf(i));
            DownloadManager.Request request = new DownloadManager.Request(uriBuilder.build());

            //保存場所、形式、ファイル名を指定
            request = requestSet(request, String.valueOf(i));
            long id = downloadManager.enqueue(request);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(id);
            final Cursor cursor = downloadManager.query(query);
            final int idStatus = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            cursor.moveToFirst();
        }

    }

    public Uri.Builder uriCreate(Uri.Builder uriBuilder, String scheme, String authority, String path) {
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        uriBuilder.scheme(scheme);
        uriBuilder.authority(authority);
        uriBuilder.path(filePath + path);
        return uriBuilder;
    }

    public DownloadManager.Request requestSet(DownloadManager.Request request, String title) {
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, "/Signage/"+title+".mp4");
        request.setTitle(title + ".mp4");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setMimeType("video/mp4");

        return request;
    }

}
