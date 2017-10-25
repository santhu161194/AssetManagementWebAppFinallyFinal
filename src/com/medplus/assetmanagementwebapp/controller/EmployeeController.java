package com.medplus.assetmanagementwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.medplus.assetmanagementcore.dao.impl.EmployeeDaoImpl;
import com.medplus.assetmanagementcore.exceptions.AssetException;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.EmployeeService;
import com.medplus.assetmanagementcore.service.impl.AssetServiceImpl;
import com.medplus.assetmanagementcore.service.impl.EmployeeServiceImpl;





@Controller

public class EmployeeController {
	@Autowired	
	EmployeeService employeeServiceImpl;
	@Autowired
	Employee employee;
	@Autowired
	Asset asset;
	@Autowired
	EmployeeDaoImpl imp;
	@Autowired
	AssetServiceImpl assetServiceImpl;
	
	
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
		
		String rows = employeeServiceImpl.addEmployee(emp);

		return mav;
	}
	@RequestMapping(value="/Employee",method=RequestMethod.GET)
	public ModelAndView EmployeeForm(@RequestParam("code") String code)
	{
		ModelAndView mav=new ModelAndView();
		employee=employeeServiceImpl.getEmployee(code);
		mav.addObject(employee);
		mav.setViewName("Employee");
		return mav;
	}
	@RequestMapping(value="/Employee",method=RequestMethod.POST)
	public ModelAndView Emp(@ModelAttribute("employee") Employee emp,BindingResult result)
	{
		ModelAndView mav=new ModelAndView();
		
		String rows=employeeServiceImpl.updateEmployee(emp,"10",new Date());
		if(rows.equals(0))
		{
			String msg="record not inserted";
			mav.addObject("msg",msg);
			return mav;
		
		}
		else
		{
			return new ModelAndView("redirect:viewEmpls");
	}
	}
	//getting form for addRole
		@RequestMapping(value="/addRole",method=RequestMethod.GET)
			public ModelAndView addRoleForm(){
				
				ModelAndView mav=new ModelAndView();
				mav.addObject(employee);
				mav.setViewName("AddRole");
				return mav;
		}
		@RequestMapping(value="/addRole",method=RequestMethod.POST)
		public ModelAndView addRoleForm(@RequestParam("roleId") int roleId,@RequestParam("roleName") String roleName,@RequestParam("addedBy") String addedBy)
		{
			ModelAndView mav=new ModelAndView();
			
			String rows=employeeServiceImpl.addRole(roleId,roleName,addedBy,new Date());
			if(rows.equals(0))
			{
				String msg="record not inserted";
				mav.addObject("msg",msg);
				return mav;
			
			}
			else
			{
				return new ModelAndView("redirect:viewEmpls");
		}
		}
		//getting form for addRole
				@RequestMapping(value="/addRoleToEmp",method=RequestMethod.GET)
					public ModelAndView addRoleToEmpForm(){
						
						ModelAndView mav=new ModelAndView();
						mav.addObject(employee);
						mav.setViewName("AddRoleToEmp");
						return mav;
				}
				@RequestMapping(value="/addRoleToEmp",method=RequestMethod.POST)
				public ModelAndView addRoleToEmpForm(@RequestParam("employeeId") String employeeId,@RequestParam("roleId") List<Long> roleId,@RequestParam("addedBy") String addedBy)
				{
					ModelAndView mav=new ModelAndView();
					
					String rows=employeeServiceImpl.addRoleToEmp(employeeId, roleId, addedBy,new Date());
					if(rows.equals(0))
					{
						String msg="record not inserted";
						mav.addObject("msg",msg);
						return mav;
					
					}
					else
					{
						return new ModelAndView("redirect:viewEmpls");
				}
				}
				//getting form for getSigleEmployee
				@RequestMapping(value="/getSingleEmployee",method=RequestMethod.GET)
					public ModelAndView getSingleEmployeeForm(){
						
						ModelAndView mav=new ModelAndView();
						mav.addObject(employee);
						mav.setViewName("GetSingleEmployee");
						return mav;
				}
				@RequestMapping(value="/getSingleEmployee",method=RequestMethod.POST)
				public ModelAndView getSingleEmployee(@ModelAttribute("employee") Employee emp,@RequestParam("employeeId") String employeeId){
					ModelAndView mav=new ModelAndView();
				emp=employeeServiceImpl.getEmployee(employeeId);
					mav.addObject("empl",emp);
					System.out.println("hello");
					mav.setViewName("ViewEmployee");
					return mav;
				}
				@RequestMapping(value="/getEmployeeRole",method=RequestMethod.GET)
				public ModelAndView getEmployeeRoleForm(@RequestParam("code") String code)
				{
					ModelAndView mav=new ModelAndView();
					List<String> role=employeeServiceImpl.getEmployeeRole(code);
					for(String rs:role)
					{
						System.out.println(rs);
					}
					mav.addObject("roles",role);
					
					mav.setViewName("GetEmpRole");
					
					return mav;
				}
				@RequestMapping(value="/removeEmployeeRole",method=RequestMethod.GET)
				public ModelAndView removeEmployeeRoleForm(@RequestParam("code") String code)
				{
					ModelAndView mav=new ModelAndView();
					employeeServiceImpl.removeEmployeeRole("santhu", "EDP", "santhu", new Date());
					mav.addObject(employee);
					mav.setViewName("Employee");
					return mav;
				}	
				
				
				
				//from niharika
				@RequestMapping(value="viewRequestAssetsOfEmployee",method=RequestMethod.GET)
				public ModelAndView viewRequestAssetsOfEmployeeForm(@RequestParam("code") String empcode)
				{
					ModelAndView mav=new ModelAndView();
					employee= employeeServiceImpl.getEmployee(empcode);
					mav.addObject(employee);
					mav.setViewName("ViewRequestAssetsOfEmp");
					return mav;
				}
				@RequestMapping(value="/viewRequestAssetsOfEmployee",method=RequestMethod.POST)
				public ModelAndView viewRequestAssetsOfEmployeeForm(@ModelAttribute("employee") Employee emp,BindingResult result)
				{
					ModelAndView mav=new ModelAndView();
					
					List<Request> rows;
			
						try {
							rows = assetServiceImpl.getAssetRequests("emp");
							mav.addObject("empl",rows);
						} catch (AssetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					return mav;
				}
				@RequestMapping(value="viewAssetsOfEmployee",method=RequestMethod.GET)
				public ModelAndView viewAssetsOfEmployeeForm(@RequestParam("code") String empcode)
				{
					ModelAndView mav=new ModelAndView();
					employee= employeeServiceImpl.getEmployee(empcode);
					mav.addObject(employee);
					mav.setViewName("ViewAssetsOfEmp");
					return mav;
				}
				@RequestMapping(value="/viewAssetsOfEmployee",method=RequestMethod.POST)
				public ModelAndView viewAssetsOfEmployeeForm(@ModelAttribute("employee") Employee emp,BindingResult result)
				{
					ModelAndView mav=new ModelAndView();
					
					List<Asset> rows;
			
						try {
							rows = assetServiceImpl.getAssetsOfEmployee("emp");
							mav.addObject("asss",rows);
						} catch (AssetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					return mav;
				}
				
				
}
