package com.medplus.assetmanagementcore.exceptions;

public class AssetException extends Exception{
	String message;
	public AssetException( String message) {
		this.message=message;
	}
	public String getErrorMessage(){
		return this.message;
	}

}
