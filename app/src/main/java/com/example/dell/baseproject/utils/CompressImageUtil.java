package com.example.dell.baseproject.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CompressImageUtil {

	/**
	 * 把bitmap转换成String
	 * 
	 * @param filePath
	 * @return
	 */
	public static String bitmapToString(String filePath) {
		String path = getSmallBitmap(filePath);
		Bitmap bm = BitmapFactory.decodeFile(filePath);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
		byte[] b = baos.toByteArray();
		return Base64.encodeToString(b, Base64.DEFAULT);
	}

	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	/**
	 * 根据路径压缩返回（质量压缩法）
	 * 
	 * @param
	 * @return
	 */
	public static String getSmallBitmap(String filePath) {
		Bitmap bmap = null;
		FileOutputStream fos = null;
		File file = new File(filePath);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 800);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		bmap = BitmapFactory.decodeFile(filePath, options);

		// 判断图片压缩后角度
		int degree = readPictureDegree(filePath);

		if (degree != 0) {// 旋转照片角度
			bmap = rotateBitmap(bmap, degree);
		}

		try {
			fos = new FileOutputStream(new File(SDCardUtils.getSDCardPath(), "small_" + file.getName()));
			
			if(bmap == null){
				return null;
			}
			
			bmap.compress(Bitmap.CompressFormat.JPEG, 40, fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bmap != null && !bmap.isRecycled()){
				bmap.recycle();
			}
		}
		return SDCardUtils.getSDCardPath() + "/small_"
				+ file.getName();
	}

	
	//像素压缩发  （Bitmap压缩   在处理bitmap是防止内存溢出）
	public static String getimage(String srcPath) {
		
		FileOutputStream fos = null;
		File file = new File(srcPath);
		
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = 800f;//
		float ww = 480f;//
		int be = 1;
		if (w > h && w > ww) {
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置采样率

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		// return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
		// 其实是无效的,大家尽管尝试
		
		// 判断图片压缩后角度
		int degree = readPictureDegree(srcPath);

		if (degree != 0) {// 旋转照片角度
			bitmap = rotateBitmap(bitmap, degree);
		}

		try {
			fos = new FileOutputStream(new File(file.getParentFile()
					.getAbsolutePath(), "small_" + file.getName()));
			
			if(bitmap == null){
				return null;
			}
			
			bitmap.compress(Bitmap.CompressFormat.JPEG, 40, fos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bitmap != null && !bitmap.isRecycled()){
				bitmap.recycle();
			}
		}
		return file.getParentFile().getAbsolutePath()+"/small_" + file.getName();
	}

	/** 保存方法 */
	public static String saveBitmap(Bitmap bm, String path) {
		File f = new File(path);
		File ff = new File(f.getParentFile().getAbsolutePath(), "small_"
				+ f.getName());

		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(ff);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(bm != null && !bm.isRecycled()){
			bm.recycle();
		}
		return f.getParentFile().getAbsolutePath() + "small_" + f.getName();

	}

	/**
	 * 根据路径删除图片
	 * 
	 * @param path
	 */
	public static void deleteTempFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 添加到图库
	 */
	public static void galleryAddPic(Context context, String path) {
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(path);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}

	/**
	 * 获取保存图片的目录
	 * 
	 * @return
	 */
	public static File getAlbumDir() {
		File dir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				getAlbumName());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	/**
	 * 获取保存 隐患检查的图片文件夹名称
	 * 
	 * @return
	 */
	public static String getAlbumName() {
		return "sheguantong";
	}

	// 判断照片角度
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	// 旋转图片

	public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
		if (bitmap != null) {
			Matrix m = new Matrix();
			m.postRotate(degress);
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), m, true);
			return bitmap;
		}
		return bitmap;
	}

	
}
