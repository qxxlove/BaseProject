package com.example.dell.baseproject.bean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/8.
 * 邮箱：123123@163.com
 */

public class RecyclerData {

    private  String url ;
    private  String name ;
    private  String content;


    public RecyclerData() {
    }

    public RecyclerData(String url, String name, String content) {
        this.url = url;
        this.name = name;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
