package com.medplus.assetmanagementcore.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medplus.assetmanagementcore.dao.impl.EmployeeDaoImpl;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.service.EmployeeService;
import com.medplus.assetmanagementcore.utils.Encryption;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	Employee employee;
	@Autowired
	EmployeeDaoImpl employeeDao;

	public String addEmployee(Employee employee, String edpId, Date insertDate) {

		String password = Encryption.cryptWithMD5(employee.getPassword());
		employee.setPassword(password);
		if (employeeDao.insertEmployee(employee, edpId, insertDate) != 0)
			return "INSERTED";
		else
			return "NOT INSERTED";

	}

	public Employee getEmployee(String empId) {
		Employee emp = employeeDao.getEmployee(empId);

		System.out.println(emp.getEmployeeId() + " " + emp.getGender());
		return emp;

	}

	public String changePassword(String empId, String password,
			String newPassword) {

		if (employeeDao.updatePassword(empId, "someone",
				new java.sql.Date(new Date().getTime()), "123456", "123") != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}

	public String resetPassword(String empId, String changedBy,
			String newPassword) {
		if (employeeDao.resetPassword(empId, changedBy,
				new java.sql.Date(new Date().getTime()), "121") != 0)
			return "SUCCESS";
		else
			return "FAILURE";

	}


	public String authenticateEmployee(String empId, String password) {
		
		password=Encryption.cryptWithMD5(password);
		System.out.println(password);
		System.out.println(employeeDao.getEmployeePassword(empId));
		if(password.equals(employeeDao.getEmployeePassword(empId)))
		return "LOGIN SUCCESSFUL";
		else
		return "LOGIN FAILED";
	}

	public String updateEmployee(Employee employee, String updatedBy,
			Date updatedDate) {
		
		
			int rows = employeeDao.updateEmployeeInfo(employee, updatedBy, updatedDate);
			
			if(rows > 0)
				return "SUCCESS";
			else
			return "FAILURE";
					
		
		
				
	}
	
	
	public boolean isUserExisting(String empId) {
		Employee emp = employeeDao.getEmployee(empId);
		if(emp==null)
			return false;
		else
			return true;
	}


	public List<Employee> getAllEmployees() {
		String msg=null;
		
				
		List<Employee> rows = employeeDao.getEmployees();
			
			return rows;
		
	}



	public boolean addRole(String empId, List<Long> roleIdList, String addedBy,Date addedDate) {
		// TODO Auto-generated method stub
		return false;
	}

	public List< String> getEmployeeRole(String empId) {
		// TODO Auto-generated method stub
		return employeeDao.getRole(empId);
	}

	public List< String> getAllRole() {
		// TODO Auto-generated method stub
		return null;
	}



	public String removeEmployeeRole(String empid, String removedBy,
			Date removedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addRoleToEmp(String empId, List<Long> roleIdList,
			String addedBy,Date addedDate) {
		
		String msg=null;
		try {
			System.out.println(msg);
			int rows = employeeDao.addRoleToEmp(empId, roleIdList, addedBy, addedDate);
			if(rows > 0)
				 msg="SUCCESS";
			else
			msg="FAILURE";
		} catch (Exception e) {
			System.out.println("sql exception raised");
		}
		
		return msg;
	}

	public String addRole(int roleId, String roleName, String addedBy,
			Date addedDate) {
		
		
			String msg=null;
			try {
				int rows = employeeDao.addRole(roleId, roleName, addedBy, addedDate);
				
				if(rows > 0)
					 msg="SUCCESS";
				else
				msg="FAILURE";
			} catch (Exception e) {
				System.out.println("sql exception raised");
			}
			
			return msg;
			
	}

	public long getRoleId(String roleName) {
		// TODO Auto-generated method stub
		return 0;
	}




	
}
