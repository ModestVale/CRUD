package ru.netology.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Issue {
    private int id;
    private String status;
    private String author;
    private HashSet<String> labels;
    private String assignee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public HashSet<String> getLabels() {
        return labels;
    }

    public void setLabels(HashSet<String> labels) {
        this.labels = labels;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Issue(int id, String status, String author, String assignee, String... labels) {
        this.id = id;
        this.status = status;
        this.author = author;
        this.assignee = assignee;
        this.labels = new HashSet<String>(List.of(labels));
    }
}
