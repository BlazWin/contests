package com.blazwin.contests.test;

import com.blazwin.contests.dao.GlobalDao;
import com.blazwin.contests.service.SampleContestInitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@Transactional
public class MyTest {

    @Autowired
    SampleContestInitService serv;
    @Autowired
    GlobalDao globalDto;

    @Test
    @Transactional
    public void test() {
        serv.doInit("name");
        globalDto.calcResults(1);
    }
}
