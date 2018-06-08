package cn;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**s
 * @author zhangxinhe
 * @date 2018/3/23 14:23
 */
public class DwtThread extends Thread{

    private static final Logger logger = Logger.getLogger(DwtThread.class);

    private static String Url = "http://www.topit.me/albums?p=";

    private int FIRST_PAGE = 1;

    private int MAX_PAGE = 155;

    private String BASE_PATH = "D:\\Dwp";

    public static final String UA =
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.3408.400 QQBrowser/9.6.12028.400";

    public static String CSS_QUERY = "div";

    private int poolSize = 0;

    //已下载数量
//    private AtomicInteger hasDownloadNums = new AtomicInteger(0);

    private static int hasDownloadNums_;
    //总下载量
    private static Double totalNums;

    //文件名连接符，mac用/,win //.
    private String Symbol ="//";

    private ExecutorService threadPool;

    public ExecutorService getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ExecutorService cachedThreadPool) {
        this.threadPool = cachedThreadPool;
    }

    DwtThread(){

    }

    DwtThread(String url,int page,int poolSize,String filePath){
        this.Url = url;
        this.FIRST_PAGE = page;
        this.poolSize = poolSize;
        threadPool = Executors.newFixedThreadPool (poolSize);
        this.BASE_PATH = filePath;
        System.out.println("设定任务[爬图地址url:"+url+", 起始页："+page+", 线程池大小："+poolSize+", 文件保存路径："+filePath+"]");
    }

    /***
     * 打印任务状态信息
     */
    public void printState(){
        // 当前排队线程数
        int queueSize = ((ThreadPoolExecutor)threadPool).getQueue().size();

        // 当前活动线程数
        int activeCount = ((ThreadPoolExecutor)threadPool).getActiveCount();

        // 执行完成线程数
        long completedTaskCount = ((ThreadPoolExecutor)threadPool).getCompletedTaskCount();
        // 总线程数（排队线程数 + 活动线程数 +  执行完成线程数）
        long taskCount = ((ThreadPoolExecutor)threadPool).getTaskCount();

        System.out.println("  爬图地址url:"+Url+", 起始页："+FIRST_PAGE+", 线程池大小："+poolSize+", 文件保存路径："+BASE_PATH);

        Double totals =  totalNums!=null?totalNums:getTotalNums();

        System.out.println("  总图片数量："+ totals);
        System.out.println("  当前排队线程数:"+queueSize+",活动线程数:"+activeCount+",执行完成线程数:"+completedTaskCount+",总线程数:"+taskCount);
        Double process = Double.valueOf(hasDownloadNums_/totals);
        DecimalFormat dFormat = new DecimalFormat("#0.000");
        System.out.println("  已下载图片数量："+ hasDownloadNums_+",当前进度："+ dFormat.format(process*100)+"%");
//        System.out.println("  hasDownloadNums："+ hasDownloadNums +", hasDownloadNums_:"+hasDownloadNums_);
        System.out.println();
    }

    public void setSymbol(){
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        if(osName.equals("Mac OS X")){
            Symbol="/";
        }
    }

    @Override
    public void run() {
        System.out.println("  log:"+Thread.currentThread().getName() + "开始执行......");
        logger.info("******************注意：1.url格式规范,如：http://www.topit.me/user/3346272/albums,http://www.topit.me/list/25090****************");
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MAX_PAGE = setPageSize(Url);
        logger.info("\n" + "**************当前(" + Url + ",总页数:" + MAX_PAGE + ",开始时间：" + sdf2.format(new Date()) + "**************\n");

        for (int i = FIRST_PAGE; i <= MAX_PAGE; i++) {//分页下载所有主题帖url
            final String page_url = Url + "?p=" + i;

            final String pagePath = BASE_PATH + Symbol + sdf.format(new Date()) + Symbol + getTitle(page_url);

            logger.info("\n" + "**************当前( " + i + " page):" + page_url + "**************\n");

            final List<String> list = getPostUrl(page_url, "div.albumlist_blk h2 a");

            for (String url : list) {
                threadPool = Executors.newFixedThreadPool (poolSize);
                String title = getTitle(url);
                int size = setPageSize(url);
                int num = 1;
                while (num <= size) {
                    String pageUrl = url + "?p=" + num;
                    logger.info("\n" + "**************开始下载 主题帖：" + title + ", 总页数：" + size + ", 地址:" + pageUrl + ", 开始时间：" + sdf2.format(new Date()) + "**************\n");

                    final String path = pagePath + Symbol + title;
                    Iterator iterator = getPostUrl(pageUrl, "div.title a").iterator();

                    if (!iterator.hasNext()) {
                        iterator = getPostUrl(pageUrl, "div.t a").iterator();
                        if (!iterator.hasNext()) {
                            iterator = getPostUrl(pageUrl, "div.catalog a").iterator();
                        }
                    }

                    while (iterator.hasNext()) {

//                        try {
//                            ThreadDemo.currentThread().sleep(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        final String postUrl = (String) iterator.next();

                        Thread thread = new Thread(new Runnable() {
                            public void run() {
                                List<String> urls = getImgUrl(postUrl);
                                for (final String str : urls) {
                                    //保存图片
                                    getStoreFileAndsaveImg(str,path);
                                }
                            }
                        });
                        threadPool.execute(thread);
                    }
                    num++;
                }
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用
                threadPool.shutdown();
                while(true){
                    if(threadPool.isTerminated()){//下载该贴子的所有线程执行完毕
                        logger.info("\n" + "**************完成下载完成 主题帖：" + title + ",url:" + url +",完成时间："+sdf2.format(new Date())+ "************************\n");
                        break;
                    }
                }
            }
        }
       logger.info("\n" + "**************本次下载"+Url+"完成" + sdf2.format(new Date()) + "**************************************************\n");
    }

    //获取总图片数量
    public Double getTotalNums(){
        Double totals = 0d;
        for (int i = FIRST_PAGE; i <= MAX_PAGE; i++) {
            String page_url = Url + "?p=" + i;
            totals += GetNums(page_url);
        }
        totalNums = totals;
        return totals;
    }


    //根据帖子url,当前页总图片数量
    public int GetNums(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(UA).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements es = doc.select("div.albumlist_blk span");

        int nums = 0;
        for (Iterator<Element> i = es.iterator(); i.hasNext();){
            Element e = i.next();
            String content = e.text().replace(" ","");
            if(content.contains("张")){
                try{
                    if(content.length()>3){
                        String s = content.substring(1,content.length()-2);
                        if(StringUtil.isNumeric(s)){
                            nums += Math.abs(Integer.parseInt(s));
                        }
                    }
                }catch (Exception e2){
                    logger.info(e2 + ",content:"+content+"");
                }
            }
        }
        return nums;
    }

    public static String getTitle(String url){
        Document doc =  getDocument(url);
        Elements es3 = doc.select("div.pageheader h2"); //page a
        String title = es3.text();
        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|.。~、？！]");
        Matcher matcher = pattern.matcher(title);
        title = matcher.replaceAll("");
        return title;
    }

    public static Document getDocument(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent(UA).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }


    public static int setPageSize(String url) {
        Document doc = getDocument(url);
        Elements es = doc.select("div.pages a"); //page a
        int pageSize = 1;
        for (Iterator<Element> i = es.iterator(); i.hasNext();) {
            Element e = i.next();
            String s = e.text();
            int page = Integer.parseInt(StringUtil.isNumeric(s)?s:"0");
            if(page > pageSize){
                pageSize = page;
            }
        }
        return pageSize;
    }

    public static List<String> getPostUrl(String url,String cssQuery){
        Document doc = getDocument(url);
        List<String> rtn = new ArrayList<String>();
        Elements es = doc.select(cssQuery); //imgwkc

        for (Iterator<Element> i = es.iterator(); i.hasNext();) {
            Element e = i.next();
            String u = e.attr("href");
            if(!u.contains("?p=")||!u.contains("login")||!u.contains("user")){
                rtn.add(u);
            }
//            logger.info("图片下载页面链接url:" + u+"  ");
        }
        return rtn;
    }

    public static List<String> getImgUrl(String url){
        List<String> img_urls = new ArrayList<String>();
        Document doc = getDocument(url);

        Elements es = doc.select("a#item-tip img[src~=(?i)\\.(png|jpe?g)]");
        for (Iterator<Element> i = es.iterator(); i.hasNext();) {
            Element e = i.next();
            String u = e.attr("src");
            if(u.equals("")){
                u = e.attr("file");
            }
            if(u.contains("http")){
                img_urls.add(u);
            }
        }
        return img_urls;
    }

    public void getStoreFileAndsaveImg(String imgUrl, String postPath) {

        String[] tmp = imgUrl.split("/");
        String imgName =tmp[tmp.length - 1];
        File dir = new File(postPath);
        if (!dir.exists())
            dir.mkdirs();
        File imgFile = new File(postPath + Symbol + imgName);
        if (!imgFile.exists()) {//文件不存在
            try {
                imgFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long length = imgFile.length();
        if(imgFile.length()>0){
            //说明文件一下载
//            hasDownloadNums.incrementAndGet();
            hasDownloadNums_++;
        }else{
            if(Utils.saveImg(imgUrl,imgFile)){
//                hasDownloadNums.incrementAndGet();
                hasDownloadNums_++;
//                logger.info("异步保存图片:" + imgFile.getName());
            }else {
                logger.info("异步保存失败图片:" + imgFile.getName());
                imgFile.delete();
            }
        }
    }
}
