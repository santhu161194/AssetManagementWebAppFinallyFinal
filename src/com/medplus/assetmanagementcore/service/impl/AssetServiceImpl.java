package com.medplus.assetmanagementcore.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.medplus.assetmanagementcore.dao.AssetDao;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.AssetService;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;
@Service
public class AssetServiceImpl implements AssetService{
	
	@Autowired
	AssetDao dao;
	public Asset getAsset(int assetId)  {
		if(assetId<=0){
			//AssetException iae= new AssetException("Invalid Asset Id");
	        //throw iae;
		}
		 Asset asset = dao.getAsset(assetId);
		 return asset;
	   
	       
		 
	}

	public List<Asset> getAllAssets() {
		
		return dao.getAllAssets();
	}

	public String addAsset(Asset asset, String createdBy, Date createdDate) {
		int result=dao.addAsset(asset, createdBy, createdDate);
		if(result!=0)
		return ("Asset Added Successfully..");
		else
			return ("Failed to insert");
		
	}

	public String updateAsset(Asset asset, String updatedBy, Date updatedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateAssetStatus(String assetId, String updatedBy,
			Date updatedDate) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Asset> getAssetsByStatus(String status) {                      //done
		
		return dao.getAssetByStatus(status);
	}

	public List<Asset> getAssetsOfEmployee(String empId) {                    //working
		// TODO Auto-generated method stub
		return dao.getEmployeeAssets(empId);
	}

	public String postAssetRequest(AssetTypeEnum assetType, String requestedBy,Date requestedDate) {
		int postResult;
		 try {
			
			try {
				postResult = dao.postAssetRequest(assetType, requestedBy, requestedDate);
				 if(postResult>0)
					{
						return "Asset Request Posted Successfully..";
					}
					else
						return "Request Failed";
			
			
			
			} catch (SQLException e) {
				
		        return ("Sql exception ..");
			}	
		    }
		    catch (DataIntegrityViolationException e) {
		        return ("Request Already exist..");
		    }
		
	}

	public List<Request> getAllAssetRequests() {                           //done
		// TODO Auto-generated method stub
		return dao.getAllAssetRequests();
	}

	public List<Request> getAssetRequests(String requestedBy) {
		// TODO Auto-generated method stub
		return dao.getAssetRequestsByEmployee(requestedBy);
	}

	public boolean allocateAsset(String allocatedTo, long assetId,
			String allocatedBy, Date allocationDate) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deAllocateAsset(long assetId, String deallocatedBy,
			Date deallocationDate) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}