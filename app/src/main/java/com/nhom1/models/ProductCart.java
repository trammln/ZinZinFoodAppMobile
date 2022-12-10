package com.nhom1.models;

import java.io.Serializable;

public class ProductCart implements Serializable {

    int productId;
    String productThumb;
    String productName;
    double productPrice;
    Integer productAmount;

    public ProductCart(int productId, String productThumb, String productName, double productPrice, Integer productAmount) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productAmount = productAmount;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

}
