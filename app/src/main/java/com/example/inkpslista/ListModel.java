package com.example.inkpslista;


public class ListModel {

    private int id;
    private String item;
    private int goupId;


    public ListModel(int id, String item, int goupId) {
        this.id = id;
        this.item = item;
        this.goupId = goupId;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public int getGoupId() {
        return goupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setGoupId(int goupId) {
        this.goupId = goupId;
    }

    @Override
    public String toString() {
        return "ListModel{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", goupId=" + goupId +
                '}';
    }
}

