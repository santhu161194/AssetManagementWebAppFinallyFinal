package com.medplus.assetmanagementcore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.medplus.assetmanagementcore.dao.AssetDao;
import com.medplus.assetmanagementcore.model.Asset;
import com.medplus.assetmanagementcore.model.Request;
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
	public List<Asset> getAllAssets() { //working 
		return template.query(Queries.getAllAsset,new RowMapper<Asset>(){  
	        public Asset mapRow(ResultSet rs, int row) throws SQLException {  
	        	Asset e=new Asset(); 
	        	e.setAssetId(rs.getInt(1));
	        	e.setSerialNumber(rs.getString(2)); 
	        	e.setAssetName(rs.getString(3));
	           e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));
	            
	            e.setCost(rs.getDouble(5));
	           /*  code=rs.getString(6);
	            for(AssetStatus ee : AssetStatus.values()){
	    			if(code.equalsIgnoreCase(ee.value)){
	    				System.out.println("name =="+ee.name());
	    			}*/
	            AssetStatusEnum assetStatus=(AssetStatusEnum.valueOf((rs.getString(6))));

	            e.setStatus(assetStatus.getName(assetStatus.toString()));
	            
	            return e;  
	        }  
	    });  
	}

	public Asset getAsset(int assetId) {
		
		Object args[]={assetId};
		asset=template.queryForObject(Queries.getAsset, args,new BeanPropertyRowMapper<Asset>(Asset.class));
	
		return asset;
		 
	}

	public int addAsset(final Asset asset, String createdBy, final Date createdDate) {
		int rows=template.update(Queries.addNewAsset,new PreparedStatementSetter() {
			
		//	@Override
			public void setValues(PreparedStatement pst) throws SQLException {
				pst.setString(1,asset.getSerialNumber());
				pst.setString(2,asset.getAssetName());
				pst.setString(3,asset.getAssetType().toString());
				pst.setDouble(4,asset.getCost());
				pst.setString(5,asset.getStatus().toString());
				pst.setString(6,asset.getCreatedBy());
				pst.setDate(7,(java.sql.Date)createdDate);
			}
		});
		return rows;
	}

	public int updateAsset(Asset asset, String updatedBy, Date updatedDate) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateAssetStatus(final long assetId, final AssetStatusEnum status) {
		
			int rows=template.update(Queries.updateAssetStatus,new PreparedStatementSetter() {
				
				//@Override
				public void setValues(PreparedStatement pst) throws SQLException {
					
					pst.setString(1,status.toString());
					pst.setLong(2,assetId);
		
				}
			});	
			return rows;
		}
	

	
	
	
	
	public List<Asset> getAssetByStatus(String status) {      //done
		Object args[]={status};
		
		return template.query(Queries.getAssetByStatus,args,new RowMapper<Asset>(){  
	        public Asset mapRow(ResultSet rs, int row) throws SQLException {  
	        	Asset e=new Asset(); 
	        	System.out.println("1");
	        	e.setAssetId(rs.getInt(1));
	        	e.setSerialNumber(rs.getString(2)); 
	        	e.setAssetName(rs.getString(3));
	        	e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));
	            
	            e.setCost(rs.getDouble(5));
	            AssetStatusEnum assetStatus=(AssetStatusEnum.valueOf((rs.getString(6))));
	            e.setStatus(assetStatus.getName(assetStatus.toString()));
	           
	            return e;  
	        }  
	    });  
	}

	
	
	
	public List<Asset> getEmployeeAssets(String empId) {  //still working
		Object args[]={empId};
		return template.query(Queries.getEmployeeAsset,args,new RowMapper<Asset>(){  
	        public Asset mapRow(ResultSet rs, int row) throws SQLException {  
	        	Asset e=new Asset(); 
	        	e.setAssetId(rs.getInt(1));
	        	e.setSerialNumber(rs.getString(2)); 
	        	e.setAssetName(rs.getString(3));
	        	e.setAssetType(AssetTypeEnum.valueOf(rs.getString(4)));
	            
	            e.setCost(rs.getDouble(5));
	            AssetStatusEnum assetStatus=(AssetStatusEnum.valueOf((rs.getString(6))));
	            e.setStatus(assetStatus.getName(assetStatus.toString()));
	           
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
	
	
	

	public List<Request> getAssetRequests(String requestedBy) {//repeated
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
				pst.setDate(3,(java.sql.Date) requestedDate);
				
			}
		});
		return rows;
		
	}

	public List<Request> getAssetRequestsByEmployee(String empId) {          //done
		return template.query(Queries.getAssetRequestsByEmployee,new RowMapper<Request>(){  
	        public Request mapRow(ResultSet rs, int row) throws SQLException {  
	        	Request e=new Request(); 
	        	e.setEmployeeId(rs.getString(1));
	        	e.setAssetType(AssetTypeEnum.valueOf(rs.getString(2)));
	        	e.setRequestDate(new java.util.Date(rs.getDate(3).getTime()));
	            return e;
	            
	        }  
	    });  
	}

	public int allocateAsset(String empId, long assetId, String edpId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deAllocateAsset(long assetId, String edpId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}