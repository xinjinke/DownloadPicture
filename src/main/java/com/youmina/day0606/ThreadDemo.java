package com.youmina.day0606;

import cn.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangxinhe
 * @date 2018/6/6 10:09
 */
public class ThreadDemo implements Runnable {

    final String url = "http://192.168.1.71:8084/api/demand/2";

    @Override
    public void run() {
        String result = HttpClientUtil.doGet(url,"");
        JSONObject object = JSONObject.parseObject(result);
        String s = object.getString("data");
        String count = JSONObject.parseObject(s).getString("browsing");
        System.out.println("浏览数："+count);
    }
}
