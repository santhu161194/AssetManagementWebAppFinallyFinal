package com.medplus.assetmanagementcore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.medplus.assetmanagementcore.dao.AssetDao;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.AssetMapping;
import com.medplus.assetmanagementcore.model.NewTypeRequest;
import com.medplus.assetmanagementcore.model.Request;
import com.medplus.assetmanagementcore.service.impl.AssetServiceImpl;
import com.medplus.assetmanagementcore.utils.AssetAllocation;
import com.medplus.assetmanagementcore.utils.AssetStatus;
import com.medplus.assetmanagementcore.utils.AssetType;
import com.medplus.assetmanagementcore.utils.Queries;
@Component
public class AssetDaoImpl implements AssetDao {

	@Autowired
	JdbcTemplate template;
	
	@Autowired
	Asset asset;
	
	@Autowired
	Request request;
	
	@Autowired
	AssetServiceImpl ser;
	
	public List<Asset> getAllAssets() {                                              
		return template.query(Queries.getAllAsset,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException,  
            DataAccessException {  
				Asset e=new Asset(); 
				e.setAssetId(rs.getInt(1));
				e.setSerialNumber(rs.getString(2)); 
				e.setAssetName(rs.getString(3));
				e.setAssetType(AssetType.valueOf(rs.getString(4)));
				e.setCost(rs.getDouble(5));
				e.setStatus(AssetStatus.getName(rs.getString(6)));
				e.setCreatedBy(rs.getString(7));
				e.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
				         
				 return e;  
			}  
		});  
	}



	public int addAsset(final Asset asset) {  
		int rows=template.update(Queries.addNewAsset,new PreparedStatementSetter() {
				public void setValues(PreparedStatement pst) throws SQLException,  
	            DataAccessException{
				pst.setString(1,asset.getSerialNumber());
				pst.setString(2,asset.getAssetName());
				pst.setString(3,asset.getAssetType().toString());
				pst.setDouble(4,asset.getCost());
				pst.setString(5,asset.getStatus().value);
				pst.setString(6,asset.getCreatedBy());
				pst.setDate(7,new java.sql.Date(new Date().getTime()));
			}
		});
		return rows;
	}
	
	
	



	public int updateAssetStatus(final int assetId,  final AssetStatus status,final String modifiedBy, final Date dateModifed) {
		
		int rows=0;
		int rows1=updateAssetToLog(assetId,modifiedBy);
		
		if(rows1!=0)
		{
		rows=template.update(Queries.updateAssetStatus,new PreparedStatementSetter() {
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,status.value);
				System.out.println(status.name());
				pst.setString(2,modifiedBy);
				pst.setDate(3,new java.sql.Date(dateModifed.getTime()));
				pst.setInt(4,assetId);
			}
		});	
		}
		return rows;
	}
	
	
	

	
     private int updateAssetToLog(int assetId, final String modifiedBy) {
		
    	 final Asset asset=getAsset(assetId);
 		
 		int rows=template.update(Queries.UPDATE_ASSET_TO_LOG_,new PreparedStatementSetter() {
 				
 		public void setValues(PreparedStatement pst) throws SQLException {
 			
 			pst.setInt(1,asset.getAssetId());
 			pst.setString(3, asset.getAssetName());
 			pst.setString(2, asset.getSerialNumber());
 			pst.setString(4, asset.getAssetType().toString());
 			pst.setDouble(5, asset.getCost());
 			pst.setString(6, asset.getStatus().value);
 			pst.setString(7, asset.getCreatedBy());
 			pst.setDate(8, (java.sql.Date) asset.getCreatedDate());
 			pst.setString(9, modifiedBy);
 			pst.setDate(10, new java.sql.Date(new Date().getTime()));
 			

 		
 			
 		}
 	});
 	
 		
 	return rows;
	}


	public List<Asset> getAssetByStatus(String status) {      
		Object args[]={status};
		System.out.println(status);
		return template.query(Queries.getAssetByStatus,args,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException {  
				Asset asset=new Asset(); 
				asset.setAssetId(rs.getInt(1));
				asset.setSerialNumber(rs.getString(2)); 
				asset.setAssetName(rs.getString(3));
				asset.setAssetType(AssetType.valueOf(rs.getString(4)));
				asset.setCost(rs.getDouble(5));
				asset.setStatus(AssetStatus.getName(rs.getString(6)));
				asset.setCreatedBy(rs.getString(7));
				asset.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
				return asset;  
			}  
		});  
	}
	
	

	public List<Asset> getEmployeeAssets(String empId) {  
		Object args[]={empId};
		return template.query(Queries.getEmployeeAsset,args,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException {  
				Asset asset=new Asset(); 
				asset.setAssetId(rs.getInt(1));
				asset.setSerialNumber(rs.getString(2)); 
				asset.setAssetName(rs.getString(3));
				asset.setAssetType(AssetType.valueOf(rs.getString(4)));
				asset.setCost(rs.getDouble(5));
				asset.setStatus(AssetStatus.getName(rs.getString(6)));
				asset.setCreatedBy(rs.getString(7));
				asset.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
				return asset;  
			}  
		});  
	}



	public List<Request> getAllAssetRequests() {               
		return template.query(Queries.getAllAssetRequests,new RowMapper<Request>(){  
			public Request mapRow(ResultSet rs, int row) throws SQLException {  
				Request request=new Request(); 
				request.setEmployeeId(rs.getString(1));
				request.setAssetType(AssetType.valueOf(rs.getString(2)));
				request.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));

				return request;

			}  
		});  
	}





	public int postAssetRequest(final AssetType assetType, final String empId, 
			final Date requestedDate)throws SQLException {
		int rows=template.update(Queries.postAssetRequest,new PreparedStatementSetter() {

		public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,empId);
				pst.setString(2,assetType.toString());
				pst.setDate(3,new java.sql.Date(requestedDate.getTime()));

			}
		});
		return rows;

	}

	
	public List<Request> getAssetRequestsByEmployee(String empId) {          
		Object args[]={empId};
		return template.query(Queries.getAssetRequestsByEmployee,args,new RowMapper<Request>(){  
			public Request mapRow(ResultSet rs, int row) throws SQLException {  
				Request request=new Request(); 
				request.setEmployeeId(rs.getString(1));
				request.setAssetType(AssetType.valueOf(rs.getString(2)));
				request.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));
				
				return request;

			}  
		});  
	}

	
	
	
	public int allocateAsset(final String assignedTo, final int assetId, final String assignedBy,final Date handOverDate) {
		int rows1=0;
		int rows=template.update(Queries.allocateAsset,new PreparedStatementSetter() {
			public void setValues(PreparedStatement pst) throws SQLException {
			pst.setString(1,assignedTo);
			pst.setString(2,assignedBy);
			pst.setInt(3,assetId);
			pst.setString(4,AssetAllocation.Allocated.value);
			pst.setDate(5,new java.sql.Date(handOverDate.getTime()));
		}
	});
		if(rows>0)
		rows1=updateMappingToLog( assetId,AssetAllocation.Allocated);
	return rows1;
	}

	
	
	
	public int deAllocateAsset(final int assetId, final String deAllocatedBy,final Date deAllocationdate) {
		
		int rows=0;
		int rows1=updateMappingToLog(assetId,AssetAllocation.DeAllocated);
		String sql=Queries.deallocateAsset;
		Object args[]={assetId};
		if(rows1>0)
		rows=template.update(sql, args);
		return rows;
	}
	
	
	//insert into mapping log 
	private int updateMappingToLog(final int assetId, final AssetAllocation status) {
		
		final AssetMapping map=getAssetMapping(assetId);
		
		int rows=template.update(Queries.UPDATE_ASSET_MAPPING_TO_LOG,new PreparedStatementSetter() {
				
		public void setValues(PreparedStatement pst) throws SQLException {
			
			pst.setString(1,map.getEmployeeId());
			pst.setString(2, map.getAssignedBy());
			pst.setInt(3, map.getAssetId());
			pst.setDate(4, new java.sql.Date (map.getAssignedDate().getTime()));
			if(status.equals(AssetAllocation.Allocated)){
			pst.setDate(5, null);	
			}
			else
			pst.setDate(5, new java.sql.Date (map.getReturnDate().getTime()));
			pst.setString(6, status.value.toString());
			
		}
	});
		
	return rows;
	
}
	
	//geting entry from asset
	private AssetMapping getAssetMapping(int assetId){  
		Object args[]={assetId};
		return template.query(Queries.getAssetMapping,args,new ResultSetExtractor<AssetMapping>(){  

			public AssetMapping extractData(ResultSet rs) throws SQLException,DataAccessException {   
				if(rs.next()){
				AssetMapping e=new AssetMapping();  
				e.setEmployeeId(rs.getString(1));
				e.setAssignedBy(rs.getString(2));
				e.setAssetId(rs.getInt(3));
				e.setAssignedDate(new java.util.Date(rs.getDate(4).getTime())); 
				
				e.setReturnDate((new Date() ));
				
				e.setStatus(AssetAllocation.getName(rs.getString(6)));
				
				return e; 
				}
				else
					return null;
			}  
		});  
	}


	
	public Asset getAsset(int assetId){  
		Object args[]={assetId};
		return template.query(Queries.getAsset,args,new ResultSetExtractor<Asset>(){  

			public Asset extractData(ResultSet rs) throws SQLException,DataAccessException {   
				if(rs.next()){
				Asset asset=new Asset();  
				asset.setAssetId(rs.getInt(1));
				asset.setSerialNumber(rs.getString(2)); 
				asset.setAssetName(rs.getString(3));
				asset.setAssetType(AssetType.valueOf(rs.getString(4))); 
				asset.setCost(rs.getDouble(5));
				asset.setStatus(AssetStatus.getName(rs.getString(6)));
				return asset; 
				}
				else
					return null;
			}  
		});  
	}

	
	
	public int postNewAssetTypeRequest(final String requestedBy, final String assetType,
			final String assetName, final Date requestedDate) throws SQLException {
		
		int rows=template.update(Queries.postNewAssetTypeRequest,new PreparedStatementSetter() {

			
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,requestedBy);
				pst.setString(2,assetType);
				pst.setString(3, assetName);
				pst.setDate(4,new java.sql.Date( requestedDate.getTime()));

			}
		});
		return rows;

	}

	
	public List<NewTypeRequest> getNewAssetTypeRequests() {
		return template.query(Queries.getAllAssetRequests,new RowMapper<NewTypeRequest>(){  
			public NewTypeRequest mapRow(ResultSet rs, int row) throws SQLException {  
				NewTypeRequest e=new NewTypeRequest(); 
				e.setEmployeeId(rs.getString(1));
				e.setAssetType((rs.getString(2)));
				e.setAssetName(rs.getString(3));
				e.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));

				return e;

			}  
		});  
	}


	
	@Override
	public List<Asset> getAssetByType(AssetType type) {
		// TODO Auto-generated method stub
		return null;
	}



	

}
