package com.arnold.server.arnoldService;

import com.arnold.server.model.*;
import com.arnold.server.service.BaseService;
import com.arnold.server.service.api.*;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.*;

/**
 * @description TODO
 */
public class ArnoldService2 extends BaseService {

	/**
	 * @Description: 添加就业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午2:56:19
	 * @return
	 */
	public Map<String, Object> addEmploymenValliager(String villagerId, String postId, String memberId,
			Integer ralationType, Double salary, Double averageIncome, String companyProject, String jobCategory,
			Date postLeaveDate, String reason, String createUserId, Integer isEat, Integer isEatEncase,
			Integer isEncase, Integer isFiveFund, Integer isLunch, Integer isOther, String department, boolean isEdit,
			String jobAdress, String id, Boolean isTrain, String phone) {
		VillagerService villagerService = new VillagerService();
		Villager villager = villagerService.queryValligerById(villagerId);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (villager == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			return resultMap;
		}
		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isTrain", isTrain);
			villagerService.updateVillagerPhoneAndIsWork(villagerId, 0, phone, paramMap);
			// 保存流水
			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();
			valigerPostHappenService.addVillagerPostHappen(villagerId, postId, memberId, ralationType, salary,
					averageIncome, companyProject, jobCategory, postLeaveDate, reason, createUserId, isEat, isEatEncase,
					isEncase, isFiveFund, isLunch, isOther, department, isEdit, jobAdress, id);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 根据村名ID查询就业记录
	 * @return
	 */
	public Map<String, Object> queryVillagerPostHappenByVillagerId(String villagerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			resultMap.put(ConstUtils.R_MODEL, Arnold2.queryVillagerPostHappenByVillagerId(villagerId));

		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 根据villagerId获取
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午2:56:19
	 * @return
	 */
	public Map<String, Object> queryEmploymenValliagerByVillagerId(String villagerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			resultMap.put(ConstUtils.R_MODEL, Arnold2.queryEmploymentValliagerByValliagerId(villagerId));

		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 更新就业人员 - 失业
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午2:56:19
	 * @return
	 */
	public Map<String, Object> updateEmploymenValliager(String villagerId, String userId, Date postLeaveDate,
			String reason, int isWorking, int ralationType) {
		VillagerService villagerService = new VillagerService();
		Villager villager = villagerService.queryValligerById(villagerId);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (villager == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			return resultMap;
		}
		try {

			villagerService.updateVillagerIsWorking(villagerId, 1, null);

			// 保存流水
			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();
			VillagerPostHappen valigerPostHappen = valigerPostHappenService.queryVillagerPostHappen(villagerId);
			valigerPostHappenService.addVillagerPostHappen(villagerId, valigerPostHappen.getPostId(),
					valigerPostHappen.getMemberId(), ralationType, valigerPostHappen.getSalary(),
					valigerPostHappen.getAverageIncome(), valigerPostHappen.getCompanyProject(),
					valigerPostHappen.getJobCategory(), postLeaveDate, reason, userId, valigerPostHappen.getIsEat(),
					valigerPostHappen.getIsEatEncase(), valigerPostHappen.getIsEncase(),
					valigerPostHappen.getIsFiveFund(), valigerPostHappen.getIsLunch(), valigerPostHappen.getIsOther(),
					valigerPostHappen.getDepartment(), true, (valigerPostHappen.get("jobAddress") == null) ? null
							: valigerPostHappen.get("jobAddress").toString(),
					null);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查询就业或者失业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午1:05:58
	 * @param pageNumber
	 * @param pageSize
	 * @param typeId
	 * @param postId
	 * @param isWorking
	 * @param keyWord
	 * @return
	 */
	public Map<String, Object> pageValliager(int pageNumber, int pageSize, String typeId, String postId, int isWorking,
			String keyWord) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold2 arnold2 = new Arnold2();
			Page<Record> recordModel = arnold2.pageUnEmploymentValliager(pageNumber, pageSize, typeId, postId,
					isWorking, keyWord);

			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	/**
	 * @Description:
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午3:38:19
	 * @param id
	 * @return
	 */
	public Map<String, Object> queryPublicProjectById(String id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold1 arnold1 = new Arnold1();
			Record recordModel = arnold1.queryPublicProjectById(id);

			resultMap.put(ConstUtils.R_MODEL, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}

		return resultMap;
	}

	/**
	 * @Description: 增加岗位
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午1:05:58
	 */
	public Map<String, Object> addPost(String userId, String name, String typeId, String orgId, int number,
			Double averageIncome, String department, String content, int isFiveFund, int isEatEncase, int isEat,
			int isEncase, int isLunch, int isOther, String attachment) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			PostService postService = new PostService();

			postService.addPost(userId, name, typeId, orgId, number, averageIncome, department, content, isFiveFund,
					isEatEncase, isEat, isEncase, isLunch, isOther, attachment);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	/**
	 * @Description:根据岗位id查询
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午1:05:58
	 */
	public Map<String, Object> queryPostById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		Arnold2 arnold2 = new Arnold2();

		resultMap.put(ConstUtils.R_MODEL, arnold2.queryPostById(id));
		return resultMap;
	}

	/**
	 * @Description: 分页查询岗位信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pagePost(int pageNumber, int pageSize, String postId, String keyWord) {
		PostService postService = new PostService();
		return postService.pagePost(pageNumber, pageSize, postId, keyWord);

	}

	/**
	 * @Description: 删除岗位信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> deletePostBy(String id) {
		PostService postService = new PostService();
		return postService.delPost(id);

	}

	/**
	 * @Description: 增加企业信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午1:39:36
	 * @param name
	 * @param userId
	 * @param address
	 * @param personNo
	 * @param orgId
	 * @param personId
	 * @param typeId
	 * @param writerId
	 * @param operatorId
	 * @param remark
	 * @return
	 */
	public Map<String, Object> addEnterprise(String name, String contact, String userId, String address,
			String personNo, String orgId, String personId, String typeId, String writerId, String operatorId,
			String remark, String phone, String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			EnterpriseService enterpriseService = new EnterpriseService();
			enterpriseService.addEnterprise(name, contact, userId, address, personNo, orgId, personId, typeId, writerId,
					operatorId, remark, phone, email);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		return resultMap;
	}

	/**
	 * @Description: 分页查询企业信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageEnterprise(int pageNumber, int pageSize, String keyWord) {
		EnterpriseService enterpriseService = new EnterpriseService();
		return enterpriseService.pageEnterprise(pageNumber, pageSize, keyWord);

	}

	/**
	 * @Description: 根据id查询
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午1:39:36
	 * @param id
	 * @return
	 */
	public Map<String, Object> queryEnterpriseById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {
			resultMap.put(ConstUtils.R_MODEL, arnold2.queryEnterpriseById(id));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		return resultMap;
	}

	/**
	 * @Description: 删除企业信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> deleteEnterpriseById(String id) {
		EnterpriseService enterpriseService = new EnterpriseService();
		return enterpriseService.delEnterprise(id);

	}

	/**
	 * @Description: 查找村名
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> findValligerByKeyWord(String keyWord) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		VillagerService villagerService = new VillagerService();
		resultMap.put(ConstUtils.R_LIST, villagerService.queryValligerByKeyWord(keyWord));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 更新产业信息
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午2:16:48
	 * @param typeId
	 * @return
	 */
	public Map<String, Object> queryIndustryByTypeId(String typeId) {
		IndustryService industryService = new IndustryService();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ConstUtils.R_MODEL, industryService.queryIndustryByTypeId(typeId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新产业信息
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午2:16:48
	 * @param typeId
	 * @param unitName
	 * @param costUnitPrice
	 * @param underwriteUnitPrice
	 * @param guidePrice
	 * @param predictAmount
	 * @param guidePriceFile
	 * @param userId
	 * @param lsslUnitPrice
	 * @param syUnitPrice
	 * @param slUnitPrice
	 * @param qtUnitPrice
	 * @param ggUnitPrice
	 * @param bmUnitPrice
	 * @param lyUnitPrice
	 * @param flUnitPrice
	 * @return
	 */
	public Map<String, Object> updateIndustry(String typeId, String ptypeId, String typeName, String ptypeName,
			String unitName, Double costUnitPrice, Double underwriteUnitPrice, Double guidePrice, Integer predictAmount,
			String guidePriceFile, String userId, Double flUnitPrice, Double lyUnitPrice, Double bmUnitPrice,
			Double ggUnitPrice, Double qtUnitPrice, Double slUnitPrice, Double syUnitPrice, Double lsslUnitPrice,
			Double expected_amount) {
		IndustryService industryService = new IndustryService();
		return industryService.updateIndustry(typeId, ptypeId, typeName, ptypeName, unitName, costUnitPrice,
				underwriteUnitPrice, guidePrice, predictAmount, guidePriceFile, userId, flUnitPrice, lyUnitPrice,
				bmUnitPrice, ggUnitPrice, qtUnitPrice, slUnitPrice, syUnitPrice, lsslUnitPrice, expected_amount);
	}

	/**
	 * @Description: 分页查询价格日志
	 * @author Li Bangming;
	 * @date 2017年8月17日 下午3:01:01
	 * @param pageNumber
	 * @param pageSize
	 * @param keyWord
	 * @param startDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public Map<String, Object> pageIndustryPrice(int pageNumber, int pageSize, String keyWord, String startDate,
			String endDate, String userId) {
		IndustryService industryService = new IndustryService();
		return industryService.pageIndustryPrice(pageNumber, pageSize, keyWord, startDate, endDate, userId);
	}

	/**
	 * @Description: 查找集体经济
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageCollectiveEconomy(int pageNumber, int pageSize, String keyWord, String rName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyService collectiveEconomyService = new CollectiveEconomyService();
		resultMap.put(ConstUtils.R_PAGE,
				collectiveEconomyService.pageCollectiveEconomy(pageNumber, pageSize, keyWord, rName));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 保存集体经济信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> saveCollectiveEconomy(String regionId, String valligerId, String phone, String userId,
			String valligerName, Double totalPrice) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyService collectiveEconomyService = new CollectiveEconomyService();
		String collectiveEconomyId = collectiveEconomyService.saveCollectiveEconomy(regionId, valligerId, userId, phone,
				valligerName, totalPrice);
		if (String.valueOf(ConstUtils.DATA_OPERATE_ERROR).equals(collectiveEconomyId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			return resultMap;

		}
		/*
		 * CollectiveEconomyPurchaseHappenService
		 * collectiveEconomyPurchaseHappenService=new
		 * CollectiveEconomyPurchaseHappenService();
		 * collectiveEconomyPurchaseHappenService.saveCollectiveEconomyPurchaseHappen(
		 * collectiveEconomyId, regionId,industryTypeId, purchaseContent, totalPrice,
		 * orgId, tradeTime, userId);
		 */
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 检测集体经济采购信息参数有效性
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public boolean checkCePurchasesJsons(JSONArray cePurchasesJsons) {

		for (int i = 0; i < cePurchasesJsons.length(); i++) {
			try {
				String purchaseContent = cePurchasesJsons.getJSONObject(i).getString("purchaseContent");
				Double totalPrice = cePurchasesJsons.getJSONObject(i).getDouble("totalPrice");
				String orgId = cePurchasesJsons.getJSONObject(i).getString("orgId");
				String industryTypeId = cePurchasesJsons.getJSONObject(i).getString("industryTypeId");

				String tradeTimeStr = cePurchasesJsons.getJSONObject(i).getString("tradeTime");
				if (purchaseContent == null || totalPrice == null || orgId == null || tradeTimeStr == null
						|| industryTypeId == null) {
					return false;
				}
				new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tradeTimeStr.trim());
			} catch (Exception e) {
				return false;
			}
		}
		return true;

	}

	/**
	 * @Description: 保存集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> saveCollectiveEconomyPurachase(String collectiveEconomyId, String regionId,
			String industryTypeId, String purchaseContent, Double totalPrice, String orgId, Date tradeTime,
			String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyService collectiveEconomyService = new CollectiveEconomyService();
		CollectiveEconomy collectiveEconomy = collectiveEconomyService
				.queryCollectiveEconomyByRegionIdOrCollectiveEconomyId(regionId, collectiveEconomyId);
		if (collectiveEconomy == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERRO);
			return resultMap;

		}
		CollectiveEconomyPurchaseHappenService collectiveEconomyPurchaseHappenService = new CollectiveEconomyPurchaseHappenService();
		collectiveEconomyPurchaseHappenService.saveCollectiveEconomyPurchaseHappen(collectiveEconomy.getId(),
				collectiveEconomy.getRegionId(), industryTypeId, purchaseContent, totalPrice, orgId, tradeTime, userId);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查找集体经济
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageCollectiveEconomyPurchase(int pageNumber, int pageSize, String collectiveEconomyId,
			String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyPurchaseHappenService collectiveEconomyPurchaseHappenService = new CollectiveEconomyPurchaseHappenService();
		resultMap.put(ConstUtils.R_PAGE, collectiveEconomyPurchaseHappenService
				.pageCollectiveEconomyPurchaseHappen(pageNumber, pageSize, collectiveEconomyId, regionId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description:
	 * @author Li Bangming;
	 * @date 2017年8月18日 下午4:26:27
	 * @return
	 */
	public List<Enterprise> queryAllEnterprises() {
		EnterpriseService enterpriseService = new EnterpriseService();
		return enterpriseService.queryAllEnterprises();
	}

	/**
	 * @Description: 保存集体经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> saveCollectiveEconomySell(String collectiveEconomyId, String regionId,
			String industryTypeId, String purchaseContent, Double totalPrice, String orgId, Date tradeTime,
			String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyService collectiveEconomyService = new CollectiveEconomyService();
		CollectiveEconomy collectiveEconomy = collectiveEconomyService
				.queryCollectiveEconomyByRegionIdOrCollectiveEconomyId(regionId, collectiveEconomyId);
		if (collectiveEconomy == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_REGION_COLLECTIVE_ECONOMY_ERRO);
			return resultMap;

		}
		CollectiveEconomySellHappenService collectiveEconomySellHappenService = new CollectiveEconomySellHappenService();
		collectiveEconomySellHappenService.saveCollectiveEconomySellHappen(collectiveEconomy.getId(),
				collectiveEconomy.getRegionId(), industryTypeId, purchaseContent, totalPrice, orgId, tradeTime, userId);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description:分页 查找集体经济收入
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageCollectiveEconomySell(int pageNumber, int pageSize, String collectiveEconomyId,
			String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomySellHappenService collectiveEconomySellHappenService = new CollectiveEconomySellHappenService();
		resultMap.put(ConstUtils.R_PAGE, collectiveEconomySellHappenService.pageCollectiveEconomySellHappen(pageNumber,
				pageSize, collectiveEconomyId, regionId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public int updateCollectiveEconomyPurachase(String id, String purchaseContent, Double totalPrice, String orgId,
			Date tradeTime, String userId) {
		CollectiveEconomyPurchaseHappenService collectiveEconomyPurchaseHappenService = new CollectiveEconomyPurchaseHappenService();
		return collectiveEconomyPurchaseHappenService.updateCollectiveEconomyPurchaseHappen(id, purchaseContent,
				totalPrice, orgId, tradeTime, userId);

	}

	/**
	 * @Description: 更新集体经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public int updateCollectiveEconomySell(String id, String purchaseContent, Double totalPrice, String orgId,
			Date tradeTime, String userId) {
		CollectiveEconomySellHappenService collectiveEconomySellHappenService = new CollectiveEconomySellHappenService();
		return collectiveEconomySellHappenService.updateCollectiveEconomySellHappen(id, purchaseContent, totalPrice,
				orgId, tradeTime, userId);
	}

	/**
	 * @Description:根据id 查找集体经济收入
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> queryCollectiveEconomySellById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomySellHappenService collectiveEconomySellHappenService = new CollectiveEconomySellHappenService();
		resultMap.put(ConstUtils.R_MODEL, collectiveEconomySellHappenService.queryCollectiveEconomySellById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description:根据id 查找集体经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> queryCollectiveEconomyPurchaseById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		CollectiveEconomyPurchaseHappenService collectiveEconomyPurchaseHappenService = new CollectiveEconomyPurchaseHappenService();
		resultMap.put(ConstUtils.R_MODEL,
				collectiveEconomyPurchaseHappenService.queryCollectiveEconomyPurchaseById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description:根据行政区域id
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> queryFamilysByRegionId(String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyService familyService = new FamilyService();
		RegionService regionService = new RegionService();

		String regionIds = regionService.getAllChildAndSelfRegionIdStrsByRegionId(regionId);
		resultMap.put(ConstUtils.R_MODEL, familyService.queryFamilyByRegionIds(regionIds));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> findFamilyIdByRegionId(String regionId, String regionName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyService familyService = new FamilyService();
		resultMap.put(ConstUtils.R_LIST, familyService.findFamilyIdByRegionId(regionId, regionName));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 保存家庭经济信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> saveFamilyEconomy(String familyId, String userId, String memberId, String parentTypeId,
			String typeId, JSONObject purchaseJson, JSONObject underwritingJson, JSONObject selfSaleJson,
			JSONObject headerJson) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();
		String familyEconomicId = familyEconomicService.saveFamilyEconomic(familyId, userId);
		if (String.valueOf(ConstUtils.DATA_OPERATE_ERROR).equals(familyEconomicId)) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
			return resultMap;

		}
		Integer underwritingAmount = 0;
		Integer amountTotal = 0;
		if (purchaseJson != null) {
			Date tradeTime = new Date();
			if (purchaseJson.has("tradeTime")) {
				String tradeTimeStr = purchaseJson.getString("tradeTime");
				try {
					tradeTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tradeTimeStr.trim());
				} catch (ParseException e) {

				}
			}
			Double unitPrice = 0.0;
			if (purchaseJson.has("unitPrice")) {
				unitPrice = purchaseJson.getDouble("unitPrice");
			}
			if (purchaseJson.has("amount")) {
				amountTotal = purchaseJson.getInt("amount");
			}
			if (purchaseJson.has("underwritingAmount")) {
				underwritingAmount = purchaseJson.getInt("underwritingAmount");
			}
			FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
			familyEconomicPurchaseHappenService.saveFamilyEconomicPurchaseHappen(familyEconomicId, familyId,
					parentTypeId, typeId, unitPrice, amountTotal, underwritingAmount, memberId, tradeTime, userId);
		}
		if (underwritingJson != null) {
			Date tradeTime = new Date();
			if (underwritingJson.has("tradeTime")) {
				String tradeTimeStr = underwritingJson.getString("tradeTime");
				try {
					tradeTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tradeTimeStr.trim());
				} catch (ParseException e) {

				}
			}
			Double unitPrice = 0.0;
			if (underwritingJson.has("unitPrice")) {
				unitPrice = underwritingJson.getDouble("unitPrice");
			}
			Integer amount = 0;
			if (underwritingJson.has("amount")) {
				amount = underwritingJson.getInt("amount");
			}

			Double sale_amount1 = 0.0;
			if (underwritingJson.has("sale_amount1")) {
				sale_amount1 = underwritingJson.getDouble("sale_amount1");
			}

			Double surplus = (double) (underwritingAmount - amount);

			FamilyEconomicUnderwritingHappenService familyEconomicUnderwritingHappenService = new FamilyEconomicUnderwritingHappenService();
			familyEconomicUnderwritingHappenService.saveFamilyEconomicUnderwritingHappen(familyEconomicId, familyId,
					parentTypeId, typeId, unitPrice, amount, memberId, tradeTime, userId, sale_amount1, surplus);
		}
		if (selfSaleJson != null) {
			Date tradeTime = new Date();
			if (selfSaleJson.has("tradeTime")) {
				String tradeTimeStr = selfSaleJson.getString("tradeTime");
				try {
					tradeTime = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(tradeTimeStr.trim());
				} catch (ParseException e) {

				}
			}
			Double unitPrice = 0.0;
			if (selfSaleJson.has("unitPrice")) {
				unitPrice = selfSaleJson.getDouble("unitPrice");
			}
			Integer amount = 0;
			if (selfSaleJson.has("amount")) {
				amount = selfSaleJson.getInt("amount");
			}

			Double sale_amount2 = 0.0;
			if (selfSaleJson.has("sale_amount2")) {
				sale_amount2 = selfSaleJson.getDouble("sale_amount2");
			}

			FamilyEconomicSelfSaleHappenService familyEconomicSelfSaleHappenService = new FamilyEconomicSelfSaleHappenService();
			familyEconomicSelfSaleHappenService.saveFamilyEconomicSelfSaleHappen(familyEconomicId, familyId,
					parentTypeId, typeId, unitPrice, amount, memberId, tradeTime, userId, underwritingAmount,
					sale_amount2);
		}
		if (headerJson != null) {
			FamilyEconomicHerdsService familyEconomicHerdsService = new FamilyEconomicHerdsService();
			Integer pupsAmount = 0;
			if (headerJson.has("pupsAmount")) {
				pupsAmount = headerJson.getInt("pupsAmount");
			}
			Integer adultsAmount = 0;
			if (headerJson.has("adultsAmount")) {
				adultsAmount = headerJson.getInt("adultsAmount");
			}
			Integer malesAmount = 0;
			if (headerJson.has("malesAmount")) {
				malesAmount = headerJson.getInt("malesAmount");
			}
			familyEconomicHerdsService.saveFamilyEconomicHerds(familyEconomicId, familyId, typeId, amountTotal,
					pupsAmount, adultsAmount, malesAmount, userId, headerJson.get("remark") + "");
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 检测家庭经济采购信息参数有效性
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public boolean checkPurchasesJson(JSONObject purchaseJson) {
		if (purchaseJson == null) {
			return true;
		}
		/**
		 * 不能同时为空
		 */
		if (!purchaseJson.has("tradeTime") && !purchaseJson.has("unitPrice") && !purchaseJson.has("amount")
				&& !purchaseJson.has("underwritingAmount")) {
			return false;
		}
		return true;
	}

	/**
	 * @Description: 检测家庭经济收入参数有效性
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public boolean checkCeSellJsons(JSONObject sellJson) {
		if (sellJson == null) {
			return true;
		}
		/**
		 * 不能同时为空
		 */
		if (!sellJson.has("tradeTime") && !sellJson.has("unitPrice") && !sellJson.has("amount")) {
			return false;
		}
		return true;
	}

	/**
	 * @Description: 查找家庭经济
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyconomy(int pageNumber, int pageSize, String regionId, String keyWord) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();
		resultMap.put(ConstUtils.R_PAGE,
				familyEconomicService.pageFamilyEconomic(pageNumber, pageSize, regionId, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 保存家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> saveFamilyEconomyPurachase(String familyEconomyId, String familyId, String memberId,
			String parentTypeId, String typeId, Double unitPrice, Integer amount, Integer underwritingAmount,
			Date tradeTime, String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();

		FamilyEconomic familyEconomic = familyEconomicService.queryFamilyEconomyByFamilyIdOrFamilyEconomyId(familyId,
				familyEconomyId);
		if (familyEconomic == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERROR_STR);
			return resultMap;

		}
		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		familyEconomicPurchaseHappenService.saveFamilyEconomicPurchaseHappen(familyEconomic.getId(),
				familyEconomic.getFamilyId(), parentTypeId, typeId, unitPrice, amount, underwritingAmount, memberId,
				tradeTime, userId);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查找家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyEconomyPurchase(int pageNumber, int pageSize, String familyEconomyId,
			String familyId, String parentTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		resultMap.put(ConstUtils.R_PAGE, familyEconomicPurchaseHappenService
				.pageFamilyEconomicPurchaseHappen(pageNumber, pageSize, familyEconomyId, familyId, parentTypeId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public int updateFamilyEconomyPurachase(String id, String parentTypeId, String typeId, Double unitPrice,
			Integer amount, Integer underwritingAmount, String memberId, Date tradeTime, String userId) {
		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		return familyEconomicPurchaseHappenService.updateFamilyEconomicPurchaseHappen(id, parentTypeId, typeId,
				unitPrice, amount, underwritingAmount, memberId, tradeTime, userId);

	}

	/**
	 * @Description:根据id 查找家庭经济采购信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> queryFamilyEconomyPurchaseById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		resultMap.put(ConstUtils.R_MODEL, familyEconomicPurchaseHappenService.queryCollectiveEconomyById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 保存家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> saveFamilyEconomyUnderwriting(String familyEconomyId, String familyId, String memberId,
			String parentTypeId, String typeId, Double unitPrice, Integer amount, Date tradeTime, String userId,
			Double sale_amount1) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();

		FamilyEconomic familyEconomic = familyEconomicService.queryFamilyEconomyByFamilyIdOrFamilyEconomyId(familyId,
				familyEconomyId);
		if (familyEconomic == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERROR_STR);
			return resultMap;

		}
		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		Map<String, Object> purchMap = familyEconomicPurchaseHappenService.queryFamilyEconomicPurchaseHappen(familyId,
				typeId, memberId);
		double surplus = 0d;
		if (purchMap != null && !purchMap.isEmpty()) {
			Integer underwritingAmount = ((FamilyEconomicPurchaseHappen) purchMap.get("rm")).getUnderwritingAmount();
			surplus = (double) (underwritingAmount - amount);
		}
		if (surplus == 0)
			surplus = amount;
		FamilyEconomicUnderwritingHappenService familyEconomicUnderwritingHappenService = new FamilyEconomicUnderwritingHappenService();
		familyEconomicUnderwritingHappenService.saveFamilyEconomicUnderwritingHappen(familyEconomic.getId(), familyId,
				parentTypeId, typeId, unitPrice, amount, memberId, tradeTime, userId, sale_amount1, surplus);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查找家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyEconomyUnderwriting(int pageNumber, int pageSize, String familyEconomyId,
			String familyId, String parentTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyEconomicUnderwritingHappenService familyEconomicPurchaseHappenService = new FamilyEconomicUnderwritingHappenService();
		resultMap.put(ConstUtils.R_PAGE, familyEconomicPurchaseHappenService
				.pageFamilyEconomicUnderwritingHappen(pageNumber, pageSize, familyEconomyId, familyId, parentTypeId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public int updateFamilyEconomyUnderwriting(String id, String parentTypeId, String typeId, Double unitPrice,
			Integer amount, String memberId, Date tradeTime, String userId, Double sale_amount1, Double surplus) {
		FamilyEconomicUnderwritingHappenService familyEconomicUnderwritingHappenService = new FamilyEconomicUnderwritingHappenService();

		/*
		 * Integer surplus = 0; FamilyEconomicUnderwritingHappen wHappen =
		 * familyEconomicUnderwritingHappenService.queryFamilyEconomyById(id); if
		 * (wHappen != null) { surplus = wHappen.getSurplus() - amount; }
		 */
		return familyEconomicUnderwritingHappenService.updateFamilyEconomicUnderwritingHappen(id, parentTypeId, typeId,
				unitPrice, amount, memberId, tradeTime, userId, sale_amount1, surplus);

	}

	/**
	 * @Description:根据id 查找家庭经济包销信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> queryFamilyEconomyUnderwritingById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicUnderwritingHappenService familyEconomicUnderwritingHappenService = new FamilyEconomicUnderwritingHappenService();
		resultMap.put(ConstUtils.R_MODEL, familyEconomicUnderwritingHappenService.queryFamilyEconomyById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 保存家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> saveFamilyEconomySelfSale(String familyEconomyId, String familyId, String memberId,
			String parentTypeId, String typeId, Double unitPrice, Integer amount, Date tradeTime, String userId,
			Double sale_amount2) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();

		FamilyEconomic familyEconomic = familyEconomicService.queryFamilyEconomyByFamilyIdOrFamilyEconomyId(familyId,
				familyEconomyId);
		if (familyEconomic == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERROR_STR);
			return resultMap;

		}
		FamilyEconomicPurchaseHappenService familyEconomicPurchaseHappenService = new FamilyEconomicPurchaseHappenService();
		Map<String, Object> purchMap = familyEconomicPurchaseHappenService.queryFamilyEconomicPurchaseHappen(familyId,
				typeId, memberId);
		Integer surplus = 0;
		if (purchMap != null && !purchMap.isEmpty()) {
			Integer amountTotal = ((FamilyEconomicPurchaseHappen) purchMap.get("rm")).getAmount();
			Integer underwritingAmount = ((FamilyEconomicPurchaseHappen) purchMap.get("rm")).getUnderwritingAmount();

			surplus = amountTotal - underwritingAmount - amount;
		}
		FamilyEconomicSelfSaleHappenService familyEconomicSelfSaleHappenService = new FamilyEconomicSelfSaleHappenService();
		familyEconomicSelfSaleHappenService.saveFamilyEconomicSelfSaleHappen(familyEconomic.getId(), familyId,
				parentTypeId, typeId, unitPrice, amount, memberId, tradeTime, userId, surplus, sale_amount2);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查找家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyEconomySelfSale(int pageNumber, int pageSize, String familyEconomyId,
			String familyId, String parentTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyEconomicSelfSaleHappenService familyEconomicPurchaseHappenService = new FamilyEconomicSelfSaleHappenService();
		resultMap.put(ConstUtils.R_PAGE, familyEconomicPurchaseHappenService
				.pageFamilyEconomicSelfSaleHappen(pageNumber, pageSize, familyEconomyId, familyId, parentTypeId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public int updateFamilyEconomySelfSale(String id, String parentTypeId, String typeId, Double unitPrice,
			Integer amount, String memberId, Date tradeTime, String userId, Double sale_amount2) {
		FamilyEconomicSelfSaleHappenService familyEconomicSelfSaleHappenService = new FamilyEconomicSelfSaleHappenService();
		FamilyEconomicSelfSaleHappen hd = familyEconomicSelfSaleHappenService.queryFamilyEconomySelfSaleById(id);
		Integer surplus = 0;
		if (hd != null) {
			surplus = hd.getInt("surplus") - amount;
		}
		return familyEconomicSelfSaleHappenService.updateFamilyEconomicSelfSaleHappen(id, parentTypeId, typeId,
				unitPrice, amount, memberId, tradeTime, userId, surplus, sale_amount2);

	}

	/**
	 * @Description:根据id 查找家庭经济自主收入信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> queryFamilyEconomySelfSaleById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicSelfSaleHappenService familyEconomicSelfSaleHappenService = new FamilyEconomicSelfSaleHappenService();
		resultMap.put(ConstUtils.R_MODEL, familyEconomicSelfSaleHappenService.queryFamilyEconomySelfSaleById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 保存家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> saveFamilyEconomySpecialSituation(String familyEconomyId, String familyId, String remark,
			String parentTypeId, String typeId, Integer amount, Integer increaseDecrease, String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();

		FamilyEconomic familyEconomic = familyEconomicService.queryFamilyEconomyByFamilyIdOrFamilyEconomyId(familyId,
				familyEconomyId);
		if (familyEconomic == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_FAMILY_ECONOMIC_ERROR_STR);
			return resultMap;

		}
		FamilyEconomicSpecialSituationHappenService familyEconomicSpecialSituationHappenService = new FamilyEconomicSpecialSituationHappenService();
		familyEconomicSpecialSituationHappenService.saveFamilyEconomicSpecialSituationHappen(familyEconomic.getId(),
				familyId, parentTypeId, typeId, amount, increaseDecrease, userId, remark);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	/**
	 * @Description: 查找家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyEconomySpecialSituation(int pageNumber, int pageSize, String familyEconomyId,
			String familyId, String parentTypeId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyEconomicSpecialSituationHappenService familyEconomicPurchaseHappenService = new FamilyEconomicSpecialSituationHappenService();
		resultMap.put(ConstUtils.R_PAGE, familyEconomicPurchaseHappenService.pageFamilyEconomicSpecialSituationHappen(
				pageNumber, pageSize, familyEconomyId, familyId, parentTypeId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public int updateFamilyEconomySpecialSituation(String id, String parentTypeId, String typeId, Integer amount,
			Integer increaseDecrease, String userId, String remark) {
		FamilyEconomicSpecialSituationHappenService familyEconomicSpecialSituationHappenService = new FamilyEconomicSpecialSituationHappenService();
		return familyEconomicSpecialSituationHappenService.updateFamilyEconomicSpecialSituationHappen(id, parentTypeId,
				typeId, amount, increaseDecrease, userId, remark);

	}

	/**
	 * @Description:根据id 查找家庭经济特殊情况信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> queryFamilyEconomySpecialSituationById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicSpecialSituationHappenService familyEconomicSpecialSituationHappenService = new FamilyEconomicSpecialSituationHappenService();
		resultMap.put(ConstUtils.R_MODEL,
				familyEconomicSpecialSituationHappenService.queryFamilyEconomySpecialSituationById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 查找家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> pageFamilyEconomyHerds(int pageNumber, int pageSize, String familyEconomyId,
			String familyId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		FamilyEconomicHerdsService familyEconomicPurchaseService = new FamilyEconomicHerdsService();
		resultMap.put(ConstUtils.R_PAGE,
				familyEconomicPurchaseService.pageFamilyEconomicHerds(pageNumber, pageSize, familyEconomyId, familyId));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	/**
	 * @Description: 更新家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public int updateFamilyEconomyHerds(String id, String typeId, Integer amount, Integer pupsAmount,
			Integer adultsAmount, Integer malesAmount, String userId) {
		FamilyEconomicHerdsService familyEconomicHerdsService = new FamilyEconomicHerdsService();
		return familyEconomicHerdsService.updateFamilyEconomicHerds(id, typeId, amount, pupsAmount, adultsAmount,
				malesAmount, userId, "");

	}

	/**
	 * @Description:根据id 查找家庭经济存栏量信息
	 * @author Li Bangming;
	 * @date 2017年8月16日 下午12:32:49
	 * @return
	 */
	public Map<String, Object> queryFamilyEconomyHerdsById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicHerdsService familyEconomicHerdsService = new FamilyEconomicHerdsService();
		resultMap.put(ConstUtils.R_MODEL, familyEconomicHerdsService.queryFamilyEconomyHerdsById(id));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageVillagerPostHappen(int pageNumber, int pageSize, String villagerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();
			Page<VillagerPostHappen> valigerPostHappen = valigerPostHappenService.pageVillagerPostHappen(pageNumber,
					pageSize, villagerId);

			resultMap.put(ConstUtils.R_PAGE, valigerPostHappen);

		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	public Map<String, Object> updateEnterprise(String id, String name, String contact, String userId, String address,
			String personNo, String orgId, String personId, String typeId, String userId1, String userId2,
			String remark, String phone, String email) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		EnterpriseService enterpriseService = new EnterpriseService();
		return enterpriseService.updateEnterprise(id, name, contact, userId, address, personNo, orgId, personId, typeId,
				userId, userId, remark, phone, email);
	}

	public Map<String, Object> getAllEnterprise() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		EnterpriseService enterpriseService = new EnterpriseService();
		List<Enterprise> list = enterpriseService.queryAllEnterprises();
		resultMap.put(ConstUtils.R_LIST, list);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> deletePostById(String id) {
		PostService postService = new PostService();
		return postService.delPost(id);
	}

	public Map<String, Object> updatePost(String id, String name, String typeId, String orgId, int number,
			Double averageIncome, String department, String content, String userId, int isFiveFund, int isEatEncase,
			int isEat, int isEncase, int isLunch, int isOther, String attachment) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		PostService postService = new PostService();
		return postService.updatePost(id, name, typeId, orgId, number, averageIncome, department, content, userId,
				isFiveFund, isEatEncase, isEat, isEncase, isLunch, isOther, attachment);
	}

	public Map<String, Object> pagePostByEId(int pageNumber, int pageSize, String enId) {
		PostService postService = new PostService();
		return postService.pagePostByEId(pageNumber, pageSize, enId);
	}

	public Map<String, Object> addPostVillager(String villagerId, String postId, String userId, String memberId) {
		VillagerService villagerService = new VillagerService();
		Villager villager = villagerService.queryValligerById(villagerId);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (villager == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			return resultMap;
		}

		PostService postService = new PostService();
		Post post = postService.queryPostById(postId);
		try {
			// villagerService.updateVillagerIsWorking(villagerId, 1);
			// 保存流水
			Double salary = post.getAverageIncome();
			// post.getAverageIncome()==null?null:Double.parseDouble();

			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();// ralationType--岗位关系：1申请，2，取消，3上岗，4辞退，5辞职
			@SuppressWarnings("unchecked")
			List<VillagerPostHappen> lists = (List<VillagerPostHappen>) valigerPostHappenService
					.getVilagerPostHappen(villagerId).get("rl");
			if (lists != null && !lists.isEmpty()) {
				for (VillagerPostHappen list : lists) {
					if (list.getRalationType().intValue() == 1) {
						resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
						resultMap.put(ConstUtils.R_MSG, "申请人重复!");
						return resultMap;
					}
				}
			}
			valigerPostHappenService.addVillagerPostHappen(villagerId, postId, memberId, 1, salary, salary, "", "",
					new Date(), "", userId, post.getIsEat(), post.getIsEatEncase(), post.getIsEncase(),
					post.getIsFiveFund(), post.getIsLunch(), post.getIsOther(), post.getDepartment(), false,
					(post.get("jobAddress") == null) ? null : post.get("jobAddress").toString(), null);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> pageFamilyEconomicByHam(int page, int pageSize, String hamletName, String groupName,
			String keyWord) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		FamilyEconomicService familyEconomicService = new FamilyEconomicService();
		resultMap.put(ConstUtils.R_PAGE,
				familyEconomicService.pageFamilyEconomicByHam(page, pageSize, hamletName, groupName, keyWord));
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;

	}

	public Map<String, Object> queryCollectiveEconomyById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			CollectiveEconomyService collectiveEconomyService = new CollectiveEconomyService();
			CollectiveEconomy collectiveEconomy = collectiveEconomyService.queryCollectiveEconomyById(id);
			resultMap.put(ConstUtils.R_MODEL, collectiveEconomy);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}
		return resultMap;
	}

	public Map<String, Object> queryRegionById(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			RegionService regionService = new RegionService();
			Region region = regionService.findRegionById(id);
			resultMap.put(ConstUtils.R_MODEL, region);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}
		return resultMap;
	}

	public Map<String, Object> pagePostVillagerByPostIdJson(int pageNumber, int pageSize, String postId,
			int ralationType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {

			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();
			Page<Record> valigerPostHappen = valigerPostHappenService.pagePostVillagerByPostIdJson(pageNumber, pageSize,
					postId, ralationType);

			resultMap.put(ConstUtils.R_PAGE, valigerPostHappen);

		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

	public Map<String, Object> updatePostVillagerRalationType(String id, int ralationType, String reason,
			String userId) {
		ValigerPostHappenService service = new ValigerPostHappenService();
		return service.updatePostVillagerRalationType(id, ralationType, reason, userId);
	}

	public Map<String, Object> deletePostVillageById(String id) {
		ValigerPostHappenService service = new ValigerPostHappenService();
		return service.deletePostVillageById(id);
	}

	/*****
	 * 
	 * 帮扶数据分析 贫困数据分析
	 * 
	 * 
	 * @return
	 */
	public Map<String, Object> jsonReport1() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold2 arnold2 = new Arnold2();
			// 贫困数据分析-贫困发生率-贫困人口规模
			String sql1 = "SELECT lastYear,sum(family_count) AS family_count, sum(family_total) AS family_total, sum(family_villager_count) AS family_villager_count, "
					+ "sum(family_villager_total) AS family_villager_total FROM ( SELECT lastYear, 0 AS family_count, sum(family_total) as family_total, 0 AS family_villager_count, "
					+ "sum(family_villager_total) as family_villager_total FROM ( select year(ifnull(lastModifyTime,now())) as lastYear,1 as family_total,0 as family_villager_total "
					+ "from arnold.tb_family as family1 where isValid=0 union ALL select year(ifnull(lastModifyTime,now())) as lastYear,0 as family_total,1 as family_villager_total "
					+ "from arnold.tb_family as family1 inner join arnold.tb_villager as temp_family1 on family1.id=temp_family1.familyId where family1.isValid=0 ) as family2 "
					+ "group by lastYear UNION ALL SELECT lastYear, count(1) AS family_count, 0 AS family_total, 0 AS family_villager_count, 0 AS family_villager_total FROM ( "
					+ "SELECT YEAR ( ifnull( family3.lastModifyTime, now())) AS lastYear, ifnull(family3.ralationTypeId, '0') AS lastRelationTypeId, family3.id FROM arnold.tb_family AS family3 "
					+ "WHERE family3.isValid = 0 and (family3.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family3.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b')) AS family4 "
					+ "GROUP BY lastYear UNION ALL SELECT lastYear, 0 AS family_count, 0 AS family_total, count(1) AS family_villager_count, 0 AS family_villager_total FROM ( SELECT YEAR ( ifnull( family5.lastModifyTime, now())) AS lastYear, ifnull(family5.ralationTypeId, '0') AS lastRelationTypeId, family5.id FROM arnold.tb_family AS family5 WHERE family5.isValid = 0 and (family5.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family5.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b') ) AS family6 inner JOIN arnold.tb_villager AS villager1 ON family6.id = villager1.familyId GROUP BY lastYear ) AS total_all GROUP BY lastYear ";
			// 贫困数据分析 致贫原因
			String sql2 = "SELECT burdenId, count(1) AS burden_total FROM ( SELECT family_relation1.burdenId, family1.id FROM arnold.tb_family AS family1 "
					+ "INNER JOIN arnold.tb_family_burden_relation AS family_relation1 ON family1.id = family_relation1.familyId WHERE family1.isValid = 0 ) "
					+ "AS family_total GROUP BY burdenId";
			// 贫困户收入
			String sql3 = " SELECT rrr.id as regionId,sum(ifnull(work3.family_income,0)) as family_income,ifnull(work3.work_year,year(now())) as work_year,rrr.fullName AS regionName "
					+ " FROM (SELECT regionId, sum(work_averageIncome) AS family_income, work_year FROM (SELECT regionId, sum(averageIncome * 12) AS work_averageIncome,"
					+ " work_year FROM ( SELECT family1.regionId, villager_happen1.villagerId, villager_happen1.averageIncome, YEAR( villager_happen1.postLeaveDate ) AS work_year FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON "
					+ " family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId "
					+ " where villager_happen1.ralationType=3 and (family1.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family1.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b')) AS work1 GROUP BY "
					+ " regionId, work_year UNION ALL SELECT regionId, sum(family_averageIncome) AS work_averageIncome, work_year FROM ( SELECT family1.regionId, family_happen1.amount * family_happen1.unitPrice AS "
					+ " family_averageIncome, YEAR (family_happen1.tradeTime) AS work_year FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_self_sale_happen AS family_happen1 ON family1.id = family_happen1.familyid where family1.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family1.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b' "
					+ " ) AS work1 GROUP BY regionId, work_year UNION ALL SELECT regionId, sum(family_averageIncome) AS work_averageIncome, work_year FROM  "
					+ " ( SELECT family1.regionId, family_happen1.amount * family_happen1.unitPrice AS family_averageIncome, YEAR(family_happen1.tradeTime) AS work_year FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_underwriting_happen AS "
					+ " family_happen1 ON family1.id = family_happen1.familyid where family1.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family1.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b') "
					+ " AS work1 GROUP BY regionId, work_year ) AS work2 GROUP BY regionId, work_year  ) AS work3 RIGHT JOIN tb_region r1 ON r1.id=work3.regionId and r1.regionType=6 INNER JOIN tb_region as rrr on rrr.id=r1.parentId and rrr.regionType=5 group by rrr.id,ifnull(work3.work_year,year(now())),rrr.fullName";
			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			List<Record> recordModel3 = arnold2.getRecordBySql(sql3);
			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);
			resultMap.put("rl3", recordModel3);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	/****
	 * 
	 * 
	 * 就业产业分析
	 * 
	 * @return
	 */
	public Map<String, Object> jsonReport2() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold2 arnold2 = new Arnold2();
			/// 就业产业分析-就业人数统计
			String sql1 = "SELECT rrr.id as regionId,sum(ifnull(work3.work_count,0)) as work_count,rrr.fullName AS regionName "
					+ " FROM (select regionId,count(0) as work_count "
					+ " from(SELECT distinct family1.regionId,villager1.id as villagerId "
					+ " FROM arnold.tb_family as family1 inner join arnold.tb_villager as villager1 on family1.id=villager1.familyid "
					+ " inner join arnold.tb_villager_post_happen as villager_happen1 on villager1.id=villager_happen1.villagerId "
					+ " where villager_happen1.ralationType=3 and villager1.isworking=0"
					+ " ) as work1 group by regionId  ) AS work3 RIGHT JOIN tb_region r1 ON r1.id=work3.regionId and r1.regionType=6 INNER JOIN tb_region as rrr on rrr.id=r1.parentId and rrr.regionType=5 group by rrr.id,rrr.fullName ";

			// 就业产业分析-就业人数-分岗位
			String sql2 = "SELECT rrr.id as regionId, sum(ifnull(work3.work_count,0)) as work_count, work3.postId, rrr.fullName AS regionName "
					+ " FROM ( SELECT regionId, count(0) AS work_count, postId FROM ( "
					+ " SELECT family1.regionId, villager1.id as villagerId,villager_happen1.jobCategory as postId FROM  "
					+ " arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid "
					+ " INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId WHERE "
					+ " villager_happen1.ralationType = 3 and villager1.isWorking=0 and "
					+ " villager_happen1.createTime=(select max(villager_happen2.createTime) from arnold.tb_villager_post_happen AS villager_happen2 where villager_happen2.villagerId=villager1.id) "
					+ " ) AS work1 GROUP BY regionId, postId ) AS work3  "
					+ " RIGHT JOIN tb_region r1 ON r1.id=work3.regionId and r1.regionType=6 INNER JOIN tb_region as rrr on rrr.id=r1.parentId and rrr.regionType=5 group by rrr.id,rrr.fullName,work3.postId";

			// 就业数据分析-产业发展收入
			String sql3 = "select sum(work_averageIncome) as family_income,work_year  from("
					+ "select sum(family_averageIncome) as work_averageIncome,work_year from("
					+ " SELECT family1.regionId,family_happen1.amount*family_happen1.unitPrice as family_averageIncome,year(family_happen1.tradeTime) as work_year"
					+ " FROM arnold.tb_family as family1 inner join arnold.tb_family_economic_self_sale_happen as family_happen1 on family1.id=family_happen1.familyid"
					+ " where  family1.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family1.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b'"
					+ " ) as work1 group by work_year   union all"
					+ " select sum(family_averageIncome) as work_averageIncome,work_year from("
					+ " SELECT family_happen1.amount*family_happen1.unitPrice as family_averageIncome,year(family_happen1.tradeTime) as work_year"
					+ " FROM arnold.tb_family as family1 inner join arnold.tb_family_economic_underwriting_happen as family_happen1 on family1.id=family_happen1.familyid"
					+ " where  family1.ralationTypeId='e87df7f3-f7ad-4fea-982c-127c6e24fba6' or family1.ralationTypeId='dea6cc62-d4f5-47f9-9943-972900e2fc9b' "
					+ " ) as work1 group by work_year ) as work2 group by work_year";

			// 此处差一个 产业发展规模
			String sql4 = "SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId FROM "
					+ "( SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId FROM "
					+ "( SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, "
					+ "YEAR (family_happen1.tradeTime) AS work_year FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_purchase_happen "
					+ "AS family_happen1 ON family1.id = family_happen1.familyid ) AS work1 GROUP BY regionId, work_year, typeId "
					+ "UNION ALL SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId FROM "
					+ "( SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, YEAR "
					+ "(family_happen1.tradeTime) AS work_year FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_self_sale_happen "
					+ "AS family_happen1 ON family1.id = family_happen1.familyid ) AS work1 GROUP BY regionId, work_year, typeId "
					+ "UNION ALL SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId FROM "
					+ "( SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, "
					+ "YEAR (family_happen1.tradeTime) AS work_year FROM arnold.tb_family AS family1 "
					+ "INNER JOIN arnold.tb_family_economic_underwriting_happen AS family_happen1 ON family1.id = family_happen1.familyid ) "
					+ "AS work1 GROUP BY regionId, work_year, typeId ) AS work2 GROUP BY regionId, work_year, typeId";

			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			List<Record> recordModel3 = arnold2.getRecordBySql(sql3);
			List<Record> recordModel4 = arnold2.getRecordBySql(sql4);

			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);
			resultMap.put("rl3", recordModel3);
			resultMap.put("rl4", recordModel4);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> jsonReport3() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold2 arnold2 = new Arnold2();

			String sql1 = " select rrr.id as regionId, sum(ifnull(work3.train_count,0)) as train_count, ifnull(work3.train_year,year(now())) as train_year, rrr.fullName AS regionName from ( "
					+ " select regionId,train_year,sum(train_count) as train_count from (select distinct regionId,year(now()) as train_year,0 as train_count from arnold.tb_family as family "
					+ " union all select regionId,train_year,count(1) as train_count from ( select family1.regionId,year(edu1.startTime) as train_year,villager1.id "
					+ " from arnold.tb_family as family1 inner join  arnold.tb_villager as villager1 on villager1.familyId=family1.id inner join arnold.tb_education_participant as edu_participant1 on villager1.id=edu_participant1.joinPersonid "
					+ " inner join arnold.tb_education as edu1 on edu1.id =edu_participant1.educationid ) as valiger_edu1 "
					+ " group by regionId,train_year ) as work2 group by regionId,train_year) AS work3 RIGHT JOIN tb_region r1 ON r1.id=work3.regionId and r1.regionType=6 INNER JOIN tb_region as rrr on rrr.id=r1.parentId and rrr.regionType=5 group by rrr.id,rrr.fullName,ifnull(work3.train_year,year(now()))";

			String sql2 = "select rrr.id as regionId, ifnull(work3.train_year,year(now())) as train_year, sum(ifnull(work3.train_count,0)) as train_count, rrr.fullName AS regionName from ( "
					+ " select regionId,train_year,sum(train_count) as train_count from( select distinct regionId,year(now()) as train_year,0 as train_count from arnold.tb_family as family "
					+ " union all  select regionId,train_year,count(1) as train_count from ( select family1.regionId,year(ifnull(train1.startTime,train1.createTime)) as train_year,villager1.id "
					+ " from arnold.tb_family as family1 inner join  arnold.tb_villager as villager1 on villager1.familyId=family1.id inner join arnold.tb_train_participant as train_participant1 on villager1.id=train_participant1.joinPersonid "
					+ " inner join arnold.tb_train as train1 on train1.id =train_participant1.trainid ) as valiger_edu1 "
					+ " group by regionId,train_year) as work2 group by regionId,train_year ) AS work3 RIGHT JOIN tb_region r1 ON r1.id=work3.regionId and r1.regionType=6 INNER JOIN tb_region as rrr on rrr.id=r1.parentId and rrr.regionType=5 group by rrr.id,rrr.fullName,ifnull(work3.train_year,year(now()))";

			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> jsonReport4() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Arnold2 arnold2 = new Arnold2();

			String sql1 = "SELECT accreditDepartmentId, ( SELECT count(1) FROM tb_family AS family1 INNER JOIN arnold.tb_member_family_relation "
					+ "AS member_relation1 ON family1.id = member_relation1.familyId WHERE family1.ralationTypeId = '2' "
					+ "AND member_id = member_relation1.memberId ) AS member_count FROM ( SELECT member1.id "
					+ "AS member_id, member1.accreditDepartmentId, member1.id FROM arnold.tb_member AS member1 WHERE member1.isValid = 0 ) "
					+ "AS member_total GROUP BY accreditDepartmentId";

			String sql2 = " select project_happen1.company AS companyId,project_happen1.investPrice,year(ifnull(project_happen1.investDate,project_happen1.createTime)) as project_year "
					+ " from arnold.tb_project_invest_happen as project_happen1 ";

			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> pageEffectTPGJJson(int pageNumber, int pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {

			String sql1 = "SELECT region2.fullName as hamletId, postId, jobCategory, lastyear, sum(work_total_count) AS work_total_count, sum(work_current_count) AS work_current_count, sum(no_work_total_count) AS no_work_total_count,sum(work_and_train_total_count) as work_and_train_total_count "
					+ "FROM (    "
					+ "	SELECT regionId, jobCategory, lastyear,count(0) AS work_total_count, 0 AS work_current_count, postId, 0 AS no_work_total_count,0 as work_and_train_total_count "
					+ "	FROM (    "
					+ "		SELECT family1.regionId,villager_happen1.jobCategory, YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear,villager_happen1.villagerId, villager_happen1.postId    "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id)   "
					+ "		WHERE villager_happen1.ralationType = 3    " + "	) AS work1    "
					+ "	GROUP BY regionId,postId, lastyear    " + "	UNION ALL /***当期就业人数***/    "
					+ "	SELECT regionId, jobCategory, lastyear, 0 AS work_total_count, count(1) AS work_current_count, postId, 0 AS no_work_total_count,0 as work_and_train_total_count "
					+ "	FROM (    "
					+ "		SELECT family1.regionId,villager_happen1.jobCategory,YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId,villager_happen1.postId    "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id)   "
					+ "		WHERE villager_happen1.ralationType = 3 AND YEAR ( villager_happen1.postLeaveDate ) = YEAR (now())   "
					+ "	) AS work1    " + "	GROUP BY regionId, postId,lastyear    " + "	UNION ALL /***失业总人数***/    "
					+ "	SELECT regionId, jobCategory, lastyear, 0 AS work_total_count, 0 AS work_current_count, postId, count(1) AS no_work_total_count,0 as work_and_train_total_count "
					+ "	FROM (    "
					+ "		SELECT family1.regionId, villager_happen1.jobCategory, YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId, villager_happen1.postId    "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id)   "
					+ "		WHERE (villager_happen1.ralationType = 4 OR villager_happen1.ralationType = 5 )   "
					+ "	) AS work1    " + "GROUP BY regionId, postId, lastyear   " + "UNION ALL "
					+ "SELECT regionId, jobCategory, lastyear, 0 AS work_total_count, 0 AS work_current_count, postId, 0 AS no_work_total_count,count(1) AS work_and_train_total_count   "
					+ "	FROM (    "
					+ "		SELECT family1.regionId, villager_happen1.jobCategory, YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId, villager_happen1.postId    "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id)   "
					+ "		WHERE villager_happen1.ralationType = 3 and villager1.isTrain=1   " + "	) AS work1    "
					+ "GROUP BY regionId, postId, lastyear  "
					+ ") AS work2 inner join tb_region as region1 on region1.id=regionId inner join tb_region as region2 on region1.parentId=region2.id   "
					+ "where region2.regionType=5   " + "GROUP BY region2.id, jobCategory, postId, lastyear";

			String sql2 = "SELECT lastyear, region2.fullName as hamletId, count(1) AS train_count, postid AS postId "
					+ "FROM ( "
					+ "	SELECT family1.regionId, YEAR ( ifnull( train1.startTime, train1.createTime )) AS lastyear, villager1.id, train1.postid "
					+ "	FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON villager1.familyId = family1.id INNER JOIN arnold.tb_train_participant AS train_participant1 ON villager1.id = train_participant1.joinPersonid INNER JOIN tb_train AS train1 ON train1.id = train_participant1.trainid "
					+ ") AS valiger_train1 inner join tb_region as region1 on region1.id=regionId inner join tb_region as region2 on region1.parentId=region2.id "
					+ "where region2.regionType=5 " + "GROUP BY lastyear,region2.id, postid";

			String sql3 = "SELECT work_year, region2.fullName as hamletId, typeId, sum(family_sale_amount1) AS family_sale_amount1, sum(family_no_sale_amount) AS family_no_sale_amount, sum(family_plan_amount) AS family_plan_amount, sum(family_sale_amount2) AS family_sale_amount2  "
					+ "FROM (  "
					+ "	SELECT work_year, regionId, typeId, sum(family_amount) AS family_sale_amount1, 0 AS family_no_sale_amount, 0 AS family_plan_amount, 0 AS family_sale_amount2  "
					+ "	FROM (  " + "		SELECT regionId, sum(family_amount) AS family_amount, work_year,typeId  "
					+ "		FROM (  "
					+ "			SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, YEAR(family_happen1.tradeTime) AS work_year  "
					+ "			FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_self_sale_happen AS family_happen1 ON family1.id = family_happen1.familyid  "
					+ "		) AS work1  " + "		GROUP BY regionId, work_year, typeId  " + "		UNION ALL  "
					+ "		SELECT regionId, sum(family_amount) AS family_amount, work_year,typeId  "
					+ "		FROM (  "
					+ "		SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, YEAR(family_happen1.tradeTime) AS work_year  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_underwriting_happen AS family_happen1 ON family1.id = family_happen1.familyid  "
					+ "		) AS work1  " + "		GROUP BY regionId, work_year, typeId  " + "	) AS work2  "
					+ "	GROUP BY regionId, work_year, typeId  " + "	UNION ALL  "
					+ "	SELECT work_year,regionId, typeId, 0 AS family_sale_amount1, 0 AS family_no_sale_amount, 0 AS family_plan_amount, sum(family_amount) AS	family_sale_amount2  "
					+ "	FROM (  " + "		SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId  "
					+ "		FROM (  "
					+ "			SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount, YEAR (family_happen1.tradeTime)	AS work_year  "
					+ "			FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_self_sale_happen AS family_happen1 ON family1.id = family_happen1.familyid  "
					+ "		) AS work1  " + "		GROUP BY regionId, work_year, typeId  " + "	) AS work2  "
					+ "	GROUP BY regionId,work_year, typeId  " + "	UNION ALL  "
					+ "	SELECT work_year, regionId, typeId, 0 AS family_sale_amount1, 0 AS family_no_sale_amount,sum(family_amount) AS family_plan_amount, 0 AS family_sale_amount2  "
					+ "	FROM (  " + "		SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId  "
					+ "		FROM (  "
					+ "			SELECT family1.regionId, family_happen1.typeId,family_happen1.underwritingAmount AS family_amount, YEAR (family_happen1.tradeTime) AS work_year  "
					+ "			FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_purchase_happen AS family_happen1 ON family1.id = family_happen1.familyid  "
					+ "		) AS work1  " + "	GROUP BY regionId, work_year, typeId  " + "	) AS work2  "
					+ "	GROUP BY regionId, work_year, typeId  " + "	UNION ALL  "
					+ "	SELECT work_year, regionId, typeId, 0 AS family_sale_amount1,sum(family_amount) AS family_no_sale_amount, 0 AS family_plan_amount, 0 AS family_sale_amount2  "
					+ "	FROM  " + "		(  "
					+ "			SELECT regionId, sum(family_amount) AS family_amount, work_year, typeId  "
					+ "			FROM(  "
					+ "				SELECT family1.regionId, family_happen1.typeId, family_happen1.amount AS family_amount,YEAR( ifnull( family_happen1.operatorTime, now())) AS work_year  "
					+ "				FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_family_economic_herds AS family_happen1 ON family1.id = family_happen1.familyid  "
					+ "			) AS work1  " + "			GROUP BY regionId, work_year, typeId  "
					+ "		) AS work2  " + "	GROUP BY regionId, work_year, typeId  "
					+ ") AS work_total inner join tb_region as region1 on region1.id=regionId inner join tb_region as region2 on region1.parentId=region2.id "
					+ "where region2.regionType=5  " + "GROUP BY work_year,region2.id, typeId";
			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			List<Record> recordModel3 = arnold2.getRecordBySql(sql3);

			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);
			resultMap.put("rl3", recordModel3);

			Page<Record> recordModel = arnold2.pageEffectTPGJJson(pageNumber, pageSize);
			/*
			 * for(Record record : recordModel.getList()){ record.set("jyryhj",0);
			 * record.set("jjjnpx",0); record.set("bfjy",0); record.set("zzjy",0);
			 * record.set("lwsg",0); record.set("lzrylj",0); record.set("pxryhj",0);
			 * record.set("jzsg",0); record.set("jsy",0); record.set("jxczs",0);
			 * record.set("fwhy",0); record.set("sfy",0); record.set("jttj",0);
			 * record.set("jyzywy",0); record.set("ncjs",0);
			 * 
			 * record.set("clxzs",0); record.set("chulxzs",0); record.set("ddsgs",0);
			 * record.set("ygxzs",0); record.set("qt",0); }
			 */
			resultMap.put(ConstUtils.R_PAGE, recordModel);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> pageEffectJCSSJson(int pageNumber, int pageSize) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {

			Page<Record> recordModel = arnold2.pageEffectJCSSJson(pageNumber, pageSize);
			for (Record record : recordModel.getList()) {

			}

			resultMap.put(ConstUtils.R_PAGE, recordModel);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> pageEffectXZYZJson(int pageNumber, int pageSize) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {

			Page<Record> recordModel = arnold2.pageEffectXZYZJson(pageNumber, pageSize);
			resultMap.put(ConstUtils.R_PAGE, recordModel);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	/***
	 * 
	 * 通过家庭Id查询香猪及相关的信息
	 * 
	 * 
	 * @param familyId
	 * @return
	 */
	public Map<String, Object> queryXzyzByFamliyIdToJson(String familyId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {

			Record recordModel = arnold2.queryXzyzByFamliyId(familyId);
			resultMap.put(ConstUtils.R_MODEL, recordModel);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> pageEffectJYWGJson(int pageNumber, int pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {
			/*
			 * Page<Record> recordModel = arnold2.pageEffectJYWGJson(pageNumber, pageSize);
			 * int i=0; Random random = new Random(); for(Record record :
			 * recordModel.getList()){
			 * 
			 * int sfy=random.nextInt(5); int jsy=random.nextInt(5); int
			 * jxczs=random.nextInt(5); int fwhy=random.nextInt(5); int
			 * jyzywry=random.nextInt(5); int jzsg=random.nextInt(5); int
			 * jttj=random.nextInt(5);
			 * 
			 * int bqxz=random.nextInt(5); int bqxzpx=random.nextInt(5); int
			 * ljwdjy=random.nextInt(5); int ljwdjypx=random.nextInt(5); int
			 * ljlg=random.nextInt(5); int ljlgpx=random.nextInt(5);
			 * 
			 * int hj1=sfy+jsy+jxczs+fwhy+jyzywry+jzsg+jttj; int
			 * hj2=hj1+bqxz+bqxzpx+ljwdjy+ljwdjypx+ljlg+ljlgpx;
			 * 
			 * int lswg=random.nextInt(10); int bqzzjyrs=random.nextInt(20); int
			 * bqzjs=random.nextInt(20);
			 * 
			 * record.set("sfy",sfy); record.set("jsy",jsy); record.set("jxczs",jxczs);
			 * record.set("fwhy",fwhy); record.set("jyzywry",jyzywry);
			 * record.set("jzsg",jzsg); record.set("jttj",jttj); record.set("bqxz",bqxz);
			 * record.set("bqxzpx",bqxzpx); record.set("ljwdjy",ljwdjy);
			 * record.set("ljwdjypx",ljwdjypx); record.set("ljlg",ljlg);
			 * record.set("ljlgpx",ljlgpx); record.set("hj1",hj1); record.set("hj2",hj2);
			 * record.set("lswg",lswg); record.set("bqzzjyrs",bqzzjyrs);
			 * record.set("bqzjs",bqzjs);
			 * 
			 * i++; }
			 * 
			 * resultMap.put(ConstUtils.R_PAGE, recordModel);
			 */

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> page_getRegionByPId_json(String parentId, int pageNumber, int pageSize) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Arnold2 arnold2 = new Arnold2();
		try {

			Page<Record> recordModel = arnold2.page_getRegionByPId_json(parentId, pageNumber, pageSize);

			resultMap.put(ConstUtils.R_PAGE, recordModel);

			String sql1 = "SELECT region2.fullName as hamletId, postId, jobCategory, lastyear, sum(work_total_count) AS work_total_count, sum(work_current_count) AS work_current_count, sum(no_work_total_count) AS no_work_total_count  "
					+ " FROM ( "
					+ "	SELECT regionid,hamletId, jobCategory, lastyear, count(0) AS work_total_count, 0 AS work_current_count, postId, 0 AS no_work_total_count  "
					+ "	FROM (  "
					+ "		SELECT family1.regionid,family1.hamletId, villager_happen1.jobCategory, YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId, villager_happen1.postId  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id) "
					+ "		WHERE villager_happen1.ralationType = 3  " + "		) AS work1  "
					+ "	GROUP BY regionid,hamletId, postId,lastyear  " + "	UNION ALL /***当期就业人数***/  "
					+ "	SELECT regionid,hamletId, jobCategory, lastyear, 0 AS work_total_count, count(1) AS work_current_count,postId, 0 AS no_work_total_count  "
					+ "	FROM (  "
					+ "		SELECT family1.regionid,family1.hamletId, villager_happen1.jobCategory, YEAR( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId, villager_happen1.postId  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid  INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id) "
					+ "		WHERE villager_happen1.ralationType = 3 AND YEAR ( villager_happen1.postLeaveDate ) = YEAR (now()) "
					+ "		) AS work1  " + "	GROUP BY regionid,hamletId, postId, lastyear "
					+ "	UNION ALL /***失业总人数***/  "
					+ "	SELECT regionid, hamletId, jobCategory, lastyear, 0	AS work_total_count, 0 AS work_current_count, postId, count(1) AS no_work_total_count "
					+ "	FROM( "
					+ "		SELECT family1.regionid,family1.hamletId, villager_happen1.jobCategory, YEAR ( ifnull( villager_happen1.postLeaveDate, now())) AS lastyear, villager_happen1.villagerId, villager_happen1.postId "
					+ "		FROM arnold.tb_family AS family1  INNER JOIN arnold.tb_villager AS villager1 ON family1.id = villager1.familyid INNER JOIN arnold.tb_villager_post_happen AS villager_happen1 ON villager1.id = villager_happen1.villagerId AND villager_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id)"
					+ "		WHERE ( villager_happen1.ralationType = 4 OR villager_happen1.ralationType = 5 ) "
					+ "		) AS work1 " + "	GROUP BY regionid,hamletId, postId, lastyear  "
					+ ") AS work2 inner join tb_region as region1 on region1.id=regionid inner join tb_region as region2 on region2.id=region1.parentId where region2.regionType=5 "
					+ "GROUP BY hamletId, jobCategory,postId, lastyear ";

			String sql2 = "SELECT lastyear, region2.fullName as hamletId, sum(train_total_count) AS train_total_count, sum(train_current_count) AS train_current_count,sum(no_work_train_count) AS no_work_train_count  "
					+ "FROM (  "
					+ "	SELECT regionid,lastyear, hamletId, count(1) AS train_total_count, 0 AS train_current_count, 0 AS no_work_train_count  "
					+ "	FROM (  "
					+ "		SELECT DISTINCT family1.regionid, family1.hamletId, YEAR ( ifnull( train1.startTime, train1.createTime )) AS lastyear, villager1.id  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON villager1.familyId = family1.id INNER JOIN arnold.tb_villager_post_happen AS post_happen1 ON villager1.id = post_happen1.villagerId INNER JOIN arnold.tb_train_participant AS train_participant1 ON villager1.id = train_participant1.joinPersonid INNER JOIN tb_train AS train1 ON train1.id = train_participant1.trainid AND post_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id) "
					+ "		WHERE  post_happen1.ralationType = 3  " + "	) AS valiger_train1  "
					+ "	GROUP BY lastyear, regionid,hamletId  " + "	UNION ALL  "
					+ "	SELECT regionid,lastyear, hamletId, 0 AS train_total_count, count(1) AS train_current_count, 0 AS no_work_train_count  "
					+ "	FROM (  "
					+ "		SELECT DISTINCT family1.regionid,family1.hamletId, YEAR( ifnull( train1.startTime, train1.createTime )) AS lastyear, villager1.id  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON villager1.familyId = family1.id INNER JOIN arnold.tb_villager_post_happen AS post_happen1 ON villager1.id = post_happen1.villagerId INNER JOIN arnold.tb_train_participant AS train_participant1 ON villager1.id = train_participant1.joinPersonid INNER JOIN tb_train AS train1 ON train1.id = train_participant1.trainid AND post_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id) "
					+ "		WHERE YEAR( ifnull( train1.startTime, train1.createTime )) = YEAR (now()) AND post_happen1.ralationType = 3  "
					+ "	) AS valiger_train1  " + "	GROUP BY lastyear, regionid,hamletId  " + "	UNION ALL  "
					+ "	SELECT regionid,lastyear, hamletId, 0 AS train_total_count, 0 AS train_current_count, count(1) AS no_work_train_count  "
					+ "	FROM (  "
					+ "		SELECT DISTINCT family1.regionid,family1.hamletId, YEAR ( ifnull( train1.startTime, train1.createTime )) AS lastyear, villager1.id  "
					+ "		FROM arnold.tb_family AS family1 INNER JOIN arnold.tb_villager AS villager1 ON villager1.familyId = family1.id INNER JOIN arnold.tb_villager_post_happen AS post_happen1 ON villager1.id = post_happen1.villagerId INNER JOIN arnold.tb_train_participant AS train_participant1 ON villager1.id = train_participant1.joinPersonid INNER JOIN tb_train AS train1 ON train1.id = train_participant1.trainid AND post_happen1.createTime=(select max(post1.createTime) from tb_villager_post_happen as post1 where post1.villagerId=villager1.id) "
					+ "		WHERE post_happen1.ralationType = 4 OR post_happen1.ralationType = 5  "
					+ "	) AS valiger_train1  " + "	GROUP BY regionid,lastyear, hamletId  "
					+ ") AS work1 inner join tb_region as region1 on region1.id=regionid inner join tb_region as region2 on region1.parentId=region2.id "
					+ " where region2.regionType=5 " + "GROUP BY region2.fullName, lastyear";

			List<Record> recordModel1 = arnold2.getRecordBySql(sql1);
			List<Record> recordModel2 = arnold2.getRecordBySql(sql2);
			resultMap.put("rl1", recordModel1);
			resultMap.put("rl2", recordModel2);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;

	}

	public Map<String, Object> pagePriceLog(int pageNumber, int pageSize, String startdate, String enddate,
			String keyWord) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String sql1 = "SELECT * FROM tb_industry_log l WHERE 1=1 ";
			if (startdate != null && !startdate.equals("")) {
				sql1 = sql1 + " AND l.operatorTime >= '" + startdate + "' ";
			}
			if (enddate != null && !enddate.equals("")) {
				sql1 = sql1 + " AND l.operatorTime <= '" + enddate + "' ";
			}
			if (keyWord != null && !keyWord.equals("")) {
				sql1 = sql1 + " AND l.typeName LIKE '%" + keyWord + "%' ";
			}

			Page<Record> recordModel = Db.paginate(pageNumber, pageSize, sql1, "");

			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		} catch (Exception e) {

			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);

		}

		return resultMap;
	}

	public Map<String, Object> updateEmploymenValliager(String villagerId, String postId, String memberId,
			Integer ralationType, Double salary, Double averageIncome, String companyProject, String jobCategory,
			Date postLeaveDate, Object object, String userId, Integer isEat, Integer isEatEncase, Integer isEncase,
			Integer isFiveFund, Integer isLunch, Integer isOther, String department, Boolean isEdit, String jobAdress,
			String id, Boolean isTrain, String phone) {
		VillagerService villagerService = new VillagerService();
		Villager villager = villagerService.queryValligerById(villagerId);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (villager == null) {
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			return resultMap;
		}
		try {
			ValigerPostHappenService valigerPostHappenService = new ValigerPostHappenService();
			valigerPostHappenService.updatePostVillagerRalationType(villagerId, postId, memberId, 
					ralationType, salary, averageIncome, companyProject, jobCategory, 
					postLeaveDate, object, userId, isEat, isEatEncase, isEncase, isFiveFund, 
					isLunch, isOther, department, isEdit, jobAdress, id, isTrain, phone);
		} catch (Exception e) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DATA_OPERATE_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DATA_OPERATE_ERROR_STR);
		}
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}

}
