package com.blazwin.contests.dao;

import com.blazwin.contests.entity.TeamTaskStatus;

import java.util.List;

public interface TeamTaskStatusDao extends Dao<TeamTaskStatus> {
    List<TeamTaskStatus> getByContestId(int contestId);
}
