package com.medplus.assetmanagementwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.service.EmployeeService;




@Controller
public class EmployeeController {
	@Autowired	
	EmployeeService employeeServiceImpl;
	@Autowired
	Employee employee;
	@Autowired
	Asset asset;
	
	//view employees
	@RequestMapping("/viewEmpls")
	public ModelAndView viewEmployees(@ModelAttribute("employee") Employee emp,BindingResult result){
		ModelAndView mav=new ModelAndView();
		List<Employee> empls=employeeServiceImpl.getAllEmployees();
		mav.addObject("empl", empls);
		mav.setViewName("ViewEmployees");
		return mav;
	}
	
	//getting form
	@RequestMapping(value="/empl",method=RequestMethod.GET)
		public ModelAndView getForm(){
			
			ModelAndView mav=new ModelAndView();
			mav.addObject(employee);
			mav.setViewName("Employee");
			return mav;
	}

	 @InitBinder
	 public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
	     final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	 }

	//Adding employee
	@RequestMapping(value="/empl",method=RequestMethod.POST)
	public ModelAndView employeeData( @ModelAttribute("employee") Employee emp/*,BindingResult result*/){
		ModelAndView mav=new ModelAndView();
		
		String rows = employeeServiceImpl.addEmployee(emp,"me", new Date());

		return mav;
	}
	
}
