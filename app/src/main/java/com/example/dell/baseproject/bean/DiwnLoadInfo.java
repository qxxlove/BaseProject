package com.example.dell.baseproject.bean;

import android.os.Environment;

import com.example.dell.baseproject.utils.DownloadManager;
import com.example.dell.baseproject.utils.LogUtils;

import java.io.File;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/27.
 * 邮箱：123123@163.com
 */

public class DiwnLoadInfo {


    public int id;
    public String name;
    public String downloadUrl;
    public double size;
    public String packageName;

    public long currentPos;// 当前下载位置
    public int currentState;// 当前下载状态
    public String path;// 下载到本地文件的路径

    public static final String GOOGLE_MARKET = "GOOGLE_MARKET";// sdcard根目录文件夹名称
    public static final String DONWLOAD = "download";// 子文件夹名称, 存放下载的文件

    // 获取下载进度(0-1)
    public float getProgress() {
        if (size == 0) {
            return 0;
        }

        float progress = currentPos / (float) size;
        return progress;
    }

    /**
     * 拷贝对象,
     * 从AppInfo中拷贝出一个DownloadInfo，并返回DownLOadInfo
     *
     * APpInfo是是下载应用称需使用的信息，用户可以自行封装，只要包含downloadUrl，和 id name即可，
     */

    //
    public static DiwnLoadInfo copy(Appinfo info) {
        DiwnLoadInfo downloadInfo = new DiwnLoadInfo();

        downloadInfo.id = info.id;
        downloadInfo.name = info.name;
        downloadInfo.downloadUrl = info.downloadUrl;
        downloadInfo.packageName = info.packageName;
        downloadInfo.size = info.size;

        downloadInfo.currentPos = 0;
        downloadInfo.currentState = DownloadManager.STATE_UNDO;// 默认状态是未下载
        downloadInfo.path = downloadInfo.getFilePath();
        LogUtils.e("路径：",downloadInfo.path);
        return downloadInfo;
    }


    /**
     *1.获取文件下载路径
     *2.动态的拼接出一个文件的下载路径，
     *3.使用name作为下载文件的文件名称
     *4.通过createDir()判断，文件夹是否存在
     * @return　
     *          null:获取文件夹路径失败
     *          ！＝null :获取文件夹路径成功
     */
    public String getFilePath() {
        StringBuffer sb = new StringBuffer();
        String sdcard = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        sb.append(sdcard);
        // sb.append("/");
        //这一句等价　＝＝　sb.append("/");
        sb.append(File.separator);
        sb.append(GOOGLE_MARKET);
        sb.append(File.separator);
        sb.append(DONWLOAD);

        if (createDir(sb.toString())) {
            // 文件夹存在或者已经创建完成
            return sb.toString() + File.separator + name + ".apk";// 返回文件路径
        }

        return null;
    }

    /**
     *1.如果存在的话，就返回文件夹的路径
     *2.如果不存在就创建文件夹，并将返回的结果返回：true:创建成功;false：创建失败
     * @param dir
     * @return
     */
    private boolean createDir(String dir) {
        File dirFile = new File(dir);

        // 文件夹不存在或者不是一个文件夹
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return dirFile.mkdirs();
        }

        return true;// 文件夹存在
    }


}
