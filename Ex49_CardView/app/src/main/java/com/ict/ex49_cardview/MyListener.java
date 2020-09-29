package com.ict.ex49_cardview;

import android.view.View;

public interface MyListener {
    public void onItemClick(MyAdapter.ViewHolder holder, View view, int position);
}
