package com.example.inkpslista;

public class Listname {

    private int nameId;
    private String listName;


    public Listname(int nameId, String listName) {
        this.nameId = nameId;
        this.listName = listName;
    }


    public int getNameId() {
        return nameId;
    }

    public String getListName() {
        return listName;
    }


    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }


    @Override
    public String toString() {
        return "Listname{" +
                "nameId=" + nameId +
                ", listName='" + listName + '\'' +
                '}';
    }
}




