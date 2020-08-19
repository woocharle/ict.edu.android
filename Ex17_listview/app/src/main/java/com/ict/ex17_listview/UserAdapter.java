package com.ict.ex17_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<VO> list;
    LayoutInflater inflater;

    public UserAdapter(Context context, int layout, ArrayList<VO> list){
        this.context = context;
        this.layout = layout;
        this.list = list;

        /* user_item.xml 정보를 메모리에 적재시키고, 자바에서 해당 메모리에 적재된 정보를
           사용할 수 있도록 해야 하는 데, 이것을 'Inflater'라고 한다.*/

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.txt1);
        ImageView imageView = view.findViewById(R.id.image1);

        final VO vo = list.get(i);
        textView.setText(vo.getImgName());
        imageView.setImageResource(vo.getResID());

        //이벤트 처리
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, vo.getImgName(),Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
