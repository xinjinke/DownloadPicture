package com.youmina.handler;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.*;

/**
 * Created by xinhezhang on 2018/3/29.
 */
public class StreamTest {


    public static void main(String[] args) {

        File file = new File("/Users/xinhezhang/Project/downloadPicture/pom.xml");

        String str = "";

        try {
            InputStream inputStream = new FileInputStream(file);

            byte[] buffer = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, len));
            }
            str = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
