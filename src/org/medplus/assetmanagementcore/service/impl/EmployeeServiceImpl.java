package org.medplus.assetmanagementcore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.medplus.assetmanagementcore.dao.impl.EmployeeDaoImpl;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.utils.Encryption;
import org.medplus.assetmanagementcore.validations.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDaoImpl employeeDaoImpl;

	public boolean isUserExisting(String empId) throws AuthenticationException {
		Employee emp ;
		try{
				emp = employeeDaoImpl.getEmployee(empId);
		}
		catch(NullPointerException e)
		{
			throw new AuthenticationException("The User ID doesnt exist");
		}
		if (emp == null)
			return false;
		else
			return true;
	}

	@Override
	public String addEmployee(Employee employee, String createdBy)
			throws AuthenticationException, EmployeeException {
		if (!checkRoles(createdBy).contains("admin")) {
			AuthenticationException authException = new AuthenticationException(
					"You need to be an administrator");
			throw authException;
		}
		String msg = null;
		employee.setCreatedBy(createdBy);
		try

		{
			int rows = employeeDaoImpl.insertEmployee(employee);

			if (rows > 0)

				msg = "SUCCESS";
			else

				msg = "FAILURE";

			return msg;

		} catch (DuplicateKeyException e) {

			EmployeeException employeeException = new EmployeeException(
					"employee id already existed please choose another employee id");

			throw employeeException;
		}

	}

	@Override
	public String updateEmployee(Employee employee, String updatedBy)
			throws EmployeeException, AuthenticationException {
		if (!checkRoles(updatedBy).contains("admin")) {
			AuthenticationException authException = new AuthenticationException(
					"You need to be an administrator");
			throw authException;
		}
		
		String msg = null;
		try {
			int rows = employeeDaoImpl.updateEmployeeInfo(employee);

			if (rows > 0)
				msg = "SUCCESS";
			else
				msg = "FAILURE";
		} catch (Exception e) {
			EmployeeException invalidEmployeeException = new EmployeeException(
					"Invalid details Entered");
			throw invalidEmployeeException;
		}

		return msg;

	}

	public Employee getEmployee(String empId) throws EmployeeException {
		String message = "";
		message = CommonValidations.isValidNumberForString(empId);
		if (!message.equals("valid")) {
			EmployeeException employeeException = new EmployeeException(
					message);
			throw employeeException;
		}
		Employee emp = employeeDaoImpl.getEmployee(empId);

		return emp;

	}

	public String changePassword(String empId, String password,
			String newPassword, String changedBy, Date changedDate) throws EmployeeException, AuthenticationException {
		if(!isUserExisting(empId))
		{
			EmployeeException employeeException = new EmployeeException(
					"User ID entered Doesnt exist");
			throw employeeException;
		}
		if (employeeDaoImpl.updatePassword(empId, changedBy, password,
				newPassword) != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}

	public String resetPassword(String empId, String changedBy,
			String newPassword,Date modifiedDate) throws AuthenticationException {
		if (!checkRoles(changedBy).contains("admin")) {
			AuthenticationException authException = new AuthenticationException(
					"You need to be an administrator");
			throw authException;
		}
		if (employeeDaoImpl.resetPassword(empId, changedBy, newPassword) != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}

	@Override
	 public List<Employee> getAllEmployees() throws EmployeeException {

	  List<Employee> employees=null;
	  try{
	   employees=employeeDaoImpl.getEmployees();
	   if(employees.size()==0)
	   {
	    EmployeeException invalidEmployeeException= new EmployeeException("No Employee Found");
	    throw invalidEmployeeException; 
	   }
	  }
	  catch(DataAccessException e){

	   EmployeeException employeeException= new EmployeeException("Failure to get All employees list");
	   throw employeeException;
	  }
	  
	  return employees;
	 
	  
	 }

	
	@Override
	public String addRoleToEmployee(String empId, String roleName,
			String addedBy,Date addedDate) throws AuthenticationException, EmployeeException {
		if (!checkRoles(addedBy).contains("admin")) {
			AuthenticationException authException = new AuthenticationException(
					"You need to be an administrat");
			throw authException;
		}
		String msg = null;
		try {

			int rows = employeeDaoImpl.addRoleToEmp(empId, roleName, addedBy,
					addedDate);
			if (rows > 0)
				msg = "SUCCESS";
			else
				msg = "FAILURE";
		} catch (DuplicateKeyException e) {
			EmployeeException employeeException= new EmployeeException("ROle Id already exists for the user");
			   throw employeeException;
		}
		catch (DataAccessException e) {
			EmployeeException employeeException= new EmployeeException("Invalid Data entered check your all details");
			   throw employeeException;
		}

		return msg;
	}

	 @Override
	 public String addRole(int roleId, String roleName, String addedBy, Date addedDate) throws EmployeeException, AuthenticationException {
	  
		 if (!checkRoles(addedBy).contains("admin")) {
				AuthenticationException authException = new AuthenticationException(
						"You need to be an administrat");
				throw authException;
			}
	  String message="";
	  
	   message=CommonValidations.isValidNumberForInt(roleId);
	  if(!message.equals("valid")){
	   EmployeeException invalidAssetException= new EmployeeException(message);
	   throw invalidAssetException;
	  }
	  try{
	  int rows = employeeDaoImpl.addRole(roleId,roleName,addedBy,addedDate);
	  
	  if(rows > 0)
	    message="SUCCESS";
	  else
	   message="FAILURE";
	  return message;
	  }
	  catch(DuplicateKeyException e) {
	   
	   EmployeeException invalidAssetException= new EmployeeException("role id already existed please choose another employee id");
	   throw invalidAssetException;
	  }
	  }

	 
	 @Override 
	public String authenticateEmployee(String empId, String password) throws EmployeeException, DataAccessException, AuthenticationException {
		String msg="";
		if (!isUserExisting(empId)) {
			msg="INVALID USER";
			EmployeeException invalidAssetException= new EmployeeException("role id already existed please choose another employee id");
			   throw invalidAssetException;
			
		} else {
			password = Encryption.cryptWithMD5(password);

			try {
				if (employeeDaoImpl.autheticateEmployee(empId, password) != null) {
					msg="LOGIN SUCCESSFUL";
				} else
					msg="Username and password do not match";
			} catch (NullPointerException e) {
				msg="Username and password do not match";
			}
		}
		return msg;
	}

	
	
	
	@Override
	public List<Map<Integer, String>> getEmployeeRole(String empId) throws EmployeeException {
		String message=CommonValidations.isValidNumberForString(empId);
		List<Map<Integer, String>> list = employeeDaoImpl.getRole(empId);
		if(!message.equals("valid"))
		  {
		   EmployeeException invalidEmployeeException=new EmployeeException("Invalid Employee ID");
		   throw invalidEmployeeException;
		  }
		if(list.size()==0)
		  {
		  EmployeeException invalidEmployeeException= new EmployeeException("No Special roles assigned to that employee");
		   throw invalidEmployeeException;
		  }
		
		return list;
	}
	
	


	@Override
	public List<Map<Integer, String>> getRole(String empId) throws EmployeeException, AuthenticationException {
		
		if (!isUserExisting(empId)) {
			EmployeeException invalidEmployeeException= new EmployeeException("INVALID EMPLOYEE");
			   throw invalidEmployeeException;
			
		}
		List<Map<Integer, String>> list = employeeDaoImpl.getRole(empId);
		if (list.size() == 0) {
			EmployeeException iae = new EmployeeException("No Employee Found");
			try {
				throw iae;
			} catch (EmployeeException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	
	public List<String> checkRoles(String empID) {
		List<Map<Integer, String>> list = null;
		try {
			list = getEmployeeRole(empID);
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
		List<String> roles = new ArrayList<String>();
		for (Map<Integer, String> map : list) {
			if (map.containsValue("admin")) {
				roles.add("admin");
			}
			if (map.containsValue("edp")) {
				roles.add("edp");
			}
			continue;
		}
		
		return roles;

	}
	
	@Override
	public String removeEmployeeRole(String empId, String roleName,
			String removedBy, Date removedDate) throws EmployeeException {

		String message = null;
		message = CommonValidations.isValidNumberForString(empId);
		message = CommonValidations.isValidNumberForString(roleName);
		if (!message.equals("valid")) {
			EmployeeException invalidEmployeeException = new EmployeeException(
					message);
			throw invalidEmployeeException;
		}
		if (checkRoles(removedBy).contains("admin")) {
			try {

				int rows = employeeDaoImpl.removeRole(empId, roleName,
						removedBy);
				if (rows > 0)
					message = "REMOVED";
				else
					message = "FAILURE";
			} catch (Exception e) {
				EmployeeException invalidEmployeeException = new EmployeeException(
						"please enter correct employee Id and rolename");
				throw invalidEmployeeException;
			}
		} else
			return "NO AUTHORITY";

		return message;

	}

}