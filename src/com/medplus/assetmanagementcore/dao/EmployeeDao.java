package com.medplus.assetmanagementcore.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.medplus.assetmanagementcore.model.AssetMapping;
import com.medplus.assetmanagementcore.model.Employee;

public interface EmployeeDao {
	public int insertEmployee(Employee employee);//done
	public int updateEmployeeInfo(Employee employee,String updatedBy,Date updatedDate);//done
	public Employee getEmployee(String empId);//done
	
	public List<Employee> getEmployees();//done
	
	public List<Employee> getEmployeeByRole(String role);//done
	
	public String getEmployeePassword(String empId);
	
	public int addRole(int roleId, String roleName, String addedBy,Date addedDate);//done
	
	public int addRoleToEmp(String empId,List<Long> roleIdList, String addedBy,Date addedDate);//done
	
	public List<String> getRole(String empid);//done
	
	public Map<Integer,String> getAllRoles();
	
	public long getRoleId(String roleName);
	
	public int removeRole(String empid,String roleName,String removedBy,Date removedDate);
	
	//public int updatePassword(String empId,String changedBy,String updatedDate,String oldPassword,String newPassword);
	
	
	//public boolean resetPassword(String empId,String changedBy,String updatedDate,String newPassword);
	
	public String getRoleName(int roleId);
	public int employeeModification(String employeeId, String updatedBy,
			Date updatedDate);
	public int updatePassword(String empId, String changedBy, Date updatedDate,
			String oldPassword, String newPassword);
	public int resetPassword(String empId, String changedBy, Date updatedDate,
			String newPassword);
	
	
}
	

