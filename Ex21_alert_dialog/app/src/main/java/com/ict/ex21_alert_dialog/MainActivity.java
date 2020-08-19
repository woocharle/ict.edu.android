package com.ict.ex21_alert_dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6;
    // button3에 사용할 메뉴 목록을 배열만든다.
    String[] foods = {"치킨","삽겹살","곱창","파전"};
    boolean[] isFoods = {false,false,false,false};
    ArrayList<String> k_foods = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("대화상자1")
                        .setMessage("대화상자내용")
                        .setIcon(R.mipmap.ic_launcher)
                        .show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("프로그램종료")
                        .setMessage("정말종료할까요?")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        })
                        .show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("메뉴선택하세요")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(foods, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // int i => 선택된 목록의 위치값(index)
                                String msg = foods[i];
                                Toast.makeText(MainActivity.this, msg+" 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("메뉴선택하세요")
                        .setIcon(R.mipmap.ic_launcher)
                        .setSingleChoiceItems(foods, 3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // int i => 선택된 목록의 위치값(index)
                                String msg = foods[i];
                                Toast.makeText(MainActivity.this, msg+" 선택", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("메뉴선택하세요")
                        .setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(foods, isFoods, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                // int i(위치), boolean b(선택/해제)
                                if(b){
                                    Toast.makeText(MainActivity.this, foods[i]+"선택", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, foods[i]+"해제", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 생성 메소드
                showDialog();
            }
        });
    }
   private void showDialog(){
        // 출력하는 배열 초기화
        k_foods = new ArrayList<>();
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("대화상자6");
       builder.setIcon(R.mipmap.ic_launcher);
       builder.setMultiChoiceItems(foods, isFoods, new DialogInterface.OnMultiChoiceClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i, boolean b) {
               if(b){
                    k_foods.add(foods[i]);
               }else{
                    k_foods.remove(foods[i]);
               }
               // 체크박스 초기화
              isFoods[i] = false;
           }

       });
     builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               // Toast.makeText(MainActivity.this, "예 버튼클릭", Toast.LENGTH_SHORT).show();
               String msg = "";
               for (String k : k_foods){
                   msg += k+"   " ;
               }
               Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
           }
       });
       builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               Toast.makeText(MainActivity.this, "아니요 버튼 클릭", Toast.LENGTH_SHORT).show();
               return ;
           }
       });
       builder.show();
   }
}