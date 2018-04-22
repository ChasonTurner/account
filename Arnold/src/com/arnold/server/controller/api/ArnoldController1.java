package com.arnold.server.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.arnoldService.ArnoldService1;
import com.arnold.server.bean.request.RequestAddTrain;
import com.arnold.server.bean.request.RequestUpdateTrain;
import com.arnold.server.controller.BaseController;
import com.arnold.server.service.api.TrainService;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;


/**
 * 
 * 
 * @description TODO
 */
public class ArnoldController1 extends BaseController {
	
	Map<String, Object> resultMap = new HashMap<String, Object>();
	
	
	//查询公共基础设施
	public void page_public_project() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String kind = this.getPara("kind",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		if(kind == null&&keyWord == null){
			resultMap = service.pagePublicProject(pageNumber, pageSize, "");
		}else if(kind != null&&(keyWord == null||keyWord.equals(""))){
			resultMap = service.pagePublicProjectByKindAndKey(pageNumber, pageSize ,kind, "");
		}else if(kind == null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pagePublicProjectByKindAndKey(pageNumber, pageSize ,"", keyWord);
		}else if(kind != null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pagePublicProjectByKindAndKey(pageNumber, pageSize ,kind, keyWord);
		}
		
	
		renderJson(resultMap);
		return;
	}
	
	//根据id查询公共基础设施
	public void page_public_project_by_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		if(Utils.isBlankOrEmpty(ids)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pagePublicProject(pageNumber, pageSize, ids);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_public_project_by_kind() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		int kind = this.getParaToInt("kind",-1);
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pagePublicProjectByKind(pageNumber, pageSize, kind);
		
		renderJson(resultMap);
		return;
		
	}
	
	//增加公共基础设施
	public void add_public_project() {
		
		String name = this.getPara("name");
		String cotentAndArea = this.getPara("cotentAndArea");
		String kind = this.getPara("kind");
		String natureId = this.getPara("natureId");
		String villageName = this.getPara("villageName");
		Double fundation = this.getParaToDouble("fundation", 0);
		Double departmentMoney = this.getParaToDouble("departmentMoney", 0);
		Double selfMoney = this.getParaToDouble("selfMoney", 0);
		int year = this.getParaToInt("year", 0);
		String startTime = this.getPara("startTime");
		String realStartTime = this.getPara("realStartTime");
		String endTime = this.getPara("endTime");
		String realEndTime = this.getPara("realEndTime");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addPublicProject(name, cotentAndArea, kind, natureId, villageName,
				fundation, departmentMoney, selfMoney, year, startTime, 
				realStartTime, endTime, realEndTime, writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	//编辑公共基础设施
	public void update_public_project() {
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		String cotentAndArea = this.getPara("cotentAndArea");
		String kind = this.getPara("kind");
		String natureId = this.getPara("natureId");
		String villageName = this.getPara("villageName");
		Double fundation = this.getParaToDouble("fundation", 0);
		Double departmentMoney = this.getParaToDouble("departmentMoney", 0);
		Double selfMoney = this.getParaToDouble("selfMoney", 0);
		int year = this.getParaToInt("year", 0);
		String startTime = this.getPara("startTime");
		String realStartTime = this.getPara("realStartTime");
		String endTime = this.getPara("endTime");
		String realEndTime = this.getPara("realEndTime");
		String nowWriter = this.getPara("nowWriter");
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "projectId不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.updatePublicProject(id, name, cotentAndArea, kind, natureId, villageName,
				fundation, departmentMoney, selfMoney, year, startTime, 
				realStartTime, endTime, realEndTime, "", nowWriter,-1,
				-1,-1,-1,-1,-1);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_project_invest_by_project_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String projectid = this.getPara("projectid");
		
		if(Utils.isBlankOrEmpty(projectid)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "publicProjectid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pageProjectInvestByProjectid(pageNumber, pageSize, projectid);
		
		renderJson(resultMap);
		return;
		
	}
	
	//根据id查询公共基础设施投资
	public void page_project_invest_by_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pageProjectInvestByid(pageNumber, pageSize, id);
		
		renderJson(resultMap);
		return;
		
	}
	
	//增加投资
	public void add_project_invest() {
		
		String projectid = this.getPara("projectid");
		double investPrice = (double)this.getParaToDouble("investPrice", (long)-1);
		String investTypeId = this.getPara("investTypeId");
		Date investDate = this.getParaToDate("investDate");
		String company = this.getPara("company");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		String attachment = this.getPara("attachment");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addProjectInvest(projectid, investPrice, investTypeId, investDate, 
				company, writer, nowWriter, attachment);
		
		renderJson(resultMap);
		return;
		
	}
	
	//编辑投资
	public void update_project_invest() {
		String id = this.getPara("id");
		String projectid = this.getPara("projectid");
		double investPrice = (double)this.getParaToDouble("investPrice", -1);
		String investTypeId = this.getPara("investTypeId");
		Date investDate = this.getParaToDate("investDate");
		String company = this.getPara("company");
		String nowWriter = this.getPara("nowWriter");
		String attachment = this.getPara("attachment");
		if(Utils.isBlankOrEmpty(id)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "projectId不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.updateProjectInvest(id, projectid, investPrice, investTypeId, 
				investDate, company, nowWriter, attachment);
		renderJson(resultMap);
		return;
	}
	
	public void del_project_invest() {
		String id = this.getPara("id");
		if(Utils.isBlankOrEmpty(id)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.delProjectInvest(id);
		renderJson(resultMap);
		return;
	}
	
	public void page_project_progress_by_project_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String projectid = this.getPara("projectid");
		
		if(Utils.isBlankOrEmpty(projectid)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "projectid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pageProjectProgressByProjectid(pageNumber, pageSize, projectid);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void add_project_progress() {
		
		String projectid = this.getPara("projectid");
		float progress = this.getParaToFloat("progress", -1.0f);
		String note = this.getPara("note");
		String writer = this.getPara("writer");
		String time = this.getPara("time");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addProjectProgress(projectid, progress, note, writer, time);
		
		renderJson(resultMap);
		return;
		
	}
	
	//查询帮扶项目
	public void page_help_project() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String kind = this.getPara("kind",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		if(kind == null&&keyWord == null){
			resultMap = service.pageHelpProject(pageNumber, pageSize, "");
		}else if(kind != null&&(keyWord == null||keyWord.equals(""))){
			resultMap = service.pageHelpProjectByKindAndKey(pageNumber, pageSize ,kind, "");
		}else if(kind == null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageHelpProjectByKindAndKey(pageNumber, pageSize ,"", keyWord);
		}else if(kind != null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageHelpProjectByKindAndKey(pageNumber, pageSize ,kind, keyWord);
		}
		renderJson(resultMap);
		return;
	}
	
	public void page_help_project_by_kind() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		int kind = this.getParaToInt("kind",-1);
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.pageHelpProjectByKind(pageNumber, pageSize, kind);
		
		renderJson(resultMap);
		return;
		
	}

	//根据id查询帮扶项目
	public void page_help_project_by_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		ArnoldService1 service = new ArnoldService1();
		
		if(Utils.isBlankOrEmpty(ids)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		resultMap = service.pageHelpProject(pageNumber, pageSize, ids);
		
		renderJson(resultMap);
		return;
		
	}
	
	//增加帮扶项目
	public void add_help_project() {
		
		String name = this.getPara("name");
		String cotentAndArea = this.getPara("cotentAndArea");
		String kind = this.getPara("kind");
		String helpType = this.getPara("helpType");
		String villageName = this.getPara("villageName");
		int finalMoney = this.getParaToInt("finalMoney", -1);
		int year = this.getParaToInt("year", -1);
		String startTime = this.getPara("startTime");
		String realStartTime = this.getPara("realStartTime");
		String endTime = this.getPara("endTime");
		String realEndTime = this.getPara("realEndTime");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addHelpProject(name, cotentAndArea, kind, helpType, villageName,
				-1, -1, -1, finalMoney, year, startTime, 
				realStartTime, endTime, realEndTime, writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	//编辑公共基础设施
	public void update_help_project() {
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		String cotentAndArea = this.getPara("cotentAndArea");
		String kind = this.getPara("kind");
		String helpType = this.getPara("helpType");
		String villageName = this.getPara("villageName");
		int finalMoney = this.getParaToInt("finalMoney", -1);
		int year = this.getParaToInt("year", -1);
		String startTime = this.getPara("startTime");
		String realStartTime = this.getPara("realStartTime");
		String endTime = this.getPara("endTime");
		String realEndTime = this.getPara("realEndTime");
		String nowWriter = this.getPara("nowWriter");
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "projectId不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.updateHelpProject(id, name, cotentAndArea, kind, helpType, villageName,
				-1, -1, -1, year, startTime, 
				realStartTime, endTime, realEndTime, "", nowWriter, finalMoney,
				-1,-1,-1,-1,-1);
		
		renderJson(resultMap);
		return;
		
	}
	
	//查询培训
	public void page_train() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String skillTypeId = this.getPara("skillTypeId",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		
		if(skillTypeId == null&&keyWord == null){
			resultMap = service.pageTrain(pageNumber, pageSize, "");
		}else if(skillTypeId != null&&(keyWord == null||keyWord.equals(""))){
			resultMap = service.pageTrainBySkillTypeAndKey(pageNumber, pageSize ,skillTypeId, "");
		}else if(skillTypeId == null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageTrainBySkillTypeAndKey(pageNumber, pageSize ,"", keyWord);
		}else if(skillTypeId != null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageTrainBySkillTypeAndKey(pageNumber, pageSize ,skillTypeId, keyWord);
		}
		
		renderJson(resultMap);
		return;
		
	}
	
	//查询教育
	public void page_education() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String typeId = this.getPara("typeId",null);
		String keyWord = this.getPara("keyWord",null);
		
		ArnoldService1 service = new ArnoldService1();
		if(typeId == null&&keyWord == null){
			resultMap = service.pageEducation(pageNumber, pageSize, "");
		}else if(typeId != null&&(keyWord == null||keyWord.equals(""))){
			resultMap = service.pageEducationByTypeAndKey(pageNumber, pageSize ,typeId, "");
		}else if(typeId == null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageEducationByTypeAndKey(pageNumber, pageSize ,"", keyWord);
		}else if(typeId != null&&keyWord != null&&!keyWord.equals("")){
			resultMap = service.pageEducationByTypeAndKey(pageNumber, pageSize ,typeId, keyWord);
		}
	
		renderJson(resultMap);
		return;
		
	}

	//类别查询教育
	public void page_education_type() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String typeId = this.getPara("typeId");
		
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageEducationByTypeAndKey(pageNumber, pageSize ,typeId,"");
		
		renderJson(resultMap);
		return;
		
	}
	
	//根据id查询教育
	public void page_education_by_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");
		
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageEducation(pageNumber, pageSize, ids);
		
		renderJson(resultMap);
		return;
		
	}
	
	//增加教育
	public void add_education() {
		
		String className = this.getPara("className");
		String typeId = this.getPara("typeId");
		String major = this.getPara("major");
		String name = this.getPara("name");
		String address = this.getPara("address");
		String startTime = this.getPara("startTime");
		String endTime = this.getPara("endTime");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addEducation(typeId, "", -1, startTime, endTime, 0, className, 
				name, major, address, writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	//编辑教育
	public void update_education() {
		
		String id = this.getPara("id");
		String className = this.getPara("className");
		String typeId = this.getPara("typeId");
		String major = this.getPara("major");
		String name = this.getPara("name");
		String address = this.getPara("address");
		String startTime = this.getPara("startTime");
		String endTime = this.getPara("endTime");
		String nowWriter = this.getPara("nowWriter");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.updateEducation(id, typeId, "", -1, startTime, endTime, -1, 
				className, name, major, address, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	//根据id删除教育
	public void del_education() {
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "projectId不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.delEducation(id);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_education_participant_by_education_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String educationid = this.getPara("educationid");
		
		if(Utils.isBlankOrEmpty(educationid)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "educationid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageEducationParticipantByEducationid(pageNumber, pageSize, educationid);
		renderJson(resultMap);
		return;
		
	}
	
	public void page_education_participant_by_id() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		if(Utils.isBlankOrEmpty(ids)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageEducationParticipant(pageNumber, pageSize, ids);
		renderJson(resultMap);
		return;
		
	}
	
	public void add_education_participant() {
		
		String educationid = this.getPara("educationid");
		String joinPersonid = this.getPara("joinPersonid");
		String helpPersonid = this.getPara("helpPersonid");
		String joinMoneyType = this.getPara("joinMoneyType");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addEducationParticipant(educationid, joinPersonid, helpPersonid,joinMoneyType, 
				writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void del_education_participant() {
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.delEducationParticipant(id);
		
		renderJson(resultMap);
		return;
		
	}
	
	/**
	 * 
	 * @Description: 根据id查询公共基础建设
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午3:36:39
	 */
	public void query_public_project_by_id() {

		String id = this.getPara("id");// 

		if (Utils.isBlankOrEmpty(id)) {

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.PARAM_ERROR_STR);
			renderJson(resultMap);
			return;

		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.queryPublicProjectById(id);

		renderJson(resultMap);
		return;

	}
	
	//根据id查询培训
	public void page_train_by_id() {
		
//		int pageNumber = this.getParaToInt("pageNumber", 1);
//		int pageSize = this.getParaToInt("pageSize", 8);
//		String ids = this.getPara("ids");//多个id用英文逗号隔开
		String ids = this.getPara("id");
		
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.getTrainByid(ids);
		
		renderJson(resultMap);
		return;
		
	}
	
	//增加培训
	public void add_train() {
		
		TrainService trainService = new TrainService();
		
		RequestAddTrain addTrain = getRequestBean(RequestAddTrain.class, "name","typeId","postId","content","price",
				"finalMoney","realStartTime","realEndTime","orgId","count","userId","writer","nowWriter","skillTypeId",
				"startTime","endTime","address");
		
		resultMap = trainService.addTrain(addTrain);
	
		renderJson(resultMap);
		return;
	}
	
	//编辑帮扶项目
	public void update_train() {
		
		RequestUpdateTrain updateTrain = getRequestBean(RequestUpdateTrain.class, "id", "name","typeId","postId","content","price",
				"finalMoney","realStartTime","realEndTime","orgId","count","userId","writer","nowWriter","skillTypeId",
				"startTime","endTime","address");
		
		if(Utils.isBlankOrEmpty(this.getPara("id"))){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		TrainService trainService = new TrainService();
		
		resultMap = trainService.updateTrain(updateTrain);

	
		renderJson(resultMap);
		return;
		
	}
	
	//根据id删除培训
	public void del_train() {
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.delTrain(id);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_train_speaker_by_train_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String trainid = this.getPara("trainid");
		
		if(Utils.isBlankOrEmpty(trainid)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "trainid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainSpeakerByTrainid(pageNumber, pageSize, trainid);
		renderJson(resultMap);
		return;
		
	}
	
	public void page_train_speaker_by_id() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		if(Utils.isBlankOrEmpty(ids)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainSpeaker(pageNumber, pageSize, ids);
		renderJson(resultMap);
		return;
		
	}
	
	public void add_train_speaker() {
		
		String trainid = this.getPara("trainid");
		String name = this.getPara("name");
		String sex = this.getPara("sex");
		int age = this.getParaToInt("age", -1);
		String telephone = this.getPara("telephone");
		String company = this.getPara("company");
		String position = this.getPara("position");
		String department = this.getPara("department");
		String address = this.getPara("address");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addTrainSpeaker(trainid, name, sex, age, telephone, company, position, department, address, 
				writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void update_train_speaker() {
		
		String id = this.getPara("id");
		String name = this.getPara("name");
		String sex = this.getPara("sex");
		int age = this.getParaToInt("age", -1);
		String telephone = this.getPara("telephone");
		String company = this.getPara("company");
		String position = this.getPara("position");
		String department = this.getPara("department");
		String address = this.getPara("address");
		String nowWriter = this.getPara("nowWriter");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
			
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.updateTrainSpeaker(id, name, sex, age, telephone, company, position, department, address, 
				nowWriter);
		renderJson(resultMap);
		return;
		
	}
	
	public void del_train_speaker() {
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.delTrainSpeaker(id);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_train_participant_by_train_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String trainid = this.getPara("trainid");
		
		if(Utils.isBlankOrEmpty(trainid)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "trainid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainParticipantByTrainid(pageNumber, pageSize, trainid);
		renderJson(resultMap);
		return;
		
	}
	
	public void page_train_participant_by_id() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		if(Utils.isBlankOrEmpty(ids)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainParticipant(pageNumber, pageSize, ids);
		renderJson(resultMap);
		return;
		
	}
	
	public void add_train_participant() {
		
		String trainid = this.getPara("trainid");
		String joinPersonid = this.getPara("joinPersonid");
		String helpPersonid = this.getPara("helpPersonid");
		String joinMoneyType = this.getPara("joinMoneyType");
		String writer = this.getPara("writer");
		String nowWriter = this.getPara("nowWriter");
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.addTrainParticipant(trainid, joinPersonid, helpPersonid, joinMoneyType,
				writer, nowWriter);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void del_train_participant() {
		
		String id = this.getPara("id");
		
		if(Utils.isBlankOrEmpty(id)){
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "id不能为空!");
			renderJson(resultMap);
			return;
		}
		
		ArnoldService1 service = new ArnoldService1();
		
		resultMap = service.delTrainParticipant(id);
		
		renderJson(resultMap);
		return;
		
	}
	
	public void page_train_has_job_by_train_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String trainid = this.getPara("trainid");
		
		if(Utils.isBlankOrEmpty(trainid)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "trainid不能为空!");
			renderJson(resultMap);
			return;
			
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainHasJobByTrainid(pageNumber, pageSize, trainid);
		renderJson(resultMap);
		return;
		
	}
	
	
public void page_train_has_job_by_startdate_post_id() {
		
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String startDate = this.getPara("startDate");
		String postId = this.getPara("postId");

		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainHasJobByTrainDateAndPostId(pageNumber, pageSize, startDate,postId);
		renderJson(resultMap);
		return;
		
	}


	public void page_train_has_job_by_id() {
		int pageNumber = this.getParaToInt("pageNumber", 1);
		int pageSize = this.getParaToInt("pageSize", 8);
		String ids = this.getPara("ids");//多个id用英文逗号隔开
		
		if(Utils.isBlankOrEmpty(ids)){
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
			resultMap.put(ConstUtils.R_MSG, "ids不能为空!");
			renderJson(resultMap);
			return;
		}
		ArnoldService1 service = new ArnoldService1();
		resultMap = service.pageTrainHasJob(pageNumber, pageSize, ids);
		renderJson(resultMap);
		return;
		
	}
}
