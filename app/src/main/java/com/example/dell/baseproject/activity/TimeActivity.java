package com.example.dell.baseproject.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.weight.FlashLightManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 *  时间差
 *     需求：用户一进入当前界面，就开启定时任务，同事保存当前系统的时间，当用户第二次进入的时候，
 *     取出之前保存的系统时间与现在时间相减，求出时间差，也就是用户花费的时间  （需注意的是，定制时间格式）
 */


public class TimeActivity extends AppCompatActivity {

    private TextView text_time;
    private  TextView text_start_two;
    private TextView text_start;
    private  int count = 0;

    private Timer  timer;
    SharedPreferences mySharedPreferences;

    int  a  = 0;

    private FlashLightManager flashLightManager;


    //  用于计算 用车时长
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    text_start_two.setText(BaseUtils.getStringTime(a++));
                }
            });
        }
    };
    private Button button_turn_ob;   // 开启闪光灯
    private Button button_turn_off;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            BaseUtils.toast(TimeActivity.this,msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        text_time = ((TextView)findViewById(R.id.text_time));
        text_start = ((TextView) findViewById(R.id.text_start_time));
        text_start_two = ((TextView) findViewById(R.id.text_start_time_two));
        button_turn_ob = ((Button) findViewById(R.id.button_turn_on));
        button_turn_off = ((Button) findViewById(R.id.button_turn_off));
        timer = new Timer();
        mySharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);

        //创建Intent对象，action为ELITOR_CLOCK，附加信息为字符串“你该打酱油了”
        Intent intent = new Intent("ELITOR_CLOCK");
        intent.putExtra("msg","你该打酱油了");

        //定义一个PendingIntent对象，PendingIntent.getBroadcast包含了sendBroadcast的动作。
        //也就是发送了action 为"ELITOR_CLOCK"的intent
        PendingIntent pi = PendingIntent.getBroadcast(this,0,intent,0);

        //AlarmManager对象,注意这里并不是new一个对象，Alarmmanager为系统级服务
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

        //设置闹钟从当前时间开始，每隔5s执行一次PendingIntent对象pi，注意第一个参数与第二个参数的关系
        // 5秒后通过PendingIntent pi对象发送广播
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),2*1000,pi);


        IntentFilter intentFilter = new IntentFilter("ELITOR_CLOCK");
        registerReceiver(broadcastReceiver,intentFilter);
        initData();
        initClick();





    }

    private void initData() {
        String timeDifference = BaseUtils.getTimeDifference("2017-06-26 15:38:14", "2017-06-26 15:39:16");
        LogUtils.e("TAG13",timeDifference);
        String  hour = timeDifference.split(":")[0];
        String  min = timeDifference.split(":")[1];
        String  s = timeDifference.split(":")[2];
        LogUtils.e("TAG14","hour===="+hour+"====min==="+min+"====s==="+s);
        a =   Integer.parseInt(hour)*60*60 +Integer.parseInt(min)*60+ Integer.parseInt(s);

        timer.schedule(timerTask, 0, 1000);

         flashLightManager = new FlashLightManager(this);
         flashLightManager.init();






    }

    private void initClick() {
        button_turn_ob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               flashLightManager.turnOn();
            }
        });
        button_turn_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashLightManager.turnOff();
            }
        });
    }
}
