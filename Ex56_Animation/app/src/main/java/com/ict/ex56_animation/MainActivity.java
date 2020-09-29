package com.ict.ex56_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    boolean isPageOpen = false;
    Animation ani_left;
    Animation ani_right;
    LinearLayout base, movepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        base = findViewById(R.id.base);
        movepage = findViewById(R.id.movepage);

        ani_left = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        ani_right = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        SlidingPageAnimationListener listener = new SlidingPageAnimationListener();
        ani_right.setAnimationListener(listener);
        ani_left.setAnimationListener(listener);

        base.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen){
                    movepage.startAnimation(ani_right);
                }else{
                    movepage.setVisibility(View.VISIBLE);
                    movepage.startAnimation(ani_left);
                }
            }
        });
    }
    // 애니메이션 이벤트 처리할 내부 클래스 생성
    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationEnd(Animation animation) {
            // 애니메이션이 발생하면
            if(isPageOpen){
                   movepage.setVisibility(View.VISIBLE);
                   isPageOpen = false;
            }else{
                   isPageOpen = true;
            }
        }
        @Override
        public void onAnimationStart(Animation animation) {}
        @Override
        public void onAnimationRepeat(Animation animation) { }
    }
}