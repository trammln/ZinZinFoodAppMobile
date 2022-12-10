package com.nhom1.models;

import java.io.Serializable;

public class Feedback implements Serializable {
    int feedbackId;
    String customerName;
    double customerRate;
    String dateTime;
    String content;
    String feedbackThumb;

    public Feedback(int feedbackId, String customerName, double customerRate, String dateTime, String content, String feedbackThumb) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.customerRate = customerRate;
        this.dateTime = dateTime;
        this.content = content;
        this.feedbackThumb = feedbackThumb;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(double customerRate) {
        this.customerRate = customerRate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeedbackThumb() {
        return feedbackThumb;
    }

    public void setFeedbackThumb(String feedbackThumb) {
        this.feedbackThumb = feedbackThumb;
    }
}
