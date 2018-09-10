package com.example.dell.baseproject.utils;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;

import com.example.dell.baseproject.base.App;

import java.io.File;
import java.io.FileOutputStream;


/**
 * @ClassName SDCardUtils
 * @Description TODO(sdcard工具)
 * @author txb
 * @Date 2017年1月7日 下午1:21:22
 * @version 1.0.0
 */
@SuppressLint("NewApi")
public class SDCardUtils {

	public static App mApplcation = App.getInstance();

	/**
	 * 获取/data/data/files目录
	 * 
	 * @param context
	 * @return
	 */
	public static File getFileDirectory() {
		File filePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			filePath = mApplcation.getExternalFilesDir(null);
		} else {
			filePath = mApplcation.getFilesDir();
		}
		return filePath;
	}

	/**
	 * 内部存储卡储存
	 * 
	 * @param context
	 * @param preferExternal
	 * @param dirName
	 * @return
	 */
	public static File getCacheDirectory() {
		File cachePath = null;
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = mApplcation.getExternalCacheDir();
		} else {
			cachePath = mApplcation.getCacheDir();
		}
		return cachePath;
	}

	/**
	 * 获取内置SD卡路径
	 * 
	 * @return
	 */
	public static String getRootPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param file
	 * @return
	 */
	public static File createFile(File file) {
		if (!file.exists())
			file.mkdirs();
		return file;
	}

	/**
	 * 判断SDCard是否可用
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 获取SD卡的剩余容量 单位byte
	 * 
	 * @return
	 */
	public static long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			// 获取空闲的数据块的数量
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			// 获取单个数据块的大小（byte）
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	/**
	 * 获取指定路径所在空间的剩余可用容量字节数，单位byte
	 * 
	 * @param filePath
	 * @return 容量字节 SDCard可用空间，内部存储可用空间
	 */
	public static long getFreeBytes(String filePath) {
		// 如果是sd卡的下的路径，则获取sd卡可用容量
		if (filePath.startsWith(getSDCardPath())) {
			filePath = getSDCardPath();
		} else {// 如果是内部存储的路径，则获取内存存储的可用容量
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	/**
	 * 获取系统存储路径
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}

	/**
	 * 保存文件文件到目录
	 * 
	 * @param context
	 * @return 文件保存的目录
	 */
	public static String setMkdir(String filePath) {
		if (isSDCardEnable()) {
			filePath = Environment.getExternalStorageDirectory()
					+ File.separator + filePath;
		} else {
			filePath = mApplcation.getCacheDir().getAbsolutePath()
					+ File.separator + filePath;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			boolean b = file.mkdirs();
			//LogUtils.e("文件不存在  创建文件    " + b);
		} else {
			//LogUtils.e("文件存在");
		}
		return filePath;
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param
	 * @return 返回文件名称,便于将文件传送到服务器
	 * @throws Exception
	 */
	public static String saveFilePath(String msg, String filePath,
									  String fileName) throws Exception {
		if (isSDCardEnable()) {
			filePath = setMkdir(filePath);
			FileOutputStream fos = new FileOutputStream(new File(filePath,
					fileName), true);
			fos.write(msg.getBytes());
			fos.close();
		}
		return fileName;
	}


}
