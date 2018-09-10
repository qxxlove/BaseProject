package com.example.dell.baseproject.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 描述 ：这个接口是用来检测host和session 的
 * 作者：Created by SEELE on 2017/9/23.
 * 邮箱：123123@163.com
 */

public class MyHostnameVerifier  implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        // 信任所有host，直接返回true
        return true;
    }
}
