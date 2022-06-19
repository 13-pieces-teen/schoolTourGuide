package com.example.schooltourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.schooltourguide.fragment_menu.fragment_map;
import com.example.schooltourguide.fragment_menu.fragment_setting;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imag1,imag2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a();
        init();
        imag1.setOnClickListener(this::onClick);
        imag2.setOnClickListener(this::onClick);
    }

    private void init() {
        imag1 = findViewById(R.id.image1);
        imag2 = findViewById(R.id.image2);
    }

    public void a() {
        List<Fragment> list=new ArrayList<>();
        list.add(0,new fragment_map());  //把之前创建的fragment添加进去
        list.add(1,new fragment_setting());
        ViewPager2 viewPager2 =findViewById(R.id.viewpager);
        viewPager2.setAdapter(new fragmentAdapter(getSupportFragmentManager(),getLifecycle(),list));

    }
    @Override
    public void onClick(View view) {
        ViewPager2 viewPager2 =findViewById(R.id.viewpager);
        switch (view.getId()){
            case R.id.image1:
                viewPager2.setCurrentItem(0,true);  //true滑动跳转，false不滑动跳转
                break;
            case R.id.image2:
                viewPager2.setCurrentItem(1,true);
        }
    }
}