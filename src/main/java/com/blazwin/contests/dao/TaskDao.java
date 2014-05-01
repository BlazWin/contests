package com.blazwin.contests.dao;

import com.blazwin.contests.entity.Task;

import java.util.List;

public interface TaskDao extends Dao<Task> {
    List<Task> getByContestId(int contestId);
}
