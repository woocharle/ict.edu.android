package com.ict.ex68_myreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

// 앱 허가 받기 :  Gradle Scripts - build.gradle(Module.app)
// implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
// allprojects {
//    repositories {
//        maven{url 'https://jitpack.io'}
//    }
// }
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 모든 위험권한을 자동으로 부여하는 메소드
        AutoPermissions.Companion.loadAllPermissions(this, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
}