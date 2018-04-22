package com.arnold.server.service.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.model.Industry;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class IndustryService extends BaseService {

	public int addIndustry(String name, String typeId, String orgId, int price, 
			int guidePrice, String guidePriceFile){
		
		//Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Industry industryModel = new Industry();
		industryModel.setId(Utils.create36UUID());
//		industryModel.setName(name);
//		industryModel.setTypeId(typeId);
//		industryModel.setOrgId(orgId);
//		industryModel.setPrice(price);
//		industryModel.setGuidePrice(guidePrice);
		industryModel.setGuidePriceFile(guidePriceFile);
		
		industryModel.save();
			
		return ConstUtils.DEAL_SUCCESS;
		
	}
	
	public Map<String, Object> pageIndustry(int pageNumber, int pageSize){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Industry industry = new Industry();
			
			Page<Industry> industryModel = industry.pageIndustryByIds(pageNumber, pageSize, "");
			
			resultMap.put(ConstUtils.R_PAGE, industryModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	
	public Map<String, Object> pageIndustryByIds(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Industry industry = new Industry();
			
			Page<Industry> industryModel = industry.pageIndustryByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, industryModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
		
	}
	/**
	 * 
	 * @Description: 价格日志
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午1:43:47
	 */
	public Map<String, Object> pageIndustryPrice(int pageNumber,int pageSize,String keyWord,String startDate, String endDate, String userId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			resultMap.put(ConstUtils.R_PAGE, Industry.pageIndustryPrice(pageNumber, pageSize, startDate, endDate, keyWord));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG,ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		return resultMap;
		
	}
	
	public Map<String, Object> delIndustry(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Industry industryModel = Industry.dao.findById(id);
			
			if(industryModel == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个产业！");
				return resultMap;
			}
			
			//TODO:逻辑删除
			//industryModel.delete();
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
		
	}
	/**
	 * 
	 * @Description: 更新产业信息
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午1:43:47
	 * @param typeId
	 * @param unitName
	 * @param costUnitPrice
	 * @param underwriteUnitPrice
	 * @param guidePrice
	 * @param predictAmount
	 * @param guidePriceFile
	 * @param userId
	 * @return
	 */
	public Map<String, Object> updateIndustry(String typeId,String ptypeId, String typeName, String ptypeName,  String unitName, Double costUnitPrice,
			Double underwriteUnitPrice, Double guidePrice, Integer predictAmount, String guidePriceFile,
			String userId, Double flUnitPrice, Double lyUnitPrice, Double bmUnitPrice, Double ggUnitPrice, 
			Double qtUnitPrice, Double slUnitPrice, Double syUnitPrice, Double lsslUnitPrice,Double expected_amount){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			int flag=1;//0保存1更新
			Industry industryModel = Industry.queryIndustryByTypeId(typeId);
			
			if(industryModel == null){
				flag=0;
				industryModel=new Industry();
				
				industryModel.setId(typeId);
			}
			//industryModel.setId(typeId);
			industryModel.setTypeId(typeId);
			industryModel.setPtypeId(ptypeId);
			if(industryModel.getUnitName()==null||(industryModel.getUnitName()!=null&&
					!industryModel.getUnitName().equals(unitName))){
				industryModel.setUnitName(unitName);
			}
			if(industryModel.getCostUnitPrice()==null||(industryModel.getCostUnitPrice()!=null&&
					!industryModel.getCostUnitPrice().equals(costUnitPrice))){
				industryModel.setCostUnitPrice(costUnitPrice);
			}
			
			if(industryModel.getUnderwriteUnitPrice()==null||(industryModel.getUnderwriteUnitPrice()!=null&&
					!industryModel.getUnderwriteUnitPrice().equals(underwriteUnitPrice))){
				industryModel.setUnderwriteUnitPrice(underwriteUnitPrice);
			}
			
			if(industryModel.getGuidePrice()==null||(industryModel.getGuidePrice()!=null&&
					!industryModel.getGuidePrice().equals(guidePrice))){
				industryModel.setGuidePrice(guidePrice);
			}
			if(industryModel.getPredictAmount()==null||(industryModel.getPredictAmount()!=null&&
					!industryModel.getPredictAmount().equals(predictAmount))){
				industryModel.setPredictAmount(predictAmount);
			}
			if(industryModel.getGuidePriceFile()==null||(industryModel.getGuidePriceFile()!=null&&
					!industryModel.getGuidePriceFile().equals(guidePriceFile))){
				industryModel.setGuidePriceFile(guidePriceFile);
			}
			if(industryModel.getFlUnitPrice()==null||(industryModel.getFlUnitPrice()!=null&&
					!industryModel.getFlUnitPrice().equals(flUnitPrice))){
				industryModel.setFlUnitPrice(flUnitPrice);
			}
			if(industryModel.getLyUnitPrice()==null||(industryModel.getLyUnitPrice()!=null&&
					!industryModel.getLyUnitPrice().equals(lyUnitPrice))){
				industryModel.setLyUnitPrice(lyUnitPrice);
			}
			if(industryModel.getBmUnitPrice()==null||(industryModel.getBmUnitPrice()!=null&&
					!industryModel.getBmUnitPrice().equals(bmUnitPrice))){
				industryModel.setBmUnitPrice(bmUnitPrice);
			}
			if(industryModel.getGgUnitPrice()==null||(industryModel.getGgUnitPrice()!=null&&
					!industryModel.getGgUnitPrice().equals(ggUnitPrice))){
				industryModel.setGgUnitPrice(ggUnitPrice);
			}
			if(industryModel.getQtUnitPrice()==null||(industryModel.getQtUnitPrice()!=null&&
					!industryModel.getQtUnitPrice().equals(qtUnitPrice))){
				industryModel.setQtUnitPrice(qtUnitPrice);
			}
			if(industryModel.getSlUnitPrice()==null||(industryModel.getSlUnitPrice()!=null&&
					!industryModel.getSlUnitPrice().equals(slUnitPrice))){
				industryModel.setSlUnitPrice(slUnitPrice);
			}
			if(industryModel.getSyUnitPrice()==null||(industryModel.getSyUnitPrice()!=null&&
					!industryModel.getSyUnitPrice().equals(syUnitPrice))){
				industryModel.setSyUnitPrice(syUnitPrice);
			}
			if(industryModel.getLsslUnitPrice()==null||(industryModel.getLsslUnitPrice()!=null&&
					!industryModel.getLsslUnitPrice().equals(lsslUnitPrice))){
				industryModel.setLsslUnitPrice(lsslUnitPrice);
			}
			if(industryModel.getExpected_amount()==null||(industryModel.getExpected_amount()!=null&&
					!industryModel.getExpected_amount().equals(expected_amount))){
				industryModel.setExpected_amount(expected_amount);
			}
			industryModel.setOperatorId(userId);
			industryModel.setOperatorTime(new Date());

			if(flag==0){
				industryModel.save();

			}else{
				industryModel.update();
			}
			
			String sql = "SELECT * FROM tb_industry_log l WHERE l.typeId = '"+typeId+"' ORDER BY l.operatorTime DESC LIMIT 0,1 ";
			Record r = Db.findFirst(sql);
			boolean isnew = true;
			if(r !=null){
				if(r.getDouble("guidePrice").doubleValue()==industryModel.getGuidePrice().doubleValue()&&
						r.getDouble("underwriteUnitPrice").doubleValue()==industryModel.getUnderwriteUnitPrice().doubleValue()){
					isnew = false;
				}
			}
			if(isnew){
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sql = "INSERT tb_industry_log (id,ptypeId,pTypeName,typeId,typeName,unitName,guidePrice,costUnitPrice,underwriteUnitPrice,operatorId,operatorTime) VALUE(";
				sql = sql+"'"+Utils.create36UUID()+"','"+ptypeId+"','"+ptypeName+"','"+typeId+"','"+typeName+"','"+unitName+"',"+guidePrice+","+costUnitPrice+","
						+underwriteUnitPrice+",'"+userId+"','"+f.format(new Date())+"')";
				Db.update(sql);
			}
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG,ConstUtils.DEAL_SUCCESS_STR);
			
		}catch(Exception e){
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		}
		return resultMap;
		
	}
	public Industry queryIndustryByTypeId(String typeId){
		return Industry.queryIndustryByTypeId(typeId);
		
	}
}
