package org.medplus.assetmanagementcore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.medplus.assetmanagementcore.dao.EmployeeDao;
import org.medplus.assetmanagementcore.model.Employee;
import org.medplus.assetmanagementcore.utils.Encryption;
import org.medplus.assetmanagementcore.utils.Gender;
import org.medplus.assetmanagementcore.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	JdbcTemplate template;

	private int updateEmployeeToLog(final Employee emp)
			throws DataAccessException, DataIntegrityViolationException {

		final Employee employee = getEmployee(emp.getEmployeeId());

		int rows = template.update(Queries.UPDATE_EMPLOYEE_TO_LOG_QRY,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {

						pst.setString(1, employee.getEmployeeId());
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
						pst.setString(10, employee.getCreatedBy());
						pst.setDate(11, new java.sql.Date(employee
								.getCreatedDate().getTime()));
						pst.setString(12, employee.getModifiedBy());

						pst.setDate(13, new java.sql.Date(employee
								.getModifiedDate().getTime()));

					}
				});

		return rows;

	}

	@Override
	public int insertEmployee(final Employee employee)
			throws DataAccessException, DataIntegrityViolationException {
		int rows = template.update(Queries.ADD_EMPLOYEE,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, employee.getEmployeeId());
						pst.setString(2, employee.getFirstName());
						pst.setString(3, employee.getLastName());
						pst.setString(4,
								Encryption.cryptWithMD5(employee.getPassword()));
						pst.setString(5, employee.getGender().value.toString());
						pst.setString(6,
								String.valueOf(employee.getMobileNumber()));
						pst.setDate(7, new java.sql.Date(employee
								.getDateOfBirth().getTime()));
						pst.setDate(8, new java.sql.Date(employee
								.getDateOfJoin().getTime()));
						pst.setString(9, employee.getAddress());
						pst.setString(10, employee.getCreatedBy());
						pst.setDate(11, new java.sql.Date(new Date().getTime()));
						pst.setString(12, employee.getModifiedBy());

						pst.setDate(13, new java.sql.Date(new Date().getTime()));

					}

				});

		return rows;
	}

	@Override
	public int updateEmployeeInfo(final Employee employee)
			throws DataAccessException, DataIntegrityViolationException {

		int rows = updateEmployeeToLog(employee);

		rows = template.update(Queries.UPDATE_EMPLOYEE,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {

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
						pst.setString(9, employee.getModifiedBy());
						pst.setDate(10, new java.sql.Date(new Date().getTime()));
						pst.setString(11, employee.getEmployeeId());

					}
				});
		return rows;

	}

	@Override
	public Employee getEmployee(String empId) throws DataAccessException {

		Object args[] = { empId };
		return template.query(Queries.GET_EMPLOYEE, args,
				new ResultSetExtractor<Employee>() {

					public Employee extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						if (rs.next()) {
							Employee employee = new Employee();
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
	public List<Employee> getEmployees() throws DataAccessException {

		List<Employee> emplist = template.query(Queries.getAllEmployees,
				new RowMapper<Employee>() {

					public Employee mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Employee employee = new Employee();
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

	@Transactional
	@Override
	public int removeRole(final String empId, final String roleName,
			final String removedBy) throws DataAccessException,
			DataIntegrityViolationException {

		int employeedatemodificationrows = 0;
		int employeeLogrows = 0;
		int roleMappingrows = template.update(Queries.removeRole,
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement pst)
							throws SQLException {

						pst.setString(1, empId);
						pst.setLong(2, getRoleId(roleName));
						pst.setString(3, removedBy);
						pst.setDate(4, new java.sql.Date(new Date().getTime()));
					}

				});
		if (roleMappingrows > 0) {
			employeedatemodificationrows = template.update(Queries.employeeModification,
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pst)
								throws SQLException {

							pst.setString(1, removedBy);
							pst.setDate(2,
									new java.sql.Date(new Date().getTime()));
							pst.setString(3, empId);

						}
					});
		}
		if (employeedatemodificationrows > 0) {
			employeeLogrows = template.update(Queries.employeeModificationLog,
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pst)
								throws SQLException {

							pst.setString(1, removedBy);
							pst.setDate(2,
									new java.sql.Date(new Date().getTime()));
							pst.setString(3, empId);

						}
					});
		}
		return employeeLogrows;
	}

	public int updatePassword(final String empId, String changedBy,
			String oldPassword, final String newPassword)
			throws DataAccessException, DataIntegrityViolationException {
		oldPassword = Encryption.cryptWithMD5(oldPassword);
		int rows = 0;
		if (autheticateEmployee(empId, oldPassword) != null) {
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

	public int resetPassword(final String empId, String changedBy,
			final String newPassword) throws DataAccessException,
			DataIntegrityViolationException {

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

	@Override
	public int addRole(final int roleId, final String roleName,
			final String addedBy, final Date addedDate)
			throws DataAccessException, DataIntegrityViolationException {
		int row = template.update(Queries.addRole,
				new PreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement pst)
							throws SQLException {

						pst.setInt(1, roleId);
						pst.setString(2, roleName);
						pst.setString(3, addedBy);
						pst.setDate(4, new java.sql.Date(addedDate.getTime()));
					}
				});

		return row;

	}

	@Override
	public int addRoleToEmp(final String empId, List<Long> roleIdList,
			final String addedBy, final Date addedDate)
			throws DataAccessException, DataIntegrityViolationException {
		int rows = 0;
		for (final Long ls : roleIdList) {
			rows = template.update(Queries.addRoleToEmp,
					new PreparedStatementSetter() {

						@Override
						public void setValues(PreparedStatement pst)
								throws SQLException {

							pst.setString(1, empId);
							pst.setLong(2, ls.longValue());
							pst.setString(3, addedBy);
							pst.setDate(4,
									new java.sql.Date(addedDate.getTime()));

						}
					});
		}
		return rows;
	}

	private long getRoleId(String roleName) throws DataAccessException {

		String sql = Queries.getRoleId;
		Object args[] = { roleName };
		int r = template.queryForObject(sql, args, (Integer.class));

		return r;

	}

	private String getRoleName(int roleId) throws DataAccessException {
		String sql = Queries.getRoleName;
		Object args[] = { roleId };
		String r = template.queryForObject(sql, args, (String.class));

		return r;
	}

	@Override
	public List<Map<Integer, String>> getRole(final String empId)
			throws DataAccessException {
		Object args[] = { empId };

		List<Integer> roleIdList = template.query(Queries.getRoleOfEmployee,
				args, new RowMapper<Integer>() {

					@Override
					public Integer mapRow(ResultSet rs, int arg1)
							throws SQLException {
						
						return (rs.getInt(1));
					}
				});
		List<Map<Integer, String>> rolemap = new ArrayList<Map<Integer, String>>();
		for (int role : roleIdList) {
			Map<Integer, String> role1 = new HashMap<Integer, String>();
			role1.put(role, getRoleName(role));
			rolemap.add(role1);
			System.out.println(rolemap);
		}
		return rolemap;
	}

	@Override
	public String autheticateEmployee(String empID, String password)
			throws DataAccessException {
		String employeeID = null;
		try {

			Object[] inputs = { empID, password };
			employeeID = template.queryForObject(Queries.authenticateEmployee,
					inputs, String.class);
		} catch (EmptyResultDataAccessException e) {
			return employeeID;
		}
		return employeeID;
	}

}