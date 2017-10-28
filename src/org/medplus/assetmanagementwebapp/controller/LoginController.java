package org.medplus.assetmanagementwebapp.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@Autowired
	EmployeeService employeeService;

	Employee employee;

	// getting login form
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Login");
		return mav;
	}

	// Logging in
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("username") String username,
			@RequestParam("password") String password) throws IOException {
		ModelAndView mav = new ModelAndView();
	
		String login = null;
		
		
				try {
					
					login = employeeService
							.authenticateEmployee(username, password);
					
				} catch (DataAccessException | EmployeeException
						| AuthenticationException e) {
					mav.addObject("message", "INVALID USER ID");
					mav.setViewName("Login");
					return mav;
				}
			
			
		if (!login.equals("LOGIN SUCCESSFUL")) {
			mav.setViewName("Login");
			
			mav.addObject("message", login);
			return mav;
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			mav.addObject("username", username);
			System.out.println(request.getSession(false).getAttribute("username"));
			List<String> roles = null;
			roles = employeeService.checkRoles(username);
			session.setAttribute("role", roles);
			if (roles.contains("admin")) {
				mav.setViewName("AdminHome");
			} else if (roles.contains("edp")) {
				mav.setViewName("EDPHome");

			} else {
				mav.setViewName("EmployeeHome");

			}
		}

		return mav;
	}

	// invalidate
	@RequestMapping(value = "/invalidate", method = RequestMethod.GET)
	public String invalidate(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession(false);
		session.invalidate();
		mav.addObject("status", null);
		mav.setViewName("Login");
		return "redirect:login";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ChangePassword");
		return mav;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView ChangePassword(
			@RequestParam("employeeID") String username,
			@RequestParam("oldpassword") String oldpassword,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("employeeID") String changedBy) {
		ModelAndView mav = new ModelAndView();

		String msg = "";
		try {
			try {
				msg = employeeService.changePassword(username, oldpassword,
						newpassword, changedBy, new Date());
			} catch (AuthenticationException e) {
				msg += e.getMessage();
			}
		} catch (EmployeeException e) {
			msg += e.getMessage();
		}
		mav.addObject("message", msg);
		mav.setViewName("Login");
		return mav;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView resetPassword() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ResetPassword");
		return mav;
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView ResetPassword(
			@RequestParam("employeeID") String username,
			@RequestParam("newpassword") String newpassword,
			@RequestParam("resetby") String resetby) {
		ModelAndView mav = new ModelAndView();

		String msg = "";
		try {
			try {
				msg = employeeService.resetPassword(username, resetby,
						newpassword, new Date());
			} catch (EmployeeException e) {
				msg += e.getMessage();
			}
		} catch (AuthenticationException e) {
			msg += e.getMessage();
		}
		mav.addObject("message", msg);
		mav.setViewName("Login");
		return mav;
	}

	@RequestMapping(value = "/adminhome", method = RequestMethod.GET)
	public ModelAndView emphome(@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		request.getSession(false).setAttribute("username", username);
		mav.setViewName("AdminHome");
		return mav;

	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ModelAndView EmployeeHome(@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		request.getSession(false).setAttribute("username", username);
		mav.setViewName("EmployeeHome");
		return mav;
	}

	@RequestMapping(value = "/EDPHome", method = RequestMethod.GET)
	public ModelAndView EDPHome(@RequestParam("username") String username,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		request.getSession(false).setAttribute("username", username);
		mav.setViewName("EDPHome");
		return mav;
	}

}