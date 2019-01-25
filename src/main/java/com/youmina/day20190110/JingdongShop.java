package com.youmina.day20190110;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxinhe
 * @date 2019/1/10 10:10
 */
public class JingdongShop {


    private final static ExecutorService pool = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws Exception {

        //衣服
//      String url = "https://search.jd.com/Search?keyword=%E8%A1%A3%E6%9C%8D&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=1&s=1&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E9%A3%9F%E7%89%A9&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E9%A3%9F%E7%89%A9&stock=1&page=1&s=1&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E8%A3%99%E5%AD%90&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=6.def.0.V16&wq=qun&page=1&s=1&click=0";
//      String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=2.def.0.V16&wq=shouji&cid2=653&cid3=655&page=1&s=1&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E4%B8%AA%E6%8A%A4%E6%B8%85%E6%B4%81&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E4%B8%AA%E6%8A%A4%E6%B8%85%E6%B4%81&stock=1&page=1&s=1&click=0";
//      String url = "https://search.jd.com/Search?keyword=%E5%9B%BE%E4%B9%A6&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E5%9B%BE%E4%B9%A6&page=1&s=1&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E9%9B%AA%E7%BA%BA%E8%A1%AB&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=3&s=53&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E6%96%B0%E5%93%81&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=4.def.0.V16&wq=%E8%BF%9E%E8%A1%A3%E8%A3%99&zx=2&page=1&s=1&click=0";

//      String url = "https://search.jd.com/Search?keyword=%E5%A5%B3%E8%A3%85&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E5%A5%B3%E8%A3%85&cid2=1343&page=1&s=1&click=0";

//        String url = "https://search.jd.com/Search?keyword=%E7%89%9B%E4%BB%94%E8%A3%A4%E5%A5%B3&enc=utf-8&qrst=1&rt=1&stop=1&spm=2.1.1&vt=2&page=1&s=1&click=0";

//        String url = "https://search.jd.com/Search?keyword=%E5%86%85%E8%A1%A3&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E5%86%85%E8%A1%A3&page=1&s=1&click=0";
//        String url = "https://search.jd.com/Search?keyword=%E5%AE%B6%E7%94%A8%E7%94%B5%E5%99%A8&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E5%AE%B6%E7%94%A8%E7%94%B5%E5%99%A8&stock=1&page=1&s=1&click=0";
//        String url = "https://search.jd.com/Search?keyword=%E6%95%B0%E7%A0%81&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E6%95%B0%E7%A0%81&page=1&s=1&click=0";
//        String url = "https://search.jd.com/Search?keyword=%E7%94%9F%E9%B2%9C&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E7%94%9F%E9%B2%9C&stock=1&page=1&s=1&click=0";
//        String url = "https://search.jd.com/Search?keyword=%E5%8A%9E%E5%85%AC&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E5%8A%9E%E5%85%AC&stock=1&page=1&s=1&click=0";



//        pool.execute(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    getGoodsInfo();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        });
//        String url = "https://search.jd.com/Search?keyword=%E8%83%8C%E5%BF%83&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E8%83%8C%E5%BF%83&page=1&s=1&click=0";
//
//        getParseredHtml2(url);


        getGoodsInfo();
    }


    public static String getParseredHtml2(String url) throws IOException
    {
        String exePath = "D:\\github\\DownloadPicture\\src\\main\\java\\com\\youmina\\day20190110\\phantomjs.exe";
        String jsPath = "D:\\github\\DownloadPicture\\src\\main\\java\\com\\youmina\\day20190110\\config.js";

        Runtime rt = Runtime.getRuntime();
        Process p = rt.exec(exePath + " " + jsPath + " " + url);
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while ((tmp = br.readLine()) != null)
        {
            sbf.append(tmp);
        }
        System.out.println("resut: "+sbf.toString());
        return sbf.toString();
    }


    public static void getGoodsInfo() throws IOException {
//        String url = "https://search.jd.com/Search?keyword=%E5%9B%B4%E5%B7%BE&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&suggest=6.def.0.V16&wq=weij&page=1&s=1&click=0";

        String url = "https://search.jd.com/Search?keyword=%E8%83%8C%E5%BF%83&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wq=%E8%83%8C%E5%BF%83&page=1&s=1&click=0";
        int m = 1;
        for(int i = 1; i<= 100 ;i ++){//i 第几页
            if(i > 1){
                url = url.replaceAll("page=" + (m-2), "page=" + m);
                url = url.replaceAll("s=" + (53 * (i - 2) + 1), "s=" + (53 * (i - 1) + 1));
            }
            m += 2;

            url = getParseredHtml2(url);

            Document doc = Jsoup.parse(url);
//            Document doc = Jsoup.connect(url).maxBodySize(0).get();

            Elements ulList = doc.select("ul[class='gl-warp clearfix']");

            Elements liList = ulList.select("li[class='gl-item']");


            List<GoodsAllinone> dataJson = new ArrayList();
            for (Element item : liList) {

                if (!item.select("span[class='p-promo-flag']").text().trim().equals("广告")) {
                    //连接
                    String linkUrl = item.select("div.p-img").select("a").attr("href");

                    //商品显示图片
                    String img = item.select("div.p-img").select("a > img").attr("source-data-lazy-img");
                    //价格
                    String price = item.select("div.p-price").select("strong > i").text();
                    //
                    String name = item.select("div[class='p-name p-name-type-2']").select("em").text().trim();

//                System.out.println("linkUrl:"+linkUrl+",img:"+img+",price:"+price+",name:"+name);

                    Document docx = Jsoup.connect("https:"+linkUrl).maxBodySize(0).get();//商品内容详情页面

                    Elements garlysList = docx.select("div.spec-items").select("ul > li");

                    List<String> gallerys = new ArrayList();
                    for(Element gallery:garlysList){
                        gallerys.add(gallery.select("img").attr("src"));
                    }

                    //尺碼大小
                    List<String> size = new ArrayList();

                    Elements sizeList = docx.select("div[data-type='尺码']").select("div.dd > div");

                    for(Element color : sizeList){
                        size.add(color.attr("data-value"));
                    }

                    System.out.println("name:"+name+",gallerys:"+gallerys+",size:"+size);

                    //圖片介紹
                    String detail = docx.select("div#J-detail-content").html();//TODO ......


                    //商品屬性
                    Elements attributeEList = docx.select("div.p-parameter").select("ul[class='parameter2 p-parameter-list'] > li");

                    List<GoodsAttribute> attributeList = new ArrayList<>();
                    GoodsAttribute attribute = null;
                    for(Element e : attributeEList){
                        String [] str = e.text().split("：");
                        attribute = new GoodsAttribute();
                        attribute.setAttribute(str[0]);
                        attribute.setValue(str[1]);
                        if(!str[0].equals("商品名称")){
                            attributeList.add(attribute);
                        }
                    }

                    Goods goods = new Goods();
                    goods.setGallery(gallerys.toArray(new String[gallerys.size()]));
                    goods.setDetail(detail);//TODO
                    goods.setCategoryId(37L);
                    goods.setName(name);
                    goods.setPicUrl(img);
                    try{
                        goods.setCounterPrice(new BigDecimal(price));
                        goods.setRetailPrice(new BigDecimal(price));
                    }catch (Exception e){

                    }

                    //颜色
                    List<GoodsSpecification> color_specifications = new ArrayList<>();
                    //尺寸
                    List<GoodsSpecification> size_specifications = new ArrayList<>();
                    //系列
                    List<GoodsSpecification> xilie_specifications = new ArrayList<>();

                    GoodsSpecification specification = null;

                    //顔色種類
                    Elements colorList = docx.select("div[data-type='颜色']").select("div.dd > div");

                    //系列
                    Elements xiList = docx.select("div[data-type='系列']").select("div.dd > div");

                    for(Element color : colorList){
                        //颜色名称
                        String colorName = color.attr("data-value");
                        //颜色图片
                        String colorUrl = color.select("a > img").attr("src");
                        specification = new GoodsSpecification();
                        specification.setSpecification("顔色");
                        specification.setValue(colorName);
                        specification.setPicUrl(colorUrl);
                        color_specifications.add(specification);
                    }

                    for(Element element : xiList){
                        //名称
                        String xname = element.attr("data-value");
                        //图片
                        String ximg = element.select("a > img").attr("src");
                        specification = new GoodsSpecification();
                        specification.setSpecification("系列");
                        specification.setValue(xname);
                        specification.setPicUrl(ximg);
                        xilie_specifications.add(specification);
                    }


                    for(Object str : size){
                        specification = new GoodsSpecification();
                        specification.setSpecification("尺码");
                        specification.setValue(str.toString());
                        size_specifications.add(specification);
                    }



                    GoodsAllinone allinone = new GoodsAllinone();
                    allinone.setGoods(goods);

                    List<GoodsProduct> goodsProducts = new ArrayList<>();
                    GoodsProduct product = null;

                    if(color_specifications.size() != 0 && size_specifications.size() != 0){
                        for(GoodsSpecification colorSpec : color_specifications){
                            for(GoodsSpecification sizeSpec : size_specifications){
                                product = new GoodsProduct();
                                String[] str = new String[]{colorSpec.getValue(),sizeSpec.getValue()};
                                product.setSpecifications(str);
                                product.setNumber(100);


                                BigDecimal p = new BigDecimal(0l);
                                try{
                                    p = new BigDecimal(price);
                                }catch (Exception e){

                                    System.out.println("价格异常："+price);
                                }
                                product.setPrice(p);
                                product.setUrl("");//比较难获取
                                goodsProducts.add(product);
                            }
                        }
                    }else if(color_specifications.size() == 0){
                        for(GoodsSpecification sizeSpec : size_specifications){
                            product = new GoodsProduct();
                            String[] str = new String[]{sizeSpec.getValue()};
                            product.setSpecifications(str);
                            product.setNumber(100);
                            BigDecimal p = new BigDecimal(0l);
                            try{
                                p = new BigDecimal(price);
                            }catch (Exception e){

                                System.out.println("价格异常："+price);
                            }
                            product.setPrice(p);
                            product.setUrl("");//比较难获取
                            goodsProducts.add(product);
                        }
                    }else if(size_specifications.size() == 0){
                        for(GoodsSpecification colorSpec : color_specifications){
                            product = new GoodsProduct();
                            String[] str = new String[]{colorSpec.getValue()};
                            product.setSpecifications(str);
                            product.setNumber(100);

                            BigDecimal p = new BigDecimal(0l);
                            try{
                                p = new BigDecimal(price);
                            }catch (Exception e){

                                System.out.println("价格异常："+price);
                            }
                            product.setPrice(p);
                            product.setUrl("");//比较难获取
                            goodsProducts.add(product);
                        }
                    }

                    color_specifications.addAll(size_specifications);
                    color_specifications.addAll(xilie_specifications);

                    allinone.setSpecifications(color_specifications.toArray(new GoodsSpecification[color_specifications.size()]));
                    allinone.setAttributes(attributeList.toArray(new GoodsAttribute[attributeList.size()]));
                    allinone.setProducts(goodsProducts.toArray(new GoodsProduct[goodsProducts.size()]));

                    dataJson.add(allinone);
                }
            }
            File file = new File(String.format("/goods/背心/背心%s.json",i));
            FileHelper.saveData(file,JSONObject.toJSONString(dataJson));
        }
    }



    public void getPhone() throws Exception{

        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&psort=3&page=3";//第二页商品
        //网址分析
        /*keyword:关键词（京东搜索框输入的信息）
         * enc：编码方式（可改动:默认UTF-8）
         * psort=3 //搜索方式  默认按综合查询 不给psort值
         * page=分业（不考虑动态加载时按照基数分业，每一页30条，这里就不演示动态加载）
         * 注意：受京东商品个性化影响，准确率无法保障
         * */
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        //doc获取整个页面的所有数据
        Elements ulList = doc.select("ul[class='gl-warp clearfix']");
        Elements liList = ulList.select("li[class='gl-item']");
        //循环liList的数据
        for (Element item : liList) {
            //排除广告位置
            if (!item.select("span[class='p-promo-flag']").text().trim().equals("广告")) {
                //如果向存到数据库和文件里请自行更改
                System.out.println(item.select("div[class='p-name p-name-type-2']").select("em").text());//打印商品标题到控制台
            }
        }
    }


}
