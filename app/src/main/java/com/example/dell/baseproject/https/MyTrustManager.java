package com.example.dell.baseproject.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/9/23.
 * 邮箱：123123@163.com
 */

public class MyTrustManager   implements X509TrustManager {


    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        //return new X509Certificate[0];
        return null;
    }
}
