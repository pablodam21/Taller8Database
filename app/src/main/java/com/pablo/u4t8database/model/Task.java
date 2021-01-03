package com.pablo.u4t8database.model;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String todo;
    private String to_accomplish;
    private String description;
    private String priority;
    private String status;


    public Task(int id, String todo, String to_accomplish, String description, String priority, String status) {
        this.id = id;
        this.todo = todo;
        this.to_accomplish = to_accomplish;
        this.description = description;
        this.priority = priority;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTo_accomplish() {
        return to_accomplish;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setTo_accomplish(String to_accomplish) {
        this.to_accomplish = to_accomplish;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", todo='" + todo + '\'' +
                ", to_accomplish='" + to_accomplish + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
