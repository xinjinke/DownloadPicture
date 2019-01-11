package com.youmina.day20190110;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxinhe
 * @date 2019/1/10 10:10
 */
public class JingdongShop {


    public static void main(String[] args) throws Exception {

        String url = "https://search.jd.com/Search?keyword=%E8%A1%A3%E6%9C%8D&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=1&s=1&click=0";

        int m = 1;
        for(int i = 1; i< 5 ;i ++){//i 第几页
            if(i > 1){
                url = url.replaceAll("page=" + (m-2), "page=" + m);
                url = url.replaceAll("s=" + (53 * (i - 2) + 1), "s=" + (53 * (i - 1) + 1));
            }
            m += 2;

            Document doc = Jsoup.connect(url).maxBodySize(0).get();

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
                    String name = item.select("div[class='p-name p-name-type-2']").select("em").text();

//                System.out.println("linkUrl:"+linkUrl+",img:"+img+",price:"+price+",name:"+name);

                    Document docx = Jsoup.connect("https:"+linkUrl).maxBodySize(0).get();//商品内容详情页面

                    Elements garlysList = docx.select("div.spec-items").select("ul > li");

                    List<String> gallerys = new ArrayList();
                    for(Element gallery:garlysList){
                        gallerys.add(gallery.select("img").attr("src"));
                    }

                    //顔色種類
                    List<String> colors = new ArrayList();

                    Elements colorList = docx.select("div[data-type='颜色']").select("div.dd > div");

                    for(Element color : colorList){
                        colors.add(color.attr("data-value"));
                    }

                    //尺碼大小
                    List<String> size = new ArrayList();

                    Elements sizeList = docx.select("div[data-type='尺码']").select("div.dd > div");

                    for(Element color : sizeList){
                        size.add(color.attr("data-value"));
                    }

                    System.out.println("name:"+name+"gallerys:"+gallerys+",colors:"+colors+",size:"+size);


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
                    goods.setName(name);
                    goods.setPicUrl(img);
                    goods.setCounterPrice(new BigDecimal(price));


                    List<GoodsSpecification> specifications = new ArrayList<>();
                    GoodsSpecification specification = null;
                    for(Object str : colors){
                        specification = new GoodsSpecification();
                        specification.setSpecification("顔色");
                        specification.setValue(str.toString());
                        specifications.add(specification);
                    }

                    for(Object str : size){
                        specification = new GoodsSpecification();
                        specification.setSpecification("尺码");
                        specification.setValue(str.toString());
                        specifications.add(specification);
                    }



                    GoodsAllinone allinone = new GoodsAllinone();
                    allinone.setGoods(goods);

                    allinone.setSpecifications(specifications.toArray(new GoodsSpecification[specifications.size()]));
                    allinone.setAttributes(attributeList.toArray(new GoodsAttribute[attributeList.size()]));
//                allinone.setProducts();

                   dataJson.add(allinone);
                }
            }
            File file = new File(String.format("/goods/goods%s.json",i));
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
