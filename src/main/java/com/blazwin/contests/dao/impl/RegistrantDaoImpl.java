package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.RegistrantDao;
import com.blazwin.contests.entity.Registrant;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RegistrantDaoImpl extends DaoImpl<Registrant> implements RegistrantDao {
    public RegistrantDaoImpl(){
        super(Registrant.class);
    }

    public List<Registrant> getByContestId(int contestId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Registrant> query = builder.createQuery(Registrant.class);
        Root<Registrant> root = query.from(Registrant.class);
        query.where(builder.equal(root.get("contest"), contestId));
        query.select(root);
        List<Registrant> registrants = getEntityManager().createQuery(query).getResultList();
        return registrants;
    }
}
