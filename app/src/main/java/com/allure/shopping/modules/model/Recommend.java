package com.allure.shopping.modules.model;

/**
 * 推荐实体类
 */
public class Recommend {

    private String linkId;
    private String title;
    private String imgUrl;
    private String htmlImgUrl;
    private String price;
    private String priceIcon;
    private String saleNum;
    private int type;
    private String activityImg;

    public Recommend(String linkId, String title, String imgUrl, String htmlImgUrl, String price, String saleNum) {
        this.linkId = linkId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.htmlImgUrl = htmlImgUrl;
        this.price = price;
        this.saleNum = saleNum;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHtmlImgUrl() {
        return htmlImgUrl;
    }

    public void setHtmlImgUrl(String htmlImgUrl) {
        this.htmlImgUrl = htmlImgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceIcon() {
        return priceIcon;
    }

    public void setPriceIcon(String priceIcon) {
        this.priceIcon = priceIcon;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }
}
