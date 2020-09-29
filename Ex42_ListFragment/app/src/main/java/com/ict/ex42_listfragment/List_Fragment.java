package com.ict.ex42_listfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class List_Fragment extends Fragment {
    ListView list;
    String[] users = {"홍길동","임꺽정","장길산","일지매"};
    String[] addr =  {"충청도","함경도", "제주도", "전라도"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup vg =
             (ViewGroup)inflater.inflate(R.layout.list_fragment, container, false);
        list = vg.findViewById(R.id.list);

        // 리스트뷰 어댑터를 사용해야 한다.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,users);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // int i(position), long l(id)
                // 보여줄 프레그먼트 생성
                View_Fragment view_fragment =
                        (View_Fragment)getFragmentManager().findFragmentById(R.id.viewfragment);
                view_fragment.userChange("이름 : " + users[i], "주소 : " + addr[i]);
            }
        });
        return vg;
    }
}
