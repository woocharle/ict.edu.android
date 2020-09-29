package com.ict.ex46_viewpager_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    Fragment_A fragment_1;
    Fragment_B fragment_2;
    Fragment_C fragment_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.pager);
        // setOffscreenPageLimit를 통하여
        // 좌우 몇개의 페이지를 그려놓고(준비) 있을지 설정해 줄 수 있습니다.
        pager.setOffscreenPageLimit(3);

        // 뷰페이져의 내용은 어탭터를 이용한다.
        MyPagerAdater adater = new MyPagerAdater(getSupportFragmentManager(),0);

        // 프레그먼튼 생성 후 어댑터에 넣기
        fragment_1 = new Fragment_A();
        fragment_2 = new Fragment_B();
        fragment_3 = new Fragment_C();

        adater.addItem(fragment_1);
        adater.addItem(fragment_2);
        adater.addItem(fragment_3);

        pager.setAdapter(adater);


    }
    // 어탭터 만들기 (프레그먼트 넣어서 활용)
    class MyPagerAdater extends FragmentStatePagerAdapter{
           ArrayList<Fragment> items = new ArrayList<>();
        // 계속 오류 나면 생성자 문제
        // alter+Insert = 생성자 선택

        public MyPagerAdater(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }
        // 프레그먼트 추가하는 메소드 생성
        public void addItem(Fragment item){      items.add(item);       }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {    return "페이지"+(position+1);   }

        @NonNull
        @Override
        public Fragment getItem(int position) {   return items.get(position);      }
        @Override
        public int getCount() {          return items.size();       }
    }
}