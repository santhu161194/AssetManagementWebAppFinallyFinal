package org.medplus.assetmanagementcore.utils;

public class Queries {
	// Employee
	public static String updateEmployee = "update AssetManagement.tbl_employee set FirstName=?,LastName=?,Gender=?,MobileNumber=?,DateOfBirth=?,DateOfJoining=?,Address=?,ModifiedBy=?,DateModified=? where EmployeeId=?";

	public static String getAllEmployees = "select EmployeeId,FirstName,LastName,Password,Gender,MobileNumber,DateOfBirth,DateOfJoining,Address,CreatedBy,DateCreated,ModifiedBy,DateModified from AssetManagement.tbl_employee";

	public static String GET_EMPLOYEE = "select EmployeeId,FirstName,LastName,Password,Gender,MobileNumber,DateOfBirth,DateOfJoining,Address,CreatedBy,DateCreated,ModifiedBy,DateModified from AssetManagement.tbl_employee where employeeId=?";

	public static String addRole = "insert into AssetManagement.tbl_role values(?,?,?,?)";

	public static String addRoleToEmp = "insert into AssetManagement.tbl_role_mapping values(?,?,?,?)";

	public static String getAllRoles = "select RoleId,RoleName,CreatedBy,DateCreated from AssetManagement.tbl_role";

	public static String getRoleId = "select RoleId from AssetManagement.tbl_role where RoleName =?";

	public static String getRoleName = "select RoleName from AssetManagement.tbl_role where RoleId =?";

	public static String authenticateEmployee = "select EmployeeId from AssetManagement.tbl_employee where employeeId=? and Password=?";

	public static String getRoleOfEmployee = "select RoleId from AssetManagement.tbl_role_mapping where EmployeeId=?";

	public static String removeRole = "delete from AssetManagement.tbl_role_mapping where EmployeeId=? and RoleId=?";

	public static String employeeModification = "update AssetManagement.tbl_employee set ModifiedBy=?,DateModified=? where EmployeeId=?";

	public static String ADD_EMPLOYEE = "insert into AssetManagement.tbl_employee values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String ADD_EMPLOYEE_LOG = "insert into AssetManagement.tbl_employee_log values(?,?,?,?,?,?,?,?,?)";

	public static String UPDATE_EMPLOYEE_TO_LOG_QRY = "INSERT INTO AssetManagement.tbl_employee_log VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String UPDATE_EMPLOYEE = "UPDATE AssetManagement.tbl_employee SET EmployeeId = ?,FirstName = ?,LastName = ?,Gender = ?,MobileNumber = ?,DateOfBirth = ?,DateOfJoining = ?,Address = ?,ModifiedBy=?,DateModified = ? WHERE EmployeeId = ?";

	public static String UPDATE_PASSWORD = "UPDATE AssetManagement.tbl_employee set Password=? where EmployeeId=?";

	public static String addRoleLog = "insert into AssetManagement.tbl_role_log values(?,?,?,?)";

	public static String updateEmployeeLog = "update AssetManagement.tbl_employee_log set FirstName=?,LastName=?,Gender=?,MobileNumber=?,DateOfBirth=?,DateOfJoining=?,Address=?,ModifiedBy=?,DateModified=? where EmployeeId=?";

	public static String employeeModificationLog = "update AssetManagement.tbl_employee_log set CreatedBy=?,DateModified=? where EmployeeId=?";

	// Asset
	public static String getAllAssetRequests = "select EmployeeId,AssetType,RequestDate from AssetManagement.tbl_asset_request";

	public static String getEmployeeAsset = "select a.AssetId,a.SerialNumber,a.AssetName,a.AssetType,a.Cost,a.Status,a.CreatedBy,a.DateCreated from AssetManagement.tbl_asset  a inner join AssetManagement.tbl_asset_mapping am on a.AssetId=am.AssetId where am.AssignedTo=?";

	public static String getAssetByStatus = "select  AssetId,SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated,ModifiedBy,DateModified from AssetManagement.tbl_asset where Status=?";
	
	public static String getAsset = "select AssetId,SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated from AssetManagement.tbl_asset where assetId=?";
	
	public static String getAllAsset = "select  AssetId,SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated,ModifiedBy,DateModified from AssetManagement.tbl_asset";
	
	public static String getAssetRequestsByEmployee = "select EmployeeId,AssetType,RequestDate from AssetManagement.tbl_asset_request where EmployeeId=?";
	
	public static String postAssetRequest = "insert into AssetManagement.tbl_asset_request values(?,?,?)";
	
	public static String updateAssetStatus = "update AssetManagement.tbl_asset set Status=?,ModifiedBy=?,DateModified=? where AssetId=?";
	
	public static String addNewAsset = "insert into AssetManagement.tbl_asset(SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated) values(?,?,?,?,?,?,?)";
	
	public static String allocateAsset = "insert into AssetManagement.tbl_asset_mapping(AssignedTo,AssignedBy,AssetId,Status,HandOverDate)values(?,?,?,?,?)";

	public static String REMOVE_ENTRY_FROM_ASSET_MAPPING = "delete from AssetManagement.tbl_asset_mapping where AssignedTo=? and Status=?";
	
	public static String deallocateAsset = "delete from AssetManagement.tbl_asset_mapping where AssetId=?";
	
	public static String postNewAssetTypeRequest = "insert into AssetManagement.tbl_asset_request_unavailable values(?,?,?,?)";
	
	public static String getNewAssetTypeRequest = "select EmployeeId,AssetType,AssetName,RequestDate from AssetManagement.tbl_asset_request_unavailable ";
	
	public static String removeAssetRequest="delete from tbl_asset_request where EmployeeId=? and AssetType=?";

	// asset log
	public static String UPDATE_ASSET_TO_LOG_ = "insert into AssetManagement.tbl_asset_log values(?,?,?,?,?,?,?,?,?,?)";
	
	public static String addNewAssetLog = "insert into AssetManagement.tbl_asset_log(SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated) values(?,?,?,?,?,?,?)";
	
	public static String updateAssetStatusLog = "update AssetManagement.tbl_asset_log set Status=?,ModifiedBy=?,DateModified=? where AssetId=?";
	
	public static String allocateAssetLog = "insert into AssetManagement.tbl_asset_mapping_log(AssignedTo,AssignedBy,AssetId,Status,HandOverDate)values(?,?,?,?,?)";

	// log asset-mapping
	public static String getAssetMapping = "select AssignedTo,AssignedBy,AssetId,HandOverDate,ReturnDate,Status from AssetManagement.tbl_asset_mapping where AssetId=?";
	
	public static String getAssetMappingLog = "select AssignedTo,AssignedBy,AssetId,HandOverDate,ReturnDate,Status from AssetManagement.tbl_asset_mapping_log ";
	
	public static String UPDATE_ASSET_MAPPING_TO_LOG = "insert into AssetManagement.tbl_asset_mapping_log values(?,?,?,?,?,?)";

	public static String getAssetByType="select AssetId,SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated,ModifiedBy,DateModified from AssetManagement.tbl_asset where AssetType=? and Status='A'";

	public static String updateAsset="update tbl_asset set AssetName=?,SerialNumber=?,Cost=?,DateModified=?,ModifiedBy=? where AssetId=?";

	public static String getAllNewAssetRequests = "select EmployeeId,AssetType,AssetName,RequestDate from AssetManagement.tbl_asset_request_unavailable";

}
