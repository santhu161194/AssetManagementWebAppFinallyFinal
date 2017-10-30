package org.medplus.assetmanagementwebapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.service.AssetService;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
	EmployeeService employeeService;

	@Autowired
	AssetService assetService;
	
	Employee employee;

	String msg;

	@RequestMapping("/viewEmployees")
	public ModelAndView viewEmployees(@RequestParam("role") String requestrole, @ModelAttribute("employee") Employee emp,
			BindingResult result) {
		ModelAndView mav = new ModelAndView();
		List<Employee> empls;
		try {
			empls = employeeService.getAllEmployees();
			mav.addObject("empl", empls);
			mav.addObject("requestrole", requestrole);
			mav.addObject("viewdetails", "View All Employees");
			mav.setViewName("ViewEmployees");
		} catch (EmployeeException e) {
			mav.addObject("requestrole", requestrole);
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("ViewEmployees");
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "/empl", method = RequestMethod.GET)
	public ModelAndView getFormEmployee() {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject(employee);
		mav.setViewName("Employee");
		return mav;
	}

	@InitBinder
	public final void initBinderUsuariosFormValidator(
			final WebDataBinder binder, final Locale locale) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
				locale);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(value = "/empl", method = RequestMethod.POST)
	public ModelAndView employeeData(@ModelAttribute("employee") Employee emp) {
		ModelAndView mav = new ModelAndView();
		String createdBy = "";
		try {
			String msg = employeeService.addEmployee(emp, createdBy);
		} catch (EmployeeException e) {
			msg+=e.getMessage();
		} catch (AuthenticationException e) {
			msg+=e.getMessage();
		}

		return mav;
	}

	@RequestMapping(value = "/UpdateEmployee", method = RequestMethod.GET)
	public ModelAndView updateEmployeeForm(@RequestParam("code") String empcode) {
		Employee employee = new Employee();
		ModelAndView mav = new ModelAndView();
		try {
			employee = employeeService.getEmployee(empcode);
			mav.addObject(employee);
			mav.addObject("viewdetails", "Update Employee Form");
			mav.setViewName("UpdateEmployee");
		} catch (EmployeeException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("UpdateEmployee");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/UpdateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmp(@ModelAttribute("employee") Employee emp,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		String rows = null;
		try {
			emp.setModifiedBy(request.getSession(false).getAttribute("username").toString());
			System.out.println(request.getSession(false).getAttribute("username").toString());
			rows = employeeService.updateEmployee(emp,request.getSession(false).getAttribute("username").toString());
			
			if (rows.equals("FAILURE")) {

				String msg = "record not inserted";
				mav.addObject("message", msg);
				mav.setViewName("UpdateEmployee");
				return mav;
			} else {
				String msg = "record  updated";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");
				return mav;
			}
		} catch (EmployeeException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("UpdateEmployee");
			return mav;
		} catch (AuthenticationException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("UpdateEmployee");
			return mav;
		}


	}

	@RequestMapping(value = "/removeRole", method = RequestMethod.GET)
	public ModelAndView removeRoleForm() {
		Employee employee = new Employee();
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
			rows = employeeService.removeEmployeeRole(employeeId, roleName,
					removedBy, new Date());
			if (rows.equals("FAILURE")) {
				String msg = "role not removed";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");

			} else {
				String msg = rows;
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");

			}
		} catch (EmployeeException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		} catch (AuthenticationException e) {
			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		}

		return mav;
	}


	@RequestMapping(value = "viewRequestAssetsOfEmployee", method = RequestMethod.GET)
	public ModelAndView viewRequestAssetsOfEmployeeForm(
			@RequestParam("code") String empcode) {
		ModelAndView mav = new ModelAndView();
		try {
			employee = employeeService.getEmployee(empcode);
		} catch (EmployeeException e) {
			mav.addObject("message", e);
		}
		mav.addObject(employee);
		mav.setViewName("ViewRequestAssetsOfEmp");
		return mav;
	}

	@RequestMapping(value = "/viewRequestAssetsOfEmployee", method = RequestMethod.POST)
	public ModelAndView viewRequestAssetsOfEmployeeForm(
			@ModelAttribute("employee") Employee emp, BindingResult result) {
		ModelAndView mav = new ModelAndView();

		List<Request> rows;

		try {
			rows = assetService.getAssetRequests("emp");
			mav.addObject("empl", rows);
		} catch (AssetException e) {
			mav.addObject("message", e);
		}

		return mav;
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView getForm() {
		Employee employee = new Employee();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject(employee);
		mav.addObject("viewdetails", "add Employee Form");
		mav.setViewName("AddEmployee");
		return mav;
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ModelAndView employeeData(@ModelAttribute("employee") Employee emp,HttpServletRequest request, HttpServletResponse response
			) {
		ModelAndView mav = new ModelAndView();
		String rows = "";
		try {
			emp.setCreatedBy(request.getSession(false).getAttribute("username").toString());
			rows = employeeService.addEmployee(emp,request.getSession(false).getAttribute("username").toString());
			
			if (rows.equals("FAILURE")) {
				String msg = "add employee failed";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");
			} else {
				String msg = " record added Successfully";
				mav.addObject("message", msg);
				mav.setViewName("AdminHome");

			}

		} catch (EmployeeException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		} catch (AuthenticationException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("AdminHome");
			return mav;
		}

		return mav;
	}


	// getting form for addRole
	@RequestMapping(value = "/addRole", method = RequestMethod.GET)
	public ModelAndView addRoleForm() {
		Employee employee = new Employee();
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

		String rows = "";
		try {
			try {
				rows = employeeService.addRole(roleId, roleName, addedBy,
						new Date());
			} catch (AuthenticationException e) {
				mav.addObject("message", e);
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

	@RequestMapping(value = "/addRoleToEmp", method = RequestMethod.GET)
	public ModelAndView addRoleToEmpForm() {
		employee=new Employee();
		ModelAndView mav = new ModelAndView();
		mav.addObject(employee);
		mav.addObject("viewdetails", "add Role To Employee Form");
		mav.setViewName("AddRoleToEmp");
		return mav;
	}

	@RequestMapping(value = "/addRoleToEmp", method = RequestMethod.POST)
	public ModelAndView addRoleToEmpForm(
			@RequestParam("employeeId") String employeeId,
			@RequestParam("roleName") String roleName,
			@RequestParam("addedBy") String addedBy) {
		ModelAndView mav = new ModelAndView();
		
		String rows = "";
		try {
			try {
				rows = employeeService.addRoleToEmployee(employeeId,
						roleName, addedBy, new Date());
			} catch (AuthenticationException e) {
				mav.addObject("message", e);
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
			emp = employeeService.getEmployee(employeeId);
			mav.addObject("empl", emp);

			mav.setViewName("ViewEmployee");

			return mav;
		} catch (EmployeeException e) {
			mav.setViewName("AdminHome");
			mav.addObject("message", e.getErrorMessage());
		}
		return mav;
	}

	@RequestMapping(value = "/getRole", method = RequestMethod.GET)
	public ModelAndView getEmployeeRoleForm(@RequestParam("code") String code) {
		ModelAndView mav = new ModelAndView();
		List<Map<Integer, String>> role;
		try {
			role = employeeService.getEmployeeRole(code);
			for (Map<Integer, String> rs : role) {
				System.out.println(rs);
			}
			mav.addObject("roles", role);
			mav.addObject("viewdetails", "Single Employee Role");
			mav.setViewName("GetEmpRole");
		} catch (EmployeeException e) {

			mav.addObject("message", e.getErrorMessage());
			mav.setViewName("GetEmpRole");
			return mav;
		}

		return mav;
	}

	@RequestMapping(value = "viewAssetsOfEmployee", method = RequestMethod.GET)
	public ModelAndView viewAssetsOfEmployeeForm(
			@RequestParam("code") String empcode) {
		ModelAndView mav = new ModelAndView();
		try {
			employee = employeeService.getEmployee(empcode);
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
			rows = assetService.getAssetsOfEmployee("emp");
			mav.addObject("asss", rows);
		} catch (AssetException e) {
			mav.addObject("message", e);
		}

		return mav;
	}

	

}
