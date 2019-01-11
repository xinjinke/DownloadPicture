package com.youmina.day20190110;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

/**
 * @author zhangxinhe
 * @date 2019/1/11 11:48
 */
public class readJson {

    public static void main(String[]  args){
            try {
                 String input = FileUtils.readFileToString(new File("/goods/goods1.json"), "UTF-8");

                JSONArray array = JSONArray.parseArray(input);
                List<GoodsAllinone> list = array.toJavaList(GoodsAllinone.class);

                for(GoodsAllinone allinone : list){
                    System.out.println(JSONObject.toJSONString(allinone));
                }
           } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
