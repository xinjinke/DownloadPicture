package com.youmina.model;

import cn.HttpClientUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by xinhezhang on 2018//28.
 */
public class test {
    private static final Logger logger = Logger.getLogger(test.class);
    public static void main(String[] args){
//        Properties props = System.getProperties();
////        Logger.getGlobal()
//        logger.info("hello");
//        System.out.println(props.getProperty("os.name"));
//        Double d = 18.62345;
//        System.out.println(d);

//        String[] ss ="/usr/local/image/2018-03-19/119271801195b7da02l.jpg".split("image");
//        if(ss.length>=2){
//            System.out.println(ss.length);
//        }
        String url = "http://192.168.1.197:8200";
                //"https://www.shuliao.com/users/activityDetail?aid=1827";
        String result = HttpClientUtil.doGet(url,"");
        JSONObject object = JSONObject.parseObject(result);
    }
}
