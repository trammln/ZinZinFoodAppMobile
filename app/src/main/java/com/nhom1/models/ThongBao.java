package com.nhom1.models;

import java.io.Serializable;

public class ThongBao implements Serializable {
    int thongBaoId;
    String thongBaoThumb,thongBaoName, thongBaoContent;

    public ThongBao(int thongBaoId, String thongBaoThumb, String thongBaoName, String thongBaoContent) {
        this.thongBaoId = thongBaoId;
        this.thongBaoThumb = thongBaoThumb;
        this.thongBaoName = thongBaoName;
        this.thongBaoContent = thongBaoContent;
    }

    public int getThongBaoId() {
        return thongBaoId;
    }

    public void setThongBaoId(int thongBaoId) {
        this.thongBaoId = thongBaoId;
    }

    public String getThongBaoThumb() {
        return thongBaoThumb;
    }

    public void setThongBaoThumb(String thongBaoThumb) {
        this.thongBaoThumb = thongBaoThumb;
    }

    public String getThongBaoName() {
        return thongBaoName;
    }

    public void setThongBaoName(String thongBaoName) {
        this.thongBaoName = thongBaoName;
    }

    public String getThongBaoContent() {
        return thongBaoContent;
    }

    public void setThongBaoContent(String thongBaoContent) {
        this.thongBaoContent = thongBaoContent;
    }
}
