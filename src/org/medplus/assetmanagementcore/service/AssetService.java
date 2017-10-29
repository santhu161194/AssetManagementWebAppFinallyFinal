package org.medplus.assetmanagementcore.service;

import java.util.Date;
import java.util.List;

import org.medplus.assetmanagementcore.exceptions.AssetException;
import org.medplus.assetmanagementcore.exceptions.AuthenticationException;
import org.medplus.assetmanagementcore.exceptions.EmployeeException;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.AssetMapping;
import org.medplus.assetmanagementcore.model.NewTypeRequest;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.utils.AssetStatus;
import org.medplus.assetmanagementcore.utils.AssetType;
import org.springframework.dao.DataAccessException;

public interface AssetService {

	public String updateAssetStatus(long assetId,AssetStatus status,String ModifiedBy) throws AuthenticationException, AssetException, EmployeeException;

	public String saveAssetRequest(AssetType assetType,String requestedBy) throws AuthenticationException, AssetException ;

	public Asset getAsset(long assetId) throws AssetException;
	
	public String addAsset(Asset asset) throws AssetException, AuthenticationException, EmployeeException;

	public List<Asset> getAllAssets() throws AssetException ;
	
	public List<Asset> getAssetsByStatus(String status) throws AssetException ;
	
	public List<Asset> getAssetsOfEmployee(String empId) throws AssetException ;
	
	public String saveNewAssetTypeRequest(String requestedBy,String assetType,String assetName) throws AuthenticationException, AssetException ;
	
	public List<Request> getAllAssetRequests() throws AssetException ;
	
	public List<NewTypeRequest> getNewAssetTypeRequests() throws AssetException ;
	
	public List<Request> getAssetRequests(String requestedBy) throws AssetException;
	
	public String allocateAsset(String allocatedTo,long assetId,String allocatedBy) throws AssetException, AuthenticationException, EmployeeException ;
	
	public String deAllocateAsset(long assetId,String deallocatedBy) throws AssetException, AuthenticationException, EmployeeException;
	
	public List<Asset> getAssetByType(AssetType type)throws AssetException;
	
	public String updateAsset(Asset asset) throws AssetException, AuthenticationException;

	public List<AssetMapping> getAssetMappingLog() throws AssetException;
	
	public String removeAssetRequest(Request request) throws AssetException;

	
}

