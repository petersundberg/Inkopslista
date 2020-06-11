package com.example.inkpslista;

import java.util.ArrayList;

public class ListModel {

    private int id;
    private String listName;
    private ArrayList<String> itemsList;

    public ListModel(int id, String listName, ArrayList<String> itemsList) {
        this.id = id;
        this.listName = listName;
        this.itemsList = itemsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public ArrayList<String> getItemsList() {
        return itemsList;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public void setItemsList(ArrayList<String> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public String toString() {
        return "ListModel{" +
                "listName='" + listName + '\'' +
                ", itemsList=" + itemsList +
                '}';
    }
}

