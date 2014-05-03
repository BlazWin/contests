package com.blazwin.contests.service;

import com.blazwin.contests.entity.*;
import com.blazwin.contests.entity.enums.AttemptVerdict;
import com.blazwin.contests.service.utils.RowsCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

@Transactional
public class SampleContestInitService {

    @Autowired
    RowsCreator rowsCreator;
    Language language;
    AcmRegion region;
    School school;
    Contest contest;
    List<Team> teams = new ArrayList<Team>();
    List<Task> tasks = new ArrayList<Task>();
    Date start = new Date();

    Resource mockTeamsResource;

    private void mockInit(String contestName) {
        language = rowsCreator.createLanguage("C++");
        region = rowsCreator.createRegion("Western region");
        school = rowsCreator.createSchool("BSUIR", region);
        contest = rowsCreator.createContest(contestName, start);
    }

    private void getTeams() {
        BufferedReader br = null;
        try {
            String curLine;
            br = new BufferedReader(new FileReader(mockTeamsResource.getFile()));
            while ((curLine = br.readLine()) != null) {
                Participant first = null, second = null, third = null;
                String[] strs = curLine.split(";");
                first = rowsCreator.createParticipant(strs[1], school);
                if (strs.length > 2) {
                    second = rowsCreator.createParticipant(strs[2], school);
                    if (strs.length > 3)
                        third = rowsCreator.createParticipant(strs[3], school);
                }
                teams.add(rowsCreator.createTeam(strs[0], first, second, third));
                rowsCreator.createRegistrant(contest, teams.get(teams.size() - 1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void mockTasks() {
        for(int i = 0; i < 10; i++)
            tasks.add(rowsCreator.createTask("task" + i, (char) ((int) 'A' + i), contest));
    }

    private void mockAttempts() {
        TreeMap<Integer, Double> taskDiff = new TreeMap<Integer, Double>();
        TreeMap<Integer, Double> teamPower = new TreeMap<Integer, Double>();
        for(Task task : tasks)
            taskDiff.put(task.getId(), Math.random() * 3.0);
        for(Team team : teams)
            teamPower.put(team.getId(), Math.random());
        for(Team team : teams) {
            double power = teamPower.get(team.getId());
            for (Task task : tasks) {
                double diff = taskDiff.get(task.getId());
                if (Math.random() > diff - power * 1.5 - 1.00) {
                    double rn = Math.random();
                    int tries = 0;
                    while (rn < 0.9 - 0.8 * power) {
                        rn /= 0.9 - 0.8 * power;
                        tries++;
                    }
                    rn = Math.random();
                    for (int i = 0; i < tries; i++)
                        rowsCreator.createAttempt(team, task, language, (int) (rn * 300), AttemptVerdict.WRONG_ANSWER);
                    if (Math.random() > diff - power * 1.5) {
                        rowsCreator.createAttempt(team, task, language, (int) (rn * 300), AttemptVerdict.ACCEPTED);
                    }
                }
            }
        }
    }

    public int doInit(String contestName) {
        teams = new ArrayList<Team>();
        tasks = new ArrayList<Task>();
        mockInit(contestName);
        getTeams();
        mockTasks();
        mockAttempts();
        return contest.getId();
    }

    public Resource getMockTeamsResource() {
        return mockTeamsResource;
    }

    public void setMockTeamsResource(Resource mockTeamsResource) {
        this.mockTeamsResource = mockTeamsResource;
    }

}
