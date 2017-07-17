package com.chiragawale.folinsight.entity;

/**
 * Created by chira on 7/17/2017.
 */

public class Details_ig {

    String dataFor,date;
    double aLikesPer,aCommentsPer;

    public Details_ig(String dataFor,  double aLikesPer, double aCommentsPer) {
        this.dataFor = dataFor;
        this.aLikesPer = aLikesPer;
        this.aCommentsPer = aCommentsPer;
    }

    public String getDataFor() {
        return dataFor;
    }

    public void setDataFor(String dataFor) {
        this.dataFor = dataFor;
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

