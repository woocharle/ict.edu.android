package com.ict.ex82_socket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

// res - layout - goole - MapView 다운로드 한 후
public class MainActivity12 extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double[] lat;
    double[] lon;
    String[] names;
    LatLng[] latlng;
    String msg;
    final static String OPENLIB_URL = "http://openapi.seoul.go.kr:8088/sample/json/SeoulLibraryTime/1/5/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        new Thread(new Runnable() {
            @Override
            public void run() {
                serverConnect(OPENLIB_URL);
                json_process();
            }
        }).start();

        SupportMapFragment mapFragment =
                (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        // 구글맵이 준비 되었으면 프레그먼트에 리턴한다.
        mapFragment.getMapAsync(this);
    }
    private void serverConnect(String str){
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(str);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(10000);
                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader( new InputStreamReader(
                            conn.getInputStream(),"utf-8"));
                    while(true){
                        String line = br.readLine();
                        if(line == null) break;
                        sb.append(line+"\n");
                    }
                    br.close();
                };
                conn.disconnect();
            }
        }catch (Exception e){
        }
        msg = sb.toString();
    }
    public void json_process(){
        try {
            BufferedReader br = new BufferedReader(new StringReader(msg));
            String res = br.readLine();
            JSONObject obj1 = new JSONObject(res);
            JSONObject obj2 = new JSONObject(obj1.getString("SeoulLibraryTime"));
            JSONArray obj3 = new JSONArray(obj2.getString("row"));

            lat = new double[obj3.length()];
            lon = new double[obj3.length()];
            names = new String[obj3.length()];
            latlng = new LatLng[obj3.length()];

            for (int i=0; i<obj3.length(); i++){
               lat[i] = Double.parseDouble(obj3.getJSONObject(i).getString("XCNTS"));
               lon[i] = Double.parseDouble(obj3.getJSONObject(i).getString("YDNTS"));
               names[i] = (obj3.getJSONObject(i).getString("LBRRY_NAME"));
            }
        }catch (Exception e){
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (int i=0; i<names.length; i++){
            latlng[i] = new LatLng(lat[i], lon[i]);
            mMap.addMarker(new MarkerOptions().position(latlng[i]).title(names[i]));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng[i]));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

            // 이벤트
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    if(marker.getTitle().equals(names[0])){
                        CameraPosition position = new CameraPosition.Builder()
                            .target(latlng[0])
                            .zoom(17)
                            .bearing(45)
                            .tilt(30)
                            .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                    }else if(marker.getTitle().equals(names[1])) {
                        CameraPosition position = new CameraPosition.Builder()
                                .target(latlng[1])
                                .zoom(17)
                                .bearing(45)
                                .tilt(30)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                    } else if(marker.getTitle().equals(names[2])){
                            CameraPosition position = new CameraPosition.Builder()
                                    .target(latlng[2])
                                    .zoom(17)
                                    .bearing(45)
                                    .tilt(30)
                                    .build();
                           mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));
                    }
                }
            });
        }
   }
}