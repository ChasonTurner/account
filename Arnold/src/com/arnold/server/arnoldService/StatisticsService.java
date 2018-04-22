package com.arnold.server.arnoldService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.BreedIncome;
import com.arnold.server.model.CultivationIncome;
import com.arnold.server.model.Family;
import com.arnold.server.model.FamilyEconomicPurchaseHappen;
import com.arnold.server.model.FamilyEconomicSelfSaleHappen;
import com.arnold.server.model.FamilyEconomicUnderwritingHappen;
import com.arnold.server.model.FamilyLivecondition;
import com.arnold.server.model.StaticFamilyIncome;
import com.arnold.server.model.VillagerPostHappen;
import com.arnold.server.service.BaseService;
import com.arnold.server.service.api.RegionService;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Record;

public class StatisticsService extends BaseService {
	
	RegionService regionService= new RegionService();
	
	public Map<String, Object> statisticsFamilyIncome(String pYear, String familyId, String groupId, String hamletId, String countryId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int dealNo = 0;
		
		//获取需要统计的贫困户
		List<Record> familyInfos = Family.dao.getNeedStatisticsFamilyInfo(familyId, groupId, hamletId, countryId);
		
		if(null!=familyInfos && familyInfos.size()>0){
			for(Record familyInfo : familyInfos){
				Long curVillagerCount = familyInfo.getLong("villagerCount");
				
				if(Utils.isBlankOrEmpty(familyInfo.getStr("regionId")) || null==curVillagerCount || curVillagerCount == 0){
					continue;
				}
				
				StaticFamilyIncome staticFamilyIncome = new StaticFamilyIncome();//统计对象
				staticFamilyIncome.setYear(pYear);
				
				Record detailInfo = regionService.getCountryDetailInfoById(familyInfo.getStr("regionId"));
				
				//设置家庭统计相关基本信息
				staticFamilyIncome.setFamilyId(familyInfo.getStr("familyId"));
				staticFamilyIncome.setRegionId(familyInfo.getStr("regionId"));
				staticFamilyIncome.setCountryId(detailInfo.getStr("countryId"));
				staticFamilyIncome.setCountryName(detailInfo.getStr("countryName"));
				staticFamilyIncome.setHamletId(detailInfo.getStr("hamletId"));
				staticFamilyIncome.setHamletName(detailInfo.getStr("hamletName"));
				staticFamilyIncome.setGroupId(detailInfo.getStr("groupId"));
				staticFamilyIncome.setGroupName(detailInfo.getStr("groupName"));
				staticFamilyIncome.setVillagerId(familyInfo.getStr("villagerId"));
				staticFamilyIncome.setName(familyInfo.getStr("name"));
				staticFamilyIncome.setVillagerCount(familyInfo.getLong("villagerCount").intValue());
				
				//工资性收入
//				double salaryIncome = VillagerPostIncomeHappen.dao.countYearSalaryByFamilyId(familyInfo.getStr("familyId"), pYear);
				double salaryIncome = VillagerPostHappen.dao.countYearSalaryByFamilyId(familyInfo.getStr("familyId"), pYear);
				
				//家庭生产经营收入=自主+包销
				double selfSale = FamilyEconomicSelfSaleHappen.dao.countSelfSaleByFamilyId(familyInfo.getStr("familyId"), pYear);
				double underwriting = FamilyEconomicUnderwritingHappen.dao.countUnderwritingByFamilyId(
						familyInfo.getStr("familyId"), pYear);
				double familyIncome = selfSale + underwriting;
				
				//家庭生产经营收入分析=养殖+种植
				double breedIncome = BreedIncome.dao.countBreedIncomeByFamilyId(familyInfo.getStr("familyId"), pYear);
				double cultivationIncome = CultivationIncome.dao.countCultivationIncomeByFamilyId(familyInfo.getStr("familyId"), 
						pYear);
				double familyAnalyseIncome = breedIncome + cultivationIncome;
				
				//财产性收入、各类补贴/转移性收入  -- 录入值为年收入，以tb_family_livecondition最新值处理
				Record incomeInfo = FamilyLivecondition.dao.findFamilyIncomeByFamilyId(familyInfo.getStr("familyId"));
				double propertyIncome = 0;
				double subsidy = 0;
				double educationalExpenditure = 0;
				double medicalExpenditure = 0;
				if(null!=incomeInfo){
					if(!Utils.isBlankOrEmpty(incomeInfo.getStr("propertyIncome")))
						propertyIncome = Double.valueOf(incomeInfo.getStr("propertyIncome"));
					if(!Utils.isBlankOrEmpty(incomeInfo.getStr("subsidy")))
						subsidy = Double.valueOf(incomeInfo.getStr("subsidy"));
					if(null!=incomeInfo.getDouble("educationalExpenditure"))
						educationalExpenditure = incomeInfo.getDouble("educationalExpenditure");
					if(null!=incomeInfo.getDouble("medicalExpenditure"))
						medicalExpenditure = incomeInfo.getDouble("medicalExpenditure");
				}
				
				//生产性支出=购买支出+成本支出（可能存在的）
				double productPay =FamilyEconomicPurchaseHappen.dao.countPurchaseOutcomeByFamilyId(
						familyInfo.getStr("familyId"), pYear);
				
				//其它支出=教育支出+医疗费用支出
				double otherPay = educationalExpenditure+medicalExpenditure;
				
				//家庭年收入 = 全家工资性收入+财产性收入+家庭生产经营收入+各类补贴
				double yearIncome = salaryIncome + propertyIncome + familyIncome + subsidy;
				//家庭年收入分析 = 全家工资性收入+财产性收入+家庭生产经营收入分析+各类补贴
				double yearAnalyseIncome = salaryIncome + propertyIncome + familyAnalyseIncome + subsidy;
				//家庭年纯收入/净收入 = 家庭年收入-支出（家庭生产性支出+其他支出）
				double familyYearIncome = yearIncome - productPay - otherPay;
				//人均收入=家庭年收入/12/总人数
				double averageIncome = yearIncome/12d/familyInfo.getLong("villagerCount").intValue();
				//人均收入分析=家庭年收入分析/12/总人数
				double averageAnalyseIncome = yearAnalyseIncome/12d/familyInfo.getLong("villagerCount").intValue();
				//家庭人均年纯收入=家庭年纯收入/总人数
				double personYearIncome = familyYearIncome/familyInfo.getLong("villagerCount").intValue();
				
				//设置家庭相关的费用统计
				staticFamilyIncome.setSalaryIncome(salaryIncome);
				staticFamilyIncome.setFamilyIncome(familyIncome);
				staticFamilyIncome.setFamilyAnalyseIncome(familyAnalyseIncome);
				staticFamilyIncome.setPropertyIncome(propertyIncome);
				staticFamilyIncome.setSubsidy(subsidy);
				staticFamilyIncome.setProductPay(productPay);
				staticFamilyIncome.setOtherPay(otherPay);
				staticFamilyIncome.setYearIncome(yearIncome);
				staticFamilyIncome.setYearAnalyseIncome(yearAnalyseIncome);
				staticFamilyIncome.setFamilyYearIncome(familyYearIncome);
				staticFamilyIncome.setAverageIncome(averageIncome);
				staticFamilyIncome.setAverageAnalyseIncome(averageAnalyseIncome);
				staticFamilyIncome.setPersonYearIncome(personYearIncome);
				
				//新增或更新统计表
				StaticFamilyIncome.dao.addOrUpdRecord(staticFamilyIncome);
				dealNo++;
			}
			
		}
		
		resultMap.put(ConstUtils.R_MODEL, dealNo);
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		return resultMap;
	}
	
}
