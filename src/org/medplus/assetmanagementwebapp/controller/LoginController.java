package org.medplus.assetmanagementwebapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller

public class LoginController {
@Autowired	
EmployeeServiceImpl employeeServiceImpl;

Employee employee;
//getting loginform
@RequestMapping(value="/login",method=RequestMethod.GET)
public ModelAndView getLoginForm(){
	ModelAndView mav=new ModelAndView();
	

	mav.setViewName("Login");
	return mav;
}



//Logging in
@RequestMapping(value="/login",method=RequestMethod.POST)
public String login(HttpServletRequest request, HttpServletResponse response,@RequestParam("username") String username,@RequestParam("password") String password) throws IOException{
	ModelAndView mav=new ModelAndView();
	String msg=null;
	String login = null;
	try {
		login = employeeServiceImpl.authenticateEmployee(username, password);
	} catch (EmployeeException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
		
		
		List<String> roles = null;
		roles = employeeServiceImpl.checkRoles(username);
		
		session.setAttribute("role",roles );
		if(roles.contains("admin"))//changes
		{
			return "redirect:adminhome?username="+username;
		//mav.setViewName("EmpHome");
		}
		else if(roles.contains("edp"))
		{
			return "redirect:EDPHome?username="+username;
		}
		else
		{
			return "redirect:employee?username="+username;
		}
	}
	
	return null;
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
		@RequestParam("employeeID") String changedBy
		){
	ModelAndView mav=new ModelAndView();
	
	
	String msg = null;
	try {
		msg = employeeServiceImpl.changePassword(username, oldpassword, newpassword, changedBy, new Date());
	} catch (EmployeeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	
	
	String msg = "";
	try {
		msg = employeeServiceImpl.resetPassword(username, resetby, newpassword,new Date());
	} catch (AuthenticationException e) {
		e.printStackTrace();
	}
	mav.addObject("status", msg);
	mav.setViewName("Login");
	return mav;
}



}