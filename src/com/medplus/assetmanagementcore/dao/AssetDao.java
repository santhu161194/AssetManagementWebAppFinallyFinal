package com.medplus.assetmanagementcore.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.NewTypeRequest;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.utils.AssetStatus;
import com.medplus.assetmanagementcore.utils.AssetType;

public interface AssetDao {

	public  int updateAssetStatus(int assetId,AssetStatus status,String ModifiedBy,Date dateModified);
	
    public int addAsset(Asset asset);
	
    public List<Asset> getAllAssets();
	
	public Asset getAsset(int assetId) ; 
	
	public List<Asset> getAssetByStatus(String status);
    
	public List<Asset> getEmployeeAssets(String empId);
	
	public List<Asset> getAssetByType(AssetType type);
	
	public List<NewTypeRequest> getNewAssetTypeRequests();
	
	public List<Request> getAllAssetRequests();
	
	public int postAssetRequest(AssetType assetType,String empId,Date requestedDate) throws SQLException;
	
	public int postNewAssetTypeRequest( String requestedBy,String assetType,String assetName,Date requestedDate)throws SQLException;
	
	public List<Request> getAssetRequestsByEmployee(String empId);
	
	public int allocateAsset(String assignedTo,int assetId,String asignedBy,Date handOverDate);
	
	public int deAllocateAsset(int assetId,String deallocatedBy,Date deallocateDate);
}