package com.example.spider.pojo;

import java.util.List;

public class SpiderProductDetail {
    private String titleName_result;
    private List<String> img_result;
    private List<String> detail_result;
    private List<String> price_result;
    private String SKU;

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getTitleName_result() {
        return titleName_result;
    }

    public void setTitleName_result(String titleName_result) {
        this.titleName_result = titleName_result;
    }

    public List<String> getImg_result() {
        return img_result;
    }

    public void setImg_result(List<String> img_result) {
        this.img_result = img_result;
    }

    public List<String> getDetail_result() {
        return detail_result;
    }

    public void setDetail_result(List<String> detail_result) {
        this.detail_result = detail_result;
    }

    public List<String> getPrice_result() {
        return price_result;
    }

    public void setPrice_result(List<String> price_result) {
        this.price_result = price_result;
    }
}
