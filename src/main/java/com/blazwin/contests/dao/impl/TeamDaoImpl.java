package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.TeamDao;
import com.blazwin.contests.entity.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDaoImpl extends DaoImpl<Team> implements TeamDao {
    public TeamDaoImpl(){
        super(Team.class);
    }
}
