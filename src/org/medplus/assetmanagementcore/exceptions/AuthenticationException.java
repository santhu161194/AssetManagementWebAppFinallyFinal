
package org.medplus.assetmanagementcore.exceptions;

public class AuthenticationException extends Exception{
	String message;
	public AuthenticationException( String message) {
		this.message=message;
	}
	public String getErrorMessage(){
		return this.message;
	}

}
