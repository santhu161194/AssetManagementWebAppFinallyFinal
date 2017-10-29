package org.medplus.assetmanagementcore.model;

import java.util.Date;

import org.medplus.assetmanagementcore.utils.AssetAllocation;

public class AssetMapping {
	private String employeeId;

	private int roleId;

	private int assetId;

	private String assignedBy;

	private Date assignedDate;

	private Date returnDate;

	private AssetAllocation status;

	
	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getAssignedBy() {
		return assignedBy;
	}


	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}


	public Date getAssignedDate() {
		return assignedDate;
	}


	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}


	public Date getReturnDate() {
		return returnDate;
	}


	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}


	public AssetAllocation getStatus() {
		return status;
	}


	public void setStatus(AssetAllocation status) {
		this.status = status;
	}


	public int getAssetId() {
		return assetId;
	}


	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Asset Mapping{" + "EmployeeId" + employeeId + "RoleId" + roleId
				+ "AssetId" + assetId + "AssignedBy" + assignedBy
				+ "AssignedDate" + assignedDate + "ReturnDate" + returnDate
				+ "Status" + status + "}";
	}

}
