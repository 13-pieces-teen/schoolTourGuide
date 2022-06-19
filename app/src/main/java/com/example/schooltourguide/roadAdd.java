package com.example.schooltourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooltourguide.DBHelper.MapDBHelper;
import com.example.schooltourguide.entity.Road;
import com.example.schooltourguide.entity.Sight;

public class roadAdd extends AppCompatActivity {
    private MapDBHelper mapDBHelper;
    private Button btn_save;
    private EditText et_first;
    private EditText et_last;
    private EditText et_length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_add);
        initView();//初始化
        mapDBHelper = new MapDBHelper(this);//很重要，之前忘了实例化，会导致空指针

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkUIInput()) {// 界面输入验证
                    return;
                }
                Road road = getRoadFromUI();
                // 插入数据库中
                long rowId;
                rowId = mapDBHelper.addRoad(road);
                Log.i("数据库", "数据库加入路径");
                if (rowId != -1) {
                    Toast.makeText(roadAdd.this, "添加成功！！！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(roadAdd.this, MainActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                    startActivity(intent);
                } else {
                    Toast.makeText(roadAdd.this, "添加失败！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //收集界面输入的数据，并将封装成road对象
    private Road getRoadFromUI()
    {
        //景点表结构（景点编号，景点名称，景点介绍，x坐标，y坐标）
        int first = Integer.parseInt(et_first.getText().toString());
        int last = Integer.parseInt(et_last.getText().toString());
        int length = Integer.parseInt(et_length.getText().toString());

        Road road = new Road(first,last,length);
        return road;
    }

    private void initView() {
        et_first = findViewById(R.id.et_first);
        et_last = findViewById(R.id.et_last);
        et_length = findViewById(R.id.et_length);
        btn_save = findViewById(R.id.btn_save);
    }

    //检查有没有正确输入，不符合不得保存
    private boolean checkUIInput() {
        String first = et_first.getText().toString();
        String last = et_last.getText().toString();
        String length = et_length.getText().toString();

        String message = null;
        View invadView = null;
        if (first.trim().length() == 0) {
            message = "起点不能为空！";
            invadView = et_first;
        } else if (last.length() == 0)
        {
            message = "未填入生产日期！";
            invadView = et_last;
        }else if(length.length() == 0)
        {
            message = "未填入生产日期！";
            invadView = et_length;
        }

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (invadView != null)
                invadView.requestFocus();
            return false;
        }         return true;
    }
}
