package com.blazwin.contests.entity;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private int id;
    @Column(nullable = false, name = "num_letter")
    private char numLetter;
    @Column(nullable = false, length = 200)
    private String folder;
    @ManyToOne
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;
    @Column(name = "total_tries", nullable = false)
    private int totalTries;
    @Column(name = "accepted_tries", nullable = false)
    private int acceptedTries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getNumLetter() {
        return numLetter;
    }

    public void setNumLetter(char numLetter) {
        this.numLetter = numLetter;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public int getTotalTries() {
        return totalTries;
    }

    public void setTotalTries(int totalTries) {
        this.totalTries = totalTries;
    }

    public int getAcceptedTries() {
        return acceptedTries;
    }

    public void setAcceptedTries(int acceptedTries) {
        this.acceptedTries = acceptedTries;
    }
}
