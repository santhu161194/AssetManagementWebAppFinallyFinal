package com.medplus.assetmanagementcore.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.medplus.assetmanagementcore.utils.AssetStatusEnum;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;




@Component
public class Asset {
	private int assetId;

	private String serialNumber;

	private String assetName;

	//private enum assetType{Laptop,Desktop,Monitor,Mouse};
	private AssetTypeEnum assetType;
	private double cost;
	private AssetStatusEnum status;
	//private enum status{Available,Notavailable}
	private Date createdDate;
	private String createdBy;
	private String modifiedBy;
	//private Date dateModified;
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	private Date dateModified;
	public int getAssetId() {
		return assetId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public AssetTypeEnum getAssetType() {
		return assetType;
	}

	public void setAssetType(AssetTypeEnum assetType) {
		this.assetType = assetType;
	}

	public AssetStatusEnum getStatus() {
		return status;
	}

	public void setStatus(AssetStatusEnum status) {
		this.status = status;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public double getCost() {
		return cost;
	}
    
	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", serialNumber=" + serialNumber + ", assetName=" + assetName
				+ ", assetType=" + assetType + ", cost=" + cost + ", status=" + status + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", dateModified=" + dateModified + "]";
	}

	public void setCost(double cost) {
		this.cost = cost;
	};

	

}
