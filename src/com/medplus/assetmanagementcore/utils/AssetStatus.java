package com.medplus.assetmanagementcore.utils;

public enum AssetStatus {
	Available("A"), NotAvailable("N");
	
	public String value;
	AssetStatus(String value){
		this.value = value;
	}
}


