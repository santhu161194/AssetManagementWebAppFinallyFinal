package com.medplus.assetmanagementcore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    Employee employee;
    
    
    //done
    private int updateEmployeeToLog(final Employee emp) {
		
		final Employee employee=getEmployee(emp.getEmployeeId());
		
		int rows=template.update(Queries.UPDATE_EMPLOYEE_TO_LOG_QRY,new PreparedStatementSetter() {
				
		public void setValues(PreparedStatement pst) throws SQLException {
			
			pst.setString(1,employee.getEmployeeId());
			pst.setString(2, employee.getFirstName());
			pst.setString(3, employee.getLastName());
			pst.setString(4, employee.getPassword());
			pst.setString(5, employee.getGender().value);
			pst.setLong(6, employee.getMobileNumber());
			pst.setDate(8, new java.sql.Date(employee
					.getDateOfJoin().getTime()));
			pst.setDate(7, new java.sql.Date(employee
					.getDateOfBirth().getTime()));
			

			pst.setString(9, employee.getAddress());
			pst.setString(10,employee.getCreatedBy());
			pst.setDate(11,new java.sql.Date(employee.getCreatedDate().getTime()));
			pst.setString(12, employee.getModifiedBy());
			
			pst.setDate(13,new java.sql.Date(employee.getModifiedDate().getTime()));
			
			
		}
	});
	
		
	return rows;
	
}
    
	@Override
	public int insertEmployee(final Employee employee) {
		int rows = template.update(Queries.ADD_EMPLOYEE,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, employee.getEmployeeId());
						pst.setString(2, employee.getFirstName());
						pst.setString(3, employee.getLastName());
						pst.setString(4, Encryption.cryptWithMD5(employee.getPassword()));
						pst.setString(5, employee.getGender().value.toString());
						pst.setString(6,
								String.valueOf(employee.getMobileNumber()));
						pst.setDate(7, new java.sql.Date(employee
								.getDateOfBirth().getTime()));
						pst.setDate(8, new java.sql.Date(employee
								.getDateOfJoin().getTime()));
						pst.setString(9, employee.getAddress());
						pst.setString(10,employee.getCreatedBy());
						pst.setDate(11,new java.sql.Date(new Date().getTime()));
						pst.setString(12, employee.getModifiedBy());
						
						pst.setDate(13,new java.sql.Date(new Date().getTime()));
						
					}

				});

		return rows;
	}

	@Override
	
	public int updateEmployeeInfo(final Employee employee) {
		
		int rows=updateEmployeeToLog(employee);
				
				 rows=template.update(Queries.UPDATE_EMPLOYEE,new PreparedStatementSetter() {
						
					public void setValues(PreparedStatement pst) throws SQLException {
						
						pst.setString(1, employee.getEmployeeId());
						pst.setString(2, employee.getFirstName());
						pst.setString(3, employee.getLastName());
					
						pst.setString(4, employee.getGender().value.toString());
						pst.setString(5,
								String.valueOf(employee.getMobileNumber()));
						pst.setDate(6, new java.sql.Date(employee
								.getDateOfBirth().getTime()));
						pst.setDate(7, new java.sql.Date(employee
								.getDateOfJoin().getTime()));
						pst.setString(8, employee.getAddress());
						pst.setString(9,employee.getModifiedBy());
						pst.setDate(10, new java.sql.Date(new Date().getTime()));
						pst.setString(11, employee.getEmployeeId());	
						
					}
				});
				return rows;
				
			}
	
//modifying modified date and modified by after  changing roles
	public int employeeModification(final String employeeId, final String updatedBy) {
		int row=template.update(Queries.employeeModification,new PreparedStatementSetter() {
				
			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setString(1,updatedBy);
				pst.setDate(2, new java.sql.Date(new Date().getTime()));
				pst.setString(3,employeeId);
				
				
			}
		});
		return row;
		
	}
	
	

	@Override
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
						employee.setCreatedBy(rs.getString(10));
						employee.setCreatedDate(rs.getDate(11));
						employee.setModifiedBy((rs.getString(12)));
						employee.setModifiedDate(rs.getDate(13));
						return employee;  
					}  
					
					else
						return null;
					
				}
			});  
		}  
	
	

	@Override
	public List<Employee> getEmployees() {
	
		List<Employee> emplist=template.query(Queries.getAllEmployees,new RowMapper<Employee>() {

	
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
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
				employee.setCreatedBy(rs.getString(10));
				employee.setCreatedDate(rs.getDate(11));
				employee.setModifiedBy((rs.getString(12)));
				employee.setModifiedDate(rs.getDate(13));
				return employee;  
				
			
			}
		});
		return emplist;
		
	}
	

	@Override
	public int removeRole(final String empId,final String roleName, final String removedBy) {
		
		
		int rows2=0;
		int rows3=0;
		int rows=template.update(Queries.removeRole,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setString(1,empId);
				pst.setLong(2,getRoleId(roleName));
				pst.setString(3,removedBy);
				pst.setDate(4, new java.sql.Date(new Date().getTime()));
			}
		
		});
		if(rows>0){
		rows2=template.update(Queries.employeeModification,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setString(1,removedBy);
				pst.setDate(2, new java.sql.Date(new Date().getTime()));
				pst.setString(3,empId);
				
				
			}
		});
		}
		if(rows2>0){
		rows3=template.update(Queries.employeeModificationLog,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setString(1,removedBy);
				pst.setDate(2, new java.sql.Date(new Date().getTime()));
				pst.setString(3,empId);
				
				
			}
		});
		}
		return rows3;
	}
	
	
	@Override
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
	@Override
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
	//done
	@Override
	public int addRole(final int roleId,final String roleName, final String addedBy,
			final Date addedDate) {
		int row=template.update(Queries.addRole,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				
				pst.setInt(1,roleId);
				pst.setString(2,roleName);
				pst.setString(3,addedBy);
				pst.setDate(4, new java.sql.Date(addedDate.getTime()));
			}
		});

		return row;
	
	}
	//done
	@Override
	public int addRoleToEmp(final String empId, List<Long> roleIdList,
			final String addedBy, final Date addedDate) {
		int rows = 0;
		for(final Long ls : roleIdList) {
			rows = template.update(Queries.addRoleToEmp,new PreparedStatementSetter() {
		
				@Override
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
		
	@Override
	public Map<Integer, String> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}
	//done
	@Override
	public long getRoleId(String roleName) {
		
			String sql = Queries.getRoleId;
			Object args[] = {roleName };
			int r= template.queryForObject(sql, args,(Integer.class));
			
			return r;
		
	}
	//done
	@Override
	public String getRoleName(int roleId) {
		String sql = Queries.getRoleName;
		Object args[] = {roleId };
		String r= template.queryForObject(sql, args,(String.class));
		
		return r;
	}
	
	
	
	
	/*public int getRoleName(int roleId) {
		String sql = Queries.getRoleId;
		Object args[] = {roleName };
		int r= template.queryForObject(sql, args,(Integer.class));
		
		return r;
		
	}*/
	//}



//done
	@Override
	public List<String> getRole(final String empId) {
		Object args[]={empId};
		List<String> roleIdList=template.query(Queries.getRoleOfEmployee,args,new RowMapper<String>()  {

			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				return getRoleName(rs.getInt(1));
			}
		});
	//System.out.println(roleIdList);
		return roleIdList;
	}

	@Override
	public List<Employee> getEmployeeByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getEmployeePassword(String empId) {
		Employee employee = getEmployee(empId);
		return employee.getPassword();
	}
	
	
}	