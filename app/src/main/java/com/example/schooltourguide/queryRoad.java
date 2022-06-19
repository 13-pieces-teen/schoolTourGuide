package com.example.schooltourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schooltourguide.DBHelper.MapDBHelper;
import com.example.schooltourguide.algorithm.Dijkstra;
import com.example.schooltourguide.algorithm.Graph;
import com.example.schooltourguide.algorithm.PathDfs;
import com.example.schooltourguide.entity.Road;
import com.example.schooltourguide.entity.Sight;

import java.util.ArrayList;
import java.util.List;

public class queryRoad extends AppCompatActivity {
    private MapDBHelper mapDBHelper;
    private EditText et_first;
    private EditText et_last;
    private Button btn_all;
    private Button btn_shortest;

    private String allRoads = "";//弹出查询到的道路信息
    private String allRoadsTitle = "所有路径查询\n";
    private String shortestRoad ;
    private String shortestRoadTitle = "最短路径查询\n";

    private List<Road> roadList = new ArrayList<Road>();
    private List<Sight> sightList = new ArrayList<Sight>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_road);


        initView();//初始化
        mapDBHelper = new MapDBHelper(this);//很重要，之前忘了实例化，会导致空指针

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAllRoads();

                AlertDialog textTips = new AlertDialog.Builder(queryRoad.this)
                        .setTitle(allRoadsTitle)
                        .setMessage(allRoads)
                        .create();
                textTips.show();

                allRoadsTitle = "所有路径查询\n";
                allRoads = "";
            }
        });

        btn_shortest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findShortestRoad();
                AlertDialog textTips = new AlertDialog.Builder(queryRoad.this)
                        .setTitle(shortestRoadTitle)
                        .setMessage(shortestRoad)
                        .create();
                textTips.show();
                shortestRoad = "";
            }
        });

    }

    private void findAllRoads()
    {
        String first = et_first.getText().toString();
        String last = et_last.getText().toString();
        sightList = mapDBHelper.getSightInfo();
        roadList = mapDBHelper.getRoadInfo();
        int lenSight = sightList.size();
        int lenRoad = roadList.size();

        Sight sight1 = mapDBHelper.getSightFromID(Integer.parseInt(first));
        Sight sight2 = mapDBHelper.getSightFromID(Integer.parseInt(last));

        PathDfs pathDfs = new PathDfs(true);
        for (int i=0; i<lenRoad; i++)
        {
            String name1 = mapDBHelper.getNameFromID(roadList.get(i).getFirstID());
            String name2 = mapDBHelper.getNameFromID(roadList.get(i).getLastID());
            pathDfs.addEdge(name1, name2);
        }
        List<String> findAllPath = pathDfs.findAllPath(sight1.getSight_name(), sight2.getSight_name());

        allRoads += "从" + sight1.getSight_name() + "到" + sight2.getSight_name()  + "共有" + findAllPath.size() + "条路径\n";
        for (int i =0; i<findAllPath.size(); i++)
        {
            allRoads += findAllPath.get(i) + "\n";
        }
        System.out.println(findAllPath);
    }


    private void findShortestRoad()
    {
        int first = Integer.parseInt(et_first.getText().toString());
        int last = Integer.parseInt(et_last.getText().toString());
        Dijkstra<String> directNet = new  Dijkstra<>(20);
        sightList = mapDBHelper.getSightInfo();
        roadList = mapDBHelper.getRoadInfo();

        int lenSight = sightList.size();
        int lenRoad = roadList.size();

        Sight sight1 = mapDBHelper.getSightFromID(first);
        Sight sight2 = mapDBHelper.getSightFromID(last);

        for (int i=0; i<lenSight; i++)
        {
            String ver = sightList.get(i).getSight_name();
            directNet.addVertex(ver);
        }

        for (int i=0; i<lenRoad; i++)
        {
            String name1 = mapDBHelper.getNameFromID(roadList.get(i).getFirstID());
            String name2 = mapDBHelper.getNameFromID(roadList.get(i).getLastID());
            int wei = roadList.get(i).getRoadLen();
            directNet.addEdge(name1, name2, wei);
        }
        directNet.displayGraph();
        directNet.dijkstra(sight1.getSight_name(), sight2.getSight_name());
        shortestRoad += directNet.getComeOutDij();
    }


    private void initView() {
        et_first = findViewById(R.id.et_first);
        et_last = findViewById(R.id.et_last);
        btn_all = findViewById(R.id.btn_all);
        btn_shortest = findViewById(R.id.btn_shortest);
    }
}