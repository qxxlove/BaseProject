package com.example.dell.baseproject.bean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/27.
 * 邮箱：123123@163.com
 */

public class Appinfo {

    public int id;
    public String name;
    public String downloadUrl;
    public double size;
    public String packageName;


    public Appinfo() {
    }

    public Appinfo(int id, String name, String downloadUrl, double size, String packageName) {
        this.id = id;
        this.name = name;
        this.downloadUrl = downloadUrl;
        this.size = size;
        this.packageName = packageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
