package com.blazwin.contests.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    @Column(name = "participant_id")
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 45)
    private String surname;
    @Column(nullable = false, length = 45, unique = true)
    private String nickname;
    @Column(length = 200)
    private String address;
    @Column(name = "t_shirt_size", length = 3)
    private String tshirt;
    @Column(nullable = false, name = "graduation_year")
    private Date graduation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTshirt() {
        return tshirt;
    }

    public void setTshirt(String tshirt) {
        this.tshirt = tshirt;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
