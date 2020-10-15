package com.ict.ex80_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {
    TextView res1, res2, res3, res4, res5;
    SensorManager manager;
    Sensor acc, gravity, light, proximity, gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        res1 = findViewById(R.id.res);
        res2 = findViewById(R.id.res2);
        res3 = findViewById(R.id.res3);
        res4 = findViewById(R.id.res4);
        res5 = findViewById(R.id.res5);

        manager = (SensorManager)getSystemService(SENSOR_SERVICE);

        //매니저를 이용해서 원하는 센서를 얻어내자.
        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravity = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        light = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);    //근접
        gyroscope = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);    //회전

        //센서에 Listener달기

        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this){
            float x = sensorEvent.values[0];
           // float y = sensorEvent.values[1];
            //float z = sensorEvent.values[2];

            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER: res1.setText("x=" + x); break;
                case Sensor.TYPE_GRAVITY:
                    res2.setText("x=" + x); break;
                case Sensor.TYPE_LIGHT:
                    res3.setText("x=" + x); break;
                case Sensor.TYPE_PROXIMITY:
                    res4.setText("x=" + x);
                    if(x== 0){
                        Toast.makeText(this, "접근완료", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this, "접근불가", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Sensor.TYPE_GYROSCOPE:
                    res5.setText("x=" + x);
                break;
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

}