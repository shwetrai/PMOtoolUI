package com.sis.onboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
 
@Controller
@SessionAttributes("user")
public class WebController {
	
	public boolean isLoggedIn=false;  
	
	@RequestMapping(value="/",method = RequestMethod.GET)
    public String homepage(){
		isLoggedIn=true; 
        return "login";
    }
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logoutpage(){
		isLoggedIn=false; 
        return "login";
    }
	
	@RequestMapping(value="/onboarding",method = RequestMethod.GET)
    public String onboardingpage(){
		if(isLoggedIn)
			return "onboarding";
		else
			return "redirect:/";
    }
	
	@RequestMapping(value="/onboarding/info",method = RequestMethod.GET)
    public String editpage(){
		if(isLoggedIn)
			return "dashboard";
		else
			return "redirect:/";
    }
	
	@RequestMapping(value="/createrecord",method = RequestMethod.GET)
    public String createpage(){
		if(isLoggedIn)
			return "createRecord";
        else
			return "redirect:/";
    }
	
//	@RequestMapping(value="/user/create",method = RequestMethod.GET)
//    public String createpageUser(){
//		if(isLoggedIn)
//			return "createRecordUser";
//        else
//			return "redirect:/";
//    }
}
