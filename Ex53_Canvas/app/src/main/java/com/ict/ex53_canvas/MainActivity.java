package com.ict.ex53_canvas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static final int LINE = 1 , CIRCLE = 2 , RECT = 3 ;
    static int curShape = LINE;
    static int curColor = Color.BLACK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyCanvas(this));
    }
    // 메뉴 만들기
    // 방법1) 직접코딩 ,  방법2) res-menu-menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"선 그리기");
        menu.add(0,2,0,"원 그리기");
        menu.add(0,3,0,"사각형 그리기");
        SubMenu sm = menu.addSubMenu("색상변경 >> ");
        sm.add(0,4,0,"빨강");
        sm.add(0,5,0,"파랑");
        sm.add(0,6,0,"초록");
        return true;
    }
    // 메뉴 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1 : curShape = LINE; return true;
            case 2 : curShape = CIRCLE; return true;
            case 3 : curShape = RECT; return true;
            case 4 : curColor = Color.RED; return true;
            case 5 : curColor = Color.BLUE; return true;
            case 6 : curColor = Color.GREEN; return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyCanvas extends View{
        Paint paint = new Paint();

        int startX =-1, startY=-1, stopX=-1, stopY = -1 ;
        public MyCanvas(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setColor(curColor);

            switch (curShape){
                case LINE:
                    canvas.drawLine(startX,startY,stopX,stopY, paint);
                    break;
                case CIRCLE:
                    // 반지름 구하는 공식
                    int radius = (int)Math.sqrt(Math.pow(stopX-startX,2)+ Math.pow(stopY-startY,2));
                    canvas.drawCircle(startX,startY, radius, paint);
                    break;
                case RECT:
                    canvas.drawRect(startX, startX, stopX, stopY, paint);
                    break;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    startX = (int)event.getX();
                    startY = (int)event.getY();
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                break;
                case  MotionEvent.ACTION_MOVE:
                    break;
                case  MotionEvent.ACTION_UP:
                    stopX = (int)event.getX();
                    stopY = (int)event.getY();
                break;
            }
            invalidate();
            return true;
        }
    }
}