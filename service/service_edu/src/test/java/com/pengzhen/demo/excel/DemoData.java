package com.pengzhen.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;

public class DemoData {
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 1)
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
