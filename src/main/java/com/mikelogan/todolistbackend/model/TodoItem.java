package com.mikelogan.todolistbackend.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoItem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String title;
    private String text;
    private int sort;

    public TodoItem() {}

    public TodoItem(UUID id, String title, String text, int sort) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.sort = sort;
    }

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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
