package com.blazwin.contests.dao;

public interface GlobalDao {
    void clearResults(int contestId);
    void calcResults(int contestId);
}
