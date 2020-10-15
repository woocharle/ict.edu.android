package com.ict.ex39_fregment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Fragment 상속받아야 Fragment
// layout-sub-frag.xml과 연결(인플레이터)
// Fragment는  Activity안에 존재하는 잔은 액티비티(조각)
public class MyFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* 내용보기*/
        /* return inflater.inflate(R.layout.sub_frag, container);*/
        /* https://developer.android.com/guide/components/fragments */
        /* 이벤트처리할때 */
        ViewGroup vg  = (ViewGroup)inflater.inflate(R.layout.sub_frag, container);
        CheckBox checkBox = vg.findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 현재 변경하고자 하는 뷰는 MainActivity에 존재하므로
                MainActivity activity = (MainActivity)getActivity();
                if(b){
                    activity.imageView.setImageResource(R.drawable.dream03);
                }else{
                    activity.imageView.setImageResource(R.drawable.dream01);
                }
            }
        });
        return vg;
    }
}
