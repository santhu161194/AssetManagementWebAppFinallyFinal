package com.medplus.assetmanagementcore.service;

import java.util.Date;
import java.util.List;

import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;

public interface AssetService {
	
	public Asset getAsset(int assetId) ;

	

	public String addAsset(Asset asset,String createdBy,Date createdDate);

	public String updateAsset(Asset asset,String updatedBy,Date updatedDate);

	public String updateAssetStatus(String assetId,String updatedBy,Date updatedDate);
	
	

	public List<Asset> getAllAssets();
	

	public List<Asset> getAssetsByStatus(String status);
	
	public List<Asset> getAssetsOfEmployee(String empId);
	
	public String postAssetRequest(AssetTypeEnum assetType,String requestedBy,Date requestedDate);
	
	
	public List<Request> getAllAssetRequests();
	
	public List<Request> getAssetRequests(String requestedBy);
	
	
	public boolean allocateAsset(String allocatedTo,long assetId,String allocatedBy,Date allocationDate);
	
	public boolean deAllocateAsset(long assetId,String deallocatedBy,Date deallocationDate);
}