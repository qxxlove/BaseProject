package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.Retrofit.API;
import com.example.dell.baseproject.bean.LoginBean;
import com.example.dell.baseproject.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * description: 此项目虽然完成了Retrofit 和 Observer 结合的写法，
 * 但实际开发中不推荐，里面好多细节没注意到，如Observer 导致内存泄漏的问题，Retrofit 请求失败时，提示信息体验性不好
 * autour: TMM
 * date: 2017/10/31 16:58
 * update: 2017/10/31
 * version:
*/

public class RetrofitLoginActivity extends BaseActivity {

    @BindView(R.id.edit_text_name)
    EditText editTextName;
    @BindView(R.id.edit_text_pass)
    EditText editTextPass;
    @BindView(R.id.activity_retrofit_login)
    LinearLayout activityRetrofitLogin;

    private  API api;



    @Override
    public int initContentID() {
        return 0;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_retrofit_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void execHttp() {

    }

    @OnClick({R.id.btn_login_retrofit})
    public    void  initClick (View  view){
        switch (view.getId()){
            case  R.id.btn_login_retrofit :
                  if (isOk()){
                       requestData();
                  }
                break;
            default:
        }
    }

    private void requestData() {
        showProgressDialog("正在加载",true);
        Retrofit retrofit = BaseUtils.getRetrofit("https://www.zslzsl999.cn/carshare/");

        api = retrofit.create(API.class);
        api.login(editTextName.getText().toString(),editTextPass.getText().toString()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        BaseUtils.toast(RetrofitLoginActivity.this, loginBean.getResult().getUserInfo().getUname()+"登录成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        BaseUtils.toast(RetrofitLoginActivity.this, "请求失败");
                        hideProgressDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        BaseUtils.toast(RetrofitLoginActivity.this, "请求完成");
                        hideProgressDialog();
                    }
                });

    }


    private boolean isOk() {
        if (TextUtils.isEmpty(editTextName.getText().toString())){
            BaseUtils.toast(this,"姓名不能为空");
            return  false;
        }
        if (TextUtils.isEmpty(editTextPass.getText().toString())){
            BaseUtils.toast(this,"密码不能为空");
            return  false;
        }
        return true;
    }


}
