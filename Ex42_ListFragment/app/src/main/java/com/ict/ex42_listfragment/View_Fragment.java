package com.ict.ex42_listfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class View_Fragment extends Fragment {
    TextView name, addr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg =
                (ViewGroup)inflater.inflate(R.layout.view_fragment,container,false);
        name = vg.findViewById(R.id.name);
        addr = vg.findViewById(R.id.addr);
        return vg;
    }

    // List_Fragment에서 이벤트가 발생하면  실행하는 메소드
    public void userChange(String name, String addr){
        this.name.setText(name);
        this.addr.setText(addr);
    }
}
