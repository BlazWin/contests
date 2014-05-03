package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.TeamTaskStatusDao;
import com.blazwin.contests.entity.TeamTaskStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TeamTaskStatusDaoImpl extends DaoImpl<TeamTaskStatus> implements TeamTaskStatusDao {
    public TeamTaskStatusDaoImpl(){
        super(TeamTaskStatus.class);
    }

    public List<TeamTaskStatus> getByContestId(int contestId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TeamTaskStatus> query = builder.createQuery(TeamTaskStatus.class);
        Root<TeamTaskStatus> root = query.from(TeamTaskStatus.class);
        query.where(builder.equal(root.get("task").get("contest"), contestId));
        query.select(root);
        List<TeamTaskStatus> list = getEntityManager().createQuery(query).getResultList();
        return list;
    }

}
