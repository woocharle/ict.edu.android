package com.ict.ex80_sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity implements SensorEventListener {
    SensorManager manager;
    // 나침판은 가속도와 자력 필요
    Sensor acc;  // 가속도
    Sensor mag;  // 자력
    // 필요한 변수들
    float[] mAcc;
    float[] mMag;
    float azimut;
    CustomerView customerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main5);
        customerView = new CustomerView(this);
        setContentView(customerView);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // 가속도
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            mAcc = sensorEvent.values;
        }
        if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            mMag = sensorEvent.values;
        }
        if(mAcc != null && mMag != null){
            float[] R = new float[9] ; // 회전
            float[] I= new float[9] ;  // 경사

            // 구글에서 지원하는 함수(나침판 구할 때 사용를 사용
            // 핸드폰 장치의 좌표계의 변화하는 값을 구한다.
            boolean success = SensorManager.getRotationMatrix(R,I,mAcc, mMag);
            if(success){
                // values[0] =>Z축, values[1] =>X축, values[2] =>Y축
                float orientaion[] = new float[3];
                SensorManager.getOrientation(R,orientaion);
                azimut = orientaion[0];  // 방위각
            }
        }
        // 값이 변화면 그림도 다시 그린다.

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {}

    // Canvas를 이용해서 나침판을 그리자
    public class CustomerView extends View{
        Paint paint = new Paint();
        public CustomerView(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int width = getWidth();
            int height = getHeight();
            int cX = width/2;
            int cY = height/2;

            // 원크기와 방위각
            float radius, dir;

            // 작은 크기 기준으로 원을 그리자
            if(cX > cY){
                radius = (float)(cY*0.9);
            }else{
                radius = (float)(cX*0.9);
            }
            dir = azimut*360 /( 2 * 3.14f);

            // 나침판 그리기
            // 테두리선
            canvas.drawCircle(cX, cY, radius,paint);
            // y축
            canvas.drawLine(cX, cY-radius, cX, cY+radius, paint);
            // X축
            canvas.drawLine(cX-radius, cY, cX+radius, cY, paint);

            paint.setColor(Color.DKGRAY);
            paint.setTextSize(300);
            canvas.drawText(String.valueOf((int)dir), cX - 150, cY, paint);

            if(azimut != 0){
                canvas.rotate(-dir, cX, cY);
            }

            // N, S 보이기
            paint.setColor(Color.BLUE);
            paint.setTextSize(100);
            canvas.drawLine(cX, cY-radius, cX, cY+radius, paint);
            canvas.drawText("N", cX-30, cY-radius-20, paint);
            canvas.drawText("S", cX-30, cY+radius+80, paint);
            paint.setColor(Color.RED);
        }
    }
}