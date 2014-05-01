package com.blazwin.contests.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{id}")
public class FrontPageController {


    @RequestMapping(method = RequestMethod.GET)
    public String frontPage(@PathVariable("id") Integer contestId, ModelMap model) {
        return "index";
    }
}
