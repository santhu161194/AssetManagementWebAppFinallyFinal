package org.medplus.assetmanagementcore.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.medplus.assetmanagementcore.model.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

public interface EmployeeDao {

	/**
	 * This method adds an Employee into the database and also generates a login
	 * ID and password for him to login
	 * 
	 * @param Employee
	 *            pojo
	 * @return Number of rows Affected in DB
	 * 
	 * 
	 */
	public int insertEmployee(Employee employee) throws DataAccessException,
			DataIntegrityViolationException;

	/**
	 * This method allows to update Employee details when the existing employee
	 * object is passed
	 * 
	 * @param employee
	 * @return Number of rows affected in DB
	 * @throws DataAccessException
	 */
	public int updateEmployeeInfo(Employee employee) throws DataAccessException;

	/**
	 * This method gets an object of Employee for the provided Employee ID
	 * 
	 * @param empId
	 * @return An object of Employee whose Employee ID is passed
	 * @throws DataAccessException
	 */
	public Employee getEmployee(String empId) throws DataAccessException;

	/**
	 * Returns the List of all the Employees from DB
	 * 
	 * @return List Of Employees
	 * @throws DataAccessException
	 */
	public List<Employee> getEmployees() throws DataAccessException;

	/**
	 * Add a new Role into the Role Table
	 * 
	 * @param roleId
	 * @param roleName
	 * @param addedBy
	 * @param addedDate
	 * @return
	 * @throws DataAccessException
	 * @throws DataIntegrityViolationException
	 */
	public int addRole(int roleId, String roleName, String addedBy,
			Date addedDate) throws DataAccessException,
			DataIntegrityViolationException;

	/**
	 * Assign a Role to an Existing Employee
	 * 
	 * @param empId
	 * @param roleIdList
	 * @param addedBy
	 * @param addedDate
	 * @return
	 * @throws DataAccessException
	 * @throws DataIntegrityViolationException
	 */
	public int addRoleToEmp(String empId, String roleName,
			String addedBy, Date addedDate) throws DataAccessException,
			DataIntegrityViolationException;

	/**
	 * Gets Roles Assigned to an Employee
	 * 
	 * @param empid
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<Integer, String>> getRole(String empid)
			throws DataAccessException;

	/**
	 * Removes assigned Role from an Employee
	 * 
	 * @param empid
	 * @param roleName
	 * @param removedBy
	 * @return
	 * @throws DataAccessException
	 */
	public int removeRole(String empid, String roleName, String removedBy)
			throws DataAccessException;

	/**
	 * Validates whether a user with the given ID and Password exists
	 * 
	 * @param empID
	 * @param password
	 * @return
	 * @throws DataAccessException
	 */
	public String autheticateEmployee(String empID, String password)
			throws DataAccessException;

	/**
	 * Updates a user password after authenticating the Old Password
	 * 
	 * @param empId
	 * @param modifiedBy
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws DataAccessException
	 * @throws DataIntegrityViolationException
	 */
	public int updatePassword(String empId, String modifiedBy,
			String oldPassword, String newPassword) throws DataAccessException,
			DataIntegrityViolationException;

	/**
	 * Resets the user password
	 * 
	 * @param empId
	 * @param modifiedBy
	 * @param newPassword
	 * @return
	 * @throws DataAccessException
	 * @throws DataIntegrityViolationException
	 */
	public int resetPassword(String empId, String modifiedBy, String newPassword)
			throws DataAccessException, DataIntegrityViolationException;

}
