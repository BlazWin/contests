package com.blazwin.contests.service.utils;

import com.blazwin.contests.dao.*;
import com.blazwin.contests.entity.*;
import com.blazwin.contests.entity.enums.AttemptVerdict;
import com.blazwin.contests.entity.enums.ContestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class RowsCreator {
    @Autowired
    AcmRegionDao acmRegionDao;
    @Autowired
    AttemptDao attemptDao;
    @Autowired
    ContestDao contestDao;
    @Autowired
    LanguageDao languageDao;
    @Autowired
    ParticipantDao participantDao;
    @Autowired
    RegistrantDao registrantDao;
    @Autowired
    SchoolDao schoolDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    TeamDao teamDao;
    @Autowired
    TeamTaskStatusDao teamTaskStatusDao;

    public AcmRegion createRegion(String name) {
        AcmRegion entity = acmRegionDao.getById(1);
        if (entity != null)
            return entity;
        entity = new AcmRegion();
        entity.setName(name);
        acmRegionDao.save(entity);
        return entity;
    }
    public Language createLanguage(String name) {
        Language entity = languageDao.getById(1);
        if (entity != null)
            return entity;
        entity = new Language();
        entity.setName(name);
        languageDao.save(entity);
        return entity;
    }
    public School createSchool(String name, AcmRegion region) {
        School entity = schoolDao.getById(1);
        if (entity != null)
            return entity;
        entity = new School();
        entity.setName(name);
        entity.setRegion(region);
        schoolDao.save(entity);
        return entity;
    }
    public Participant createParticipant(String surname, School school) {
        Participant entity = new Participant();
        entity.setSurname(surname);
        entity.setSchool(school);
        //mock fields
        entity.setName("name");
        entity.setNickname("nickname"+Math.random());
        entity.setAddress("address");
        entity.setGraduation(new Date());
        entity.setTshirt("XLL");
        participantDao.save(entity);
        return entity;
    }
    public Team createTeam(String name, Participant first, Participant second, Participant third) {
        Team entity = new Team();
        entity.setName(name);
        entity.setFirst(first);
        entity.setSecond(second);
        entity.setThird(third);
        //mock fields
        entity.setFolder("folder");
        teamDao.save(entity);
        return entity;
    }
    public Contest createContest(String name, Date start) {
        Contest entity = new Contest();
        entity.setName(name);
        entity.setStart(start);
        //mock fields
        entity.setDuration(300);
        entity.setStatus(ContestStatus.FINISHED);
        contestDao.save(entity);
        return entity;
    }
    public Task createTask(String name, char numLetter, Contest contest) {
        Task entity = new Task();
        entity.setName(name);
        entity.setNumLetter(numLetter);
        entity.setContest(contest);
        //mock fields
        entity.setFolder("folder");
        taskDao.save(entity);
        return entity;
    }
    public Attempt createAttempt(Team team, Task task, Language lang, int time, AttemptVerdict verdict) {
        Attempt entity = new Attempt();
        entity.setTask(task);
        entity.setTeam(team);
        entity.setLanguage(lang);
        entity.setVerdict(verdict);
        entity.setTime(time);
        attemptDao.save(entity);
        return entity;
    }
    public Registrant createRegistrant(Contest contest, Team team) {
        Registrant entity = new Registrant();
        entity.setContest(contest);
        entity.setTeam(team);
        entity.setFullTeamName("");
        registrantDao.save(entity);
        return entity;
    }
}
