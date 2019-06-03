package com.example.dell.baseproject.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * autour: TMM
 * date: 2017/10/30 13:45
 * update: 2017/10/30
 * version:
 * 使用：在程序开始的时候将单例类型注入到一个容器之中,
 *      也就是单例 ManagerClass ,在使用的时候再根据 key 值获取对应的实例,这种方式可以使我们很方便的管理很多单例对象,
 *      也对用户隐藏了具体实现类,降低了耦合度;但是为了避免造成内存泄漏,
 *      所以我们一般在生命周期销毁的时候也要去销毁它。
*/

public class SingleManager {

    public static Map<String,Object> map = new HashMap<>();

    private SingleManager() {
    }

    public  static  void  put (String key,Object value){
        if (!map.containsKey(key)){
            map.put(key,value);
        }
    }


    public static  Object get (String key) {
        return  map.get(key);
    }

}
