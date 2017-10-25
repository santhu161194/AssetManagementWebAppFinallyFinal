package com.medplus.assetmanagementcore.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.medplus.assetmanagementcore.utils.AssetType;

@Component
public class Request {
	
	String employeeId;
	
	AssetType assetType;
	
	Date requestDate;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public AssetType getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {

		return "REQUEST[employeeId" + employeeId + "assetType" + assetType
				+ "requestDate" + requestDate + "]";
	}

}
