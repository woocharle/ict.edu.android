package com.ict.ex50_cardview;

import android.content.Intent;

public class VO {
    private String name;
    private String birth;
    private String phone;
    int resId;

    public VO() {    }

    public VO(String name, String birth, String phone) {
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        // resId = R.mipmap.ic_launcher_round;
        // 생년월일에 따라 if문을 사용해서 그림을 변경할 수 도 있다.
        int year = Integer.parseInt(birth.substring(0,4));
        if(year >1999){
            resId = R.drawable.one;
        }else{
            resId =R.drawable.three;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
