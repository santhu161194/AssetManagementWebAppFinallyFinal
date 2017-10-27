package org.medplus.assetmanagementcore.model;

import java.util.Date;

public class NewTypeRequest {
	
	private String employeeId;
	
	private String assetType;
	
	private String assetName;
	
	private Date requestDate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		return "NewTypeRequest [employeeId=" + employeeId + ", assetType="
				+ assetType + ", assetName=" + assetName + ", requestDate="
				+ requestDate + "]";
	}

}
