package com.example.spider.pojo;

public class SpiderResult {
    private int id;
    private String img_result;
    private String price_result;
    private String detail_result;
    private String other_result;
    private String titleName_result;
    private String linkHref_result;

    public String getLinkHref_result() {
        return linkHref_result;
    }

    public void setLinkHref_result(String linkHref_result) {
        this.linkHref_result = linkHref_result;
    }

    public String getTitleName_result() {
        return titleName_result;
    }

    public void setTitleName_result(String titleName_result) {
        this.titleName_result = titleName_result;
    }

    public String getImg_result() {
        return img_result;
    }

    public void setImg_result(String img_result) {
        this.img_result = img_result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice_result() {
        return price_result;
    }

    public void setPrice_result(String price_result) {
        this.price_result = price_result;
    }

    public String getDetail_result() {
        return detail_result;
    }

    public void setDetail_result(String detail_result) {
        this.detail_result = detail_result;
    }

    public String getOther_result() {
        return other_result;
    }

    public void setOther_result(String other_result) {
        this.other_result = other_result;
    }
}
