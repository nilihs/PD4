package com.myapplicationdev.android.pd4;

import java.io.Serializable;

public class Date implements Serializable {

    private int id;
    private String dateName;
    private String dateTitle;
    private String date;

    public Date(int id, String dateName, String dateTitle, String date) {
        this.id = id;
        this.dateName = dateName;
        this.dateTitle = dateTitle;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
