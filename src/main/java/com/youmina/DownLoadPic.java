/**
 * zxh
 * 2017年10月12日
 */ 
package com.youmina;

/**
 * @author zxh
 * @2017年10月12日上午10:37:29
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import cn.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 此有效
 * @author zxh
 * @2017年10月12日上午11:18:02
 */
public class DownLoadPic {

	  public static final String URL = "https://www.dbmeinv.com/?pager_offset=";
	  //1.https://www.dbmeinv.com/?pager_offset=
	  //2.http://www.mmonly.cc/mmtp/list_9_page.html

	  public static final int MAX_PAGE = 250;

	public static final String UA =
			"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3408.400 QQBrowser/9.6.12028.400";

	// 存储路径
	  public static final String BASE_PATH = "D://dbImage";
				//"/Users/xinhezhang/zxh/doubanImage";


	public static Document getDocument(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(UA).get();
		} catch (IOException e) {
//            e.printStackTrace();
			System.out.println("被封，休息0.4秒后再试");
		}
		while(doc == null){
			try{
				Thread.sleep(400);
			}catch (InterruptedException e){
				System.out.println(e.getMessage());
			}
			doc = getDocument(url);
		}
		return doc;
	}
	
  public void getDoc() throws IOException{
	  
	  for (int i = 1; i<= MAX_PAGE; i++) {
          String page_url = URL +i;  
          // 图片按页面分文件夹  
          String pagePath = BASE_PATH;
          File f = new File(pagePath+"//"+i);
    	  if(!f.exists()){f.mkdirs();}
    		
		    Document doc = getDocument(page_url);
					//Jsoup.connect(page_url).get();
		    //1.http://www.mmonly.cc/mmtp/
		    
		    //获取后缀为png和jpg的图片的元素集合
		    Elements pngs = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
		    //遍历元素
		    for(Element e : pngs){
		        String src=e.attr("src");//获取img中的src路径
		        //获取后缀名
		        String imageName = src.substring(src.lastIndexOf("/") + 1,src.length());
		        //连接url
		        if(!src.contains("http")){
		        	continue;
		        }


				Utils.saveImg(src,new File(pagePath+"//"+i, imageName));

//		        URL url = new URL(src);
//
//		        URLConnection uri=url.openConnection();
//		        //获取数据流
//		        InputStream is=uri.getInputStream();
//		        //写入数据流
//		        OutputStream os = new FileOutputStream(new File(pagePath+"//"+i, imageName));
//		        byte[] buf = new byte[1024];
//		        int length=0;
//		        while((length=is.read(buf)) != -1){
//		        	os.write(buf,0,length);
//		        }
		    }
		    System.out.println("當前第"+i+"頁下載完成");
	  }
  }

 
  public static void main(String[] args) throws IOException {
	 new DownLoadPic().getDoc(); //调用方法 
 	}
 }

