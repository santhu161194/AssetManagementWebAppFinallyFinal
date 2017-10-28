
package org.medplus.assetmanagementcore.exceptions;

public class AuthenticationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 789158704193722737L;
	String message;
	public AuthenticationException( String message) {
		this.message=message;
	}
	public String getErrorMessage(){
		return this.message;
	}

}
