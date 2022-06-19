package com.example.schooltourguide.fragment_menu;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schooltourguide.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_setting extends Fragment {

    private TextView tv_group;//开发人员
    private TextView tv_feedback;//反馈
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_setting.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_setting newInstance(String param1, String param2) {
        fragment_setting fragment = new fragment_setting();
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

    }
    @Override
    public void onStart() {
        super.onStart();
        initView();
        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此处要想在fragment里添加弹窗，需要把context里的内容改成getActivity()
                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle("意见与反馈：")
                        .setMessage("这是我独立制作的实训项目APP，还有许多未完善的部分\n" )
                        .create();
                textTips.show();
            }
        });

        tv_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog textTips = new AlertDialog.Builder(getActivity())
                        .setTitle("开发人员：")
                        .setMessage("由刘莹独立自主设计实训APP内容需求并自主完善APP功能\n" +
                                "学号：202021004091\n" +
                                "班级：智能2002\n" +
                                "来自南京工业大学")
                        .create();
                textTips.show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


    private void initView() {
        tv_feedback = getView().findViewById(R.id.tv_feedback);
        tv_group = getView().findViewById(R.id.tv_group);
    }

}