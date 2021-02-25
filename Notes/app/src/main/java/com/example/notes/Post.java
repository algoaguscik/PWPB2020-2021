package com.example.notes;

public class Post {
    private int id;
    private String name;
    private String desc;
    private String date;

    public Post(int id, String productName, String productDesc, String date) {
        this.id = id;
        this.name = productName;
        this.desc = productDesc;
        this.date = date;
    }

    public Post(String name, String desc,  String date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
