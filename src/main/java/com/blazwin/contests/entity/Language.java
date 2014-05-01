package com.blazwin.contests.entity;

import javax.persistence.*;

@Entity(name = "programming_language")
public class Language {
    @Id
    @GeneratedValue
    @Column(name = "programming_language_id")
    private int id;
    @Column(unique = true, nullable = false, length = 45)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
