package com.ict.ex48_cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*보통 어댑터는 어댑터 상속을 받지만 RecyclerView.Adapter 상속을 받아야 한다.*/
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
    implements OnPersonitemClickListener{

    //어댑터에 들어갈 정보
    ArrayList<VO> items = new ArrayList<>();

    // 리스너 만들기-1
    OnPersonitemClickListener listener;

    // 리스너 만들기-2
    public void setOnItemClickListener(OnPersonitemClickListener listener){
        this.listener = listener;
    }
    // 리스너 만들기-3 ( implements OnPersonitemClickListener Override)
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null){
            listener.onItemClick(holder, view,position);
        }
    }

    // 인플레이트 한다.(ViewHolder)
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        // 아래 만든 클래스를 이용한것이다.
        return new ViewHolder(itemView, this);
    }
    /*VO가 생성해서 해당 위치에 holder의 위치에 넣어준다.*/
    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        VO vo = items.get(position);
        // ViewHolder 클래스안에 해당 메소드 만들기
        holder.setItem(vo);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 정보를 받아서 items에 넣기
    public void addItem(VO vo){ items.add(vo);}

    // 위치값을 받아서 정보를 꺼내기
    public VO getItem(int position){return items.get(position);}

    // 뷰를 받아서 처리하는 클래스
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, phone;
        public ViewHolder(@NonNull View itemView, final OnPersonitemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone=itemView.findViewById(R.id.phone);

            // 리스너 이벤트 처리
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
            phone.setText(vo.getPhone());
        }
    }
}
