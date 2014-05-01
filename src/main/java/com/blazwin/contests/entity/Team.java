package com.blazwin.contests.entity;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "first_participant", nullable = false)
    private Participant first;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_participant", nullable = false)
    private Participant second;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "third_participant", nullable = false)
    private Participant third;
    @Column(name = "solutions_folder", nullable = false, length = 200)
    private String folder;

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

    public Participant getFirst() {
        return first;
    }

    public void setFirst(Participant first) {
        this.first = first;
    }

    public Participant getSecond() {
        return second;
    }

    public void setSecond(Participant second) {
        this.second = second;
    }

    public Participant getThird() {
        return third;
    }

    public void setThird(Participant third) {
        this.third = third;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
