package com.sis.onboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class WebController {
	@RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
        return "Index";
    }
	
	@RequestMapping(value="/onboarding",method = RequestMethod.GET)
    public String onboardingpage(){
        return "onboarding";
    }
	
	@RequestMapping(value="/onboarding/info",method = RequestMethod.GET)
    public String editpage(){
        return "dashboard";
    }
	
	@RequestMapping(value="/createrecord",method = RequestMethod.GET)
    public String createpage(){
        return "createRecord";
    }
}
