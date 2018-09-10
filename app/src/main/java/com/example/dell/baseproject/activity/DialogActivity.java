package com.example.dell.baseproject.activity;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.CompressImageUtil;
import com.example.dell.baseproject.utils.GetDistance;

import java.io.IOException;
import java.math.BigDecimal;

public class DialogActivity extends AppCompatActivity {

    private Button button_show;
    private TextView text_one;
    private TextView text_two;
    private TextView text_result;
    private TextView text_uri_file;
    private ImageView imageView_dou;
    private ImageView imageView_location;
    private TextView text_distance;
    private TextView text_distance_two;

    private AlertDialog dlg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
        initData();
        initClick();
    }



    private void initView() {
        button_show = ((Button) findViewById(R.id.button_show_dialog));
        text_one = ((TextView) findViewById(R.id.text_one_money));
        text_two = ((TextView) findViewById(R.id.text_two_money));
        text_result = ((TextView) findViewById(R.id.text_result_money));
        text_uri_file = (TextView)findViewById(R.id.text_uri_file);
        imageView_dou = ((ImageView) findViewById(R.id.image_dou));
        imageView_location = ((ImageView) findViewById(R.id.image_location));
        text_distance = ((TextView) findViewById(R.id.text_disntance));
        text_distance_two = ((TextView) findViewById(R.id.text_disntance_one));
        Animation shake = AnimationUtils.loadAnimation(DialogActivity.this, R.anim.shake_y);
        //        		Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.button_shake);

        shake.reset();
        shake.setFillAfter(true);
        shake.setRepeatCount(Animation.INFINITE);
        shake.setRepeatMode(Animation.REVERSE);
        imageView_dou.startAnimation(shake);
    }

    private void initData() {
        text_one.setText("0.06");
        text_two.setText("300.0");

        text_result.setText(sub(text_one.getText().toString().trim(),text_two.getText().toString().trim())+"");

        //File fileByUri = BaseUtils.getFileByUri(this, Uri.parse("content://media/external/images/media/4530"));
        //text_uri_file.setText(fileByUri.toString());

        //  "/storage/emulated/0/DCIM/Camera/IMG_20150821_110139.jpg"
        //Glide.with(this).load(fileByUri).into(imageView_location);
        Bitmap photoBmp = null;
        Uri uri =     Uri.parse("/storage/emulated/0/DCIM/Camera/IMG_20150821_110139.jpg");
        if (uri != null) {
            try {
                photoBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Bitmap getimage = null;
        try {
            getimage = BaseUtils.getBitmapFormUri(this,uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // Log.e("TAG",photoBmp.getByteCount()+"a") ;
        //Log.e("TAG",getimage.getByteCount()+"b") ;

        Bitmap bitmapFormUri = null;
        String smallBitmap = CompressImageUtil.getSmallBitmap("/storage/emulated/0/DCIM/Camera/IMG_20150821_110139.jpg");
        Log.e("TAG",smallBitmap+":c") ;

      /*  39.0839881405,117.1044372305     // 海泰
       39.0823557079,117.0834967199     // 未知
         39.0870503378,117.1567003290    // 红旗南路
        39.9434854882,116.4175523633
        "gps_x":"39.055942", "gps_y":"117.057435",
        "gps_x":"39.085394","gps_y":"117.099126",
        "gps_x":"39.074831", "gps_y":"117.36909",*/
        String distance = GetDistance.getInstance().getShortDistance(39.055942,117.057435,39.085394,117.099126)  ;
        String distance1 = GetDistance.getInstance().getShortDistance(39.0839881405,117.1044372305,39.0870503378,117.1567003290)  ;
        text_distance.setText(distance);
        text_distance_two.setText(distance1);


    }

    private void initClick() {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);

                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.view_dialog_layout, null);//获取自定义布局
                builder.setView(layout);
                  layout.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          dlg.dismiss();
                      }
                  });

                //  builder.setIcon(R.drawable.ic_launcher);//设置标题图标
                //  builder.setTitle(R.string.hello_world);//设置标题内容
                dlg = builder.create();
                dlg.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dlg.show();
            }
        });

        imageView_dou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseUtils.tada(imageView_dou);
            }
        });
    }

    public static double sub(String v1, String v2){
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }
}
