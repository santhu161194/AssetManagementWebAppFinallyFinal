package org.medplus.assetmanagementwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.medplus.assetmanagementcore.dao.impl.EmployeeDaoImpl;
import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.service.impl.AssetServiceImpl;
import org.medplus.assetmanagementcore.service.impl.EmployeeServiceImpl;
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





@Controller

public class EmployeeController {
	@Autowired	
	EmployeeServiceImpl employeeServiceImpl;
	
	Employee employee;

	@Autowired
	AssetServiceImpl assetServiceImpl;
	
	
	//view employees
	@RequestMapping("/viewEmpls")
	public ModelAndView viewEmployees(@ModelAttribute("employee") Employee emp,BindingResult result){
		ModelAndView mav=new ModelAndView();
		List<Employee> empls=null;
		try {
			empls = employeeServiceImpl.getAllEmployees();
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String createdBy="";
		try {
			String rows = employeeServiceImpl.addEmployee(emp,createdBy);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mav;
	}
	@RequestMapping(value="/Employee",method=RequestMethod.GET)
	public ModelAndView EmployeeForm(@RequestParam("code") String code)
	{
		ModelAndView mav=new ModelAndView();
		try {
			employee=employeeServiceImpl.getEmployee(code);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.addObject(employee);
		mav.setViewName("Employee");
		return mav;
	}
	@RequestMapping(value="/Employee",method=RequestMethod.POST)
	public ModelAndView Emp(@ModelAttribute("employee") Employee emp,BindingResult result)
	{
		ModelAndView mav=new ModelAndView();
		
		String rows="0";
		try {
			rows = employeeServiceImpl.updateEmployee(emp,"10");
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		
				
				
				@RequestMapping(value="/removeEmployeeRole",method=RequestMethod.GET)
				public ModelAndView removeEmployeeRoleForm(@RequestParam("code") String code)
				{
					ModelAndView mav=new ModelAndView();
					try {
						employeeServiceImpl.removeEmployeeRole("santhu", "EDP", "santhu", new Date());
					} catch (EmployeeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AuthenticationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mav.addObject(employee);
					mav.setViewName("Employee");
					return mav;
				}	
				
				
				
				//from niharika
				@RequestMapping(value="viewRequestAssetsOfEmployee",method=RequestMethod.GET)
				public ModelAndView viewRequestAssetsOfEmployeeForm(@RequestParam("code") String empcode)
				{
					ModelAndView mav=new ModelAndView();
					try {
						employee= employeeServiceImpl.getEmployee(empcode);
					} catch (EmployeeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
				
				@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
				public ModelAndView gettForm() {
                    Employee employee=new Employee();
					ModelAndView mav = new ModelAndView();
					mav.addObject(employee);
					mav.addObject("viewdetails", "add Employee Form");
					mav.setViewName("AddEmployee");
					return mav;
				}	
				@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
				public ModelAndView employeeData(@ModelAttribute("employee") Employee emp,@RequestParam("username") String addedBy) {
					ModelAndView mav = new ModelAndView();
					String rows = "";
					try {

						rows = employeeServiceImpl.addEmployee(emp,addedBy);
						mav.setViewName("AdminHome");
						if (rows.equals("FAILURE")) {
							String msg = "add employee failed";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");
						} else {
							String msg = " record added Successfully";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");
							System.out.println("I GOT CALLED1");

						}

					} catch (EmployeeException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
						return mav;
					} catch (AuthenticationException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
						return mav;
					}

					return mav;
				}

				// getting form for addRole
				@RequestMapping(value = "/addRole", method = RequestMethod.GET)
				public ModelAndView addRoleForm() {
                    Employee employee=new Employee();
					ModelAndView mav = new ModelAndView();
					mav.addObject(employee);
					mav.addObject("viewdetails", "add Role Form");
					mav.setViewName("AddRole");
					return mav;
				}

				@RequestMapping(value = "/addRole", method = RequestMethod.POST)
				public ModelAndView addRoleForm(@RequestParam("roleId") int roleId,
						@RequestParam("roleName") String roleName,
						@RequestParam("addedBy") String addedBy) {
					ModelAndView mav = new ModelAndView();

					String rows="";
					try {
						try {
							rows = employeeServiceImpl.addRole(roleId, roleName, addedBy, new Date());
						} catch (AuthenticationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (rows.equals("FAILURE")) {
							String msg = "record not inserted";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");

						} else {
							String msg = "role added successfully";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");

						}
					} catch (EmployeeException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
						return mav;
					}
					return mav;
				}
				// getting form for addRole
				@RequestMapping(value = "/addRoleToEmp", method = RequestMethod.GET)
				public ModelAndView addRoleToEmpForm() {

					ModelAndView mav = new ModelAndView();
					mav.addObject(employee);
					mav.addObject("viewdetails", "add Role To Employee Form");
					mav.setViewName("AddRoleToEmp");
					return mav;
				}

				@RequestMapping(value = "/addRoleToEmp", method = RequestMethod.POST)
				public ModelAndView addRoleToEmpForm(
						@RequestParam("employeeId") String employeeId,
						@RequestParam("roleId") List<Long> roleId,
						@RequestParam("addedBy") String addedBy) {
					ModelAndView mav = new ModelAndView();

					String rows="";
					try {
						try {
							rows = employeeServiceImpl.addRoleToEmployee(employeeId, roleId, addedBy, new Date());
						} catch (AuthenticationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (rows.equals("FAILURE")) {
							String message = "record not inserted";
							mav.addObject("message", message);
							return mav;

						} else {
							String message = "Employee role added successfully";
							mav.addObject("message", message);
							mav.setViewName("AdminHome");

						}

					} catch (EmployeeException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
					}
					return mav;
				}
				// getting form for getSigleEmployee
				@RequestMapping(value = "/getSingleEmployee", method = RequestMethod.GET)
				public ModelAndView getSingleEmployeeForm() {

					ModelAndView mav = new ModelAndView();
					mav.addObject(employee);
					mav.addObject("viewdetails", "Get Single Employee");
					mav.setViewName("GetSingleEmployee");
					return mav;
				}

				@RequestMapping(value = "/getSingleEmployee", method = RequestMethod.POST)
				public ModelAndView getSingleEmployee(
						@ModelAttribute("employee") Employee emp,
						@RequestParam("employeeId") String employeeId) {
					ModelAndView mav = new ModelAndView();
					try {
						emp = employeeServiceImpl.getEmployee(employeeId);
						mav.addObject("empl", emp);

						mav.setViewName("ViewEmployee");

						return mav;
					} catch (EmployeeException e) {
						// TODO Auto-generated catch block
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
					}
					return mav;
				}
				@RequestMapping(value = "/getEmployeeRole", method = RequestMethod.GET)
				public ModelAndView getEmployeeRoleForm(@RequestParam("code") String code) {
					ModelAndView mav = new ModelAndView();
					List<String> role;
					role = employeeServiceImpl.checkRoles(code);
					for (String rs : role) {
						System.out.println(rs);
					}
					mav.addObject("roles", role);
					mav.addObject("viewdetails", "Single Employee Role");
					mav.setViewName("GetEmpRole");

					return mav;
				}
				@RequestMapping(value = "viewAssetsOfEmployee", method = RequestMethod.GET)
				public ModelAndView viewAssetsOfEmployeeForm(
						@RequestParam("code") String empcode) {
					ModelAndView mav = new ModelAndView();
					try {
						employee = employeeServiceImpl.getEmployee(empcode);
						mav.addObject(employee);
						mav.addObject("viewdetails", "view Assets Of Employee");
						mav.setViewName("ViewAssetsOfEmp");
					} catch (EmployeeException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
					}

					return mav;
				}

				@RequestMapping(value = "/viewAssetsOfEmployee", method = RequestMethod.POST)
				public ModelAndView viewAssetsOfEmployeeForm(
						@ModelAttribute("employee") Employee emp, BindingResult result) {
					ModelAndView mav = new ModelAndView();

					List<Asset> rows;

					try {
						rows = assetServiceImpl.getAssetsOfEmployee("emp");
						mav.addObject("asss", rows);
					} catch (AssetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return mav;
				}

				

				@RequestMapping(value = "/removeRole", method = RequestMethod.GET)
				public ModelAndView removeRoleForm() {

					ModelAndView mav = new ModelAndView();
					mav.addObject(employee);
					mav.addObject("viewdetails", "remove Role Form");
					mav.setViewName("RemoveRole");
					return mav;
				}

				@RequestMapping(value = "/removeRole", method = RequestMethod.POST)
				public ModelAndView RemoveRoleForm(
						@RequestParam("employeeId") String employeeId,
						@RequestParam("roleName") String roleName,
						@RequestParam("removedBy") String removedBy) {
					ModelAndView mav = new ModelAndView();

					String rows = null;
					try {
						try {
							rows = employeeServiceImpl.removeEmployeeRole(employeeId, roleName, removedBy,
									new Date());
						} catch (AuthenticationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (rows.equals("FAILURE")) {
							String msg = "role not removed";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");

						} else {
							String msg = "role removed successfully";
							mav.addObject("message", msg);
							mav.setViewName("AdminHome");

						}
					} catch (EmployeeException e) {
						mav.setViewName("AdminHome");
						mav.addObject("message", e.getErrorMessage());
					}

					return mav;
				}


}
