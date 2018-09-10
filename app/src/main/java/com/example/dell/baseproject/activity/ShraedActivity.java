package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.utils.SharePreUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * double    17位   双精度    写法一般省略 d 即可  ，因为他很长
 * float      7位    单精度       一般 f 结尾
 */

public class ShraedActivity extends AppCompatActivity {

    @BindView(R.id.text_string)
    TextView textString;
    @BindView(R.id.text_float)
    TextView textFloat;
    @BindView(R.id.text_int)
    TextView textInt;
    @BindView(R.id.text_long)
    TextView textLong;
    @BindView(R.id.text_boolean)
    TextView textBoolean;
    @BindView(R.id.text_double)
    TextView textDouble;
    @BindView(R.id.text_double_result)
    TextView textDoubleResult;
    @BindView(R.id.activity_shraed)
    LinearLayout activityShraed;
    @BindView(R.id.text_test)
    TextView textTest;
    @BindView(R.id.button_click)
    TextView buttonClick;
    private boolean isTrue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shraed);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        String result = SharePreUtils.get("string", "");
        int a = SharePreUtils.get("int", 0);
        long b = SharePreUtils.get("long", 123123L);
        float c = SharePreUtils.get("float", 1.00f);
        String e = SharePreUtils.get("double", "");
        boolean d = SharePreUtils.get("boolean", true);

        double f = 0.1234567891011121314151617;

        textBoolean.setText(d + "");
        textFloat.setText(c + "");
        textInt.setText(a + "");
        textLong.setText(b + "");
        textString.setText(result);
        textDouble.setText(e + "");
        textDoubleResult.setText(f + "");

        if (!BaseUtils.isEmpty(textTest.getText().toString())) {
            BaseUtils.toast(this, textTest.getText().toString());
        }
        getBooleanResult();

        double lat = 13904.7042;
        double lon = 11705.1695;
        LogUtils.e(LogUtils.TAG,  lat / 100 + "");
        LogUtils.e(LogUtils.TAG,  lon / 100 + "");
        BigDecimal start = new BigDecimal(lat);
        BigDecimal end = new BigDecimal(Double.valueOf(100));
        BigDecimal bigDecimal = start.movePointLeft(100);
        double  shan = bigDecimal.doubleValue();
        LogUtils.e("TAG",  bigDecimal + "");
        LogUtils.e("TAG",  shan + "");

    }

    @OnClick({R.id.button_click})
    public  void  initClick(View view) {
          switch (view.getId()) {
              case R.id.button_click :
                  getBooleanResult();
                  break;
          }
    }

    public  void  getBooleanResult () {
        LogUtils.e(LogUtils.TAG, isTrue + "");
        if (!isTrue) {
            isTrue = false;
            LogUtils.e("TAG", "1:" + isTrue);
        } else {
            isTrue = true;
            LogUtils.e("TAG", "2:" + isTrue + "");
        }
    }

}
