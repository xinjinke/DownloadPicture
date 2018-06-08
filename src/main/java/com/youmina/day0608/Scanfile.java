package com.youmina.day0608;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author zhangxinhe
 * @date 2018/6/8 13:24
 */
public class Scanfile {

    private static ArrayList<String> scanFiles = new ArrayList<String>();

    public static ArrayList<String> scanFilesWithRecursion(String folderPath) throws Exception {
        ArrayList<String> dirctorys = new ArrayList<String>();
        File directory = new File(folderPath);
        if (!directory.isDirectory()) {
            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        if (directory.isDirectory()) {
            File[] filelist = directory.listFiles();
            for (int i = 0; i < filelist.length; i++) {
                /**如果当前是文件夹，进入递归扫描文件夹**/
                if (filelist[i].isDirectory()) {
                    dirctorys.add(filelist[i].getAbsolutePath());
                    /**递归扫描下面的文件夹**/
                    scanFilesWithRecursion(filelist[i].getAbsolutePath());
                }
                /**非文件夹**/
                else {
                    scanFiles.add(filelist[i].getAbsolutePath());
                }
            }
        }
        return scanFiles;
    }

    public static void main(String[] args) {

        Date start = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+sdf.format(start));
        try {
            scanFilesWithRecursion("/usr/local/image");
        } catch (Exception e) {
            e.printStackTrace();
        }

        int size = scanFiles.size();
        int page = size / 100;

        for(int i = 1;i< page;i++){
            File file = new File("/var/www/html/pc/picture_"+i+".html");
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html lang=\"zh-CN\">\n");
            sb.append("<head><title>扫描文件,生成静态html</title></head>\n");
            sb.append("<body>\n");
            for (int j = (i-1)*100;j< 100 * i;j++) {
                sb.append("<img src=\"" + scanFiles.get(j));
                sb.append("\">\n");
            }
            if(i-1>0){
                sb.append("<a href=\"picture_"+ (i-1) +".html\""+ ">上一页</a>\n");
            }
            if(i+1<size){
                sb.append("<a href=\"picture_"+ (i+1) +".html\""+ ">下一页</a>\n");
            }
            sb.append("</body>\n");
            sb.append("</html>");
            try {
                PrintStream ps = new PrintStream(new FileOutputStream(file));
                ps.println(sb.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Date end = new Date();
        long s = end.getTime()-start.getTime();
        System.out.println("结束时间："+sdf.format(start));
        System.out.println("总耗时："+s+"ms");
    }
}