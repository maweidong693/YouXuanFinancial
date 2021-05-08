package com.weiwu.youxuanfinancial.main.deal.buy;

public class TabData {

    private String title;

    public TabData(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TabData{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
