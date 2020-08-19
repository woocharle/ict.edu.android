package com.ict.ex18_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    Integer[] imgId = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                        R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
                        R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
                      };
    String[] imgName = {"그림1", "그림2", "그림3", "그림4", "그림5", "그림6"
                        ,"그림7", "그림8", "그림9"};

    MyGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);

    }

    //내부 클래스로 어댑터 만들기
    //생성자에서 레이아웃과 리스트(배열)가 별도로 필요하지 않음.
    class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter() {}
        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            // 리턴 되는 이미지
            ImageView imageView = new ImageView(context);
            //이미지 크기 설정
            imageView.setLayoutParams(new GridView.LayoutParams(400,500));
            //이미지 여백
            imageView.setPadding(5,10, 5,10);
            //이미지 배치
            imageView.setImageResource(imgId[i]);

            imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, imgName[i], Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }

        @Override
        public int getCount() { return imgId.length; }

        @Override
        public Object getItem(int i) { return imgId[i]; }

        @Override
        public long getItemId(int i) { return i; }

        // getter, setter
        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }


    }

}