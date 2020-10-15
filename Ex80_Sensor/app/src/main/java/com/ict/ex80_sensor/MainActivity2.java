package com.ict.ex80_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv1);

        //센서는 SensorManager가 관리
        SensorManager manager = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);

        String msg = "전체 센서의 수: list:" + list.size();
        for (Sensor k : list){
            msg += "sensor_name : " + k.getName() + "\n" +              // 센서 이름
                    "sensor_power : " + k.getPower() + "\n" +           // 센서 단위
                    "sensor_resolution : " + k.getResolution() + "\n"+  // 센서 해상도
                    "sensor_range : " + k.getMaximumRange() + "\n"+     // 센서 범위
                    "============================\n";
        }

        tv1.setText(msg);
    }
}