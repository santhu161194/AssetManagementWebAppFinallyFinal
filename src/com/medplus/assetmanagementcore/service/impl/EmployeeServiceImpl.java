package com.medplus.assetmanagementcore.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.assetmanagementcore.dao.impl.EmployeeDaoImpl;
import com.medplus.assetmanagementcore.exceptions.EmployeeException;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.service.EmployeeService;
import com.medplus.assetmanagementcore.utils.Encryption;
@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
EmployeeDaoImpl employeeDaoImpl;
	
	public boolean isUserExisting(String empId) {
		Employee emp = employeeDaoImpl.getEmployee(empId);
		if(emp==null)
			return false;
		else
			return true;
	}
	
	@Override
	public String addEmployee(Employee employee) {
		String msg=null;
		
		int rows = employeeDaoImpl.insertEmployee(employee);
		
		if(rows > 0)
			 msg="SUCCESS";
		else
		{
			EmployeeException iae= new EmployeeException("No Employee Found");
			try {
				throw iae;
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	return msg;
	}

	@Override
	public String updateEmployee(Employee employee, String updatedBy, Date updatedDate) {
		
			//employee=employeeDaoImpl.getEmployee(employee.getEmployeeId());
		String msg=null;
		try {
			int rows = employeeDaoImpl.updateEmployeeInfo(employee, updatedBy, updatedDate);
			
			if(rows > 0)
				 msg="SUCCESS";
			else
			msg="FAILURE";
		} catch (Exception e) {
			System.out.println("sql exception raised");
		}
		
		return msg;
		
	}

	public Employee getEmployee(String empId) {
		Employee emp = employeeDaoImpl.getEmployee(empId);

		System.out.println(emp.getEmployeeId() + " " + emp.getGender());
		return emp;

	}


	public String changePassword(String empId, String password,
			String newPassword,String changedBy,Date changedDate) {

		if (employeeDaoImpl.updatePassword(empId, changedBy,
				changedDate, password, newPassword) != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}

	public String resetPassword(String empId, String changedBy,
			String newPassword) {
		

		if (employeeDaoImpl.resetPassword(empId, changedBy,
				new java.sql.Date(new Date().getTime()), newPassword) != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}


	

	@Override
	public List<Employee> getAllEmployees() {

		
		String msg=null;
		List<Employee> rows = employeeDaoImpl.getEmployees();
		System.out.println(rows.size());
		if(rows.size()==0)
		{
		EmployeeException iae= new EmployeeException("No Employee Found");
			try {
				throw iae;
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else 
			
		
		return rows;
		return rows;
		
	}
	
	@Override
	public String removeEmployeeRole(String empId,String roleName, String removedBy, Date removedDate) {
		
		String msg=null;
		if(removedBy.equals("15")) {
		try {
			int rows = employeeDaoImpl.removeRole(empId, roleName, removedBy, removedDate);
			
			if(rows > 0)
				 msg="REMOVED";
			else
			msg="NOT REMOVED";
		} catch (Exception e) {
			System.out.println("sql exception raised");
		}
		}
		else 
			return "NO AUTHORITY";
		
		return msg;
		
	}
	
	@Override
	public String addRoleToEmp(String empId, List<Long> roleIdList, String addedBy,Date addedDate) {
		
		String msg=null;
		try {
			
			int rows = employeeDaoImpl.addRoleToEmp(empId, roleIdList, addedBy, addedDate);
			if(rows > 0)
				 msg="SUCCESS";
			else
			msg="FAILURE";
		} catch (Exception e) {
			System.out.println("sql exception raised");
		}
		
		return msg;
	}

	@Override
	public String addRole(int roleId, String roleName, String addedBy, Date addedDate) {
		
		String msg=null;
		
			int rows = employeeDaoImpl.addRole(roleId, roleName, addedBy, addedDate);
			
			if(rows > 0)
				 msg="SUCCESS";
			else
			{
				EmployeeException iae= new EmployeeException("No Employee Found");
				try {
					throw iae;
				} catch (EmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		return msg;
			
	}

public String authenticateEmployee(String empId, String password) {
		if(!isUserExisting(empId))
		{
		return "INVALID USER";
		}
		else
		{
		password=Encryption.cryptWithMD5(password);
		System.out.println(password);
		try{
		if(password.equals(employeeDaoImpl.getEmployeePassword(empId)))
		{
		return "LOGIN SUCCESSFUL";
		}
		else
		return "Username and password do not match";
	}
catch(NullPointerException e)
		{
	return "Username and password do not match";
		}
		}
}



	
	

	@Override
	public List< String> getEmployeeRole(String empId) {
		List<String> list=employeeDaoImpl.getRole(empId);
		if(list.size()==0)
		{
		EmployeeException iae= new EmployeeException("No Employee Found");
			try {
				throw iae;
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return list;
	}

	@Override
	public Map<Integer, String> getAllRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleId(String roleName) {
		
		String msg=null;
		
		long rows = employeeDaoImpl.getRoleId(roleName);
		
		if(rows > 0)
			 msg="SUCCESS";
		else
		{
			EmployeeException iae= new EmployeeException("No Employee Found");
			try {
				throw iae;
			} catch (EmployeeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
	return msg;
	}

	

//repeated
	@Override
	public List<String> getRole(String empId) {
		
			List<String> list=employeeDaoImpl.getRole(empId);
			if(list.size()==0)
			{
			EmployeeException iae= new EmployeeException("No Employee Found");
				try {
					throw iae;
				} catch (EmployeeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			return list;
		}
	@Override
	public String getRoleName(int roleId) {
String msg=null;
		
		String rows = employeeDaoImpl.getRoleName(roleId);
		
		
			 msg="SUCCESS";
		
	return msg;
		
	}
}
