package com.nhom1.models;

public class PayProduct {
    int productId;
    String productThumb;
    String productName;
    double productPrice;
    int productQuantity;

    //    Constructor

    public PayProduct(int productId,String productThumb, String productName, double productPrice, int productQuantity) {
        this.productId =productId;
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

//    Getter and Setter

    public void setProductId(int productId) {this.productId = productId;}
    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }
    public void setProductQuantity(int productQuantity) {this.productQuantity = productQuantity;}
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductId() {return productId;}
    public String getProductThumb() {
        return productThumb;
    }
    public String getProductName() {
        return productName;
    }
    public double getProductPrice() {
        return productPrice;
    }
    public int getProductQuantity() {
        return productQuantity;
    }
}
