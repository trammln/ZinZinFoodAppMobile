package com.nhom1.models;

import java.io.Serializable;

public class DonMua implements Serializable {
    int donmuaId;
    String proName;
    int proQuantity;
    int proRice;
    int quantityTotal;
    int totalDonMua;
    String DanhgiaDue;
    String productThumb;

    public DonMua(int donmuaId, String proName, int proQuantity, int proRice, int quantityTotal, int totalDonMua, String danhgiaDue, String productThumb) {
        this.donmuaId = donmuaId;
        this.proName = proName;
        this.proQuantity = proQuantity;
        this.proRice = proRice;
        this.quantityTotal = quantityTotal;
        this.totalDonMua = totalDonMua;
        DanhgiaDue = danhgiaDue;
        this.productThumb = productThumb;
    }

    public int getDonmuaId() {
        return donmuaId;
    }

    public void setDonmuaId(int donmuaId) {
        this.donmuaId = donmuaId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProQuantity() {
        return proQuantity;
    }

    public void setProQuantity(int proQuantity) {
        this.proQuantity = proQuantity;
    }

    public int getProRice() {
        return proRice;
    }

    public void setProRice(int proRice) {
        this.proRice = proRice;
    }

    public int getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public int getTotalDonMua() {
        return totalDonMua;
    }

    public void setTotalDonMua(int totalDonMua) {
        this.totalDonMua = totalDonMua;
    }

    public String getDanhgiaDue() {
        return DanhgiaDue;
    }

    public void setDanhgiaDue(String danhgiaDue) {
        DanhgiaDue = danhgiaDue;
    }

    public String getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(String productThumb) {
        this.productThumb = productThumb;
    }
}
