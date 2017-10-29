package org.medplus.assetmanagementcore.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.medplus.assetmanagementcore.dao.AssetDao;
import org.medplus.assetmanagementcore.model.Asset;
import org.medplus.assetmanagementcore.model.AssetMapping;
import org.medplus.assetmanagementcore.model.NewTypeRequest;
import org.medplus.assetmanagementcore.model.Request;
import org.medplus.assetmanagementcore.utils.AssetAllocation;
import org.medplus.assetmanagementcore.utils.AssetStatus;
import org.medplus.assetmanagementcore.utils.AssetType;
import org.medplus.assetmanagementcore.utils.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AssetDaoImpl implements AssetDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Asset> getAllAssets() throws DataAccessException {
		return template.query(Queries.getAllAsset, new RowMapper<Asset>() {
			public Asset mapRow(ResultSet rs, int row) throws SQLException {
				Asset e = new Asset();
				e.setAssetId(rs.getLong(1));
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

	@Override
	public int addAsset(final Asset asset)
			throws DataIntegrityViolationException {

		int resultCount = template.update(Queries.addNewAsset,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, asset.getSerialNumber());
						pst.setString(2, asset.getAssetName());
						pst.setString(3, asset.getAssetType().toString());
						pst.setDouble(4, asset.getCost());
						pst.setString(5, asset.getStatus().value);
						pst.setString(6, asset.getCreatedBy());
						pst.setDate(7, new java.sql.Date(new Date().getTime()));
					}
				});
		return resultCount;
	}

	@Override
	public int updateAssetStatus(final long assetId, final AssetStatus status,
			final String modifiedBy) throws DataAccessException {
		int resultCountAsset = 0;
		int resultCountAssetLog = updateAssetToLog(assetId, modifiedBy);
		if (resultCountAssetLog != 0) {
			resultCountAsset = template.update(Queries.updateAssetStatus,
					new PreparedStatementSetter() {
						public void setValues(PreparedStatement pst)
								throws SQLException {
							pst.setString(1, status.value);
							System.out.println(status.name());
							pst.setString(2, modifiedBy);
							pst.setDate(3,
									new java.sql.Date(new Date().getTime()));
							pst.setLong(4, assetId);
						}
					});
		}
		return resultCountAsset;

	}

	private int updateAssetToLog(long assetId, final String modifiedBy)
			throws DataAccessException {

		final Asset asset = getAsset(assetId);
		int resultCountAssetLog = template.update(Queries.UPDATE_ASSET_TO_LOG_,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {

						pst.setLong(1, asset.getAssetId());
						pst.setString(3, asset.getAssetName());
						pst.setString(2, asset.getSerialNumber());
						pst.setString(4, asset.getAssetType().toString());
						pst.setDouble(5, asset.getCost());
						pst.setString(6, asset.getStatus().value);
						pst.setString(7, asset.getCreatedBy());
						pst.setDate(8, new java.sql.Date(asset.getCreatedDate()
								.getTime()));
						pst.setString(9, modifiedBy);
						pst.setDate(10, new java.sql.Date(new Date().getTime()));

					}
				});
		return resultCountAssetLog;

	}

	@Override
	public List<Asset> getAssetByStatus(String status)
			throws DataAccessException {

		Object args[] = { status };
		return template.query(Queries.getAssetByStatus, args,
				new RowMapper<Asset>() {
					public Asset mapRow(ResultSet rs, int row)
							throws SQLException {
						Asset asset = new Asset();
						asset.setAssetId(rs.getLong(1));
						asset.setSerialNumber(rs.getString(2));
						asset.setAssetName(rs.getString(3));
						asset.setAssetType(AssetType.valueOf(rs.getString(4)));
						asset.setCost(rs.getDouble(5));
						asset.setStatus(AssetStatus.getName(rs.getString(6)));
						asset.setCreatedBy(rs.getString(7));
						asset.setCreatedDate(new java.util.Date(rs.getDate(8)
								.getTime()));
						return asset;
					}
				});

	}

	@Override
	public List<Asset> getEmployeeAssets(String empId)
			throws DataAccessException {
		Object args[] = { empId };

		return template.query(Queries.getEmployeeAsset, args,
				new RowMapper<Asset>() {
					public Asset mapRow(ResultSet rs, int row)
							throws SQLException {
						Asset asset = new Asset();
						asset.setAssetId(rs.getLong(1));
						asset.setSerialNumber(rs.getString(2));
						asset.setAssetName(rs.getString(3));
						asset.setAssetType(AssetType.valueOf(rs.getString(4)));
						asset.setCost(rs.getDouble(5));
						asset.setStatus(AssetStatus.getName(rs.getString(6)));
						asset.setCreatedBy(rs.getString(7));
						asset.setCreatedDate(new java.util.Date(rs.getDate(8)
								.getTime()));
						return asset;
					}
				});

	}

	@Override
	public List<Request> getAllAssetRequests() throws DataAccessException {

		return template.query(Queries.getAllAssetRequests,
				new RowMapper<Request>() {
					public Request mapRow(ResultSet rs, int row)
							throws SQLException {
						Request request = new Request();
						request.setEmployeeId(rs.getString(1));
						request.setAssetType(AssetType.valueOf(rs.getString(2)));
						request.setRequestDate(new java.util.Date(rs.getDate(3)
								.getTime()));

						return request;

					}
				});

	}

	@Override
	public int saveAssetRequest(final AssetType assetType,
			final String requestedBy) throws DataAccessException,
			DataIntegrityViolationException {

		int resultCount = template.update(Queries.postAssetRequest,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, requestedBy);
						pst.setString(2, assetType.toString());
						pst.setDate(3, new java.sql.Date(new Date().getTime()));

					}
				});
		return resultCount;
	}

	@Override
	public List<Request> getAssetRequestsByEmployee(String empId)
			throws DataAccessException {
		Object args[] = { empId };

		return template.query(Queries.getAssetRequestsByEmployee, args,
				new RowMapper<Request>() {
					public Request mapRow(ResultSet rs, int row)
							throws SQLException {
						Request request = new Request();
						request.setEmployeeId(rs.getString(1));
						request.setAssetType(AssetType.valueOf(rs.getString(2)));
						request.setRequestDate(new java.util.Date(rs.getDate(3)
								.getTime()));

						return request;

					}
				});

	}

	@Override
	public int allocateAsset(final String assignedTo, final long assetId,
			final String assignedBy) throws DataAccessException,
			DataIntegrityViolationException {
		int resultCountLog = 0;
		int resultCount = 0;

		resultCount = template.update(Queries.allocateAsset,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, assignedTo);
						pst.setString(2, assignedBy);
						pst.setLong(3, assetId);
						pst.setString(4, AssetAllocation.Allocated.value);
						pst.setDate(5, new java.sql.Date(new Date().getTime()));
					}
				});
		if (resultCount > 0)
			resultCountLog = updateMappingToLog(assetId,
					AssetAllocation.Allocated);

		return resultCountLog;
	}

	@Override
	public int deAllocateAsset(final long assetId, final String deAllocatedBy)
			throws DataAccessException {

		int resultCount = 0;
		int resultCountLog = updateMappingToLog(assetId,
				AssetAllocation.DeAllocated);
		String sql = Queries.deallocateAsset;
		Object args[] = { assetId };
		if (resultCountLog > 0)
			resultCount = template.update(sql, args);
		return resultCount;
	}

	private int updateMappingToLog(final long assetId,
			final AssetAllocation status) throws DataAccessException {

		final AssetMapping map = getAssetMapping(assetId);

		int resultCount = template.update(Queries.UPDATE_ASSET_MAPPING_TO_LOG,
				new PreparedStatementSetter() {

					public void setValues(PreparedStatement pst)
							throws SQLException {

						pst.setString(1, map.getEmployeeId());
						pst.setString(2, map.getAssignedBy());
						pst.setLong(3, map.getAssetId());
						pst.setDate(4, new java.sql.Date(map.getAssignedDate()
								.getTime()));
						if (status.equals(AssetAllocation.Allocated)) {
							pst.setDate(5, null);
						} else
							pst.setDate(5, new java.sql.Date(map
									.getReturnDate().getTime()));

						pst.setString(6, status.value.toString());

					}
				});

		return resultCount;

	}

	private AssetMapping getAssetMapping(long assetId)
			throws DataAccessException {
		Object args[] = { assetId };
		return template.query(Queries.getAssetMapping, args,
				new ResultSetExtractor<AssetMapping>() {

					public AssetMapping extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						if (rs.next()) {
							AssetMapping e = new AssetMapping();
							e.setEmployeeId(rs.getString(1));
							e.setAssignedBy(rs.getString(2));
							e.setAssetId(rs.getInt(3));
							e.setAssignedDate(new java.util.Date(rs.getDate(4)
									.getTime()));

							e.setReturnDate((new Date()));

							e.setStatus(AssetAllocation.getName(rs.getString(6)));

							return e;
						} else
							return null;
					}
				});
	}

	@Override
	public List<AssetMapping> getAssetMappingLog() throws DataAccessException {
		return template.query(Queries.getAssetMappingLog, new RowMapper<AssetMapping>() {
			public AssetMapping mapRow(ResultSet rs, int row) throws SQLException {
				AssetMapping e = new AssetMapping();
				e.setEmployeeId(rs.getString(1));
				e.setAssignedBy(rs.getString(2));
				e.setAssetId(rs.getInt(3));
				e.setAssignedDate(new java.util.Date(rs.getDate(4)
						.getTime()));
				if (AssetAllocation.getName(rs.getString(6)).equals(AssetAllocation.DeAllocated)) {
					e.setReturnDate(new java.util.Date(rs.getDate(5)
							.getTime()));
				}
				e.setStatus(AssetAllocation.getName(rs.getString(6)));
				return e;
			}
		});

	}
	@Override
	public Asset getAsset(long assetId) throws DataAccessException {
		Object args[] = { assetId };

		return template.query(Queries.getAsset, args,
				new ResultSetExtractor<Asset>() {

					public Asset extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						if (rs.next()) {
							Asset asset = new Asset();
							asset.setAssetId(rs.getLong(1));
							asset.setSerialNumber(rs.getString(2));
							asset.setAssetName(rs.getString(3));
							asset.setAssetType(AssetType.valueOf(rs
									.getString(4)));
							asset.setCost(rs.getDouble(5));
							asset.setStatus(AssetStatus.getName(rs.getString(6)));
							asset.setCreatedDate(new java.util.Date(rs.getDate(
									8).getTime()));
							return asset;
						} else
							return null;
					}
				});

	}

	@Override
	public int saveNewAssetTypeRequest(final String requestedBy,
			final String assetType, final String assetName)
			throws DataAccessException, DataIntegrityViolationException {
		int resultCount = 0;
		resultCount = template.update(Queries.postNewAssetTypeRequest,
				new PreparedStatementSetter() {
					public void setValues(PreparedStatement pst)
							throws SQLException {
						pst.setString(1, requestedBy);
						pst.setString(2, assetType);
						pst.setString(3, assetName);
						pst.setDate(4, new java.sql.Date(new Date().getTime()));

					}
				});

		return resultCount;

	}

	@Override
	public int updateAsset(final Asset asset) throws DataAccessException {
		int resultCount = 0;
		int resultCountLog = updateAssetToLog(asset.getAssetId(),
				asset.getModifiedBy());
		if (resultCountLog != 0) {
			resultCount = template.update(Queries.updateAsset,
					new PreparedStatementSetter() {
						public void setValues(PreparedStatement pst)
								throws SQLException {

							pst.setString(1, asset.getAssetName());
							pst.setString(2, asset.getSerialNumber());
							pst.setDouble(3, asset.getCost());
							pst.setDate(4,
									new java.sql.Date(new Date().getTime()));
							pst.setString(5, asset.getModifiedBy());
							pst.setLong(6, asset.getAssetId());

						}
					});
		}
		return resultCount;
	}

	@Override
	public List<NewTypeRequest> getNewAssetTypeRequests()
			throws DataAccessException {

		return template.query(Queries.getAllAssetRequests,
				new RowMapper<NewTypeRequest>() {
					public NewTypeRequest mapRow(ResultSet rs, int row)
							throws SQLException {
						NewTypeRequest e = new NewTypeRequest();
						e.setEmployeeId(rs.getString(1));
						e.setAssetType((rs.getString(2)));
						e.setAssetName(rs.getString(3));
						e.setRequestDate(new java.util.Date(rs.getDate(3)
								.getTime()));

						return e;

					}
				});

	}

	@Override
	public List<Asset> getAssetByType(AssetType type)
			throws DataAccessException {
		Object args[] = { type.toString() };
		System.out.println(type);
		return template.query(Queries.getAssetByType, args,
				new RowMapper<Asset>() {
					public Asset mapRow(ResultSet rs, int row)
							throws SQLException {
						Asset asset = new Asset();
						asset.setAssetId(rs.getInt(1));
						asset.setSerialNumber(rs.getString(2));
						asset.setAssetName(rs.getString(3));
						asset.setAssetType(AssetType.valueOf(rs.getString(4)));
						asset.setCost(rs.getDouble(5));
						asset.setStatus(AssetStatus.getName(rs.getString(6)));
						asset.setCreatedBy(rs.getString(7));
						asset.setCreatedDate(new java.util.Date(rs.getDate(8)
								.getTime()));
						return asset;
					}
				});
	}
	@Override
	public int removeAssetRequest(final Request request) throws DataAccessException {
		
			int resultCount=template.update(Queries.removeAssetRequest,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement pst) throws SQLException {	
				pst.setString(1,request.getEmployeeId());		
				pst.setString(2,request.getAssetType().toString());		
			}
			
			});
		
			return resultCount;
		}

}