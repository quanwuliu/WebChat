package com.lwj.persistence.pojo;

public class Complaint extends ComplaintKey {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}