package com.medplus.assetmanagementcore.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.medplus.assetmanagementcore.exceptions.AssetException;
import com.medplus.assetmanagementcore.exceptions.AuthenticationException;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.NewTypeRequest;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.utils.AssetStatusEnum;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;

public interface AssetService {
	
	public Asset getAsset(int assetId) throws AssetException;

	

	public String addAsset(Asset asset,String createdBy,Date createdDate) throws AssetException;

	public String updateAsset(Asset asset,String updatedBy,Date updatedDate);

	public String updateAssetStatus(int assetId,AssetStatusEnum status,String ModifiedBy,Date dateModified);

	public List<Asset> getAllAssets() throws AssetException;
	
	public List<Asset> getAssetsByStatus(String status) throws AssetException;
	
	public List<Asset> getAssetsOfEmployee(String empId) throws AssetException;
	
	public String postAssetRequest(AssetTypeEnum assetType,String requestedBy,Date requestedDate) throws AuthenticationException;
	
	public String postNewAssetTypeRequest( String requestedBy,String assetType,String assetName,Date requestedDate)throws AuthenticationException ;
	
	public List<Request> getAllAssetRequests() throws AssetException;
	
	public List<NewTypeRequest> getNewAssetTypeRequests() throws AssetException;
	
	public List<Request> getAssetRequests(String requestedBy) throws AssetException;
	
	
	public boolean allocateAsset(String assignedTo,int assetId,String asignedBy,Date handOverDate) throws AssetException, AuthenticationException;
	
	public boolean deAllocateAsset(int assetId,String deallocatedBy,Date deallocationDate) throws AssetException, AuthenticationException;
}
