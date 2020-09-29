package com.ict.ex51_canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 3. 내용변경
        // setContentView(R.layout.activity_main);
        setContentView(new CanvasView(this));
    }

    // 1. 내부클래스에 Canvas 만들기
    private class CanvasView extends View {
        // 2. 기본 생성자가 없어서 오류 발생
        // 3. 위에 있는 setContentView()의 내용을 변경하자
        public CanvasView(Context context) {
            super(context);
            // 배경색 넣기
            setBackgroundColor(Color.rgb(255,255,0));
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            // canvas : 도화지,  paint : 붓

            Paint paint = new Paint();
            paint.setColor(Color.RED);  // 색
            paint.setStrokeWidth(10);   // 두께
            paint.setStrokeCap(Paint.Cap.ROUND);  // 끝모양

            // 선그리기 : drawLine(시작좌표, 끝좌표, paint)
            canvas.drawLine(100,100,100,200, paint);
            canvas.drawLine(100,100,200,100, paint);
            canvas.drawLine(100,200,200,200, paint);
            canvas.drawLine(200,100,200,200, paint);
           // 숙제 : 대각선 그리기
            canvas.drawLine(100,100,200,200, paint);
            canvas.drawLine(200,100,100,200, paint);

            //  사각형 : drawRect(left, top, right, bottom, paint)
            paint.setColor(Color.BLUE);
            canvas.drawRect(300,100,400,200, paint);

            // 사각형 선으로 변경
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(500,100,600,200, paint);

            // 원 : drawCircle(중심좌표, 반지름, paint)
            canvas.drawCircle(750,150,50, paint);

            // 원을 면으로
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.GRAY);

            canvas.drawCircle(750,150,30, paint);

            canvas.drawCircle(950,150,50, paint);

            // 글자 : drawText("글자" , 좌표, paint)
            // (주의 : 글자 밑이 기준선이다.)
            paint.setColor(Color.BLACK);
            paint.setTextSize(100);
            canvas.drawText("Android", 100, 400, paint);

            // 글자를 선으로
            paint.setStyle(Paint.Style.STROKE);
            paint.setTextSize(150);
            canvas.drawText("Android", 500, 400, paint);

            // 그림
            // 방법1 : Bitmap
            //         canvas.drawBitmap(bitmap, src, new RectF(), paint)
            //         new RectF(left, top, right, bottom)
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.nougat);
            canvas.drawBitmap(bitmap1, null, new RectF(20, 500, 520,1000),paint);

            // 방법2 : BitmapDrawable
            BitmapDrawable bit = (BitmapDrawable)getResources().getDrawable(R.drawable.nougat, null);
            Bitmap bitmap2 = bit.getBitmap();
            canvas.drawBitmap(bitmap1, null, new RectF(20, 1010, 520,1510),paint);
        }
   }
}