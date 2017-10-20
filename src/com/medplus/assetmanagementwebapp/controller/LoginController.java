package com.medplus.assetmanagementwebapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.service.EmployeeService;
@Scope("prototype")


@Controller

public class LoginController {
@Autowired	
EmployeeService employeeServiceImpl;

//getting loginform
@RequestMapping(value="/login",method=RequestMethod.GET)
public ModelAndView getLoginForm(){
	ModelAndView mav=new ModelAndView();
	

	mav.setViewName("Login");
	return mav;
}

//direct go to employee home ..temp
@RequestMapping(value="/emphome",method=RequestMethod.GET)
public ModelAndView getEmpHome(){
	ModelAndView mav=new ModelAndView();
	

	mav.setViewName("EmpHome");
	return mav;
}

//Logging in
@RequestMapping(value="/login",method=RequestMethod.POST)
public ModelAndView login(@RequestParam("username") String username,@RequestParam("password") String password){
	ModelAndView mav=new ModelAndView();
	
	String msg=null;
	
	String login= employeeServiceImpl.authenticateEmployee(username, password);
	System.out.println(login+""+password);
	if(login.equals("LOGIN FAILED"))
	{

		mav.setViewName("Login");
		mav.addObject("status","Invalid Credentials");
	}
	else
	{
		mav.addObject("msg", msg);
		mav.addObject("userid",username);
		/*mav.addObject("status","yes");*/
		mav.setViewName("home");
	}
	return mav;
}


//returning to home
@RequestMapping("/home")
public ModelAndView home(){
	ModelAndView mav=new ModelAndView();
	mav.setViewName("home");
	return mav;
}

//invalidate
@RequestMapping(value="/invalidate",method=RequestMethod.GET)
public ModelAndView invalidate(){
	ModelAndView mav=new ModelAndView();
	
	mav.addObject("status",null);
	mav.setViewName("Login");
	return mav;
}

@RequestMapping(value="/changePassword",method=RequestMethod.GET)
public ModelAndView changePassword(){
	ModelAndView mav=new ModelAndView();
	mav.setViewName("ChangePassword");
	return mav;
}

@RequestMapping(value="/changePassword",method=RequestMethod.POST)
public ModelAndView ChangePassword(
		@RequestParam("employeeID") String username,
		@RequestParam("oldpassword") String oldpassword,
		@RequestParam("newpassword") String newpassword,
		@RequestParam("changedBy") String changedBy
		){
	ModelAndView mav=new ModelAndView();
	
	
	String msg=employeeServiceImpl.changePassword(username, oldpassword, newpassword, changedBy, new Date());
	mav.addObject("status", msg);
	mav.setViewName("Login");
	return mav;
}

@RequestMapping(value="/resetPassword",method=RequestMethod.GET)
public ModelAndView resetPassword(){
	ModelAndView mav=new ModelAndView();
	mav.setViewName("ResetPassword");
	return mav;
}

@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
public ModelAndView ResetPassword(
		@RequestParam("employeeID") String username,
		
		@RequestParam("newpassword") String newpassword,
		@RequestParam("resetby") String resetby){
	ModelAndView mav=new ModelAndView();
	
	
	String msg=employeeServiceImpl.resetPassword(username, resetby, newpassword);
	mav.addObject("status", msg);
	mav.setViewName("Login");
	return mav;
}



}