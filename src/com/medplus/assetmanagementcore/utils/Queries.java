package com.medplus.assetmanagementcore.utils;



public class Queries {
	public static String ADD_EMPLOYEE="insert into AssetManagement.tbl_employee values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String GET_EMPLOYEE="select * from AssetManagement.tbl_employee where  EmployeeId=?";
	public static String GET_ALL_EMPLOYEES="select * from AssetManagement.tbl_employee";
	public static String UPDATE_PASSWORD="UPDATE AssetManagement.tbl_employee set Password=? where EmployeeId=?";
	public static String UPDATE_EMPLOYEE="UPDATE AssetManagement.tbl_employee SET EmployeeId = ?,FirstName = ?,LastName = ?,Password = ?,Gender = ?,MobileNumber = ?,DateOfBirth = ?,DateOfJoining = ?,Address = ?,ModifiedBy=?,DateModified = ? WHERE EmployeeId = ?";
	
	
	
	//Asset Queries
	public static  String getAllAssetRequests = "select * from tbl_asset_request";
	public static  String getEmployeeAsset ="select * from tbl_asset where =?";;
	public static String getAssetByStatus = "select * from tbl_asset where Status=?";
	public static String getAsset="select * from tbl_asset where assetId=?";
	public static String getAllAsset="select * from tbl_asset";
	public static String getAssetRequestsByEmployee="select * from tbl_asset_request where EmployeeId=?";
	public static String postAssetRequest="insert into tbl_asset_request values(?,?,?)";
	public static String updateAssetStatus="update tbl_asset set Status=? where AssetId=?";
	public static String addNewAsset="insert into tbl_asset(SerialNumber,AssetName,AssetType,Cost,Status,CreatedBy,DateCreated) values(?,?,?,?,?,?,?)";
	public static String ALLOCATE_ASSET="INSERT INTO `AssetManagement`.`tbl_asset_mapping`(`AssignedTo`,`AssignedBy`,`AssetId`,`HandOverDate`,`ReturnDate`,`Status`)VALUES(?,?,?,?,?,?);";

	
	
	//Role Queries
	public static String ADD_ROLE="insert into tbl_role values(?,?,?,?)";
	public static String ADD_ROLE_TO_EMPLOYEE="insert into tbl_role_mapping values(?,?,?,?)";
	public static String GET_ROLE_ID="select RoleId from tbl_role where RoleName =?";
}
