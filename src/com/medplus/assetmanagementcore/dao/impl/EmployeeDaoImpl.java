package com.medplus.assetmanagementcore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.medplus.assetmanagementcore.dao.EmployeeDao;
import com.medplus.assetmanagementcore.model.Employee;
import com.medplus.assetmanagementcore.utils.Encryption;
import com.medplus.assetmanagementcore.utils.Gender;
import com.medplus.assetmanagementcore.utils.Queries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	@Autowired
	JdbcTemplate template;
//needs to be updated with DB
/*	public int insertEmployee(final Employee employee, final String createdBy,
			final Date insertDate) {

		int rows = template.update(Queries.ADD_EMPLOYEE,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, employee.getEmployeeId());
						pst.setString(2, employee.getFirstName());
						pst.setString(3, employee.getLastName());
						pst.setString(4, employee.getPassword());
						pst.setString(5, employee.getGender().value.toString());
						pst.setString(6,
								String.valueOf(employee.getMobileNumber()));
						pst.setDate(7, new java.sql.Date(employee
								.getDateOfBirth().getTime()));
						pst.setDate(8, new java.sql.Date(employee
								.getDateOfJoin().getTime()));
						pst.setString(9, employee.getAddress());
						pst.setString(10, createdBy);
						pst.setDate(11, new java.sql.Date(new Date().getTime()));
						pst.setDate(12, null);
						pst.setDate(13, null);
					}

				});

		return rows;
	}*/
	
	public int insertEmployee (final Employee employee, final String createdBy,
			final Date insertDate) throws SQLException {
		final PreparedStatement pst2;
		int rows = template.update(Queries.ADD_EMPLOYEE,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
		pst.setString(1, employee.getEmployeeId());
		pst.setString(2, employee.getFirstName());
		pst.setString(3, employee.getLastName());
		pst.setString(4, employee.getPassword());
		pst.setString(5, employee.getGender().value.toString());
		pst.setString(6,
				String.valueOf(employee.getMobileNumber()));
		pst.setDate(7, new java.sql.Date(employee
				.getDateOfBirth().getTime()));
		pst.setDate(8, new java.sql.Date(employee
				.getDateOfJoin().getTime()));
		pst.setString(9, employee.getAddress());
		pst.setString(10, createdBy);
		pst.setDate(11, new java.sql.Date(new Date().getTime()));
		pst.setDate(12, null);
		pst.setDate(13, null);
		pst2=pst;
		int rows=template.update(Queries.ADD_EMPLOYEE, pst);
		/*int rows = template.update(Queries.ADD_EMPLOYEE);
				new PreparedStatementSetter() {

					public void setValues(pst)
							throws SQLException {
						
					}*/

				/*});*/

		return rows;
	}
//Not Working
	public Employee getEmployee(String empId) {
		
			Object args[]={empId};
			return template.query(Queries.GET_EMPLOYEE,args,new ResultSetExtractor<Employee>(){  

				public Employee extractData(ResultSet rs) throws SQLException,DataAccessException {   
					if(rs.next())
					{
						Employee employee=new Employee();  
						employee.setEmployeeId(rs.getString(1));
						employee.setFirstName(rs.getString(2)); 
						employee.setLastName(rs.getString(3));
						employee.setPassword(rs.getString(4)); 
						employee.setGender(Gender.getName(rs.getString(5)));
						employee.setMobileNumber(rs.getLong(6));
						employee.setDateOfBirth(rs.getDate(7));
						employee.setDateOfJoin(rs.getDate(8));
						employee.setAddress(rs.getString(9));
						String createdBy=rs.getString(10);
						Date dateUpdated=rs.getDate(11);
						String ModifiedBy=rs.getString(12);
						Date dateModified=rs.getDate(13);
						return employee;  
					}  
					
					else
						return null;
					
				}
			});  
		}  
	
//Working
	public String getEmployeePassword(String empId) {
		Employee employee = getEmployee(empId);
		return employee.getPassword();
	}
//Working
	public int updatePassword(final String empId, String changedBy,
			final Date updatedDate, String oldPassword, final String newPassword) {
		Employee employee = getEmployee(empId);
		String existingPassword = employee.getPassword();
		oldPassword = Encryption.cryptWithMD5(oldPassword);
		int rows = 0;
		if (oldPassword.equals(existingPassword))

		{
			rows = template.update(Queries.UPDATE_PASSWORD,
					new PreparedStatementSetter() {

						public void setValues(PreparedStatement pst)
								throws SQLException {
							pst.setString(1,
									Encryption.cryptWithMD5(newPassword));
							pst.setString(2, empId);

						}
					});

		}
		return rows;
	}
//working
	public int resetPassword(final String empId, String changedBy,
			Date updatedDate, final String newPassword) {

		int rows = 0;

		rows = template.update(Queries.UPDATE_PASSWORD,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, Encryption.cryptWithMD5(newPassword));
						pst.setString(2, empId);

					}
				});

		return rows;
	}
//Not done update needs to bhbe done to latest DB
	public int updateEmployeeInfo(final Employee employee, final String updatedBy,
			final Date updatedDate) {
		int rows=template.update(Queries.UPDATE_EMPLOYEE,new PreparedStatementSetter() {
				
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setString(1, employee.getFirstName());
				pst.setString(2, employee.getLastName());
				
				pst.setString(3, employee.getGender().toString());
				pst.setLong(4, employee.getMobileNumber());
				pst.setDate(6, new java.sql.Date(employee
						.getDateOfJoin().getTime()));
				pst.setDate(5, new java.sql.Date(employee
						.getDateOfBirth().getTime()));
				
	
				pst.setString(7, employee.getAddress());
			
				pst.setString(8,updatedBy);
				pst.setDate(11, new java.sql.Date(updatedDate.getTime()));
				pst.setString(10,employee.getEmployeeId());
				
				
			}
		});
		
			
		return rows;
		
	}
//Update to latest DB
	public List<Employee> getEmployees() {
		@SuppressWarnings("unchecked")
		List<Employee> emplist=template.query(Queries.GET_ALL_EMPLOYEES,new RowMapper() {

			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				Employee emp=new Employee();
				emp.setEmployeeId(rs.getString(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				/*emp.setPassword(rs.getString(4));*/
				
				emp.setGender(Gender.getName((rs.getString(5))));
				emp.setMobileNumber(rs.getLong(6));
				//emp.setDateOfJoin(rs.getDate(8));
				emp.setDateOfBirth(rs.getDate(7));
				emp.setAddress(rs.getString(9));
				
				
				
				return emp;
			}
		});
		return emplist;
		
	}

	public List<Employee> getEmployeebyRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}



	public List<Employee> getEmployeeByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}
//update to latest DB
	public int addRole(final int roleId,final String roleName, final String addedBy,
			final Date addedDate) {
		int rows=template.update(Queries.ADD_ROLE,new PreparedStatementSetter() {
			
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setInt(1,roleId);
				pst.setString(2,roleName);
				pst.setString(3,addedBy);
				pst.setDate(4, new java.sql.Date(addedDate.getTime()));
			}
		});
		return rows;
	
	}

	



	public int removeRole(String empid, String removedBy, Date removedDate) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Check with DB
	public int addRoleToEmp(final String empId, List<Long> roleIdList,
			final String addedBy, final Date addedDate) {
		int rows = 0;
		for(final Long ls : roleIdList) {
			rows = template.update(Queries.ADD_ROLE_TO_EMPLOYEE,new PreparedStatementSetter() {
		
				public void setValues(PreparedStatement pst) throws SQLException {
					
					pst.setString(1,empId);
					pst.setLong(2,ls.longValue());
					pst.setString(3, addedBy);
					pst.setDate(4,new java.sql.Date(addedDate.getTime()));
					
				}
			});
		}
		
		return rows;
	}
	//DB check
		public long getRoleId(String roleName) {
			
			String sql = Queries.GET_ROLE_ID;
			Object args[] = {roleName };
			int r= template.queryForObject(sql, args,(Integer.class));
			
			return r;
		
	}
		public List<String> getRole(String empid) {
			// TODO Auto-generated method stub
			return null;
		}
		public List<String> getAllRoles() {
			// TODO Auto-generated method stub
			return null;
		}
	}


