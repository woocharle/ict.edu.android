package com.ict.ex54_canvas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Bitmap bitmap;
    int x = 0 ;  // 좌표
    int y= 0 ;   // 좌표
    int nx = 20 ;  // 이동 간격
    int ny = 20 ;  // 이동 간격
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyCanvas(this));
    }
    private class MyCanvas extends View{
        public MyCanvas(Context context) {
            super(context);
            bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.gingerbread);
            // handler.sendEmptyMessageDelayed()와 같게 한다.
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // 움직이는 좌표를 반복처리
            if(x < 0 || x > (canvas.getWidth()-200)){
                nx = -nx;
            }
            if(y < 0 || y > (canvas.getHeight()-200)){
                ny = -ny;
            }
            x = x + nx;
            y = y + ny;
            canvas.drawBitmap(bitmap,null,
                    new RectF(x,y,x+200, y+200),null);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(20);
                            invalidate();
                        }
                    });
                }
            }).start();
        }
        // 반복적인 움직임을 주기 위해서 Handler 사용
       /*Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                invalidate();
                // what:0 => Intent의 request코드와 같음
                // delayMillis => 지연시간
                handler.sendEmptyMessageDelayed(0,100);
            }
        };*/

    }

}