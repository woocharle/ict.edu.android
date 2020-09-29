package com.ict.ex50_cardview;

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
    MyListener listener;
    public void setOnItemClickListener(MyListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person,parent,false);
        return new ViewHolder(itemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        VO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(VO vo){
        list.add(vo);
    }
    // 위치값을 받아서 해당 위치에 있는 정보를 되돌려 준다.
    public VO getItem(int position){
        return list.get(position);
    }

    @Override
    public void onItemClick(MyAdapter.ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder,view,position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, birth, phone ;
        ImageView imageView1;

        public ViewHolder(@NonNull View itemView, final MyListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            birth = itemView.findViewById(R.id.birth);
            phone = itemView.findViewById(R.id.phone);
            imageView1 = itemView.findViewById(R.id.imageView1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(VO vo){
            name.setText(vo.getName());
            birth.setText(vo.getBirth());
            phone.setText(vo.getPhone());
            imageView1.setImageResource(vo.getResId());
        }
    }
}
