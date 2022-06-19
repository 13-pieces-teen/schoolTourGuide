package com.example.schooltourguide.fragment_menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.schooltourguide.DBHelper.MapDBHelper;
import com.example.schooltourguide.R;
import com.example.schooltourguide.entity.Road;
import com.example.schooltourguide.entity.Sight;
import com.example.schooltourguide.queryRoad;
import com.example.schooltourguide.roadAdd;
import com.example.schooltourguide.roadUpdate;
import com.example.schooltourguide.sightUpdate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_map#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_map extends Fragment {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_10;

    private String sightTitle = "景点信息";
    private String sightInfo;
    private String sightID;
    private String sightName;
    private String sightIntro;

    private FloatingActionButton fab_add_site;//悬浮菜单按钮-添加景点
    private FloatingActionButton fab_add_road;//添加路段
    private FloatingActionButton fab_query;//查询信息
    private FloatingActionButton fab_updateRoad;
    private MapDBHelper mapDBHelper;
    private List<Sight> sightList = new ArrayList<Sight>();
    private List<Road> roadList = new ArrayList<Road>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_map() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_map.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_map newInstance(String param1, String param2) {
        fragment_map fragment = new fragment_map();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mapDBHelper = new MapDBHelper(getActivity());//很重要，之前忘了实例化，会导致空指针
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initView();

        fab_add_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), sightUpdate.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        fab_add_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), roadAdd.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        fab_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), queryRoad.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        fab_updateRoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), roadUpdate.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
                startActivity(intent);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(0).getSight_id());
                sightName = sightList.get(0).getSight_name();
                sightIntro = sightList.get(0).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;

                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });

        btn_1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(0).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(1).getSight_id());
                sightName = sightList.get(1).getSight_name();
                sightIntro = sightList.get(1).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(1).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(2).getSight_id());
                sightName = sightList.get(2).getSight_name();
                sightIntro = sightList.get(2).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(2).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(3).getSight_id());
                sightName = sightList.get(3).getSight_name();
                sightIntro = sightList.get(3).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(3).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(4).getSight_id());
                sightName = sightList.get(4).getSight_name();
                sightIntro = sightList.get(4).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(4).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(5).getSight_id());
                sightName = sightList.get(5).getSight_name();
                sightIntro = sightList.get(5).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(5).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(6).getSight_id());
                sightName = sightList.get(6).getSight_name();
                sightIntro = sightList.get(6).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(6).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(7).getSight_id());
                sightName = sightList.get(7).getSight_name();
                sightIntro = sightList.get(7).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(7).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(8).getSight_id());
                sightName = sightList.get(8).getSight_name();
                sightIntro = sightList.get(8).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(8).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });

        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sightList = mapDBHelper.getSightInfo();
                sightID = String.valueOf(sightList.get(9).getSight_id());
                sightName = sightList.get(9).getSight_name();
                sightIntro = sightList.get(9).getSight_intro();
                sightInfo = "景点编号： " + sightID + "\n景点名称： " + sightName + "\n景点简介：" + sightIntro;
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle(sightTitle)
                        .setMessage(sightInfo)
                        .create();
                textTips.show();
            }
        });
        btn_10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(sightList.get(9).getSight_id());
                return true;   //表示不会在触发点击事件（此事已经消费）
            }
        });



    }




    private void showDialog(int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示：");
        builder.setMessage("是否删除该景点");
        builder.setIcon(R.drawable.ic_baseline_alarm_24);
        builder.setCancelable(true);            //点击对话框以外的区域是否让对话框消失

        //设置正面按钮
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "你点击了删除", Toast.LENGTH_SHORT).show();
                mapDBHelper.deleteRoadALL(id);
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "你点击了取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();      //创建AlertDialog对象
        //对话框显示的监听事件
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.e("dialog", "对话框显示了");
            }
        });
        //对话框消失的监听事件
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.e("dialog", "对话框消失了");
            }
        });
        dialog.show();
    }

    //初始化
    private void initView() {
        fab_add_site = getView().findViewById(R.id.fab_add_site);
        fab_add_road = getView().findViewById(R.id.fab_add_road);
        fab_query =  getView().findViewById(R.id.fab_query);
        fab_updateRoad = getView().findViewById(R.id.fab_updateRoad);
        btn_1 = getView().findViewById(R.id.btn_1);
        btn_2 = getView().findViewById(R.id.btn_2);
        btn_3 = getView().findViewById(R.id.btn_3);
        btn_4 = getView().findViewById(R.id.btn_4);
        btn_5 = getView().findViewById(R.id.btn_5);
        btn_6 = getView().findViewById(R.id.btn_6);
        btn_7 = getView().findViewById(R.id.btn_7);
        btn_8 = getView().findViewById(R.id.btn_8);
        btn_9 = getView().findViewById(R.id.btn_9);
        btn_10 = getView().findViewById(R.id.btn_10);

    }
}