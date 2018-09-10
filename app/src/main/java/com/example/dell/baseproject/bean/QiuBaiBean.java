package com.example.dell.baseproject.bean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/2.
 * 邮箱：123123@163.com
 */

public class QiuBaiBean {

    private String name ;
    private String name_url;
    private ResultBean resultBean ;

    public QiuBaiBean() {
    }

    public QiuBaiBean(String name, String name_url, ResultBean resultBean) {
        this.name = name;
        this.name_url = name_url;
        this.resultBean = resultBean;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_url() {
        return name_url;
    }

    public void setName_url(String name_url) {
        this.name_url = name_url;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

   public static class  ResultBean {
         private  String  title ;
         private  String  content;
         private  String  image_url;


        public  ResultBean(String title, String content, String image_url) {
            this.title = title;
            this.content = content;
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }


}
