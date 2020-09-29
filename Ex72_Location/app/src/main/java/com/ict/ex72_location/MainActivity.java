package com.ict.ex72_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.util.List;

//  메니페스트에서 퍼미션
//  Gradle Scripts 에서 위험권한 처리
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

        // 위험권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this, 100);

        // GPS 이용해서  현재 위치의 경도와 위도 찾기
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationGPS();
            }
        });

        // NetWork  이용해서  현재 위치의 경도와 위도 찾기
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationNetwork();
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
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            double lat = location.getLatitude();  // 위도
            double lon = location.getLongitude(); // 경도
            msg1 = " 내 위치 \n위도 : "+lat+"\n경도 : "+lon +"\n주소 : "+getAddress(lat,lon);
            textView1.setText(msg1);
        }
    }
    public void showLocationNetwork(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
            return;
        }
        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(location != null){
            double lat = location.getLatitude();  // 위도
            double lon = location.getLongitude(); // 경도
            msg2 = " 내 위치 \n위도 : "+lat+"\n경도 : "+lon +"\n주소 : "+getAddress(lat,lon); ;
            textView2.setText(msg2);
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
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
}