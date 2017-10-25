package com.medplus.assetmanagementcore.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.medplus.assetmanagementcore.dao.AssetDao;
import com.medplus.assetmanagementcore.exceptions.AssetException;
import com.medplus.assetmanagementcore.exceptions.AuthenticationException;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.NewTypeRequest;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.AssetService;
import com.medplus.assetmanagementcore.utils.AssetStatus;
import com.medplus.assetmanagementcore.utils.AssetType;
import com.medplus.assetmanagementcore.validations.CommonValidations;
@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	AssetDao dao;
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	public Asset getAsset(int assetId) throws AssetException {
		String message="";
		message=CommonValidations.isValidNumber(assetId);
		
		if(!message.equals("valid")){
			AssetException invalidAssetException= new AssetException(message);
			throw invalidAssetException;
		}
		
		Asset asset=null;
		asset = dao.getAsset(assetId);
		if(asset==null){
			AssetException invalidAssetException= new AssetException("Asset Not Found");
			throw invalidAssetException;
		}
		return asset; 	
	}
	
	

	public List<Asset> getAllAssets()  throws AssetException{
		List<Asset> list=dao.getAllAssets();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Asset Found");
			throw iae;	
		}
		return list;
	}

	public String addAsset(Asset asset) throws AssetException, AuthenticationException {
		
		if(asset==null){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		if(asset.getCreatedBy()==null){
			return "Provide Created By field";
		}
		if(employeeService.getEmployeeRole(asset.getCreatedBy())!=null&&!(employeeService.getEmployeeRole(asset.getCreatedBy()).contains("admin")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		employeeService.getEmployeeRole(asset.getCreatedBy());
		int result=dao.addAsset(asset);
		if(result!=0)
			return ("Asset Added Successfully..");
		else
			return ("Failed to insert");

	}

	

	public String updateAssetStatus(int assetId,AssetStatus status,String ModifiedBy,Date dateModified) throws AuthenticationException {
		
		if(employeeService.getEmployeeRole(ModifiedBy)!=null&&!(employeeService.getEmployeeRole(ModifiedBy).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		int result=dao.updateAssetStatus(assetId, status, ModifiedBy, dateModified);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		}
	
	

	public List<Asset> getAssetsByStatus(String status)throws AssetException {                      
		String msg=CommonValidations.isValidName(status);
		if(!msg.equals("valid")){
			AssetException iae= new AssetException("Asset Status Exception");
			throw iae;
		}
		List<Asset> asset=null;
		asset = dao.getAssetByStatus(status);
		if(asset.isEmpty()){
			AssetException invalidAssetException= new AssetException("Asset Not Found");
			throw invalidAssetException;
		}
		return asset;
	}

	
	
	public List<Asset> getAssetsOfEmployee(String empId) throws AssetException{ 
		String message=CommonValidations.isValidName(empId);
		if(!message.equals("valid")){
			AssetException iae= new AssetException(message);
			throw iae;
		}
		List<Asset> asset=null;
		asset = dao.getEmployeeAssets(empId);
		if(asset.isEmpty()){
			AssetException invalidAssetException= new AssetException("Asset Not Found");
			throw invalidAssetException;
		}
		return asset;
	}

	public String postAssetRequest(AssetType assetType, String requestedBy,Date requestedDate)throws AuthenticationException {
		
		
		System.out.println(assetType);
		if(!employeeService.isUserExisting(requestedBy)){
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
	
		int postResult;
		try {

			try {
				postResult = dao.postAssetRequest(assetType, requestedBy, requestedDate);
				if(postResult>0)
				{
					return "success";
				}
				else
					return "failure";
				} catch (SQLException e) {

				return ("Sql exception .."+e.getStackTrace());
			}	
		}
		catch (DataIntegrityViolationException e) {
			return ("Request Already has been posted for this type "+e.getMessage());
		}
	}


	
	public List<Request> getAllAssetRequests() throws AssetException{                           
		List<Request> list=dao.getAllAssetRequests();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Request Found");
			throw iae;	
		}
		return list;
	}

	
	
	public List<Request> getAssetRequests(String requestedBy)throws AssetException {
		if(requestedBy.equals(null)){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		List<Request> request=null;
		request = dao.getAssetRequestsByEmployee(requestedBy);
		if(request.isEmpty()){
			AssetException invalidAssetException= new AssetException("Asset Request Not Found");
			throw invalidAssetException;
		}
		return request;

	}

	public String allocateAsset(String assignedTo, int assetId,
			String asignedBy, Date handOverDate) throws AssetException,
			AuthenticationException {

		if (assetId < 0 || asignedBy.isEmpty() || assignedTo.isEmpty()
				|| asignedBy == null) {
			AssetException iae = new AssetException(
					" Exception:Asset Allocation");
			throw iae;
		}
		
		if (employeeService.getEmployeeRole(asignedBy) != null
				&& !(employeeService.getEmployeeRole(asignedBy).contains("edp"))) {
			AuthenticationException iae = new AuthenticationException(
					"Authentication Exception ..");
			throw iae;
		}
		if (dao.getAsset(assetId) != null
				&& dao.getAsset(assetId).getStatus().value.equals("N")) {
			return "Asset not Available";
		} else {

			int result = dao.allocateAsset(assignedTo, assetId, asignedBy,
					handOverDate);
			if (result > 0) { // tx mgmt required-rollback
				dao.updateAssetStatus(assetId, AssetStatus.NotAvailable,
						asignedBy, handOverDate);// for make asset unavailable
				return "Asset Allocated";
			} else
				return "Unable to Allocate Asset";
		}
	}
	
	
	
	public String deAllocateAsset(int assetId, String deallocatedBy,      //check
			Date deallocationDate)throws AssetException, AuthenticationException {
		if(assetId<0||deallocatedBy.isEmpty())//validate class
		{
			AssetException iae= new AssetException(" Exception:Asset Deallocation");
			throw iae;	
		}
		if(employeeService.getEmployeeRole(deallocatedBy)!=null&&!(employeeService.getEmployeeRole(deallocatedBy).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		 int result=dao.deAllocateAsset( assetId, deallocatedBy, deallocationDate);
		 if(result>0){ //rollback required
		 dao.updateAssetStatus(assetId, AssetStatus.Available, deallocatedBy, deallocationDate);//for make asset available
		 
		 return "Asset Deallocated";
		 }
		 else
	    return "Asset Deallocation Failed";
	}
	
	

	public String postNewAssetTypeRequest( String requestedBy,String assetType,String assetName,Date requestedDate)throws AuthenticationException {
		if(!employeeService.isUserExisting(requestedBy)){
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
	
		int postResult;
		try {

			try {
				postResult = dao.postNewAssetTypeRequest(requestedBy,assetType, assetName,requestedDate);
				if(postResult>0)
				{
					return "success";
				}
				else
					return "Request Failed";
				} catch (SQLException e) {

				return ("Sql exception ..");
			}	
		}
		catch (DataIntegrityViolationException e) {
			return ("Request Failed..");
		}
	}

	public List<NewTypeRequest> getNewAssetTypeRequests() throws AssetException {
		List<NewTypeRequest> list=dao.getNewAssetTypeRequests();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Request Found");
			throw iae;	
		}
		return list;
	
	}

	
	@Override
	public List<Asset> getAssetByType(AssetType type) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String updateAsset(Asset asset, String updatedBy, Date updatedDate) {
		// TODO Auto-generated method stub
		return null;
	}



}
