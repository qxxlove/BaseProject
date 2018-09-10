package com.example.dell.baseproject.bean;

/**
 * Created by SEELE on 2017/6/21.
 */

public class UserInfoBena {

    public static   UserInfoBena  userInfoBena ;
    private  String  token ;
    private  String  name;
    private  String pass;


    public static UserInfoBena getUserInfoBena() {
        return userInfoBena;
    }

    public static void setUserInfoBena(UserInfoBena userInfoBena) {
        UserInfoBena.userInfoBena = userInfoBena;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
