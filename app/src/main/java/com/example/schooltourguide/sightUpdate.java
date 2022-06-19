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
import com.example.schooltourguide.entity.Sight;

public class sightUpdate extends AppCompatActivity {
    private MapDBHelper mapDBHelper;
    private EditText et_sightID;
    private EditText et_sightName;
    private EditText et_sightIntro;
    private EditText et_sightX;
    private EditText et_sightY;
    private Button btn_save;
    private Button btn_update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_update);

        initView();//初始化
        mapDBHelper = new MapDBHelper(this);//很重要，之前忘了实例化，会导致空指针

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkUIInput()) {// 界面输入验证
                    return;
                }
                Sight sight = getSightFromUI();
                // 插入数据库中
                long rowId;
                rowId = mapDBHelper.addSight(sight);
                Log.i("数据库", "数据库加入景点");
                if (rowId != -1) {
                    Toast.makeText(sightUpdate.this, "添加成功！！！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(sightUpdate.this, MainActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                    startActivity(intent);

                } else {
                    Toast.makeText(sightUpdate.this, "添加失败！！！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sight sight = getSightFromUI();
                mapDBHelper.updateSightWithID(sight, sight.getSight_id());
                Toast.makeText(sightUpdate.this, "更新成功！！！", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //检查有没有正确输入，不符合不得保存
    private boolean checkUIInput() {
        int sightID = Integer.parseInt(et_sightID.getText().toString());
        String sightName = et_sightName.getText().toString().trim();
        String sightIntro = et_sightIntro.getText().toString().trim();
        int sightX = Integer.parseInt(et_sightX.getText().toString());
        int sightY = Integer.parseInt(et_sightY.getText().toString());

        String message = null;
        View invadView = null;
        if (sightName.trim().length() == 0) {
            message = "请输入景点名称！";
            invadView = et_sightName;
        } else if (sightID == 0) {
            message = "请输入景点id！";
            invadView = et_sightID;
        } else if (sightX == 0 || sightY == 0) {
            message = "请输入x，y坐标";
        }else if (sightIntro.length() == 0)
        {
            message = "未填入生产日期！";
            invadView = et_sightIntro;
        }

        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (invadView != null)
                invadView.requestFocus();
            return false;
        }         return true;
    }

    //收集界面输入的数据，并将封装成sight对象
    private Sight getSightFromUI()
    {
        //景点表结构（景点编号，景点名称，景点介绍，x坐标，y坐标）
        int sightID = Integer.parseInt(et_sightID.getText().toString());
        String sightName = et_sightName.getText().toString().trim();
        String sightIntro = et_sightIntro.getText().toString().trim();
        int sightX = Integer.parseInt(et_sightX.getText().toString());
        int sightY = Integer.parseInt(et_sightY.getText().toString());

        Sight sight = new Sight(sightID,sightName,sightIntro,sightX,sightY);

        return sight;
    }


    private void initView() {
        et_sightID = findViewById(R.id.et_sightID);
        et_sightName = findViewById(R.id.et_sightName);
        et_sightIntro = findViewById(R.id.et_sightIntro);
        et_sightX = findViewById(R.id.et_sightX);
        et_sightY = findViewById(R.id.et_sightY);
        btn_save = findViewById(R.id.btn_save);
        btn_update = findViewById(R.id.btn_update);
    }
}