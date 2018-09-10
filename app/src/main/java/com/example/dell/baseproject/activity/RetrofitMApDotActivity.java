package com.example.dell.baseproject.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.Retrofit.API;
import com.example.dell.baseproject.adapter.MapDotAdapter;
import com.example.dell.baseproject.bean.MapDotInfoBean;
import com.example.dell.baseproject.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * description:
 * autour: TMM
 * date: 2017/10/23 14:42
 * update: 2017/10/23
 * version:
 *
 *
 * Retrofit 2.0使用详解，配合OkHttp、Gson，Android最强网络请求框架（对Retrofit 1.0 和  Retrofit 2.0 的比较）
 *  http://blog.csdn.net/u012301841/article/details/49685677
*/


public class RetrofitMApDotActivity extends BaseActivity {

    @BindView(R.id.btn_retrofit_dot)
    Button btnRetrofitDot;
    @BindView(R.id.activity_retrofit_map_dot)
    LinearLayout activityRetrofitMapDot;
    @BindView(R.id.listview_dot)
    ListView listviewDot;


    private API api;
    private MapDotAdapter mapDotAdapter;
    private List<MapDotInfoBean.ResultBean>   list ;

    @Override
    public int initContentID() {
        return 0;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_retrofit_map_dot);
        ButterKnife.bind(this);
    }


    @Override
    protected void initData() {
         list = new ArrayList<>();
         mapDotAdapter = new MapDotAdapter(RetrofitMApDotActivity.this,list,R.layout.item_recyclerview_item);
         listviewDot.setAdapter(mapDotAdapter);
    }

    @Override
    protected void initListener() {
        requestData();
    }

    @Override
    protected void execHttp() {

    }




    /**
     * 请求网络
     */
    private void requestData() {
       // 注： Retrofit2 的baseUlr 必须以 /（斜线） 结束，不然会抛出一个IllegalArgumentException   http://139.196.86.35:8090/carshare/
        showProgressDialog("正在加载",true);
        Retrofit retrofit = BaseUtils.getRetrofit("https://www.zslzsl999.cn/carshare/");

        api = retrofit.create(API.class);

        //  Retrofit 的写法
        /* api.getDotInfo().enqueue(new Callback<MapDotInfoBean>() {
            @Override
            public void onResponse(Call<MapDotInfoBean> call, Response<MapDotInfoBean> response) {
                if (response.isSuccessful()) {
                    BaseUtils.toast(RetrofitMApDotActivity.this,"请求成功");
                    if (BaseUtils.isListEmpty(mapDotAdapter.getDatas())){
                        return;
                    }
                    mapDotAdapter.add(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MapDotInfoBean> call, Throwable t) {
                BaseUtils.toast(RetrofitMApDotActivity.this,"请求失败");
            }
        });
*/

        /**
         *   Observser的写法
         */
        api.getDots().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MapDotInfoBean>() {
                    @Override
                    public void accept(MapDotInfoBean mapDotInfoBean) throws Exception {
                        BaseUtils.toast(RetrofitMApDotActivity.this, "请求成功");
                        if (BaseUtils.isListEmpty(mapDotAdapter.getDatas())) {
                            return;
                        }
                        mapDotAdapter.add(mapDotInfoBean.getResult());
                    }
                }, new Consumer<Throwable>() {  // 请求失败（onError()）
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        BaseUtils.toast(RetrofitMApDotActivity.this, "请求失败");
                        hideProgressDialog();
                    }
                }, new Action() {   // 请求完成（onComplete()）
                    @Override
                    public void run() throws Exception {
                        BaseUtils.toast(RetrofitMApDotActivity.this, "请求完成");
                        hideProgressDialog();
                    }
                }) ;

    }


    @OnClick({R.id.btn_retrofit_dot})
    public void initClick(View view) {
       /* switch (view.getId()) {
            case R.id.btn_retrofit_dot:
                if(NetworkUtils.getNetWorkType(RetrofitMApDotActivity.this) == NetworkUtils.NETWORK_2G ||NetworkUtils.getNetWorkType(RetrofitMApDotActivity.this) == NetworkUtils.NETWORK_3G || NetworkUtils.getNetWorkType(RetrofitMApDotActivity.this) == NetworkUtils.NETWORK_4G  ){
                    showNormalDialog();
                } else if (NetworkUtils.getNetWorkType(RetrofitMApDotActivity.this) == NetworkUtils.NETWORK_WIFI){
                    requestData();
                } else {
                    BaseUtils.toast(RetrofitMApDotActivity.this,"暂无网络。请检查网络设置");
                }
                break;

        }*/

    }


    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(RetrofitMApDotActivity.this);
        normalDialog.setIcon(R.mipmap.ic_launcher);
        normalDialog.setTitle("当前处于非WIFI状态下");
        normalDialog.setMessage("您确定要使用吗?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestData();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 显示
        normalDialog.show();
    }
}
