package com.pengzhen.eduservice.entity.vo.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Chapter {
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

    public List<LittleChapter> getLittleChapters() {
        return littleChapters;
    }

    public void setLittleChapters(List<LittleChapter> littleChapters) {
        this.littleChapters = littleChapters;
    }

    private String id;
    private String title;
    private List<LittleChapter> littleChapters = new ArrayList<>();
}
