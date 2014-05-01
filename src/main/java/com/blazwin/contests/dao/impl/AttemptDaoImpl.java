package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.AttemptDao;
import com.blazwin.contests.entity.Attempt;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AttemptDaoImpl extends DaoImpl<Attempt> implements AttemptDao {
    public AttemptDaoImpl(){
        super(Attempt.class);
    }

    public List<Attempt> getByContestId(int contestId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Attempt> query = builder.createQuery(Attempt.class);
        Root<Attempt> root = query.from(Attempt.class);
        query.where(builder.equal(root.get("contest"), contestId));
        query.select(root);
        List<Attempt> list = getEntityManager().createQuery(query).getResultList();
        return list;
    }
}
