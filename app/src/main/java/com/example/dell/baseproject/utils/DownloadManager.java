package com.example.dell.baseproject.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.dell.baseproject.bean.Appinfo;
import com.example.dell.baseproject.bean.DiwnLoadInfo;
import com.example.dell.baseproject.callback.DownloadProgressObserver;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/27.
 * 邮箱：123123@163.com
 */

public class DownloadManager {

    private static final String TAG = "DownLoadManager";

    public static final int STATE_UNDO = 1;
    public static final int STATE_WAITING = 2;
    public static final int STATE_DOWNLOADING = 3;
    public static final int STATE_PAUSE = 4;
    public static final int STATE_ERROR = 5;
    public static final int STATE_SUCCESS = 6;




    /**
     *      单例模式，饿汉模式
     */
    private static DownloadManager mDM = new DownloadManager();
    private static ArrayList<DownloadProgressObserver> observers = new ArrayList<>();

    /**
     * 下载对象的集合
     * 使用线程安全的模式
     * @return
     */
    private ConcurrentHashMap<Integer,DiwnLoadInfo> DownLoinfoCHM =  new ConcurrentHashMap<Integer, DiwnLoadInfo>();

    /**
     *     下载任务的对象的集合
     */
    private ConcurrentHashMap<Integer, DownLoadTask> loadTaskCHM = new ConcurrentHashMap<Integer, DownLoadTask>();

    /**
     *  单例模式，饿汉模式
     */
    public static DownloadManager getInstance(){
        return mDM;
    }



    /**
     *   注册
     */
    public void registerObserver(DownloadProgressObserver observer){
        if(observer != null&& !observers.contains(observer)){
            //1.注册，也就是将接口存入集合当中
            observers.add(observer);
        }
    }

    /**
     *   注销
     */

    public void unRegisterObser(DownloadProgressObserver observer){
        if(observer!= null&& observers.contains(observer)){
            //2.注销，也就是从集合中删除
            observers.remove(observer);
        }
    }

    /**
     *  提醒观察者，数据更新了
     * @param progress
     */
    public void notifytyDownLoadPregress(Integer progress){
        for (DownloadProgressObserver observer:observers) {
            observer.downloadProgressChanged(progress);
        }
    }


    /**
     *  提醒观察者，刷新数据，下载的状态
     */
    public void notifyDownLoadStateChange(DiwnLoadInfo info){
        for (DownloadProgressObserver observer:observers) {
            observer.DOwnLoadStateChange(info);
        }
    }

    /**
     *  提醒观察者，数据更新了
     */
    public void notifytyDownLoadPregress(DiwnLoadInfo info){
        for (DownloadProgressObserver observer:observers) {
            observer.DownLoadProgressChange(info);
        }
    }

    /**
     * 1.下载apk文件
     */
    public void download(Appinfo appinfo){

        DiwnLoadInfo downLoadInfo = DownLoinfoCHM.get(appinfo.id);

        if(downLoadInfo == null){
            downLoadInfo = DiwnLoadInfo.copy(appinfo);
        }
        //修改下载的状态，等待下载
        downLoadInfo.currentState = STATE_WAITING;
        notifyDownLoadStateChange(downLoadInfo);
        //把下载对象信息，加入下载对象集合，保存起来
        DownLoinfoCHM.put(downLoadInfo.id,downLoadInfo);

        DownLoadTask task = new DownLoadTask(downLoadInfo);

        //执行线程池的下载操作，将下载任务添加到队列，并执行
        ThreadManager.getInstance(1,1).execute(task);
        loadTaskCHM.put(appinfo.id,task);

    }

    /**
     * 暂停
     * 1.获取DownLoadInfo：
     * 2.通过DownLoadInfo.id：获取DownLoadInfo对象
     * 3.将DownLoadInfo的下载任务从TashHashMap移除
     *
     */
    public void pause(Appinfo info){
        DiwnLoadInfo downinfo = DownLoinfoCHM.get(info.id);

        if(downinfo.currentState == STATE_DOWNLOADING || downinfo.currentState == STATE_WAITING){
            DownLoadTask task = loadTaskCHM.get(downinfo.id);

            if (task != null){
                //停止下载，经队列从线程池中取消
                ThreadManager.getInstance(1,1).cancel(task);
                //更新状态
                downinfo.currentState = STATE_PAUSE;
                notifyDownLoadStateChange(downinfo);
            }
        }

    }

    /**
     * 安装应用
     * 调用安装应用的activity
     * 自动安装
     */
    public void instant(Appinfo info, Context context){
        DiwnLoadInfo downloadInfo = DownLoinfoCHM.get(info.id);
        if (downloadInfo != null) {
            // 跳到系统的安装页面进行安装
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + downloadInfo.path),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    /**
     * 1.下载应用程序，
     * 2.判断线程池是否有过下载记录
     * 3.如果有，则判断已经下载的数量是否和DwonloadInfo的信息中记录的是否相同
     * 4.如果相同:则执行断点续传操作，接着上述内容，将下载的内容拼接到应用中
     * 5.如果不相同，或者已经下载错误，则把以前下载的数据清零，然后删除已经下载的文件，
     */
    class DownLoadTask implements Runnable{

        private DiwnLoadInfo dlInfo;

        public DownLoadTask(DiwnLoadInfo info) {
            this.dlInfo = info;
        }

        @Override
        public void run() {
            //更改状态，然后刷新页面通知
            File file = new File(dlInfo.path);

            dlInfo.currentState = STATE_DOWNLOADING;
            notifyDownLoadStateChange(dlInfo);
            if(file.length() == dlInfo.size&&file.length() == dlInfo.currentPos){
                dlInfo.currentState = STATE_SUCCESS;
                notifytyDownLoadPregress(dlInfo);
            }
            if (!file.exists() || file.length() != dlInfo.currentPos
                    || dlInfo.currentPos == 0) {
                // 从头开始下载
                // 删除无效文件
                file.delete();// 文件如果不存在也是可以删除的, 只不过没有效果而已
                dlInfo.currentPos = 0;// 当前下载位置置为0

            }

            HttpURLConnection connection = null;
            RandomAccessFile randomAccessFile = null;
            InputStream is = null;
            try {

                URL url = new URL(dlInfo.downloadUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");
                // 设置范围，格式为Range：bytes x-y;
                connection.setRequestProperty("Range", "bytes=" + dlInfo.currentPos + "-" + dlInfo.size);

                randomAccessFile = new RandomAccessFile(file, "rwd");
                randomAccessFile.seek(0 + dlInfo.currentPos);
                Log.i("RG", "connection--->>>" + connection);
                // 将要下载的文件写到保存在保存路径下的文件中
                is = connection.getInputStream();
                byte[] buffer = new byte[4096];
                int length = -1;
                while ((length = is.read(buffer)) != -1  && dlInfo.currentState == STATE_DOWNLOADING) {
                    randomAccessFile.write(buffer, 0, length);
                    dlInfo.currentPos += length;
                    notifytyDownLoadPregress(dlInfo);
                }
                if(dlInfo.currentPos == dlInfo.size){
                    dlInfo.currentState = STATE_SUCCESS;
                    notifytyDownLoadPregress(dlInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    randomAccessFile.close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public DiwnLoadInfo getDownloadInfo(Appinfo info) {
        return DownLoinfoCHM.get(info.id);
    }

}
