package com.pengzhen.eduservice.entity.vo;

public class TeacherQuery {
    private String name ;
    private Integer level ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    private String gmtCreate1 ;
    private String gmtCreate2 ;

}
