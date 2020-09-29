package com.ict.ex49_cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    implements MyListener{
    ArrayList<VO> list = new ArrayList<>();
    // 리스너-1
    MyListener myListener;
    // 리스너-2
    public void setOnItemClickListener(MyListener myListener){
        this.myListener = myListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(itemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // 항목 추가
    public void addItem(VO vo){
        list.add(vo);
    }

    // 위치값을 받아서  해당 위치에 존재하는 정보 리턴
    public VO getItem(int position){
        return list.get(position);
    }
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
           if(myListener != null){
               myListener.onItemClick(holder, view, position);
           }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1, textView2, textView3;
        ImageView imageView1;

        public ViewHolder(@NonNull View itemView, final MyListener myListener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            imageView1 =  itemView.findViewById(R.id.imageView1);
            // 리스너-3
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(myListener != null){
                        myListener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void  setItem(VO vo){
            textView1.setText(vo.getName());
            textView2.setText(vo.getPrice());
            textView3.setText(vo.getEvent());
            imageView1.setImageResource(vo.getResId());
        }
    }
}
