
package com.nhom1.models;

public class Sanphamyeuthich {
    String spytName;
    int spytPicture;
    int spytPrice;
    //String productSold;
    // Khong bi loi
    public Sanphamyeuthich(String spytName, int spytPicture, int spytPrice, String productSold) {
        this.spytName = spytName;
        this.spytPicture = spytPicture;
        this.spytPrice = spytPrice;
        //this.productSold = productSold;
    }

    public String getProductName() {
        return spytName;
    }

    public void setProductName(String productName) {
        this.spytName = productName;
    }

    public int getProductPicture() {
        return spytPicture;
    }

    public void setProductPicture(int productPicture) {
        this.spytPicture = productPicture;
    }

    public int getProductPrice() {
        return spytPrice;
    }

    public void setProductPrice(int productPrice) {
        this.spytPrice = productPrice;
    }

   /* public String getProductSold() {
        return productSold;
    }

    public void setProductSold(String productSold) {
        this.productSold = productSold;
    }*/
}
