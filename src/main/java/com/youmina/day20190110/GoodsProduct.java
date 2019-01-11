package com.youmina.day20190110;

import java.math.BigDecimal;

/**
 * @author zhangxinhe
 * @date 2019/1/10 17:38
 */
public class GoodsProduct {

    private String url;

    private Integer number;

    private String[] specifications;

    private BigDecimal price;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String[] specifications) {
        this.specifications = specifications;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
