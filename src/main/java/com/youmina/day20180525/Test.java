package com.youmina.day20180525;

/**
 * 实现一亿的整数倒序输出
 * @author zhangxinhe
 * @date 2018/5/25 17:27
 */
public class Test {

    //1.生成一亿个整数 通过计算大约95.3M.
    public static int[] bitmapSort(int[] inputArray) {

        // 1.获取传统数组的最大值.即位图数组的长度
        long findMaxBegin = System.currentTimeMillis();
        int maxValue = inputArray[0];
        for (int i = 0; i < inputArray.length; ++i) {
            if (maxValue < inputArray[i]) {
                maxValue = inputArray[i];
            }
        }
        System.out.println("1.找最大值:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        //2.将传统数组转换成位图数组
        long bitmapBegin = System.currentTimeMillis();
        byte[] bitmap = new byte[maxValue+1];
        //bitmap[arr[i]]=1
        for (int i = 0; i < inputArray.length; ++i) {
            bitmap[inputArray[i]] = 1;  // 转换规则：传统数组的值就是位图数组的值需要置1的下标
        }
        System.out.println("2.构建位图:" + (System.currentTimeMillis() - bitmapBegin) + "ms");

        //3.倒序遍历位图 ,如果值为1则输出 . 相当于返回一个排序好的传统数组。
        long descBegin = System.currentTimeMillis();
        int[] result = new int[inputArray.length];
        int index=0;
        for (int i = bitmap.length - 1; i >= 0& index < result.length; i--) {
            if (bitmap[i] == 1) {
                result[index++] = i;
            }
        }
        System.out.println("3.倒序输出:" + (System.currentTimeMillis() - descBegin) + "ms");
        System.out.println("4.累计耗时:" + (System.currentTimeMillis() - findMaxBegin) + "ms");

        return result;

    }

    public static void main(String[] args) {
        // 设置传统数组的长度
        int inputArray[] = new int[10000000];
        // 产生随机数的范围0~maxNum
        for (int i = 0; i < inputArray.length; ++i) {
            inputArray[i] = (int) (Math.random() * inputArray.length);
        }
        //获取Top100
        int[] result = Test.bitmapSort(inputArray);
        for (int i = 0; i <100; i++) {
            System.out.print(result[i]+",");
        }


        String s = "商品名称：ZX 夹克外套男加绒加厚秋冬季新款男装衣服青年运动休闲夹克衫男士韩版修身秋天连帽棒球服秋装褂子短上衣 7743灰色 3XL";

                String[] strings = s.split("：");


        System.out.println( String.format("/goods%s.json",1));


        String url = "https://search.jd.com/Search?keyword=%E8%A1%A3%E6%9C%8D&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&page=1&s=1&click=0";

        int m = 1;
        for(int i = 1; i< 5 ;i ++) {//i 第几页
            if(i > 1){
                url = url.replaceAll("page=" + (m-2), "page=" + m);
                url = url.replaceAll("s=" + (53 * (i - 2) + 1), "s=" + (53 * (i - 1) + 1));
            }
            m += 2;
            System.out.println(url);
        }
    }
}
