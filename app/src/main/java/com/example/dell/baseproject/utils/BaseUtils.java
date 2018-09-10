package com.example.dell.baseproject.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dell.baseproject.R;
import com.example.dell.baseproject.Retrofit.OKhttpUtils;
import com.example.dell.baseproject.base.App;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SEELE on 2017/6/19.
 */

public class BaseUtils {
    private static App   mApplcation = App.getInstance();


    private static Toast toast;
    /**
     * 强大的可以连续弹的吐司
     * @param text
     */
    public static void showToast(String text,Context context){
        if(toast==null){
            //创建吐司对象
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        }else {
            //说明吐司已经存在了，那么则只需要更改当前吐司的文字内容
            toast.setText(text);
        }
        //最后你再show
        toast.show();
    }


    /**
     * @Description (判断是否为空串)
     * @param arg0
     * @return
     */
    public static boolean isEmpty(String arg0) {
        return arg0 == null || arg0.trim().length() == 0;
    }


    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    public static  int  getScreeWith (Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return  width;
    }

    public static  int  getScreeHeight (Context context){
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return  height;
    }


    public  static String   getTimeDifference  (String startTime , String  endTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = null;
        try{
            Date d1 = df.parse(endTime);
            Date d2 = df.parse(startTime);
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            if(diff<0){
                result="error";
            }else{
                long hours = diff/(1000* 60 * 60);
                long minutes = (diff-hours*(1000* 60 * 60))/(1000* 60);
                long second = (diff-hours*(1000* 60 * 60)-minutes*(1000* 60))/(1000);
                result =String.valueOf(hours)+":" +String.valueOf(minutes)+":"+String.valueOf(second);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = "timeError";
        }
        return result;
    }
    public  static  void toast (Context context, String content) {
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }


    /**
     * @Description (根据id得到String)
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return mApplcation.getString(resId);
    }



    //检查权限时，判断系统的权限集合
    public static boolean permissionSet(String... permissions) {
        for (String permission : permissions) {
            if (isLackPermission(permission)) {//是否添加完全部权限集合
                return true;
            }
        }
        return false;
    }

    //检查系统权限是，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
    private static boolean isLackPermission(String permission) {
        return ContextCompat.checkSelfPermission(mApplcation, permission) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * 定制时间格式
     * @param cnt
     * @return
     */
    public static  String getStringTime(int cnt) {
        int hour = cnt / 3600;
        int min = cnt % 3600 / 60;
        int second = cnt % 60;
        return String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);

    }



    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (Exception e) {
        }
        return re_time;
    }


    public  static File getFileByUri (Context context , Uri uri){
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        Log.e("TAG","结果"+res);
        File file = new File(res);
        return file;
    }


    /**
     * 通过uri获取图片并进行压缩
     *
     * @param uri
     */
    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return bitmap;
    }





    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -3f * shakeFactor),
                Keyframe.ofFloat(.2f, -3f * shakeFactor),
                Keyframe.ofFloat(.3f, 3f * shakeFactor),
                Keyframe.ofFloat(.4f, -3f * shakeFactor),
                Keyframe.ofFloat(.5f, 3f * shakeFactor),
                Keyframe.ofFloat(.6f, -3f * shakeFactor),
                Keyframe.ofFloat(.7f, 3f * shakeFactor),
                Keyframe.ofFloat(.8f, -3f * shakeFactor),
                Keyframe.ofFloat(.9f, 3f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(1000);
    }



    public   static  void  setViewAnimaiton (Context context,View view) {
        Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake_y);
        shake.reset();
        shake.setFillAfter(true);
        shake.setRepeatCount(Animation.INFINITE);
        shake.setRepeatMode(Animation.REVERSE);
        view.startAnimation(shake);
    }




    /**
     * 图片压缩
     * @param bitmap2
     * @return
     */
    public static File ziphoto(Bitmap bitmap2){
        //图片允许最大空间   单位：KB
        double maxSize =800.00;
        //将bitmap放至数组中，意在bitmap的大小（与实际读取的原文件要大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        //将字节换成KB
        double mid = b.length/1024;
        //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
        if (mid > maxSize) {
            //获取bitmap大小 是允许最大大小的多少倍
            double i = mid / maxSize;
            //开始压缩  此处用到平方根 将宽带和高度压缩掉对应的平方根倍 （1.保持刻度和高度和原bitmap比率一致，压缩后也达到了最大大小占用空间的大小）
            bitmap2 = zoomImage(bitmap2, bitmap2.getWidth() / Math.sqrt(i),
                    bitmap2.getHeight() / Math.sqrt(i));
        }
        return photo(bitmap2);
    }




    /***
     * 图片的缩放方法
     *
     * @param bgimage
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }



    /**
     * 存储图片路径
     * @param bitmap
     * @return
     */
    public static File photo(Bitmap bitmap){
        File file=new File("/sdcard/"+System.currentTimeMillis()+".png");
        try {
            FileOutputStream out=new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)){
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.i( "&(ULL:<", "74362984" + file.toString());
        return file;
    }



    /**
     * 判断List
     *
     * @param
     * @return 如果包含元素返回true
     */
    @SuppressWarnings("rawtypes")
    public static boolean isListEmpty(Collection collection) {
        return !(collection == null || collection.isEmpty());
    }




    /**
     * @Description (根据布局id加载布局)
     * @param context
     * @param layoutId
     * @return
     */
    public static View inflate(Context context, int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(layoutId, null);
    }


    /**
     * 得到Retrofit 对象
     * @param baseUrl
     * @return
     */
     public   static Retrofit getRetrofit (String   baseUrl) {
         Retrofit retrofit =  new Retrofit.Builder().baseUrl(baseUrl) // ip地址
                 .client(OKhttpUtils.getOkhttpclient())
                 .addConverterFactory(GsonConverterFactory.create())    // 配置
                 //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                 // 针对rxjava2.x
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .build();
         return   retrofit;
     }


/*
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //如果用户写了泛型，就会进入这里，否者不会执行
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];
            if (beanType == String.class) {
                //如果是String类型，直接返回字符串
                return (T) str;
            } else {
                //如果是 Bean List Map ，则解析完后返回
                return new Gson().fromJson(str, beanType);
            }
        } else {
            //如果没有写泛型，直接返回Response对象
            return (T) str;
        }
    }*/


    public static void setupItem(final View view, final LibraryObject libraryObject,Context context) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        //img.setImageResource(libraryObject.getRes());
        Glide.with(context).load(libraryObject.getRes()).into(img);
    }

    public static class LibraryObject {

        private String mTitle;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }


}
