package com.blazwin.contests.controller;

import com.blazwin.contests.service.GlobalService;
import com.blazwin.contests.service.SampleContestInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/")
public class FrontPageController {

    @Autowired
    GlobalService globalService;
    @Autowired
    SampleContestInitService sampleContestInit;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allContest(ModelMap model) {
        globalService.getAllContests(model);
        return "all";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newContest() {
        int contestId = sampleContestInit.doInit(new Date().toString());
        globalService.processContest(contestId);
        return "redirect:" + "/contest/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String frontPage(@PathVariable("id") Integer contestId, ModelMap model) {
        globalService.getDataForTable(contestId, model);
        return "index";
    }
}
