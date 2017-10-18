package com.medplus.assetmanagementcore.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.medplus.assetmanagementcore.utils.AssetTypeEnum;
@Component
public class Request {
String employeeId;
AssetTypeEnum assetType;
Date requestDate;
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}
public AssetTypeEnum getAssetType() {
	return assetType;
}
public void setAssetType(AssetTypeEnum assetType) {
	this.assetType = assetType;
}
public Date getRequestDate() {
	return requestDate;
}
public void setRequestDate(Date requestDate) {
	this.requestDate = requestDate;
}


}
