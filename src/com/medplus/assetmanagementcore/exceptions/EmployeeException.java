package com.medplus.assetmanagementcore.exceptions;

public class EmployeeException extends Exception{
	
	
		String message;
		public EmployeeException( String message) {
			this.message=message;
		}
		public String getErrorMessage(){
			return this.message;
		}

	}

	

