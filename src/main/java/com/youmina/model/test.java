package com.youmina.model;

import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * Created by xinhezhang on 2018//28.
 */
public class test {
    private static final Logger logger = Logger.getLogger(test.class);
    public static void main(String[] args){
        Properties props = System.getProperties();
//        Logger.getGlobal()
        logger.info("hello");
        System.out.println(props.getProperty("os.name"));
    }
}
