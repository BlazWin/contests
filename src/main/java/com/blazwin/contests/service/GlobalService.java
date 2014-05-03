package com.blazwin.contests.service;

import com.blazwin.contests.dao.*;
import com.blazwin.contests.entity.Contest;
import com.blazwin.contests.entity.Registrant;
import com.blazwin.contests.entity.Task;
import com.blazwin.contests.entity.TeamTaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.*;

@Service
@Transactional
public class GlobalService {

    @Autowired
    private ContestDao contestDao;
    @Autowired
    private RegistrantDao registrantDao;
    @Autowired
    private TeamTaskStatusDao teamTaskStatusDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private GlobalDao globalDao;

    public void getAllContests(ModelMap model) {
        List<Contest> list = contestDao.getAll();
        model.addAttribute("contests", list);
    }

    public void processContest(int contestId) {
        globalDao.calcResults(contestId);
    }

    public void getDataForTable(int contestId, ModelMap model) {
        List<Registrant> regList = registrantDao.getByContestId(contestId);
        List<TeamTaskStatus> stList = teamTaskStatusDao.getByContestId(contestId);
        List<Task> taskList = taskDao.getByContestId(contestId);
        processRegistrants(regList);
        processTasks(taskList);
        TreeMap<Integer, List<TeamTaskStatus>> stMap = processTasksStatus(stList);
        model.addAttribute("registrants", regList);
        model.addAttribute("tasks", taskList);
        model.addAttribute("teamTasks", stMap);
    }

    private void processRegistrants(List<Registrant> regList) {
        Comparator<Registrant> cmp = new Comparator<Registrant>() {
            public int compare(Registrant o1, Registrant o2) {
                return o1.getPlace() - o2.getPlace();
            }
        };
        Collections.sort(regList, cmp);
        for(Registrant reg : regList)
            reg.getTeam().getId();
    }

    private void processTasks(List<Task> taskList) {
        Comparator<Task> cmp = new Comparator<Task>() {
            public int compare(Task o1, Task o2) {
                return Character.compare(o1.getNumLetter(), o2.getNumLetter());
            }
        };
        Collections.sort(taskList, cmp);
    }

    private TreeMap<Integer, List<TeamTaskStatus>> processTasksStatus(List<TeamTaskStatus> list) {
        TreeMap<Integer, List<TeamTaskStatus>> mp = new TreeMap<Integer, List<TeamTaskStatus>>();
        for(TeamTaskStatus st : list) {
            int teamId = st.getTeam().getId();
            if (!mp.containsKey(teamId))
                mp.put(teamId, new ArrayList<TeamTaskStatus>());
            mp.get(teamId).add(st);
        }
        Comparator<TeamTaskStatus> cmp = new Comparator<TeamTaskStatus>() {
            public int compare(TeamTaskStatus o1, TeamTaskStatus o2) {
                return Character.compare(o1.getTask().getNumLetter(), o2.getTask().getNumLetter());
            }
        };
        for(List<TeamTaskStatus> value : mp.values()) {
            Collections.sort(value, cmp);
        }
        return mp;
    }
}
