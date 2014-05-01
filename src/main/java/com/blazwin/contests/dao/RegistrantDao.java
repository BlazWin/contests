package com.blazwin.contests.dao;

import com.blazwin.contests.entity.Registrant;

import java.util.List;

public interface RegistrantDao extends Dao<Registrant> {
    List<Registrant> getByContestId(int contestId);
}
