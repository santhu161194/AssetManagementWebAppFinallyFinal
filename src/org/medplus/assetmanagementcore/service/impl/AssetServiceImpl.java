package org.medplus.assetmanagementcore.service.impl;

import java.util.List;

import org.medplus.assetmanagementcore.dao.AssetDao;
import org.medplus.assetmanagementcore.dao.impl.AssetDaoImpl;
import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.NewTypeRequest;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.service.AssetService;
import org.medplus.assetmanagementcore.utils.AssetStatus;
import org.medplus.assetmanagementcore.utils.AssetType;
import org.medplus.assetmanagementcore.validations.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	AssetDao dao;
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	public Asset getAsset(long assetId) throws AssetException {
		String message="";
		Asset asset=null;
		if(!message.equals("valid")){
			AssetException invalidAssetException= new AssetException(message);
			throw invalidAssetException;
		}
		try{
		asset = dao.getAsset(assetId);
		}
		catch(DataAccessException e)
		{
			AssetException invalidAssetException= new AssetException("Asset Exception",e);
			throw invalidAssetException;
		}
		if(asset==null){
			AssetException invalidAssetException= new AssetException("Asset Not Found");
			throw invalidAssetException;
		}
		return asset; 	
	}
	
	

	public List<Asset> getAllAssets()  throws AssetException{
		List<Asset> list=null;
		try{
		 list=dao.getAllAssets();
		 if(list.size()==0)
			{
				AssetException iae= new AssetException("No Asset Found");
				throw iae;	
			}
		}
		catch(DataAccessException e){

			AssetException invalidAssetException= new AssetException("Asset Exception",e);
			throw invalidAssetException;
		}
		
		return list;
	}

	public String addAsset(Asset asset) throws AssetException, AuthenticationException, EmployeeException {
		int result=0;
		if(asset==null){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		String msg=CommonValidations.isValidName(asset.getCreatedBy());
		if(!msg.equals("valid")){
			AssetException iae= new AssetException("provide created By field");
			throw iae;
		}
		if(employeeService.checkRoles(asset.getCreatedBy())!=null&&!(employeeService.checkRoles(asset.getCreatedBy()).contains("admin")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		try{
		result=dao.addAsset(asset);
		if(result!=0)
			return ("Asset Added Successfully..");
		else
			return ("Failed to insert");
		}
		catch(DataIntegrityViolationException e){
			AssetException invalidAssetException= new AssetException("Duplicate Asset Entry ",e);
			throw invalidAssetException;
		}
		catch(DataAccessException e){
			AssetException invalidAssetException= new AssetException("Asset Exception ",e);
			throw invalidAssetException;
		}
		

	}

	

	public String updateAssetStatus(long assetId,AssetStatus status,String ModifiedBy) throws AuthenticationException, AssetException, EmployeeException {
		
		if(employeeService.checkRoles(ModifiedBy)!=null&&!(employeeService.checkRoles(ModifiedBy).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		try{
		int result=dao.updateAssetStatus(assetId, status, ModifiedBy);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		
	 }catch(DataAccessException e){
		AssetException invalidAssetException= new AssetException("Asset Exception ",e);
		throw invalidAssetException;
	  }
	}
	
	

	public List<Asset> getAssetsByStatus(String status)throws AssetException {                      
		String msg=CommonValidations.isValidName(status);
		if(!msg.equals("valid")){
			AssetException iae= new AssetException("Asset Status Exception");
			throw iae;
		}
		List<Asset> asset=null;
		try{
		asset = dao.getAssetByStatus(status);
		}
	    catch(DataAccessException e){  
		AssetException invalidAssetException= new AssetException("Asset Exception ",e);
		throw invalidAssetException;
	    }
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
		try{
		asset = dao.getEmployeeAssets(empId);
		}catch(DataAccessException e){  
			AssetException invalidAssetException= new AssetException("Asset Exception ",e);
			throw invalidAssetException;
		    }
		if(asset.isEmpty()){
			AssetException invalidAssetException= new AssetException("Asset Not Found");
			throw invalidAssetException;
		}
		return asset;
	}

	public String saveAssetRequest(AssetType assetType, String requestedBy)throws AuthenticationException, AssetException {
	
		if(!employeeService.isUserExisting(requestedBy)){
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
	
		int postResult;
		

			try{
				postResult = dao.saveAssetRequest(assetType, requestedBy);
				if(postResult>0)
				{
					return "success";
				}
				else
					return "failure";
				
	          } 
			catch(DataIntegrityViolationException e){
				AssetException invalidAssetException= new AssetException("Duplicate Asset Request",e);
				throw invalidAssetException;
			}
			
			catch (DataAccessException e) {

					AssetException invalidAssetException= new AssetException("Asset Request Exception",e);
					throw invalidAssetException;
			}	
	}


	
	public List<Request> getAllAssetRequests() throws AssetException{ 
        try{                         
		List<Request> list=dao.getAllAssetRequests();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Request Found");
			throw iae;	
		}
		return list;
        }
        catch(DataAccessException e){

			AssetException invalidAssetException= new AssetException("Asset Exception",e);
			throw invalidAssetException;
        }
	}

	
	
	public List<Request> getAssetRequests(String requestedBy)throws AssetException {
		if(requestedBy.equals(null)){
			AssetException iae= new AssetException("Asset Exception");
			throw iae;
		}
		List<Request> request=null;
		try{
		request = dao.getAssetRequestsByEmployee(requestedBy);
		if(request.isEmpty()){
			AssetException invalidAssetException= new AssetException("Asset Request Not Found");
			throw invalidAssetException;
		}
		return request;
		}
		catch(DataAccessException e){
		AssetException invalidAssetException= new AssetException("Asset Request Exception",e);
		throw invalidAssetException;
		}
	}

	public String allocateAsset(String assignedTo, long assetId,
			String asignedBy) throws AssetException,
			AuthenticationException, EmployeeException {

		if (assetId < 0 || asignedBy.isEmpty() || assignedTo.isEmpty()
				|| asignedBy == null) {
			AssetException iae = new AssetException(" Exception:Asset Allocation");
			throw iae;
		}
		
		if (employeeService.checkRoles(asignedBy) != null
				&& !(employeeService.checkRoles(asignedBy).contains("edp"))) {
			AuthenticationException iae = new AuthenticationException(
					"Authentication Exception ..");
			throw iae;
		}
		if (dao.getAsset(assetId) != null
				&& dao.getAsset(assetId).getStatus().value.equals("N")) {
			return "Asset not Available";
		} else {
             try{
			int result = dao.allocateAsset(assignedTo, assetId, asignedBy);
			if (result > 0) { 
				dao.updateAssetStatus(assetId, AssetStatus.NotAvailable,
						asignedBy);// for make asset unavailable
				return "success";
			} else
				return "Unable to Allocate Asset";
             }
             catch(DataIntegrityViolationException e){
 				AssetException invalidAssetException= new AssetException(" Asset Not Available",e);
 				throw invalidAssetException;
 			}
 			
 			catch (DataAccessException e) {

 					AssetException invalidAssetException= new AssetException("Asset Allocation Exception",e);
 					throw invalidAssetException;
 			}
		}
	}
	
	
	
	public String deAllocateAsset(long assetId, String deallocatedBy)throws AssetException, AuthenticationException, EmployeeException {
		if(assetId<0||deallocatedBy.isEmpty())
		{
			AssetException iae= new AssetException(" Exception:Asset Deallocation");
			throw iae;	
		}
		if(employeeService.checkRoles(deallocatedBy)!=null&&!(employeeService.checkRoles(deallocatedBy).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		try{
		 int result=dao.deAllocateAsset( assetId, deallocatedBy);
		 if(result>0){ 
		 dao.updateAssetStatus(assetId, AssetStatus.Available, deallocatedBy);
		 
		 return "success";
		 }
		 else
	    return "Asset Deallocation Failed";
		}
		catch (DataAccessException e) {

				AssetException invalidAssetException= new AssetException("Asset DeAllocation Exception",e);
				throw invalidAssetException;
		}
	
	}
	
	

	public String saveNewAssetTypeRequest( String requestedBy,String assetType,String assetName)throws AuthenticationException, AssetException {
		if(!employeeService.isUserExisting(requestedBy)){
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
	
		int postResult;
	        try{
				postResult = dao.saveNewAssetTypeRequest(requestedBy,assetType, assetName);
				if(postResult>0)
				{
					return "success";
				}
				else
					return "Request Failed";
	        }
	        catch(DataIntegrityViolationException e){
 				AssetException invalidAssetException= new AssetException(" Asset Not Available",e);
 				throw invalidAssetException;
 			}
 			
 			catch (DataAccessException e) {

 					AssetException invalidAssetException= new AssetException("Asset Allocation Exception",e);
 					throw invalidAssetException;
 			}
	}

	public List<NewTypeRequest> getNewAssetTypeRequests() throws AssetException {
		try{
		List<NewTypeRequest> list=dao.getNewAssetTypeRequests();
		if(list.size()==0)
		{
			AssetException iae= new AssetException("No Request Found");
			throw iae;	
		}
		return list;
		}catch(DataAccessException e){
			AssetException invalidAssetException= new AssetException("Asset Exception",e);
				throw invalidAssetException;
		}
	
	}

	
	@Override
	public List<Asset> getAssetByType(AssetType type) throws AssetException {
		try{
		List<Asset> list=dao.getAssetByType(type);
		return list;
	}catch(DataAccessException e){
		AssetException invalidAssetException= new AssetException("Asset Exception",e);
			throw invalidAssetException;
	}
	}



	@Override
	public String updateAsset(Asset asset) throws AuthenticationException, AssetException {
		if(asset!=null&&employeeService.checkRoles(asset.getModifiedBy())!=null&&!(employeeService.checkRoles(asset.getModifiedBy()).contains("edp")))
		{
			AuthenticationException iae= new AuthenticationException("Authentication Exception ..");
			throw iae;
		}
		try{
		int result=dao.updateAsset(asset);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		
	 }catch(DataAccessException e){
		AssetException invalidAssetException= new AssetException("Asset Exception ",e);
		throw invalidAssetException;
	  }
	}








}
