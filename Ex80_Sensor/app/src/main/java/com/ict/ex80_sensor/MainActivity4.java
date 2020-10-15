package com.ict.ex80_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {
    SensorManager manager;
    Sensor sensor;
    View mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mRoot = findViewById(R.id.mRoot);

        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float lux = sensorEvent.values[0];
                getSupportActionBar().setTitle("조도: " + lux + "lx");

                //무지개 색
                if(lux <320f / 7f){
                    mRoot.setBackgroundColor(Color.MAGENTA);
                }else if(lux <320f / 7f * 2f){
                    mRoot.setBackgroundColor(Color.parseColor("#4b0082"));
                }else if(lux <320f / 7f * 3f){
                    mRoot.setBackgroundColor(Color.BLUE);
                }else if(lux <320f / 7f * 4f){
                    mRoot.setBackgroundColor(Color.GREEN);
                }else if(lux <320f / 7f * 5f){
                    mRoot.setBackgroundColor(Color.YELLOW);
                }else if(lux <320f / 7f *6f){
                    mRoot.setBackgroundColor(Color.parseColor("#FFFF57"));
                }else{
                    mRoot.setBackgroundColor(Color.RED);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }

        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        });
    }
}