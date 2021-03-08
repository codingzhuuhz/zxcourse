package com.pengzhen.eduservice.entity;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataForm {
    private String id;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SubjectDataForm> getChildren() {
        return children;
    }

    public void setChildren(List<SubjectDataForm> children) {
        this.children = children;
    }

    private List<SubjectDataForm> children = new ArrayList<>();
}
