package com.ict.ex72_location2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.List;

import static android.location.LocationManager.GPS_PROVIDER;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView textView1, textView2;
    Button button1, button2;
    String msg1 = "";
    String msg2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        AutoPermissions.Companion.loadAllPermissions(this, 100);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationGPS();
            }
        });
    }
    public void showLocationGPS(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            return;
        }
        Location location = manager.getLastKnownLocation(GPS_PROVIDER);
        if(location != null){
            double lat = location.getLatitude();  // 위도
            double lon = location.getLongitude(); // 경도
            msg1 = " 최신 위치 \n위도 : "+lat+"\n경도 : "+lon +"\n주소 : "+getAddress(lat,lon);
            textView1.setText(msg1);
        }
        MyListener myListener = new MyListener();
        long minTime = 3000;    // 3초
        float minDistance = 0 ; // 이동거리
        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, minTime, minDistance, myListener);
    }

    // 추가 내용 : 위치가 변경되거나 일정한 시간이되면 다시 위치 파악
    class MyListener implements LocationListener{
        @Override
        public void onLocationChanged(@NonNull Location location) {
            double lat = location.getLatitude();  // 위도
            double lon = location.getLongitude(); // 경도
            msg1 = " 변경위치 위치 \n위도 : "+lat+"\n경도 : "+lon +"\n주소 : "+getAddress(lat,lon);
            textView1.setText(msg1);
        }
    }

    //  google 이용해서 주소
    private String getAddress(double lat, double lon){
        String addr = "";
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = null;
        try{
            list = geocoder.getFromLocation(lat, lon, 1);
            if(list.size() > 0){
                Address address = list.get(0);
                addr = address.getAddressLine(0);
            }
        }catch (Exception e){
        }
        return addr;
    }

    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}
}