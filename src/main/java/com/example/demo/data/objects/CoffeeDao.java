package com.example.demo.data.objects;

public class CoffeeDao {

    private String type;
    private int id;

    public CoffeeDao() {
    }

    public CoffeeDao(String type,int id)
    {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String value) {
        this.type = value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int value) {
        this.id = value;
    }

}
