package com.ict.ex17_listview;

public class VO {
    private int resID;          //그림 아이디
    private String imgName;     //그림 제목

    public VO() { }

    public VO(int resID, String imgName) {
        this.resID = resID;
        this.imgName = imgName;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }
}
