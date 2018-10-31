package com.allure.shopping.modules.model;

public class CatList {

    private String catId;

    private String catName;

    private String fontColor;

    private String icon;

    private String pressIcon;

    private int type;

    public CatList(String catId, String catName, int type) {
        this.catId = catId;
        this.catName = catName;
        this.type = type;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPressIcon() {
        return pressIcon;
    }

    public void setPressIcon(String pressIcon) {
        this.pressIcon = pressIcon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
