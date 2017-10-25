package com.medplus.assetmanagementcore.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidations {

	public static final String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	  private static Pattern pattern;
	  private static Matcher matcher;
	  
	public static String isValidName(String name)
	{
		if(!name.isEmpty()&&name.length()<40)
			return "valid";
		else
		{
			return "The name needs to be within 40 characters";
		}
	}
	
	public static String isValidAddress(String address)
	{
		if(!address.isEmpty()&&address.length()<255)
			return "valid";
		else
		{
			return "The Address needs to be within 255 characters";
		}
	}
	
	public static String isValidMobileNumber(String mobileNumber)
	{
		if(!mobileNumber.isEmpty()&&mobileNumber.length()<=12)
			return "valid";
		else
		{
			return "The Mobile number  needs to be within 12 characters and should contain only digits";
		}
	}
	
	public CommonValidations(){
		 
	  }
	

	public static String isValidPassword(String password)
	{
		
		if(!password.isEmpty()&&password.length()<6)
		{
			 pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			   if(matcher.matches())
				   return "valid password";   
		}
		
		
			return "password needs to be atleast 6 character long and should contain atleast one character and one number and one special character";
		
		
	}
	
	public static String isValidNumber(int number){
	if(number<0)
	{
		return "Negative Numbers not Allowed";
	}
	else
		return "valid";
	}

}
