package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.ContestDao;
import com.blazwin.contests.entity.Contest;
import org.springframework.stereotype.Repository;

@Repository
public class ContestDaoImpl extends DaoImpl<Contest> implements ContestDao {
    public ContestDaoImpl(){
        super(Contest.class);
    }
}
