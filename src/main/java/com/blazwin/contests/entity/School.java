package com.blazwin.contests.entity;

import javax.persistence.*;

@Entity
public class School {
    @Id
    @GeneratedValue
    @Column(name = "school_id")
    private int id;
    @Column(unique = true, nullable = false, length = 45)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acm_region_id", nullable = false)
    private AcmRegion region;

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

    public AcmRegion getRegion() {
        return region;
    }

    public void setRegion(AcmRegion region) {
        this.region = region;
    }
}
