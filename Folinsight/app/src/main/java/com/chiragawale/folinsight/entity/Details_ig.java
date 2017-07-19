package com.chiragawale.folinsight.entity;

/**
 * Created by chira on 7/17/2017.
 */

public class Details_ig {
    int dataFor_code;
    String date;
    double aLikesPer,aCommentsPer;

    public Details_ig(int dataFor_code,  double aLikesPer, double aCommentsPer) {
        this.dataFor_code = dataFor_code;
        this.aLikesPer = aLikesPer;
        this.aCommentsPer = aCommentsPer;
    }

    public int getDataFor_code() {
        return dataFor_code;
    }

    public void setDataFor_code(int dataFor_code) {
        this.dataFor_code = dataFor_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getaLikesPer() {
        return aLikesPer;
    }

    public void setaLikesPer(double aLikesPer) {
        this.aLikesPer = aLikesPer;
    }

    public double getaCommentsPer() {
        return aCommentsPer;
    }

    public void setaCommentsPer(double aCommentsPer) {
        this.aCommentsPer = aCommentsPer;
    }
}

