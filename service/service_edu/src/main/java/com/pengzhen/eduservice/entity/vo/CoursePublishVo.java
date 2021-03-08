package com.pengzhen.eduservice.entity.vo;


public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getLessonNum() {
        return lessonNum;
    }

    public void setLessonNum(Integer lessonNum) {
        this.lessonNum = lessonNum;
    }

    public String getSubjectLevelOne() {
        return subjectLevelOne;
    }

    public void setSubjectLevelOne(String subjectLevelOne) {
        this.subjectLevelOne = subjectLevelOne;
    }

    public String getSubjectLevelTwo() {
        return subjectLevelTwo;
    }

    public void setSubjectLevelTwo(String subjectLevelTwo) {
        this.subjectLevelTwo = subjectLevelTwo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}