package com.ict.ex50_cardview;

import android.view.View;

public interface MyListener {
    public void onItemClick(MyAdapter.ViewHolder holder, View view, int position);
}
