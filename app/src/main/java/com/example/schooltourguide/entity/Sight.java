package com.example.schooltourguide.entity;

public class Sight {
    //景点表结构（景点编号，景点名称，景点介绍，x坐标，y坐标）
    private int sight_id;
    private String sight_name;
    private String sight_intro;
    private int sight_x;
    private int sight_y;

    public Sight() {
        super();
    }

    public Sight(int sight_id, String sight_name, String sight_intro,int sight_x
            , int sight_y) {
        super();
        this.sight_id = sight_id;
        this.sight_name = sight_name;
        this.sight_intro = sight_intro;
        this.sight_x = sight_x;
        this.sight_y = sight_y;
    }



    public int getSight_id() {
        return sight_id;
    }

    public void setSight_id(int sight_id) {
        this.sight_id = sight_id;
    }

    public String getSight_name() {
        return sight_name;
    }

    public void setSight_name(String sight_name) {
        this.sight_name = sight_name;
    }

    public String getSight_intro() {
        return sight_intro;
    }

    public void setSight_intro(String sight_intro) {
        this.sight_intro = sight_intro;
    }

    public int getSight_x() {
        return sight_x;
    }

    public void setSight_x(int sight_x) {
        this.sight_x = sight_x;
    }

    public int getSight_y() {
        return sight_y;
    }

    public void setSight_y(int sight_y) {
        this.sight_y = sight_y;
    }
}
