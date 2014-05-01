package com.blazwin.contests.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Registrant implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id")
    private Contest contest;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @Column(nullable = false)
    private boolean participated;
    @Column(nullable = false, name = "tasks_solved")
    private int tasksSolved;
    @Column(nullable = false)
    private int penalty;
    @Column(name = "full_team_name", nullable = false, length = 200)
    private String fullTeamName;
    @Column(nullable = false)
    private int place;

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public boolean isParticipated() {
        return participated;
    }

    public void setParticipated(boolean participated) {
        this.participated = participated;
    }

    public int getTasksSolved() {
        return tasksSolved;
    }

    public void setTasksSolved(int tasksSolved) {
        this.tasksSolved = tasksSolved;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getFullTeamName() {
        return fullTeamName;
    }

    public void setFullTeamName(String fullTeamName) {
        this.fullTeamName = fullTeamName;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}
