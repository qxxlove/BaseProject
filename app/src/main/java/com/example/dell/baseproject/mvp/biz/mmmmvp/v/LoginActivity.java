package com.example.dell.baseproject.mvp.biz.mmmmvp.v;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.mvp.biz.mmmmvp.MVPBaseActivity;
import com.example.dell.baseproject.mvp.biz.mmmmvp.m.LoginBean;
import com.example.dell.baseproject.mvp.biz.mmmmvp.p.IViewUserLoginPresener;
import com.example.dell.baseproject.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends MVPBaseActivity<IViewUserLogin, IViewUserLoginPresener> implements IViewUserLogin {

    @BindView(R.id.edit_input_phone)
    EditText editInputPhone;
    @BindView(R.id.edit_input_pass)
    EditText editInputPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login})
    public  void  initCLick (View view) {
        switch (view.getId()){
            case R.id.btn_login  :
                mPresenter.login();
                break;
        }
    }



    @Override
    protected IViewUserLoginPresener createPresenter() {
        return new IViewUserLoginPresener(this);
    }

    @Override
    public String getUserName() {
        return editInputPhone.getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return editInputPass.getText().toString().trim();
    }

    @Override
    public void clearUserName() {
        editInputPhone.setText("");
    }

    @Override
    public void clearUserPass() {
        editInputPass.setText("");
    }

    @Override
    public void hideDialog() {
          hideProgressDialog();
    }

    @Override
    public void shouDialog() {
         showProgressDialog("正在登录",false);
    }

    @Override
    public void toMainActivity(LoginBean loginBean) {
        //BaseUtils.toast(this,loginBean.getResult().getUserInfo().getUname()+"登陆成功");
        BaseUtils.toast(this,"登陆成功");
    }

    @Override
    public void tofaile() {
        BaseUtils.toast(this,"登陆失败");
    }
}
