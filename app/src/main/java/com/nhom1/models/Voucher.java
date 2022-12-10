package com.nhom1.models;

import java.io.Serializable;

public class Voucher implements Serializable {
    int voucherId;
    String voucherName;
    String voucherCondition;
    String voucherTime;
    String voucherNote;

    public Voucher(int voucherId, String voucherName, String voucherCondition, String voucherTime, String voucherNote) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.voucherCondition = voucherCondition;
        this.voucherTime = voucherTime;
        this.voucherNote = voucherNote;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherCondition() {
        return voucherCondition;
    }

    public void setVoucherCondition(String voucherCondition) {
        this.voucherCondition = voucherCondition;
    }

    public String getVoucherTime() {
        return voucherTime;
    }

    public void setVoucherTime(String voucherTime) {
        this.voucherTime = voucherTime;
    }

    public String getVoucherNote() {
        return voucherNote;
    }

    public void setVoucherNote(String voucherNote) {
        this.voucherNote = voucherNote;
    }
}
