package com.medplus.assetmanagementcore.utils;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.medplus.assetmanagementcore.model.Asset;



@Component
public class AssetValidation implements Validator{
	
	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Asset.class.isAssignableFrom(arg0);
	}


	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		Asset as=(Asset) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"assetId","assetId.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"serialNumber","serialNumber.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1," assetName"," assetName.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"assetType","assetType.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"status","status.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"createdDate","createdDate.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"createdBy","createdBy.err");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1,"modifiedBy","modifiedBy.err");
		if(as.getCost()<=0)
		{
			arg1.rejectValue("cost", "cost.err");
		}
		
		
	}
	}




