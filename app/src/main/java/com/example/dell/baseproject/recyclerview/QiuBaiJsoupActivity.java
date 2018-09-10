package com.example.dell.baseproject.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.QiubaiAdapter;
import com.example.dell.baseproject.bean.QiuBaiBean;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtilsThree;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QiuBaiJsoupActivity extends AppCompatActivity {

    @BindView(R.id.list_qiubai)
    ListView listQiubai;
    @BindView(R.id.activity_qiu_bai_jsoup)
    RelativeLayout activityQiuBaiJsoup;



    private String qb_url = "http://www.qiushibaike.com/8hr/page/";

    private List<QiuBaiBean> list;
    private QiubaiAdapter qiubaiAdapter ;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0 :
                    list = (List<QiuBaiBean>) msg.obj;
                    if (BaseUtils.isListEmpty(list)) {
                        qiubaiAdapter = new QiubaiAdapter(QiuBaiJsoupActivity.this,list,R.layout.item_layout_qiubai);
                        listQiubai.setAdapter(qiubaiAdapter);
                    }  else {
                        BaseUtils.toast(QiuBaiJsoupActivity.this,"数据源为空");
                    }
                    break;
                default:
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qiu_bai_jsoup);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    try {
                        Document doc = Jsoup.connect(qb_url + i + "/").get();
                        Elements els = doc.select("a.contentHerf");
                        LogUtilsThree.d("一，HTML内容" + els.toString());

                        for (int j = 0; j < els.size(); j++) {
                            Element element = els.get(j);
                            LogUtilsThree.d("1. 标题" + element.text());

                            String herf = els.attr("herf");
                            LogUtilsThree.d("2. 连接" + herf);

                            Document doc_detail = Jsoup.connect("http://www.qiushibaike.com" + herf).get();
                            Elements els_detail = doc_detail.select(".content");
                            LogUtilsThree.d("3. 内容" + els_detail.text());

                            Elements ele_pic = doc_detail.select(".thumb img[src$=jpg]");
                            String pic = null;
                            if (!ele_pic.isEmpty()) {
                                pic = ele_pic.attr("src");
                                LogUtilsThree.d("4. 图片链接" + pic);
                            } else {
                                LogUtilsThree.d("4. 无图片链接");
                            }

                            QiuBaiBean.ResultBean resultBean = new QiuBaiBean.ResultBean(element.text(), herf, pic);

                            QiuBaiBean qiuBaiBean = new QiuBaiBean("小乔" + j, "http://www/baidu.com" + j,
                                    resultBean);
                            list.add(qiuBaiBean);
                        }
                        Message message = new Message();
                        message.obj = list ;
                        message.what = 0;
                        handler.sendMessage(message);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }
}
