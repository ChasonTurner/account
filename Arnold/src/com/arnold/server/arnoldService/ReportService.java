package com.arnold.server.arnoldService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.bean.request.RequestReportFamilyIncome;
import com.arnold.server.model.Family;
import com.arnold.server.model.FamilyEconomicPurchaseHappen;
import com.arnold.server.model.FamilyEconomicSelfSaleHappen;
import com.arnold.server.model.FamilyEconomicUnderwritingHappen;
import com.arnold.server.model.Region;
import com.arnold.server.model.StaticFamilyIncome;
import com.arnold.server.model.Villager;
import com.arnold.server.model.VillagerPostHappen;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Record;

public class ReportService extends BaseService {

	public Map<String, Object> reportFamilyInfo(String familyId, Map<String,String> chooseParamMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Record model = Family.dao.reportFamilyInfo(familyId);
		if(model==null) {
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
			return resultMap;
		}else{
			String hamletId = "";
			String hamletName = "";
			String groupId = "";
			String groupName = "";
			if(!Utils.isBlankOrEmpty(model.getStr("regionId"))){
				//获取村组信息
				Region curRegionInfo = Region.dao.findByRegionId(model.getStr("regionId"));
				if(null!=curRegionInfo && null!=curRegionInfo.getRegionType()){
					switch (curRegionInfo.getRegionType()) {
					case 5://村
						hamletId = curRegionInfo.getId();
						hamletName = curRegionInfo.getShortName();
						break;
					case 6://组
						hamletId = curRegionInfo.getParentId();
						hamletName = curRegionInfo.getParentName();
						groupId = curRegionInfo.getId();
						groupName = curRegionInfo.getShortName();
						break;
					default:
						break;
					}
				}
			}
			model.set("hamletId", hamletId);
			model.set("hamletName", hamletName);
			model.set("groupId", groupId);
			model.set("groupName", groupName);
			
			model.set("villagerInfos", Villager.dao.getVillagerInfoByFamilyId(familyId));
			
			if(!Utils.isBlankOrEmpty(chooseParamMap.get("typeId"))){
				Map<String,Integer> orderInfo = new HashMap<String, Integer>();
				//养殖
				int purchaseCount = FamilyEconomicPurchaseHappen.dao.getPurchasseOutcomeInfos(familyId, 
						chooseParamMap.get("year"), chooseParamMap.get("typeId"));
				//收购
				int underwritingCount = FamilyEconomicUnderwritingHappen.dao.getUnderwritingInfos(familyId, 
						chooseParamMap.get("year"), chooseParamMap.get("typeId"));
				int selfSaleCount = FamilyEconomicSelfSaleHappen.dao.getSelfSaleInfos(familyId, 
						chooseParamMap.get("year"), chooseParamMap.get("typeId"));
				orderInfo.put("numbers", purchaseCount);
				orderInfo.put("alreadyBuys", underwritingCount+selfSaleCount);
				model.set("orderInfo", orderInfo);
			}
			
			if(!Utils.isBlankOrEmpty(chooseParamMap.get("jobCategoryIds"))){
				//务工信息
				model.set("postInfos", VillagerPostHappen.dao.getPostInfos(familyId, chooseParamMap.get("jobCategoryIds"),
						chooseParamMap.get("year")));
			}
			
		}
		
		resultMap.put(ConstUtils.R_MODEL, model);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
	/** 获取家庭年度收入信息 */
	public Map<String, Object> reportFamilyIncomeInfo(RequestReportFamilyIncome pFamilyIncome) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//获取家庭年收入基本信息
		Record familyRecord = StaticFamilyIncome.dao.queryByFamilyIdAndYear(pFamilyIncome.getFamilyId());
		
		if(null == familyRecord){
			return resultMap;
		}
		
		/*
		 * 工资性收入
		 * 劳务施工 0a8478c0-9a31-44d4-867a-73d3f457ba09
		 * 帮扶就业 b97b3358-dca5-4251-9216-8242de81469f
		 * 自主就业 6e07c067-e379-4dc3-8798-8a81ca7aca3a
		 */
		//工资收入（保洁员、护林工等功能子收入）
		double salaryVal = VillagerPostHappen.dao.countYearSalaryByFamilyId(pFamilyIncome.getFamilyId(), pFamilyIncome.getYear(),
				"0a8478c0-9a31-44d4-867a-73d3f457ba09","b97b3358-dca5-4251-9216-8242de81469f");
		familyRecord.set("salaryVal", salaryVal);
		
		//务工收入（外出务工除基本生活费、路费结余）
		double ownSalaryVal = VillagerPostHappen.dao.countYearSalaryByFamilyId(pFamilyIncome.getFamilyId(), pFamilyIncome.getYear(),
				"6e07c067-e379-4dc3-8798-8a81ca7aca3a");
		familyRecord.set("ownSalaryVal", ownSalaryVal);
		
		/*
		 * 家庭经营收入
		 * 7d03201d-65da-4a7a-88ac-b6c98dba5169	水稻	50770539-7dc8-4338-8573-e02de79ac1c0
		 * 66f36735-5075-4cb1-96f0-449b19cc5b39	玉米	50770539-7dc8-4338-8573-e02de79ac1c0
		 * a740c34d-3479-4b84-bb52-0086134fa50c	红薯	50770539-7dc8-4338-8573-e02de79ac1c0
		 * d798181e-8de1-496d-872d-77118a9f7ca5	马铃薯	50770539-7dc8-4338-8573-e02de79ac1c0
		 * eb49e5c4-3006-468e-bf91-00679d4fea80	大豆	50770539-7dc8-4338-8573-e02de79ac1c0
		 * 
		 * 529c33ce-3220-4814-b1f7-e4c96e05fd37	猪崽	f987e100-8a75-4404-a39f-050f69fe09f5
		 * 4f7987f5-5d50-45e1-9044-0ce8f2336b40	成年公猪	f987e100-8a75-4404-a39f-050f69fe09f5
		 * 6401d303-68d8-4036-ab05-44bd3bfc1aad	成年母猪	f987e100-8a75-4404-a39f-050f69fe09f5
		 * 5ec75ac7-5d76-4bec-8dc2-5ddaea76f13e	牛	f987e100-8a75-4404-a39f-050f69fe09f5
		 * 8d3eb6a2-2fc9-4e96-a184-947927db223f	羊	f987e100-8a75-4404-a39f-050f69fe09f5
		 * 60e763c2-5c6e-4759-bc05-d437e24c6a08	幼崽	f987e100-8a75-4404-a39f-050f69fe09f5
		 */
		double riceIncome = 0d;
		double cornIncome = 0d;
		double sweetPotatoIncome = 0d;
		double potatoIncome = 0d;
		double soybeanIncome = 0d;
		double zhuzaiIncome = 0d;
		double adultBoarIncome = 0d;
		double adultSowIncome = 0d;
		double cattleIncome = 0d;
		double sheepIncome = 0d;
		double cubIncome = 0d;
		
		double ricePrice = 0d;
		double cornPrice = 0d;
		double sweetPotatoPrice = 0d;
		double potatoPrice = 0d;
		double soybeanPrice = 0d;
		double zhuzaiPrice = 0d;
		double adultBoarPrice = 0d;
		double adultSowPrice = 0d;
		double cattlePrice = 0d;
		double sheepPrice = 0d;
		double cubPrice = 0d;
		
		double riceCount = 0d;
		double cornCount = 0d;
		double sweetPotatoCount = 0d;
		double potatoCount = 0d;
		double soybeanCount = 0d;
		double zhuzaiCount = 0d;
		double adultBoarCount = 0d;
		double adultSowCount = 0d;
		double cattleCount = 0d;
		double sheepCount = 0d;
		double cubCount = 0d;
		
		List<Record> incomeOrder = FamilyEconomicSelfSaleHappen.dao.listEconomicOrderInfos(pFamilyIncome.getFamilyId(), 
				pFamilyIncome.getYear());//自主+包销
		if(null!=incomeOrder && incomeOrder.size()>0){
			for(Record record : incomeOrder){
				String curTypeId = record.getStr("typeId");
				Double curUnitPrice = record.getDouble("unitPrice");
				if(null==curUnitPrice){
					curUnitPrice = 0d;
				}
				Integer curAmount = record.getInt("amount");
				if(null==curAmount){
					curAmount = 0;
				}
				double curPrice = curUnitPrice*curAmount;
				switch (curTypeId) {
					case "7d03201d-65da-4a7a-88ac-b6c98dba5169"://水稻
						riceIncome += curPrice;
						ricePrice = curUnitPrice;
						riceCount += curAmount;
						break;
					case "66f36735-5075-4cb1-96f0-449b19cc5b39"://玉米
						cornIncome += curPrice;
						cornPrice = curUnitPrice;
						cornCount += curAmount;
						break;
					case "a740c34d-3479-4b84-bb52-0086134fa50c"://红薯
						sweetPotatoIncome += curPrice;
						sweetPotatoPrice = curUnitPrice;
						sweetPotatoCount += curAmount;
						break;
					case "d798181e-8de1-496d-872d-77118a9f7ca5"://马铃薯
						potatoIncome += curPrice;
						potatoPrice = curUnitPrice;
						potatoCount += curAmount;
						break;
					case "eb49e5c4-3006-468e-bf91-00679d4fea80"://大豆
						soybeanIncome += curPrice;
						soybeanPrice = curUnitPrice;
						soybeanCount += curAmount;
						break;
					case "529c33ce-3220-4814-b1f7-e4c96e05fd37"://猪崽
						zhuzaiIncome += curPrice;
						zhuzaiPrice = curUnitPrice;
						zhuzaiCount += curAmount;
						break;
					case "4f7987f5-5d50-45e1-9044-0ce8f2336b40"://成年公猪
						adultBoarIncome += curPrice;
						adultBoarPrice = curUnitPrice;
						adultBoarCount += curAmount;
						break;
					case "6401d303-68d8-4036-ab05-44bd3bfc1aad"://成年母猪
						adultSowIncome += curPrice;
						adultSowPrice = curUnitPrice;
						adultSowCount += curAmount;
						break;
					case "5ec75ac7-5d76-4bec-8dc2-5ddaea76f13e"://牛
						cattleIncome += curPrice;
						cattlePrice = curUnitPrice;
						cattleCount += curAmount;
						break;
					case "8d3eb6a2-2fc9-4e96-a184-947927db223f"://羊
						sheepIncome += curPrice;
						sheepPrice = curUnitPrice;
						sheepCount += curAmount;
						break;
					case "60e763c2-5c6e-4759-bc05-d437e24c6a08"://幼崽
						cubIncome += curPrice;
						cubPrice = curUnitPrice;
						cubCount += curAmount;
						break;
					default:
						break;
				}
			}
		}
		familyRecord.set("riceIncome", new BigDecimal(riceIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cornIncome", new BigDecimal(cornIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("sweetPotatoIncome", new BigDecimal(sweetPotatoIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("potatoIncome", new BigDecimal(potatoIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("soybeanIncome", new BigDecimal(soybeanIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("zhuzaiIncome", new BigDecimal(zhuzaiIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("adultBoarIncome", new BigDecimal(adultBoarIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("adultSowIncome", new BigDecimal(adultSowIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cattleIncome", new BigDecimal(cattleIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("sheepIncome", new BigDecimal(sheepIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cubIncome", new BigDecimal(cubIncome).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		familyRecord.set("ricePrice", new BigDecimal(ricePrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cornPrice", new BigDecimal(cornPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("sweetPotatoPrice", new BigDecimal(sweetPotatoPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("potatoPrice", new BigDecimal(potatoPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("soybeanPrice", new BigDecimal(soybeanPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("zhuzaiPrice", new BigDecimal(zhuzaiPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("adultBoarPrice", new BigDecimal(adultBoarPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("adultSowPrice", new BigDecimal(adultSowPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cattlePrice", new BigDecimal(cattlePrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("sheepPrice", new BigDecimal(sheepPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		familyRecord.set("cubPrice", new BigDecimal(cubPrice).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		familyRecord.set("riceCount", riceCount);
		familyRecord.set("cornCount", cornCount);
		familyRecord.set("sweetPotatoCount", sweetPotatoCount);
		familyRecord.set("potatoCount", potatoCount);
		familyRecord.set("soybeanCount", soybeanCount);
		familyRecord.set("zhuzaiCount", zhuzaiCount);
		familyRecord.set("adultBoarCount", adultBoarCount);
		familyRecord.set("adultSowCount", adultSowCount);
		familyRecord.set("cattleCount", cattleCount);
		familyRecord.set("sheepCount", sheepCount);
		familyRecord.set("cubCount", cubCount);
		
		/*
		 * 生产费用支出
		 * flUnitPrices		肥料成本(元/亩)
		 * lyUnitPrices		农药成本(元/亩)
		 * bmUnitPrices		薄膜成本(元/亩)
		 * ggUnitPrices		雇工成本(元/亩)
		 * slUnitPrices		饲料成本(元/头)
		 * syUnitPrices		兽药成本(元/头)
		 * lsslUnitPrices	粮食饲料成本(元/头)
		 * qtUnitPrices		其他成本(元/亩)(元/头)
		 */
		Record outcomeOrder = FamilyEconomicPurchaseHappen.dao.listEconomicOutOrderInfos(pFamilyIncome.getFamilyId(), 
				pFamilyIncome.getYear());
		familyRecord.setColumns(outcomeOrder);
		
		//年收入
		double yearIncome = getRecordVal(familyRecord, "propertyIncome")
				+ getRecordVal(familyRecord, "receivedIncome")
				+ getRecordVal(familyRecord, "helpIncome")
				+ getRecordVal(familyRecord, "medicalIncome")
				+ getRecordVal(familyRecord, "educationalIncome")
				+ getRecordVal(familyRecord, "cashPolicyIncome")
				+ getRecordVal(familyRecord, "salaryVal")
				+ getRecordVal(familyRecord, "ownSalaryVal")
				+ getRecordVal(familyRecord, "riceIncome")
				+ getRecordVal(familyRecord, "cornIncome")
				+ getRecordVal(familyRecord, "sweetPotatoIncome")
				+ getRecordVal(familyRecord, "potatoIncome")
				+ getRecordVal(familyRecord, "soybeanIncome")
				+ getRecordVal(familyRecord, "zhuzaiIncome")
				+ getRecordVal(familyRecord, "adultBoarIncome")
				+ getRecordVal(familyRecord, "adultSowIncome")
				+ getRecordVal(familyRecord, "cattleIncome")
				+ getRecordVal(familyRecord, "sheepIncome")
				+ getRecordVal(familyRecord, "cubIncome");
		familyRecord.set("yearIncome", yearIncome);
		
		//生活消费收入
		double lifeIncome = getRecordVal(familyRecord, "yearIncome")
				- getRecordVal(familyRecord, "flUnitPrices")
				- getRecordVal(familyRecord, "lyUnitPrices")
				- getRecordVal(familyRecord, "bmUnitPrices")
				- getRecordVal(familyRecord, "ggUnitPrices")
				- getRecordVal(familyRecord, "slUnitPrices")
				- getRecordVal(familyRecord, "syUnitPrices")
				- getRecordVal(familyRecord, "lsslUnitPrices")
				- getRecordVal(familyRecord, "qtUnitPrices")
				- getRecordVal(familyRecord, "educationalOutIncome")
				- getRecordVal(familyRecord, "medicalOutIncome");
		familyRecord.set("lifeIncome", lifeIncome);
		
		//人均生活消费收入
		double avarageIncome = new BigDecimal(getRecordVal(familyRecord, "lifeIncome")/getRecordVal(familyRecord, 
				"villagerCount")).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		familyRecord.set("avarageIncome", avarageIncome);
		
		resultMap.put(ConstUtils.R_MODEL, familyRecord);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		
		return resultMap;
	}
	
	/** 通过key获取record的double值 */
	private Double getRecordVal(Record pRecord, String pKey){
		double resDouble = 0.00d;
		if(null==pRecord){
			return resDouble;
		}
		
		if(null==pRecord.get(pKey)){
			return resDouble;
		}
		
		String goalStr = pRecord.get(pKey).toString().trim();
		
		if(goalStr.length()>0){
			resDouble = new BigDecimal(goalStr).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		
		return resDouble;
	}
	
}
