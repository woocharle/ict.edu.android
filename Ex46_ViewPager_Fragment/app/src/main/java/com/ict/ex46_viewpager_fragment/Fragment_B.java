package com.ict.ex46_viewpager_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_B extends Fragment {
    TextView textView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg
                = (ViewGroup)inflater.inflate(R.layout.fragment_b,container,false);

        textView2 = vg.findViewById(R.id.textView2);

        return vg;
    }

    public String getTextView2() {
         return textView2.getText().toString();
    }
}
