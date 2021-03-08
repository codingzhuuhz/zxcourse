package com.pengzhen.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExcelHeaderData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;

    public String getOneSubjectName() {
        return oneSubjectName;
    }

    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getTwoSubjectName() {
        return twoSubjectName;
    }

    public void setTwoSubjectName(String twoSubjectName) {
        this.twoSubjectName = twoSubjectName;
    }
}
