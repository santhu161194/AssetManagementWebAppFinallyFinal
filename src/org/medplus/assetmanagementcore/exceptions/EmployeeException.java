package org.medplus.assetmanagementcore.exceptions;

public class EmployeeException extends Exception{
		/**
	 * 
	 */
	private static final long serialVersionUID = -4912773639894784636L;
		String message;
		public EmployeeException( String message) {
			this.message=message;
		}
		public EmployeeException(String message, Throwable cause) {
	        super(message, cause);
	        this.message=message;
	    }
		public String getErrorMessage(){
			return this.message;
		}

	}

	

