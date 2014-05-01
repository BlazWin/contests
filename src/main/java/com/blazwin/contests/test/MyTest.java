package com.blazwin.contests.test;

import com.blazwin.contests.dao.ParticipantDao;
import com.blazwin.contests.entity.Participant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@Transactional
public class MyTest {
    @Autowired
    ParticipantDao participantDao;

    @Test
    public void testMerge() {
        List<Participant> list = participantDao.getAll();
        assertNotNull(list);
    }

}
