package com.medplus.assetmanagementcore.dao.impl;

import java.sql.Connection;
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
import com.medplus.assetmanagementcore.utils.AssetAllocation;
import com.medplus.assetmanagementcore.utils.AssetStatusEnum;
import com.medplus.assetmanagementcore.utils.AssetTypeEnum;
import com.medplus.assetmanagementcore.utils.Queries;
@Component
public class AssetDaoImpl implements AssetDao {
	@Autowired
	JdbcTemplate template;
	@Autowired
	Asset asset;
	@Autowired
	Request request;
	Connection conn;
	PreparedStatement pst;
	String code="";
	public List<Asset> getAllAssets() {                                              //working done
		return template.query(Queries.getAllAsset,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException,  
            DataAccessException {  
				Asset e=new Asset(); 
				e.setAssetId(rs.getInt(1));
				e.setSerialNumber(rs.getString(2)); 
				e.setAssetName(rs.getString(3));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));
				e.setCost(rs.getDouble(5));
				e.setStatus(AssetStatusEnum.getName(rs.getString(6)));
				e.setCreatedBy(rs.getString(7));
				e.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
			/*	e.setModifiedBy(rs.getString(9));
				e.setCreatedDate(new java.util.Date(rs.getDate(10).getTime()));*/
				         
				 return e;  
			}  
		});  
	}

	/*public Asset getAsset(int assetId) {

		Object args[]={assetId};
		asset=template.queryForObject(Queries.getAsset, args,new BeanPropertyRowMapper<Asset>(Asset.class));

		return asset;

	}*/

	public int addAsset(final Asset asset, String createdBy, final Date createdDate) {   //done
		int rows=template.update(Queries.addNewAsset,new PreparedStatementSetter() {
				public void setValues(PreparedStatement pst) throws SQLException,  
	            DataAccessException{
				pst.setString(1,asset.getSerialNumber());
				pst.setString(2,asset.getAssetName());
				pst.setString(3,asset.getAssetType().toString());
				pst.setDouble(4,asset.getCost());
				pst.setString(5,asset.getStatus().value);
				pst.setString(6,asset.getCreatedBy());
				pst.setDate(7,new java.sql.Date(createdDate.getTime()));
			}
		});
		return rows;
	}

	public int updateAsset(Asset asset, String updatedBy, Date updatedDate) {  //Not Required
		// TODO Auto-generated method stub
		return 0;
	}
//working done with log
	public int updateAssetStatus(final int assetId, final AssetStatusEnum status,final String modifiedBy, final Date dateModifed) {
		updateAssetToLog(assetId);	
		int rows=template.update(Queries.updateAssetStatus,new PreparedStatementSetter() {
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,status.value);
				pst.setString(2,modifiedBy);
				pst.setDate(3,new java.sql.Date(dateModifed.getTime()));
				pst.setInt(4,assetId);
			}
		});	
		return rows;
	}
	// mtd update status log

	
     private int updateAssetToLog(int assetId) {
		
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
 			pst.setString(9, asset.getModifiedBy());
 			pst.setDate(10, (java.sql.Date) asset.getDateModified());
 			

 		
 			
 		}
 	});
 	
 		
 	return rows;
	}

	//
	public List<Asset> getAssetByStatus(String status) {      //done
		Object args[]={status};
		System.out.println(status);
		return template.query(Queries.getAssetByStatus,args,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException {  
				Asset e=new Asset(); 
				e.setAssetId(rs.getInt(1));
				e.setSerialNumber(rs.getString(2)); 
				e.setAssetName(rs.getString(3));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));

				e.setCost(rs.getDouble(5));
				/* AssetStatusEnum assetStatus=(AssetStatusEnum.valueOf((rs.getString(6))));
	            e.setStatus(assetStatus.getName(assetStatus.toString()));
				 */
				e.setStatus(AssetStatusEnum.getName(rs.getString(6)));
				e.setCreatedBy(rs.getString(7));
				e.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
				return e;  
			}  
		});  
	}




	public List<Asset> getEmployeeAssets(String empId) {  //done
		Object args[]={empId};
		return template.query(Queries.getEmployeeAsset,args,new RowMapper<Asset>(){  
			public Asset mapRow(ResultSet rs, int row) throws SQLException {  
				Asset e=new Asset(); 
				e.setAssetId(rs.getInt(1));
				e.setSerialNumber(rs.getString(2)); 
				e.setAssetName(rs.getString(3));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));
				e.setCost(rs.getDouble(5));
				e.setStatus(AssetStatusEnum.getName(rs.getString(6)));
				e.setCreatedBy(rs.getString(7));
				e.setCreatedDate(new java.util.Date(rs.getDate(8).getTime()));
				return e;  
			}  
		});  
	}



	public List<Request> getAllAssetRequests() {               //done
		return template.query(Queries.getAllAssetRequests,new RowMapper<Request>(){  
			public Request mapRow(ResultSet rs, int row) throws SQLException {  
				Request e=new Request(); 
				e.setEmployeeId(rs.getString(1));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(2)));
				e.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));

				return e;

			}  
		});  
	}




	public List<Request> getAssetRequests(String requestedBy) {//repeated//done
		// TODO Auto-generated method stub
		return null;
	}


	public int postAssetRequest(final AssetTypeEnum assetType, final String empId, //done
			final Date requestedDate)throws SQLException {
		int rows=template.update(Queries.postAssetRequest,new PreparedStatementSetter() {

			//@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,empId);
				pst.setString(2,assetType.toString());
				pst.setDate(3,new java.sql.Date(requestedDate.getTime()));

			}
		});
		return rows;

	}

	public List<Request> getAssetRequestsByEmployee(String empId) {          //done
		Object args[]={empId};
		return template.query(Queries.getAssetRequestsByEmployee,args,new RowMapper<Request>(){  
			public Request mapRow(ResultSet rs, int row) throws SQLException {  
				Request e=new Request(); 
				e.setEmployeeId(rs.getString(1));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(2)));
				e.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));
				
				return e;

			}  
		});  
	}
//done
	public int allocateAsset(final String assignedTo, final int assetId, final String assignedBy,final Date handOverDate) {
		
		
		int rows=template.update(Queries.allocateAsset,new PreparedStatementSetter() {
			public void setValues(PreparedStatement pst) throws SQLException {
			pst.setString(1,assignedTo);
			pst.setString(2,assignedBy);
			pst.setInt(3,assetId);
			pst.setString(4,AssetAllocation.Allocated.value);
			pst.setDate(5,new java.sql.Date(handOverDate.getTime()));
		}
	});
	return rows;
	}
//done
	public int deAllocateAsset(final int assetId, final String deAllocatedBy,final Date deAllocationdate) {
		updateMappingToLog( assetId);
		int rows=template.update(Queries.deallocateAsset,new PreparedStatementSetter() {
			public void setValues(PreparedStatement pst) throws SQLException {
			
			pst.setString(1,AssetAllocation.DeAllocated.value);
			pst.setDate(2,(new java.sql.Date(deAllocationdate.getTime())));
			pst.setString(3,deAllocatedBy);            //assignedBy& deallocatedBy both in same column
			pst.setInt(4,assetId);
		}
	});
	return rows;
	}
	//insert into mapping log 
private int updateMappingToLog(final int assetId) {
		
		final AssetMapping map=getAssetMapping(assetId);
		
		int rows=template.update(Queries.UPDATE_ASSET_MAPPING_TO_LOG,new PreparedStatementSetter() {
				
		public void setValues(PreparedStatement pst) throws SQLException {
			System.out.println("I got the status as"+map.getStatus());
			pst.setString(1,map.getEmployeeId());
			pst.setString(2, map.getAssignedBy());
			pst.setInt(3, map.getAssetId());
			pst.setDate(4, new java.sql.Date (map.getAssignedDate().getTime()));
			pst.setDate(5, new java.sql.Date (map.getReturnDate().getTime()));
			pst.setString(6, map.getStatus().value.toString());
			
		}
	});
	
		
	return rows;
	
}
	//geting entry from asset
	public AssetMapping getAssetMapping(int assetId){  
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
				System.out.println("I got the status as"+rs.getString(6));
				e.setStatus(AssetAllocation.getName(rs.getString(6)));
				
				return e; 
				}
				else
					return null;
			}  
		});  
	}

	//---working            done
	public Asset getAsset(int assetId){  
		Object args[]={assetId};
		return template.query(Queries.getAsset,args,new ResultSetExtractor<Asset>(){  

			public Asset extractData(ResultSet rs) throws SQLException,DataAccessException {   
				if(rs.next()){
				Asset e=new Asset();  
				e.setAssetId(rs.getInt(1));
				e.setSerialNumber(rs.getString(2)); 
				e.setAssetName(rs.getString(3));
				e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4))); 
				e.setCost(rs.getDouble(5));
				e.setStatus(AssetStatusEnum.getName(rs.getString(6)));
				return e; 
				}
				else
					return null;
			}  
		});  
	}

	public int postNewAssetTypeRequest(final String requestedBy, final String assetType,
			final String assetName, final Date requestedDate) throws SQLException {
		int rows=template.update(Queries.postNewAssetTypeRequest,new PreparedStatementSetter() {

			//@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,requestedBy);
				pst.setString(2,assetType);
				pst.setString(3, assetName);
				pst.setDate(4,(java.sql.Date) requestedDate);

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

	//-------------


}
