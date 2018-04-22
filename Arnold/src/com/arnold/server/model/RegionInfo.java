package com.arnold.server.model;

import java.util.List;

import com.arnold.server.model.base.BaseRegionInfo;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class RegionInfo extends BaseRegionInfo<RegionInfo> {
	public static final RegionInfo dao = new RegionInfo();

	final String tableName = "tb_region_info";
	
	public RegionInfo findByRegionId(String regionId) {
		
		String sql="select * from " + tableName + " where regionId=? and isValid=?";
		
		return dao.findFirst(sql, regionId, 0);
	}

	public List<RegionInfo> findAll() {
		String sql="select * from " + tableName + " where isValid=?";
		
		return dao.find(sql, 0);
	}
}