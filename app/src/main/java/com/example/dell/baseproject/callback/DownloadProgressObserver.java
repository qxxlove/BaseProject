package com.example.dell.baseproject.callback;

import com.example.dell.baseproject.bean.DiwnLoadInfo;

/**
 * description: 
 * autour: TMM
 * date: 2017/10/27 9:58 
 * update: 2017/10/27
 * version: 
*/

public interface DownloadProgressObserver {

    void  downloadProgressChanged (int progress);


    void DOwnLoadStateChange(DiwnLoadInfo info);

    void DownLoadProgressChange(DiwnLoadInfo info);
}
