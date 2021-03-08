package com.pengzhen.eduservice.entity.vo;

public class CourseQuery {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGmtCreate1() {
        return gmtCreate1;
    }

    public void setGmtCreate1(String gmtCreate1) {
        this.gmtCreate1 = gmtCreate1;
    }

    public String getGmtCreate2() {
        return gmtCreate2;
    }

    public void setGmtCreate2(String gmtCreate2) {
        this.gmtCreate2 = gmtCreate2;
    }

    private String status;
    private String gmtCreate1;
    private String gmtCreate2;
}
