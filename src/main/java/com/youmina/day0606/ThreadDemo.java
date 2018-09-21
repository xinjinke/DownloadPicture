package com.youmina.day0606;

import cn.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangxinhe
 * @date 2018/6/6 10:09
 */
public class ThreadDemo implements Runnable {

    final String url =
            "http://192.168.1.71:8080/api/scActivity/2";
            //"https://krcom.cn/5991896093/episodes/2358773:4256118189261819?sudaref=krcom.cn&display=0&retcode=6102";
            //"https://weibo.com/ttarticle/p/show?id=2309404258749496830738";
            //"https://www.shuliao.com/users/activityDetail?aid=1827";
            //"http://192.168.1.71:8084/api/demand/2";


    @Override
    public void run() {
        String result = HttpClientUtil.doGet(url,"");
//        JSONObject object = JSONObject.parseObject(result);
//        String s = object.getString("data");
//        String count = JSONObject.parseObject(s).getString("browsing");
//        System.out.println("浏览数："+count);
    }
}
