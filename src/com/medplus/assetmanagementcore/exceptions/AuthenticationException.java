
package com.medplus.assetmanagementcore.exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends Exception{
	String message;
	public AuthenticationException( String message) {
		this.message=message;
	}
	public String getErrorMessage(){
		return this.message;
	}

}
