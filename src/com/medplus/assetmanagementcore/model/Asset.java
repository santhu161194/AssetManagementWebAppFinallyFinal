package com.medplus.assetmanagementcore.model;

import org.springframework.stereotype.Component;

@Component

public class Asset {
	private long assetId;

	private String serialNumber;

	private String assetName;

	private enum assetType{Laptop,Desktop,Monitor,Mouse};

	private double cost;

	private enum status{Available,Notavailable}

	public long getAssetId() {
		return assetId;
	}

	public void setAssetId(long assetId) {
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

	public void setCost(double cost) {
		this.cost = cost;
	};

	

}
