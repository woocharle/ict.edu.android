package com.ict.ex79_camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    static final int IMAGE_CAPTURE_REQUEST = 200;
    Button button;
    ImageView imageView;
    // 추가
    File photoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disp();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this, IMAGE_CAPTURE_REQUEST);
    }
    private void disp(){
        // 추가
        if(photoFile == null){
            photoFile = createImageFile();
        }
        // 추가
        // Provider는 메니페스트에 등록,
        // Environment는 퍼미션해야된다.
        Uri fileUri = FileProvider.getUriForFile(this, "cameratest", photoFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,IMAGE_CAPTURE_REQUEST );
        }
    }
    // 추가
    private File createImageFile(){
        File outFile = null;
        try{
            // String fileName = "capture.jpg";
            String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            outFile = new File(Environment.getDataDirectory()+"/"+getPackageName()+"/"+fileName+".jpg");
        }catch (Exception e){
        }

        return outFile;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE_CAPTURE_REQUEST ){
            if(resultCode == RESULT_OK){
                // Bundle extras = data.getExtras();
                // Bitmap imageBitmap = (Bitmap)extras.get("data");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap imageBitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath(),options);
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }

    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}
}