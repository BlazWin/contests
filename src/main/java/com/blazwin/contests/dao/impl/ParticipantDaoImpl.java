package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.ParticipantDao;
import com.blazwin.contests.entity.Participant;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantDaoImpl extends DaoImpl<Participant> implements ParticipantDao {
    public ParticipantDaoImpl(){
        super(Participant.class);
    }
}
