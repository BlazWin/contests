package com.blazwin.contests.entity;

import com.blazwin.contests.entity.enums.TaskStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "team_task_status")
public class TeamTaskStatus implements Serializable{
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 45)
    private TaskStatus taskStatus;
    @Column(name = "attempts_count", nullable = false)
    private int attemptCount;
    @Column(name = "penalty", nullable = false)
    private int penalty;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}
