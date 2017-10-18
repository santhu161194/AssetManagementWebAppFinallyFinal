package com.medplus.assetmanagementcore.model;

import java.util.Date;

import com.medplus.assetmanagementcore.utils.AssetStatusEnum;

public class AssetMapping {
	private String EmployeeId;
	
	private int RoleId;
	
	private int AssetId;
	
	private String AssignedBy;
	
	private Date AssignedDate;
	
	private Date ReturnDate;

	private AssetStatusEnum Status;
	
	
	public AssetStatusEnum getStatus() {
		return Status;
	}

	public void setStatus(AssetStatusEnum status) {
		Status = status;
	}

	public int getAssetId() {
		return AssetId;
	}

	public void setAssetId(int assetId) {
		AssetId = assetId;
	}

	public Date getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(Date returnDate) {
		ReturnDate = returnDate;
	}

	public String getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}

	public int getRoleId() {
		return RoleId;
	}

	public void setRoleId(int roleId) {
		RoleId = roleId;
	}

	public String getAssignedBy() {
		return AssignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		AssignedBy = assignedBy;
	}

	public Date getAssignedDate() {
		return AssignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		AssignedDate = assignedDate;
	}

}
