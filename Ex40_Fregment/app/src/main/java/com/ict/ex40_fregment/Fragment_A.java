package com.ict.ex40_fregment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// 2. layout에서 만든 fragment_a, b를 인플레이 해주는 Fragment를 만들어야 한다.
// Fragment를 상속 받아야 한다.
public class Fragment_A extends Fragment {
    // 이벤트 처리를 위해
    MainActivity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_a,container,false);
       return vg;
    }
    // Fragment가 activity에 올라갔을 때 getActivity()메소드를 통해서 호출한다.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity)getActivity();
    }
    // Fragment가 종료 될때
    @Override
    public void onDetach() {
        super.onDetach();
        activity = null ;
    }
}
