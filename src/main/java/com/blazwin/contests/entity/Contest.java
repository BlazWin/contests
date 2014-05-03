package com.blazwin.contests.entity;

import com.blazwin.contests.entity.enums.ContestStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contest {
    @Id
    @GeneratedValue
    @Column(name = "contest_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(nullable = false, name = "start_date")
    private Date start;
    @Column(nullable = false, name = "duration_min")
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 45)
    private ContestStatus status;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ContestStatus getStatus() {
        return status;
    }

    public void setStatus(ContestStatus status) {
        this.status = status;
    }
}
