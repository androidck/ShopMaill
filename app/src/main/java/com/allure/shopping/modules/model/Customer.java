package com.allure.shopping.modules.model;

/**
 * 客服相关实体类
 */
public class Customer {

    private String icon;

    private String url;

    private String name;

    private String subtitle;

    private String shopId;

    private int number;

    private int type;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Customer(String icon, String name, String shopId, int number, int type) {
        this.icon = icon;
        this.name = name;
        this.shopId = shopId;
        this.number = number;
        this.type = type;
    }
}
