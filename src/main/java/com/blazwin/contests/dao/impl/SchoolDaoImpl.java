package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.SchoolDao;
import com.blazwin.contests.entity.School;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDaoImpl extends DaoImpl<School> implements SchoolDao {
    public SchoolDaoImpl(){
        super(School.class);
    }
}
