package org.medplus.assetmanagementcore.service.impl;

import java.util.List;

import org.medplus.assetmanagementcore.dao.AssetDao;
import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.AssetMapping;
import org.medplus.assetmanagementcore.model.NewTypeRequest;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.service.AssetService;
import org.medplus.assetmanagementcore.service.EmployeeService;
import org.medplus.assetmanagementcore.utils.AssetStatus;
import org.medplus.assetmanagementcore.utils.AssetType;
import org.medplus.assetmanagementcore.validations.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AssetServiceImpl implements AssetService{

	@Autowired
	AssetDao dao;
	
	@Autowired
	EmployeeService employeeService;
	
	public Asset getAsset(long assetId) throws AssetException {
		Asset asset=null;
		try{
		asset = dao.getAsset(assetId);
		}
		catch(DataAccessException e)
		{
			throw new AssetException("Asset Exception",e);
		}
		if(asset==null){
			throw new AssetException("Asset Not Found");
		}
		return asset; 	
	}
	
	

	public List<Asset> getAllAssets()  throws AssetException{
		List<Asset> list=null;
		try{
		 list=dao.getAllAssets();
		 if(list.size()==0)
			{
				throw new AssetException("No Asset Found");	
			}
		}
		catch(DataAccessException e){
 
			throw new AssetException("Asset Exception",e);
		}
		
		return list;
	}

	public String addAsset(Asset asset) throws AssetException, AuthenticationException, EmployeeException {
		int result=0;
		if(asset==null){
			throw new AssetException("Asset Exception");
		}
		String msg=CommonValidations.isValidName(asset.getCreatedBy());
		if(!msg.equals("valid")){ 
			throw new AssetException("provide created By field");
		}
		if(employeeService.checkRoles(asset.getCreatedBy())!=null&&!(employeeService.checkRoles(asset.getCreatedBy()).contains("admin")))
		{
			throw  new AuthenticationException("Authentication Exception ..");
		}
		try{
		result=dao.addAsset(asset);
		if(result!=0)
			return ("Asset Added Successfully..");
		else
			return ("Failed to insert");
		}
		catch(DataIntegrityViolationException e){ 
			throw new AssetException("Duplicate Asset Entry ",e);
		}
		catch(DataAccessException e){
			throw new AssetException("Asset Exception ",e);
		}
		

	}

	

	public String updateAssetStatus(long assetId,AssetStatus status,String ModifiedBy) throws AuthenticationException, AssetException, EmployeeException {
		
		if(employeeService.checkRoles(ModifiedBy)!=null&&!(employeeService.checkRoles(ModifiedBy).contains("edp")))
		{
			throw new AuthenticationException("Authentication Exception ..");
		}
		try{
		int result=dao.updateAssetStatus(assetId, status, ModifiedBy);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		
	 }catch(DataAccessException e){ 
		throw new AssetException("Asset Exception ",e);
	  }
	}
	
	

	public List<Asset> getAssetsByStatus(String status)throws AssetException {                      
		String msg=CommonValidations.isValidName(status);
		if(!msg.equals("valid")){
			throw new AssetException("Asset Status Exception");
		}
		List<Asset> asset=null;
		try{
		asset = dao.getAssetByStatus(status);
		}
	    catch(DataAccessException e){  
		throw new AssetException("Asset Exception ",e);
	    }
		if(asset.isEmpty()){
			throw  new AssetException("Asset Not Found");
		}
		return asset;
	}

	
	
	public List<Asset> getAssetsOfEmployee(String empId) throws AssetException{ 
		String message=CommonValidations.isValidName(empId);
		if(!message.equals("valid")){
			throw new AssetException(message);
		}
		List<Asset> asset=null;
		try{
		asset = dao.getEmployeeAssets(empId);
		}catch(DataAccessException e){  
			throw new AssetException("Asset Exception ",e);
		    }
		if(asset.isEmpty()){
			throw new AssetException("Asset Not Found");
		}
		return asset;
	}

	public String saveAssetRequest(AssetType assetType, String requestedBy)throws AuthenticationException, AssetException {
	
		if(!employeeService.isUserExisting(requestedBy)){
			throw new AuthenticationException("Authentication Exception ..");
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
				throw new AssetException("Duplicate Asset Request",e);
			}
			
			catch (DataAccessException e) {
					throw new AssetException("Asset Request Exception",e);
			}	
	}


	public List<Request> getAllAssetRequests() throws AssetException{ 
        try{                         
		List<Request> list=dao.getAllAssetRequests();
		if(list!=null &&list.size()==0)
		{
			throw new AssetException("No Request Found");	
		}
		return list;
        }
        catch(DataAccessException e){
			throw new AssetException("Asset Exception",e);
        }
	}

	
	
	public List<Request> getAssetRequests(String requestedBy)throws AssetException {
		if(requestedBy.equals(null)){
			throw new AssetException("Asset Exception");
		}
		List<Request> request=null;
		try{
		request = dao.getAssetRequestsByEmployee(requestedBy);
		if(request.isEmpty()){
			throw new AssetException("Asset Request Not Found");
		}
		return request;
		}
		catch(DataAccessException e){
		throw new AssetException("Asset Request Exception",e);
		}
	}

	public String allocateAsset(String assignedTo, long assetId,
			String asignedBy) throws AssetException,
			AuthenticationException, EmployeeException {

		if (assetId < 0 || asignedBy.isEmpty() || assignedTo.isEmpty()
				|| asignedBy == null) {
			throw new AssetException(" Exception:Asset Allocation");
		}
		
		if (employeeService.checkRoles(asignedBy) != null
				&& !(employeeService.checkRoles(asignedBy).contains("edp"))) {
			throw new AuthenticationException(
					"Authentication Exception ..");
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
 				throw  new AssetException(" Asset Not Available",e);
 			}
 			
 			catch (DataAccessException e) {
 					throw new AssetException("Asset Allocation Exception",e);
 			}
		}
	}
	
	
	
	public String deAllocateAsset(long assetId, String deallocatedBy)throws AssetException, AuthenticationException, EmployeeException {
		if(assetId<0||deallocatedBy.isEmpty())
		{
			throw new AssetException(" Exception:Asset Deallocation");
		}
		if(employeeService.checkRoles(deallocatedBy)!=null&&!(employeeService.checkRoles(deallocatedBy).contains("edp")))
		{
			throw new AuthenticationException("Authentication Exception ..");
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
				throw  new AssetException("Asset DeAllocation Exception",e);
		}
	
	}
	
	

	public String saveNewAssetTypeRequest( String requestedBy,String assetType,String assetName)throws AuthenticationException, AssetException {
		if(!employeeService.isUserExisting(requestedBy)){
			throw new AuthenticationException("Authentication Exception ..");
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
	        catch(DuplicateKeyException e){
 				throw  new AssetException(" Request already posted..",e);
 			}
	        catch(DataIntegrityViolationException e){
 				throw  new AssetException(" Asset Exception",e);
 			}
 			
 			catch (DataAccessException e) {
 					throw new AssetException("Asset Exception",e);
 			}
	}

	public List<NewTypeRequest> getNewAssetTypeRequests() throws AssetException {
		try{
		List<NewTypeRequest> list=dao.getNewAssetTypeRequests();
		if(list.size()==0)
		{
			throw new AssetException("No Request Found");	
		}
		return list;
		}catch(DataAccessException e){
				throw new AssetException("Asset Exception",e);
		}
	
	}

	
	@Override
	public List<Asset> getAssetByType(AssetType type) throws AssetException {
		try{
		List<Asset> list=dao.getAssetByType(type);
		return list;
	}catch(DataAccessException e){
			throw new AssetException("Asset Exception",e);
	}
	}



	@Override
	public String updateAsset(Asset asset) throws AuthenticationException, AssetException {
		if(asset!=null&&employeeService.checkRoles(asset.getModifiedBy())!=null&&!(employeeService.checkRoles(asset.getModifiedBy()).contains("edp")))
		{
			throw new AuthenticationException("Authentication Exception ..");
		}
		try{
		int result=dao.updateAsset(asset);

		if(result!=0)
			return ("success");
		else
			return ("Failed to Update");
		
	 }catch(DataAccessException e){
		throw new AssetException("Asset Exception "+e,e);
	  }
	}



	@Override
	public List<AssetMapping> getAssetMappingLog() throws AssetException {
		try{
			List<AssetMapping> logList=dao.getAssetMappingLog();
			return logList;
		}catch(DataAccessException e){
				throw new AssetException("Asset Log Exception",e);
		}
	}
	
	@Override
	public String removeAssetRequest(Request request) throws AssetException {
			String message = null;
				try {

					int resultCount =dao.removeAssetRequest(request);
					
					if (resultCount > 0)
						message = "REMOVED";
					else
						message = "FAILURE";
				} catch (Exception e) {
					AssetException invalidAssetException = new AssetException(
							" Remove Asset Request Exception");
					throw invalidAssetException;
				}
			return message;

		}
}
