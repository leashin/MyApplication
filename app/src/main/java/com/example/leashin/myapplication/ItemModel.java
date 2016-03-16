package com.example.leashin.myapplication;

import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by leashin on 2016/3/13.
 */
@Table(name = "items")
public class ItemModel {
    private int id;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
