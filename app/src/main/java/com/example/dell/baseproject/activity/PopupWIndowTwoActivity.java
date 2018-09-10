package com.example.dell.baseproject.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.weight.MyPopupWindowTwo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.example.dell.baseproject.activity.ElseActivity.PERMISSION;

public class PopupWIndowTwoActivity extends BaseActivity {


    private Button button_show;
    private MyPopupWindowTwo takePhotoPopWin  = null;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_cacle:     // 相册
                    // System.out.println("btn_take_photo");
                    Toast.makeText(PopupWIndowTwoActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setType("image/*");//相片类型
                    startActivityForResult(intent1, 7);
                    break;
                case R.id.btn_sure:       // 拍照
                    //  System.out.println("btn_pick_photo");
                    startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 6);
                    Toast.makeText(PopupWIndowTwoActivity.this,"确定",Toast.LENGTH_SHORT).show();

                    break;
                case  R.id.linear_down:
                    if (takePhotoPopWin != null) {
                        takePhotoPopWin.dismiss();
                    }
                    linear_bottom.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    private Button btn_bdmap;
    private Button button_gdmap;
    private LinearLayout linear_bottom;
    private ImageView imageView_up;
    private ImageView iamge_icon;


    @Override
    public int initContentID() {
        return 0;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_popup_window_two);
        initView();
    }

    @Override
    protected void initData() {
        button_gdmap.performClick();
        BaseUtils.setViewAnimaiton(this,imageView_up);
        if (takePhotoPopWin == null) {
            takePhotoPopWin = new MyPopupWindowTwo(PopupWIndowTwoActivity.this, onClickListener);
        }
    }


    @Override
    protected void initListener() {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //showAtLocation(View parent, int gravity, int x, int y)
                //  takePhotoPopWin.showAtLocation(findViewById(R.id.main_view), Gravity.BOTTOM, 0, 0);
            }
        });
        linear_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MyPopupWindow takePhotoPopWin = new MyPopupWindow(PopupWindowActivity.this, onClickListener);
                //showAtLocation(View parent, int gravity, int x, int y)

                takePhotoPopWin.showAtLocation(findViewById(R.id.activity_popup_window_two), Gravity.BOTTOM, 0, 0);
                View view = takePhotoPopWin.getView(takePhotoPopWin, R.id.image_down);
                BaseUtils.setViewAnimaiton(PopupWIndowTwoActivity.this,view);
                linear_bottom.setVisibility(View.GONE);
            }
        });


        button_gdmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseUtils.isAvilible(getBaseContext(),"com.autonavi.minimap")){
                    try
                    {
                        Intent intent = Intent.getIntent("androidamap://viewMap?sourceApplication=厦门通&poiname=百度奎科大厦&lat=40.047669&lon=116.313082&dev=0");
                        startActivity(intent);
                        BaseUtils.showToast("高德地图客户端已经安装", getBaseContext()); ;

                    } catch (URISyntaxException e)
                    {
                        e.printStackTrace();
                    }
                }else  {
                    BaseUtils.showToast("高德百度地图客户端", getBaseContext()); ;

                }

            }
        });
        btn_bdmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent  intent = Intent.getIntent("intent://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                    if(BaseUtils.isAvilible(getBaseContext(),"com.baidu.BaiduMap")){
                        startActivity(intent); //启动调用
                        BaseUtils.showToast( "百度地图客户端已经安装",getBaseContext()); ;
                    }else{
                        BaseUtils.showToast("没有百度地图客户端", getBaseContext()); ;
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void execHttp() {

    }


    private void initView() {
        button_show = ((Button) findViewById(R.id.button_popup_show_activity));
        btn_bdmap = ((Button) findViewById(R.id.button_baidumap_activity));
        button_gdmap = ((Button) findViewById(R.id.button_gaode_activity));
        linear_bottom = ((LinearLayout) findViewById(R.id.linear_bottom_arrow));
        imageView_up = ((ImageView) findViewById(R.id.image_up));
        iamge_icon = ((ImageView) findViewById(R.id.image_icon));
    }


    @Override
    public void process(Bundle savedInstanceState) {
        super.process(savedInstanceState);

        //如果有什么需要初始化的，在这里写就好～

    }

    @Override
    public void getAllGrantedPermission() {
        //当获取到所需权限后，进行相关业务操作
        super.getAllGrantedPermission();
        BaseUtils.toast(this,"授权完毕");
    }

    @Override
    public String[] getPermissions() {
        return PERMISSION;
    }


    private Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if(bitmap!=null&&!bitmap.isRecycled()){
                bitmap.recycle() ;
                bitmap=null;
                System.gc();
            }
            if (requestCode == 6){
                if (data.getExtras()!=null){
                    bitmap = ((Bitmap) data.getExtras().get("data"));
                    iamge_icon.setImageBitmap(bitmap);
                    File ziphoto = BaseUtils.ziphoto(bitmap);
                    Log.e("TAG","1====="+ziphoto);

                }
            }else if (requestCode == 7){
                if (data.getData()!=null){
                    Uri uri = data.getData();
                    if(uri==null){
                        //use bundle to get data
                        Bundle bundle = data.getExtras();
                        if (bundle!=null) {
                            bitmap = (Bitmap) bundle.get(uri.toString());
                            iamge_icon.setImageBitmap(bitmap);
                        } else {
                            BaseUtils.showToast("图片获取失败",this);
                            return;
                        }
                    }else{
                        ContentResolver resolver = getBaseContext().getContentResolver();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        iamge_icon.setImageBitmap(bitmap);
                    }
                    File file = BaseUtils.ziphoto(bitmap);
                    Log.e("TAG","2====="+file);
                }
            }
        }

    }
}
