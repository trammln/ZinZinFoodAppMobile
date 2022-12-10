package com.nhom1.models;

import java.io.Serializable;

public class Product implements Serializable {
    int productId;
    String productName;
    int productPrice;
    double productRate;
    int productSold;
    String productDue;
    int productQuantity;
    String productComponent;
    String productDescription;
    String productThumb;

    public Product(int productId, String productName, int productPrice, Double productRate, int productSold, String productDue, int productQuantity, String productComponent, String productDescription, String productThumb) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productRate = productRate;
        this.productSold = productSold;
        this.productDue = productDue;
        this.productQuantity = productQuantity;
        this.productComponent = productComponent;
        this.productDescription = productDescription;
        this.productThumb = productThumb;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductRate() {
        return productRate;
    }

    public void setProductRate(Double productRate) {
        this.productRate = productRate;
    }

    public int getProductSold() {
        return productSold;
    }

    public void setProductSold(int productSold) {
        this.productSold = productSold;
    }

    public String getProductDue() {
        return productDue;
    }

    public void setProductDue(String productDue) {
        this.productDue = productDue;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductComponent() {
        return productComponent;
    }

    public void setProductComponent(String productComponent) {
        this.productComponent = productComponent;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }
}

