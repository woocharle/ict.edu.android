package com.ict.ex41_listfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

// ListFragment에 존재하는 인터페이스를 오버라이딩 한다.
public class MainActivity extends AppCompatActivity implements ListFragment.ButtonSelectionCallback{
    ListFragment listFragment;
    ViewFragment viewFragment;
    // 이미지 배열
    int[] images = {R.drawable.one, R.drawable.two, R.drawable.three };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        // layout-activity.main.xml에  fragment에 존재하는 아이디
        listFragment = (ListFragment)manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment)manager.findFragmentById(R.id.viewFragment);
    }

    @Override
    public void onButtonSelected(int position) {
        viewFragment.setImageView(images[position]);
    }
}