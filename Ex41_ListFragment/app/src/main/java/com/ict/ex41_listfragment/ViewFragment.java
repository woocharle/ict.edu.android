package com.ict.ex41_listfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ViewFragment extends Fragment {
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg =
            (ViewGroup)inflater.inflate(R.layout.view_fragment,container,false);
            imageView = vg.findViewById(R.id.imageView);
        return vg;
    }

    // 이미지를 채우는 메소드
    public void setImageView(int resId){
        //이미지 뷰에 이미지 넣기
        imageView.setImageResource(resId);
    }
}
