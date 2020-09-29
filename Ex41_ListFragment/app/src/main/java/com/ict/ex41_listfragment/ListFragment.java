package com.ict.ex41_listfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {

    // callback 함수 사용
    public ButtonSelectionCallback callback;

    // 인터페이스를 이용한 call 함수 ( 이벤트 처리하기 위해서 )
    public interface ButtonSelectionCallback{
        public void onButtonSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      ViewGroup vg =
             (ViewGroup)inflater.inflate(R.layout.list_fragment,container,false);
        Button button1 = vg.findViewById(R.id.button1);
        Button button2 = vg.findViewById(R.id.button2);
        Button button3 = vg.findViewById(R.id.button3);

           // position을 0,1,2로 주는 이유는 배열처리를 하기 위함이다.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onButtonSelected(0);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onButtonSelected(1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onButtonSelected(2);
            }
        });
      return vg;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 콜백 객체가 만들어질때 실행 해주세요
        if (context instanceof ButtonSelectionCallback){
            callback = (ButtonSelectionCallback)context;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
