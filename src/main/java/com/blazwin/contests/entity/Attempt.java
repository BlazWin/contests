package com.blazwin.contests.entity;

import com.blazwin.contests.entity.enums.AttemptVerdict;

import javax.persistence.*;

@Entity
public class Attempt {
    @Id
    @GeneratedValue
    @Column(name = "attempt_id")
    private int id;
    @Column(nullable = false)
    private int time;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private AttemptVerdict verdict;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public AttemptVerdict getVerdict() {
        return verdict;
    }

    public void setVerdict(AttemptVerdict verdict) {
        this.verdict = verdict;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
