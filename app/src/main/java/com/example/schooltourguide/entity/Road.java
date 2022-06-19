package com.example.schooltourguide.entity;

public class Road {
    //景点路径表结构（起点编号，终点编号，路径长度）
    private int firstID;
    private int lastID;
    private int roadLen;

    public Road() {
        super();
    }

    public Road(int firstID, int lastID, int roadLen) {
        this.firstID = firstID;
        this.lastID = lastID;
        this.roadLen = roadLen;
    }

    public int getFirstID() {
        return firstID;
    }

    public void setFirstID(int firstID) {
        this.firstID = firstID;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    public int getRoadLen() {
        return roadLen;
    }

    public void setRoadLen(int roadLen) {
        this.roadLen = roadLen;
    }
}
