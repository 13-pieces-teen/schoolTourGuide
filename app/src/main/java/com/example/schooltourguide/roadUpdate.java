package com.example.schooltourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooltourguide.DBHelper.MapDBHelper;
import com.example.schooltourguide.entity.Road;
import com.example.schooltourguide.entity.Sight;

import java.util.ArrayList;
import java.util.List;

public class roadUpdate extends AppCompatActivity {

    private Button btn_del;
    private Button btn_update_road;
    private EditText et_first;
    private EditText et_last;
    private EditText et_length;
    private MapDBHelper mapDBHelper;
    private List<Sight> sightList = new ArrayList<Sight>();
    private List<Road> roadList = new ArrayList<Road>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_update);
        mapDBHelper = new MapDBHelper(this);//很重要，之前忘了实例化，会导致空指针

        initView();

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(et_first.getText().toString());
                int end = Integer.parseInt(et_last.getText().toString());
                mapDBHelper.deleteRoadWithIDS(start,end);
                Toast.makeText(roadUpdate.this, "删除成功", Toast.LENGTH_SHORT).show();
            }
        });

        btn_update_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Integer.parseInt(et_first.getText().toString());
                int end = Integer.parseInt(et_last.getText().toString());
                int len = Integer.parseInt(et_length.getText().toString());
                mapDBHelper.updateRoadWithID(start, end, len);
                Toast.makeText(roadUpdate.this, "更新成功", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initView() {
        btn_del = findViewById(R.id.btn_del);
        btn_update_road = findViewById(R.id.btn_update_road);
        et_first = findViewById(R.id.et_first);
        et_last = findViewById(R.id.et_last);
        et_length = findViewById(R.id.et_length);
    }
}