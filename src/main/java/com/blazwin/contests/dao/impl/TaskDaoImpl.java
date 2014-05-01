package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.TaskDao;
import com.blazwin.contests.entity.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TaskDaoImpl extends DaoImpl<Task> implements TaskDao {
    public TaskDaoImpl(){
        super(Task.class);
    }

    public List<Task> getByContestId(int contestId) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.where(builder.equal(root.get("contest"), contestId));
        query.select(root);
        List<Task> tasks = getEntityManager().createQuery(query).getResultList();
        return tasks;
    }
}
