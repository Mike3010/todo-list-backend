package com.mikelogan.todolistbackend.dto;

import java.util.UUID;

public class TodoItemDto {
    
    private UUID id;
    private String title;
    private String text;
    private int order;

    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public TodoItemDto(UUID id, String title, String text, int order) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.order = order;
    }

    public TodoItemDto() {}
}
