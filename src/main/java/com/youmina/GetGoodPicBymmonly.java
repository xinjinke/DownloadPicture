/**
 * zxh
 * 2017年10月11日
 */ 
package com.youmina;

/**
 * @author zxh
 * @2017年10月11日下午5:52:00
 */
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;  
import java.util.List;

import org.apache.commons.io.IOUtils;  
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document;  
import org.jsoup.nodes.Element;  
import org.jsoup.select.Elements;  
  
public class GetGoodPicBymmonly {  
    /********************** 
     ******* Setting ****** 
     **********************/  
    //从第x页抓起  
    public static final String URL = 
    		//"http://thibt.com/forum.php?mod=forumdisplay&fid=56&filter=&orderby=lastpost&&page=";
    		"http://www.mmonly.cc/mmtp/list_9_";
    		//"https://www.dbmeinv.com/?pager_offset=";  
    //douban.花瓣
    // 模仿UA  
//  public static final String UA =   "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.47 Safari/536.11";  
    public static final String UA =  
    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3408.400 QQBrowser/9.6.12028.400";
  
    // 图片节点选择器  
    public static final String IMG_DIV_SELECTOR = ".t_fsz img";
    		//".topic-figure img";
    		//".image-show img";  
    //帖子节点选择器  
    public static final String POST_SELECTOR = "";
    		//".img_single a[class]";
    		//".photo_wrap a[class]";  
    // 最高页数  
    public static final int MAX_PAGE = 255;  
    // 存储路径  
    public static final String BASE_PATH = "/Users/xinhezhang/zxh/images";
      
      
  
    /** 
     * @Description 主函数 
     */  
    public static void main(String[] args) {  
  
    	SimpleDateFormat sdf =new SimpleDateFormat("HHmmss");
    	int number = 0;
        for (int i = 10; i<=MAX_PAGE; i=i++) {
            String page_url = URL +i+".html";  
            // 图片按页面分文件夹  
            String pagePath = BASE_PATH+"/"+sdf.format(new Date());
              
            System.out.println("\n" + "**************解析URL(第" + i + "页):" + page_url + "**************\n");  
            String pageResult = getResultByUrl(page_url); 
            System.out.println(pageResult.length()+"\n"+pageResult);
            Iterator iterator=getPostUrl(pageResult).iterator();  
            while(iterator.hasNext()){  
                try {  
                    Thread.currentThread().sleep(1000);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                String postUrl = (String) iterator.next();  
                System.out.println("解析图片帖子URL:" + postUrl);  
                String postResult = getResultByUrl(postUrl);  
                List<String> urls = getImgUrl(postResult);  
                for (String str : urls) {  
                    try {  
                        Thread.currentThread().sleep(500);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                    }  
                    System.out.println("解析图片url："+str);  
                    File imgFile = getStoreFile(str, pagePath,number++);
                    if (saveImg(str, imgFile))  
                        System.out.println("存入图片" + imgFile.getName());  
                }  
            }  
            System.out.println("\n" + "**************解析URL完成(第" + i + "页)**************\n");  
        }  
        System.out.println("\n" + "**************全部URL解析完成**************\n");  
              
              
    }  
  
    /** 
     * 获取帖子目录名和对应的url 
     * @param pageResult 
     * @return 返回map，key：图片目录path，value：帖子url 
     */  
    public static List<String> getPostUrl(String pageResult){  
        Document doc = Jsoup.parse(pageResult);  
        List<String> rtn = new ArrayList<String>();  
        Elements es = doc.select("div.ABox a");
        for (Iterator<Element> i = es.iterator(); i.hasNext();) {  
            Element e = i.next();  
            String url = e.attr("href");
         if(!url.contains("http")){//加上主站地址
        	 url = "http://thibt.com/"+url; 
         }
         rtn.add(url); 
         System.out.println("图片帖子链接：" + url);  
        }  
        return rtn;  
    }  
      
    /** 
     * 给定url获取整个页面内容 
     *  
     * @param url 
     * @return 
     */  
    public static String getResultByUrl(String url) {  
        HttpClient hc = new DefaultHttpClient();  
        try {  
            HttpGet httpget = new HttpGet(url);  
            httpget.setHeader("User-Agent", UA);  
            httpget.setHeader("Accept-Encoding", "utf-8");  
            HttpResponse response = hc.execute(httpget);  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
                InputStream in = entity.getContent();  
                BufferedReader br = new BufferedReader(new InputStreamReader(in,"utf-8"));  
                StringBuffer buffer = new StringBuffer();  
                String line = "";  
                while ((line = br.readLine()) != null){  
                  buffer.append(line);  
                }  
                in.close();  
                return buffer.toString();  
            }  
        } catch (Exception e) {  
            //再来一遍  
                e.printStackTrace();  
        }  
        return "";  
    }  
      
      
    /** 
     * 从帖子内容中获取图片url 
     */  
    public static List<String> getImgUrl(String str) {  
        List<String> img_urls = new ArrayList<String>();  
        Document doc = Jsoup.parse(str);  
  
        Elements es = doc.select("img[src~=(?i)\\.(png|jpe?g)]");  
        for (Iterator<Element> i = es.iterator(); i.hasNext();) {  
            Element e = i.next();  
            String url = e.attr("src");
            if(url.equals("")){
            	url = e.attr("file");
            }
            if(url.contains("http")){
            	 img_urls.add(url);  
            }
        }  
        return img_urls;  
    }  
  
    /** 
     * 从图片url和帖子名，生成图片的存储路径 
     */  
    public static File getStoreFile(String imgUrl, String postPath,int number) {
  
        String[] tmp = imgUrl.split("/");  
  
        String imgName = number+".jpg";
                //tmp[tmp.length - 1];
  
        File dir = new File(postPath);  
        if (!dir.exists())  
            dir.mkdirs();  
        File imgFile = new File(postPath + "/" + imgName);
        if (!imgFile.exists()) {  
            try {  
                imgFile.createNewFile();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return imgFile;  
    }  
    /** 
     * 将图片写入本地 
     */  
    public static boolean saveImg(String img_url, File file) {  
        HttpClient hc = new DefaultHttpClient();  
        try {  
            HttpGet httpget = new HttpGet(img_url);  
            httpget.setHeader("User-Agent", UA);  
            httpget.setHeader("Accept-Encoding", "gzip, deflate, sdch, br");  
  
            HttpResponse response = hc.execute(httpget);  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {  
                InputStream in = entity.getContent();  
                OutputStream os = new FileOutputStream(file);  
                int count = IOUtils.copy(in, os);  
                IOUtils.closeQuietly(in);  
                IOUtils.closeQuietly(os);  
                if (0 != count)  
                    return true;  
            }  
        } catch (Exception e) {  
                e.printStackTrace();  
        }  
        return false;  
    }  
}  
