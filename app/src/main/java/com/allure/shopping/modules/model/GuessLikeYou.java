package com.allure.shopping.modules.model;

/**
 * 猜你喜欢实体类
 */
public class GuessLikeYou {

    private String goodsName;//商品名称

    private String goodsId;//商品编号

    private String imgUrl;//缩略图

    private String saleNum;//销售量

    private String price;//金额

    private String groupPrice;

    private String activityImg;

    private String priceIcon;

    public String getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(String groupPrice) {
        this.groupPrice = groupPrice;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getPriceIcon() {
        return priceIcon;
    }

    public void setPriceIcon(String priceIcon) {
        this.priceIcon = priceIcon;
    }

    public GuessLikeYou(String goodsName, String goodsId, String imgUrl, String saleNum, String price) {
        this.goodsName = goodsName;
        this.goodsId = goodsId;
        this.imgUrl = imgUrl;
        this.saleNum = saleNum;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }
}
