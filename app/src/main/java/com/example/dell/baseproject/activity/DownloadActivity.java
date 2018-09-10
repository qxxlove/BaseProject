package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.base.API;
import com.example.dell.baseproject.bean.Appinfo;
import com.example.dell.baseproject.bean.DiwnLoadInfo;
import com.example.dell.baseproject.callback.DownloadProgressObserver;
import com.example.dell.baseproject.utils.DownloadManager;
import com.example.dell.baseproject.utils.LogUtilsThree;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadActivity extends AppCompatActivity implements DownloadProgressObserver {

    @BindView(R.id.text_name_apk)
    TextView textNameApk;
    @BindView(R.id.text_progress_apk)
    TextView textProgressApk;
    @BindView(R.id.pb_progress_bar_apk)
    ProgressBar pbProgressBarApk;
    @BindView(R.id.pb_progress_bar_count_apk)
    TextView pbProgressBarCountApk;
    @BindView(R.id.activity_download)
    LinearLayout activityDownload;
    @BindView(R.id.text_start_apk)
    TextView textStartApk;
    @BindView(R.id.text_stop_apk)
    TextView textStopApk;

    private DownloadManager downloadManager;
    private   Appinfo appinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        appinfo = new Appinfo(1,"豌豆荚", API.apk_url,7.64,"com..andoujia.com");
        downloadManager = DownloadManager.getInstance();
    }

    @OnClick({R.id.text_start_apk,R.id.text_stop_apk})
    public void initClick(View view){
        switch (view.getId()){
            case R.id.text_start_apk:
                 downloadManager.download(appinfo);
                break;
            case R.id.text_stop_apk:
                downloadManager.pause(appinfo);
                break;
            default:
                LogUtilsThree.e("error",new Throwable());
        }
    }

    @Override
    public void downloadProgressChanged(int progress) {

    }

    @Override
    public void DOwnLoadStateChange(DiwnLoadInfo info) {

    }

    @Override
    public void DownLoadProgressChange(DiwnLoadInfo info) {
        pbProgressBarApk.setProgress((int)info.getProgress());
    }
}
