package com.blazwin.contests.dao.impl;

import com.blazwin.contests.dao.*;
import com.blazwin.contests.entity.*;
import com.blazwin.contests.entity.enums.AttemptVerdict;
import com.blazwin.contests.entity.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class GlobalDaoImpl implements GlobalDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private AttemptDao attemptDao;
    @Autowired
    private RegistrantDao registrantDao;
    @Autowired
    private TeamTaskStatusDao teamTaskStatusDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ContestDao contestDao;
    @Autowired
    private TeamDao teamDao;

    private Contest contest;
    private List<Task> tasks;
    private List<Registrant> registrants;
    private List<TeamTaskStatus> teamTaskSt;
    List<Attempt> attempts;
    TreeMap<Integer, TreeMap<Integer, TeamTaskStatus>> tm;

    public void clearResults(int contestId){
        clearRegistrantsResults(contestId);
        clearTaskStatistics(contestId);
        clearTeamTaskStatus(contestId);
    }

    public void calcResults(int contestId){

        clearResults(contestId);

        contest = contestDao.getById(contestId);
        tasks = taskDao.getByContestId(contestId);
        registrants = registrantDao.getByContestId(contestId);
        teamTaskSt = teamTaskStatusDao.getByContestId(contestId);
        attempts = attemptDao.getByContestId(contestId);
        tm = new TreeMap<Integer, TreeMap<Integer, TeamTaskStatus>>();

        initTeamTaskStatus(tm);
        Comparator<Attempt> cmp = new Comparator<Attempt>() {
            public int compare(Attempt o1, Attempt o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        };
        Collections.sort(attempts, cmp);
        for(Attempt a : attempts) {
            TeamTaskStatus st = tm.get(a.getTeam().getId()).get(a.getTask().getId());
            Task task = a.getTask();
            if (a.getVerdict() != AttemptVerdict.COMPILATION_ERROR
                    && st.getTaskStatus() != TaskStatus.OK
                    && st.getTaskStatus() != TaskStatus.FIRST_OK) {
                task.setTotalTries(task.getTotalTries() + 1);
                st.setAttemptCount(st.getAttemptCount() + 1);
                if (a.getVerdict() == AttemptVerdict.ACCEPTED) {
                    task.setAcceptedTries(task.getAcceptedTries() + 1);
                    if (a.getTask().getAcceptedTries() == 1) {
                        st.setTaskStatus(TaskStatus.FIRST_OK);
                    } else {
                        st.setTaskStatus(TaskStatus.OK);
                    }
                    long minutes = (a.getTime().getTime() - contest.getStart().getTime()) / 100 / 60;
                    st.setPenalty((st.getAttemptCount() - 1) * 20 + (int) minutes);
                } else {
                    st.setTaskStatus(TaskStatus.FAILED);
                }
            }
        }
        for (Registrant reg : registrants) {

            int solved = 0;
            int penalty = 0;
            boolean tried = false;
            for (TeamTaskStatus st : tm.get(reg.getTeam().getId()).values()) {
                if (st.getTaskStatus() == TaskStatus.OK ||
                        st.getTaskStatus() == TaskStatus.FIRST_OK) {
                    solved++;
                    penalty+=st.getPenalty();
                }
                if (st.getTaskStatus() != TaskStatus.UNTRIED)
                    tried = true;
            }
            reg.setParticipated(tried);
            reg.setPenalty(penalty);
            reg.setTasksSolved(solved);
            reg.setFullTeamName(reg.getTeam().getName() + ":" + reg.getTeam().getFirst().getSurname() + ", "
                    + reg.getTeam().getSecond().getSurname() + ", " + reg.getTeam().getThird().getSurname());
        }
        Comparator<Registrant> cmpReg = new Comparator<Registrant>() {
            public int compare(Registrant o1, Registrant o2) {
                int diff = o1.getTasksSolved() - o2.getTasksSolved();
                if (diff == 0) {
                    diff = o2.getPenalty() - o1.getPenalty();
                }
                return diff;
            }
        };
        Collections.sort(registrants, cmpReg);
        int pos = 1;
        for (Registrant reg : registrants) {
            reg.setPlace(pos++);
            registrantDao.merge(reg);
        }
        for (Task task : tasks)
            taskDao.merge(task);
        for (TeamTaskStatus st : teamTaskSt)
            teamTaskStatusDao.save(st);
    }

    private void initTeamTaskStatus(TreeMap<Integer, TreeMap<Integer, TeamTaskStatus>> tm) {

        for (Registrant rg : registrants) {
            tm.put(rg.getTeam().getId(), new TreeMap<Integer, TeamTaskStatus>());
            for (Task task : tasks) {
                TeamTaskStatus newSt = new TeamTaskStatus();
                newSt.setTask(task);
                newSt.setTeam(rg.getTeam());
                newSt.setTaskStatus(TaskStatus.UNTRIED);
                newSt.setAttemptCount(0);
                newSt.setPenalty(0);
                tm.get(rg.getTeam()).put(task.getId(), newSt);
            }
        }
    }

    private void clearRegistrantsResults(int contestId) {
        List<Registrant> registrants = registrantDao.getByContestId(contestId);
        for(Registrant r : registrants) {
            r.setPenalty(0);
            r.setTasksSolved(0);
            r.setParticipated(false);
            entityManager.merge(r);
        }
    }

    private void clearTaskStatistics(int contestId) {
        List<Task> tasks = taskDao.getByContestId(contestId);
        for(Task t : tasks) {
            t.setTotalTries(0);
            t.setAcceptedTries(0);
            entityManager.merge(t);
        }
    }

    private void clearTeamTaskStatus(int contestId) {

        List<TeamTaskStatus> statuses = teamTaskStatusDao.getByContestId(contestId);
        for(TeamTaskStatus t : statuses) {
            t.setAttemptCount(0);
            t.setPenalty(0);
            t.setTaskStatus(TaskStatus.UNTRIED);
            entityManager.remove(t);
        }
    }
}
