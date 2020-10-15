package com.ict.ex46_viewpager_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_A extends Fragment {
    TextView textView1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg
                = (ViewGroup)inflater.inflate(R.layout.fragment_a,container,false);
        textView1 = vg.findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mActivity = (MainActivity)getActivity();
                String msg = mActivity.Event01();
                Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
            }
        });
        return vg;
    }
}
