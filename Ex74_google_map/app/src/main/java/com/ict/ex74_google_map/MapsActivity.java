package com.ict.ex74_google_map;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AutoPermissionsListener {

    private GoogleMap mMap;
    double lat, log; //위도와 경도

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // 위험 권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this, 100);
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if(ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        lat = location.getLatitude();
        log = location.getLongitude();

        // layout-activity-maps.xml의 fragment 사용하기 위한 코딩
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // 구글맵이 준비 되어있으면 프레그먼트에 리턴한다.
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        final LatLng sydney = new LatLng(lat, log);
        mMap.addMarker(new MarkerOptions().position(sydney).title("ICT 인재 개발원"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));


        final LatLng sydney2 = new LatLng(lat + 0.0003, log + 0.0003);
        mMap.addMarker(new MarkerOptions().position(sydney2).title("애슐리"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney2));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        //마커의 타이틀을 획득했을 때
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // 마커가 하나일 때
                /*  CameraPosition position = new CameraPosition.Builder()
                                        .target(sydney)
                                        .zoom(19)       //확대
                                        .bearing(45)     //각도
                                        .tilt(30)         //깊기
                                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));*/
                // 마커가 여러개 일 때
                if(marker.getPosition().equals(sydney)){
                    /*
                    CameraPosition position = new CameraPosition.Builder()
                                            .target(sydney)
                                            .zoom(19)       //확대
                                            .bearing(45)     //각도
                                            .tilt(30)         //깊기
                                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                    */
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:027397235"));
                    if (intent.resolveActivity(getPackageManager()) != null){
                        if(checkSelfPermission(Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED){
                            return;
                        }
                        startActivity(intent);
                    }

                }else if(marker.getPosition().equals(sydney2)) {
                    CameraPosition position = new CameraPosition.Builder()
                            .target(sydney2)
                            .zoom(19)       //확대
                            .bearing(45)     //각도
                            .tilt(30)         //깊기
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                }
            }
        });

    }

    @Override
    public void onDenied(int i, String[] strings) { }

    @Override
    public void onGranted(int i, String[] strings) { }


}