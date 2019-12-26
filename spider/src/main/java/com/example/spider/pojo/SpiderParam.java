package com.example.spider.pojo;

public class SpiderParam {
    private String url;
    private String innerLabel;
    private String outerLabel;
    private String idOrClassname;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInnerLabel() {
        return innerLabel;
    }

    public void setInnerLabel(String innerLabel) {
        this.innerLabel = innerLabel;
    }

    public String getOuterLabel() {
        return outerLabel;
    }

    public void setOuterLabel(String outerLabel) {
        this.outerLabel = outerLabel;
    }

    public String getIdOrClassname() {
        return idOrClassname;
    }

    public void setIdOrClassname(String idOrClassname) {
        this.idOrClassname = idOrClassname;
    }
}
