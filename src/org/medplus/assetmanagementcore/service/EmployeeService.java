package org.medplus.assetmanagementcore.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Employee;
import org.springframework.dao.DataAccessException;



public interface EmployeeService 

{
	
	public String addEmployee(Employee employee,String createdBy) throws EmployeeException,AuthenticationException;

	public String updateEmployee(Employee employee,String updatedBy) throws EmployeeException,AuthenticationException;

	public Employee getEmployee(String employeeId) throws EmployeeException;
	
	public List<Employee> getAllEmployees() throws EmployeeException;

	public String addRoleToEmployee(String employeeId,List<Long> roleIdList, String addedBy,Date addedDate) throws EmployeeException,AuthenticationException;
	
	public String addRole(int roleId, String roleName, String createdBy,Date addedDate) throws EmployeeException, AuthenticationException;
	
	public List<Map<Integer, String>> getEmployeeRole(String employeeId) throws EmployeeException;

	
	public String removeEmployeeRole(String employeeId,String roleName,String removedBy,Date removedDate) throws EmployeeException,AuthenticationException;
	
	public String changePassword(String employeeId,String oldPassword,String newPassword,String changedBy,Date changedDate) throws EmployeeException,AuthenticationException;
	
	public String resetPassword(String employeeId,String changedBy,String newPassword, Date date) throws EmployeeException,AuthenticationException;

	public List<Map<Integer, String>> getRole(String employeeId) throws EmployeeException, AuthenticationException;

	public String authenticateEmployee(String employeeId, String password) throws EmployeeException, DataAccessException, AuthenticationException;

	public List<String> checkRoles(String empID);
	
	public boolean isUserExisting(String empId) throws AuthenticationException;
	
}



	

