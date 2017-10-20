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
import com.medplus.assetmanagementcore.utils.AssetStatusEnum;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;
@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	AssetDao dao;
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	public Asset getAsset(int assetId) throws AssetException {
		//validate asset id--new method
		if(assetId<=0){
			AssetException iae= new AssetException("Invalid Asset Id");
			throw iae;
		}
		Asset asset=null;
		asset = dao.getAsset(assetId);
		if(asset==null){
			AssetException iae= new AssetException("Asset Not Found");
			throw iae;
		}
		return asset; 	
	}
//ui done
	public List<Asset> getAllAssets()  throws AssetException{
		List<Asset> list=dao.getAllAssets();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Asset Found");
			throw iae;	
		}
		return list;
	}
//ui done
	public String addAsset(Asset asset, String createdBy, Date createdDate) throws AssetException {
		//check role of createdBy
		if(asset==null){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		if(createdBy==null){
			return "date";
		}
		int result=dao.addAsset(asset, createdBy, createdDate);
		if(result!=0)
			return ("Asset Added Successfully..");
		else
			return ("Failed to insert");

	}

	public String updateAsset(Asset asset, String updatedBy, Date updatedDate) {//not required
		// TODO Auto-generated method stub
		return null;
	}

	public String updateAssetStatus(int assetId,AssetStatusEnum status,String ModifiedBy,Date dateModified) {
		//check role of ModifedBy
		int result=dao.updateAssetStatus(assetId, status, ModifiedBy, dateModified);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		}

	public List<Asset> getAssetsByStatus(String status)throws AssetException {                      //done
		if(status.isEmpty()){    //change and validate using validate class
			AssetException iae= new AssetException("Asset Status Exception");
			throw iae;
		}
		return dao.getAssetByStatus(status);
	}

	public List<Asset> getAssetsOfEmployee(String empId) throws AssetException{                    //done
		if(empId.isEmpty()){  //change and validate using validate class
			AssetException iae= new AssetException("Employee ID Exception");
			throw iae;
		}
		return dao.getEmployeeAssets(empId);
	}

	public String postAssetRequest(AssetTypeEnum assetType, String requestedBy,Date requestedDate)throws AuthenticationException {
		//check for valid RequestedBy
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

				return ("Sql exception ..");
			}	
		}
		catch (DataIntegrityViolationException e) {
			return ("failure");
		}
	}

	
	public List<Request> getAllAssetRequests() throws AssetException{                           //done
		List<Request> list=dao.getAllAssetRequests();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Request Found");
			throw iae;	
		}
		return dao.getAllAssetRequests();
	}

	public List<Request> getAssetRequests(String requestedBy)throws AssetException {
		if(requestedBy.equals(null)){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		
		return dao.getAssetRequestsByEmployee(requestedBy);
	}

	public boolean allocateAsset(String assignedTo,int assetId,String asignedBy,Date handOverDate)throws AssetException, AuthenticationException {
		
		if(assetId<0||asignedBy.isEmpty()||assignedTo.isEmpty()||asignedBy==null)
		{
			AssetException iae= new AssetException(" Exception:Asset Allocation");
			throw iae;	
		}
		//System.out.println(employeeService.getEmployeeRole(asignedBy));
		if(employeeService.getEmployeeRole(asignedBy)!=null&&!(employeeService.getEmployeeRole(asignedBy).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		if(dao.getAsset(assetId)!=null&&dao.getAsset(assetId).getStatus().value.equals("N")){
			System.out.println("Asset not Available");
			return false;
		}else{
		//System.out.println(dao.getAsset(assetId).getStatus().value);
		int result=dao.allocateAsset(assignedTo, assetId, asignedBy, handOverDate);
		 if(result>0){    //tx mgmt required-rollback
			 int r=dao.updateAssetStatus(assetId, AssetStatusEnum.NotAvailable, asignedBy, handOverDate);//for make asset unavailable
			 return true;
			 }
		 else
	    return false;
		}
	}

	
	
	
	public boolean deAllocateAsset(int assetId, String deallocatedBy,      //check
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
		 int r=dao.updateAssetStatus(assetId, AssetStatusEnum.NotAvailable, deallocatedBy, deallocationDate);//for make asset available
 
		 return true;
		 }
		 else
	    return false;
	}
	
	//new asset type request
	public String postNewAssetTypeRequest( String requestedBy,String assetType,String assetName,Date requestedDate)throws AuthenticationException {
		//check for valid RequestedBy
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
		return dao.getNewAssetTypeRequests();
	
	}



}
