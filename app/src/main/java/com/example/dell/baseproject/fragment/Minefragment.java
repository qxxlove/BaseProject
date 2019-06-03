package com.example.dell.baseproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.activity.ActivityContentActivity;
import com.example.dell.baseproject.activity.ListViewActivity;
import com.example.dell.baseproject.activity.ScrollViewActivity;
import com.example.dell.baseproject.https.HttpsActivity;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by DELL on 2017/6/10.
 */
public class Minefragment extends Fragment {


    @BindView(R.id.btn_listview_single)
    Button btnListviewSingle;
    Unbinder unbinder;

    
    private OutputStream socketOutputStream;
    private InputStream socketInputStream;

    private  byte[] bytes;
    private StringBuffer stringBuffer;

    private  Socket socket;
    private  String IP ="192.168.10.52";
    private  int PORT = 6789;
    private  String IP_Other ="111.30.107.158";
    private  int PORT_Other = 60022;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);

        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (socket == null){
                        socket = new Socket(IP,PORT);
                    }
                    socket.setSoTimeout(20000);
                    if (!socket.isConnected()){
                        Log.e("TAG","当前连接状态"+socket.isConnected());
                    }
                    /**获取输出流，向服务器发送消息*/
                    socketOutputStream = socket.getOutputStream();
                    /**将输出流包装为打印流*/
                    PrintWriter printWriter = new PrintWriter(socketOutputStream);

                    String resut = "你好";
                    bytes =resut.getBytes();
                    String deal = new String(resut.getBytes("utf-8"),"ISO-8859-1");
                    byte[] gbks = deal.getBytes("GBK");
                    Log.e("TAG","处理之后的结果:"+deal);
                    printWriter.write(deal);
                    printWriter.flush();
                    Log.e("TAG","发送成功");
                }catch (Exception c){
                    Log.e("TAG","连接异常");
                }
            }
        }).start();



    }


    @OnClick({R.id.btn_listview_single,R.id.btn_https_internet,R.id.btn_srollvoiew_activity,R.id.btn_activity_base_content})
    public  void  initClick (View view) {
        switch (view.getId()){
            case R.id.btn_listview_single :
                Intent intent = new Intent(getActivity(), ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_https_internet :
                Intent intent1 = new Intent(getActivity(), HttpsActivity.class);
                startActivity(intent1);
                break;
            case  R.id.btn_srollvoiew_activity:
                Intent intent2 = new Intent(getActivity(), ScrollViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_activity_base_content:
                Intent intent3 = new Intent(getActivity(), ActivityContentActivity.class);
                startActivity(intent3);
                break;
            default:
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
