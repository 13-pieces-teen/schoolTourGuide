package com.example.schooltourguide.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.schooltourguide.entity.Road;
import com.example.schooltourguide.entity.Sight;

import java.util.ArrayList;
import java.util.List;

public class MapDBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;    //构造方法
    private static final String TAG = "数据库";
    //数据库的名字
    public static final String DB_NAME = "mapDataBase.db";
    //数据库表名
    private static final String TABLE_SIGHTS = "sights";
    private static final String TABLE_ROAD = "road";
    SQLiteDatabase db;
    //景点表结构（景点编号，景点名称，景点介绍，x坐标，y坐标）
    private static final String CREATE_TABLE_SIGHTS = "create table " + TABLE_SIGHTS + " " +
            "(sight_id integer primary key ," +
            "sight_name text, " +
            "sight_intro text, " +
            "sight_x integer" +
            ", sight_y integer)";
    //景点路径表结构（起点编号，终点编号，路径长度）
    private static final String CREATE_TABLE_ROAD = "create table " + TABLE_ROAD + " " +
            "(firstNum integer," +
            "lastNum integer, " +
            "roadLen integer)";


    public MapDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    public MapDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    //onCreate仅在构建数据库时调用一次，若对数据库进行修改则需要删表重试
    @Override
    public void onCreate(SQLiteDatabase db) {
        //构建数据库
        db.execSQL(CREATE_TABLE_SIGHTS);
        db.execSQL(CREATE_TABLE_ROAD);
    }

    //升级方法，删除旧表增加新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
//        onCreate(db);
    }

    //添加景点
    public long addSight(Sight s)
    {
        //get一个可写的数据库
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sight_id", s.getSight_id());
        values.put("sight_name", s.getSight_name());
        values.put("sight_intro",s.getSight_intro());
        values.put("sight_x",s.getSight_x());
        values.put("sight_y",s.getSight_y());
        return  db.insert(TABLE_SIGHTS, null, values);
    }

    //添加路径
    public long addRoad(Road r)
    {
        //get一个可写的数据库
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstNum", r.getFirstID());
        values.put("lastNum", r.getLastID());
        values.put("roadLen",r.getRoadLen());
        return  db.insert(TABLE_ROAD, null, values);
    }

    //通过id更新景点
    public int updateSightWithID(Sight sight, int id) {
        ContentValues values = new ContentValues();
        values.put("sight_name", sight.getSight_name());
        values.put("sight_intro", sight.getSight_intro());

        System.out.println("通过id更新景点成功！！！");
        return db.update(TABLE_SIGHTS, values, "sight_id" + "=?", new String[]{String.valueOf(id)});
    }

    //通过id更新道路长度
    public int updateRoadWithid(Road road, int len) {
        ContentValues values = new ContentValues();
        values.put("roadLen", road.getRoadLen());

        System.out.println("通过id更新路径长度成功！！！");

        return db.update(TABLE_ROAD, values, "roadLen" + "=?", new String[]{String.valueOf(len)});
    }


    //用过编号id查询名称
    public Sight getSightFromID(int S_ID) {
        Sight sight = new Sight();
        //select null1 from tableName where null2=null3 group by null4 having null5 order by null6
        Cursor cursor = db.query(TABLE_SIGHTS, null, "sight_id" + "=?", new String[]{String.valueOf(S_ID)}, null, null, null, null);
        if (cursor.moveToFirst()){
            sight.setSight_name(cursor.getString(1));
            sight.setSight_intro(cursor.getString(2));
            sight.setSight_x(cursor.getInt(3));
            sight.setSight_y(cursor.getInt(4));
        }
        return sight;
    }

    //用过编号id查询名称
    public String  getNameFromID(int S_ID) {
        Sight sight = new Sight();
        //select null1 from tableName where null2=null3 group by null4 having null5 order by null6
        Cursor cursor = db.query(TABLE_SIGHTS, null, "sight_id" + "=?", new String[]{String.valueOf(S_ID)}, null, null, null, null);
        if (cursor.moveToFirst()){
            sight.setSight_name(cursor.getString(1));
            sight.setSight_intro(cursor.getString(2));
            sight.setSight_x(cursor.getInt(3));
            sight.setSight_y(cursor.getInt(4));
        }
        return sight.getSight_name();
    }

    //更新道路长度
    public void updateRoadWithID(int first,int last,int len) {
        String upgrade="update road set roadLen=? where firstNum=? and lastNum=?";
        db.execSQL(upgrade,new String[]{String.valueOf(len), String.valueOf(first), String.valueOf(last)});
        System.out.println("道路长度更新成功！！！");
    }

    //通过编号删除路径（边）
    public void deleteRoadWithID1(int id) {
        db.delete(TABLE_ROAD, "firstNum" + "=?", new String[]{String.valueOf(id)});
        System.out.println("道路删除成功！！！");
    }

    public void deleteRoadWithID2(int id) {
        db.delete(TABLE_ROAD, "lastNum" + "=?", new String[]{String.valueOf(id)});
        System.out.println("道路删除成功！！！");
    }

    public void deleteRoadALL(int id) {
        String del = "delete from road where firstNum=? or lastNum=?";
        db.execSQL(del,new String[]{String.valueOf(id),String.valueOf(id)});
        System.out.println("道路删除成功！！！");
    }


    public void deleteRoadWithIDS(int id1,int id2) {
        String del = "delete from road where firstNum=? and lastNum=?";
        db.execSQL(del,new String[]{String.valueOf(id1),String.valueOf(id2)});
        System.out.println("道路删除成功！！！");
    }




    //获取道路整个列表信息，建立无向表
    public List<Road> getRoadInfo() {
        SQLiteDatabase db = getWritableDatabase();
        List<Road> infos = new ArrayList<>();
        Road road = null;
        //select null1 from tableName where null2=null3 group by null4 having null5 order by null6
        Cursor cursor = db.query(TABLE_ROAD, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                road = new Road();
                road.setFirstID(cursor.getInt(0));
                road.setLastID(cursor.getInt(1));
                road.setRoadLen(cursor.getInt(2));
                infos.add(road);
            }
            cursor.close();
        }
        System.out.println("MapDBHelper.getRoadInfo.size->>>" + infos.size());
        return infos;
    }

    //获取景点整个列表信息，建立无向表
    public List<Sight> getSightInfo() {
        SQLiteDatabase db = getWritableDatabase();
        List<Sight> infos = new ArrayList<>();
        Sight sight = null;
        //select null1 from tableName where null2=null3 group by null4 having null5 order by null6
        Cursor cursor = db.query(TABLE_SIGHTS, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                sight = new Sight();
                sight.setSight_id(cursor.getInt(0));
                sight.setSight_name(cursor.getString(1));
                sight.setSight_intro(cursor.getString(2));
                sight.setSight_x(cursor.getInt(3));
                sight.setSight_y(cursor.getInt(4));
                infos.add(sight);
            }
            cursor.close();
        }
        System.out.println("MapDBHelper.getSightInfo.size->>>" + infos.size());
        return infos;
    }

};




