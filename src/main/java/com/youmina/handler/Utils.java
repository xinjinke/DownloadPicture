package com.youmina.handler;

/**
 * Created by xinhezhang on 2018/3/29.
 */
public class Utils {
    //二分查找法
    public static int ef(int a[], int tag) {
        int first = 0;
        int end = a.length;
        for (int i = 0; i < a.length; i++) {
            int middle = (first + end) / 2;
            if (tag == a[middle]) {
                return middle;
            }
            if (tag > a[middle]) {
                first = middle + 1;
            }
            if (tag < a[middle]) {
                end = middle - 1;
            }
        }
        return 0;
    }
}