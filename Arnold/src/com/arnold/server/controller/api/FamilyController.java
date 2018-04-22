package com.arnold.server.controller.api;

import java.util.HashMap;
import java.util.Map;

import com.arnold.server.controller.BaseController;
import com.arnold.server.service.api.FamilyService;
import com.arnold.server.service.api.VillagerService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;

public class FamilyController extends BaseController {

	//创建家庭
	public void add_family(){
		
		String number = this.getPara("number");
		String administrativeRegionId = this.getPara("administrativeRegionId");
		String description = this.getPara("description");
		
		if(Utils.isBlankOrEmpty(number)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "number不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(administrativeRegionId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "administrativeRegionId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService service = new FamilyService();
		
		int result = 1;
		//
		//int result = service.addFamily(number, administrativeRegionId, description);
		
		if(result == 1){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "添加成功!");
			
		}else if(result == 2){
			
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "添加失败!");
			
		}
		
		renderJson(resultMap);
	}
	
	//分页查询家庭
	public void page_family(){
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService service = new FamilyService();
		resultMap = service.pageFamily(pageNumber, pageSize);
		
		renderJson(resultMap);
			
	}
	
	public void findByFamilyId(){
		String id = this.getPara("familyId");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService service = new FamilyService();
		resultMap = service.findByFamilyId(id);
		
		renderJson(resultMap);
	}
	
	
	//修改家庭
	public void update_family(){
		
		String id = this.getPara("id");
		String villagerId = this.getPara("villagerId");
		String number = this.getPara("number");
		String administrativeRegionId = this.getPara("administrativeRegionId");
		String description = this.getPara("description");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService service = new FamilyService();
		//resultMap = service.updateFamily(id, villagerId, number, administrativeRegionId, description);
		
		renderJson(resultMap);
		
	}
	
	//删除家庭（逻辑删除）
	public void del_family(){
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		FamilyService service = new FamilyService();
		resultMap = service.delFamily(id);
		
		renderJson(resultMap);
		
	}
	
	//增加家庭成员
	public void add_villager(){
		
		String name = this.getPara("name");
		int sex = this.getParaToInt("sex", -1);
		String IDnumber = this.getPara("IDnumber");
		String birthday = this.getPara("birthday");
		String familyId = this.getPara("familyId");
		String housemasterRelation = this.getPara("housemasterRelation");
		String createUserId = this.getPara("createUserId");
		int isValid = this.getParaToInt("isValid");
		
		if(Utils.isBlankOrEmpty(name)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "name不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(sex == -1){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "sex不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(IDnumber)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "IDnumber不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(birthday)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "birthday不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(familyId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "familyId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(housemasterRelation)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "housemasterRelation不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(Utils.isBlankOrEmpty(createUserId)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "createUserId不能为空！");
			renderJson(resultMap);
			return;
		}
		
		if(isValid == -1){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "isValid不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		VillagerService service = new VillagerService();
//		int result = service.addVillager(name, sex, IDnumber, birthday, 
//				familyId, housemasterRelation, createUserId, isValid);
		
//		if(result == 1){
//			
//			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
//			resultMap.put(ConstUtils.R_MSG, "添加成功!");
//			
//		}else if(result == 2){
//			
//			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
//			resultMap.put(ConstUtils.R_MSG, "添加失败!");
//			
//		}
		
		
		renderJson(resultMap);
	}
	
	//根据家庭id查询家庭成员
	public void list_villager(){
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		VillagerService service = new VillagerService();
		//resultMap = service.listVillager(id);
		
		renderJson(resultMap);
	}
	
	//修改家庭成员
	public void update_villager(){
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		int sex = this.getParaToInt("sex", -1);
		String IDnumber = this.getPara("IDnumber");
		String birthday = this.getPara("birthday");
		String housemasterRelation = this.getPara("housemasterRelation");
		int isValid = this.getParaToInt("isValid");
		
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		VillagerService service = new VillagerService();
		//resultMap = service.updateVillager(id, name, sex, IDnumber, birthday, housemasterRelation, isValid);
		
		renderJson(resultMap);
	}
	
	//删除家庭成员
	public void del_villager(){
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空！");
			renderJson(resultMap);
			return;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		VillagerService service = new VillagerService();
		//resultMap = service.delVillager(id);
		
		renderJson(resultMap);
		
	}
}
