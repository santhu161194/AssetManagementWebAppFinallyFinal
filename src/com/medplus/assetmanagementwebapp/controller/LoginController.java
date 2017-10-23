package com.medplus.assetmanagementwebapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.service.EmployeeService;



@Controller

public class LoginController {
@Autowired	
EmployeeService employeeServiceImpl;
@Autowired
Employee employee;
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
public ModelAndView login(HttpServletRequest request, HttpServletResponse response,@RequestParam("username") String username,@RequestParam("password") String password){
	ModelAndView mav=new ModelAndView();
	String msg=null;
	String login= employeeServiceImpl.authenticateEmployee(username, password);
	System.out.println(login+""+password);
	if(!login.equals("LOGIN SUCCESSFUL"))
	{

		mav.setViewName("Login");
		mav.addObject("status",login);
	}
	else
	{
		HttpSession session=request.getSession();
		session.setAttribute("username", username);
		
		
		List<String> roles=employeeServiceImpl.getRole(username);
		
		session.setAttribute("role",roles );
		if(roles.contains("admin"))
		{
		mav.setViewName("adminhome");
		}
		else if(roles.contains("edp"))
		{
		mav.setViewName("edphome");
		}
		else if(roles.contains("emp"))
		{
			mav.setViewName("emphome");
		}
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
public ModelAndView invalidate(HttpServletRequest request, HttpServletResponse response){
	ModelAndView mav=new ModelAndView();
	HttpSession session=request.getSession(false);
	session.invalidate();
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