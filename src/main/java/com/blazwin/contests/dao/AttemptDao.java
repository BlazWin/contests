package com.blazwin.contests.dao;

import com.blazwin.contests.entity.Attempt;

import java.util.List;

public interface AttemptDao extends Dao<Attempt> {
    List<Attempt> getByContestId(int contestId);
}
