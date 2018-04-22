package com.arnold.server.arnoldService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arnold.server.model.Arnold1;
import com.arnold.server.model.EducationParticipant;
import com.arnold.server.model.ProjectInvestHappen;
import com.arnold.server.model.ProjectProgress;
import com.arnold.server.model.TrainHasJob;
import com.arnold.server.model.TrainParticipant;
import com.arnold.server.model.TrainSpeaker;
import com.arnold.server.model.Villager;
import com.arnold.server.service.BaseService;
import com.arnold.server.service.api.EducationParticipantService;
import com.arnold.server.service.api.EducationService;
import com.arnold.server.service.api.HelpService;
import com.arnold.server.service.api.ProjectHelpService;
import com.arnold.server.service.api.ProjectInvestHappenService;
import com.arnold.server.service.api.ProjectProgressService;
import com.arnold.server.service.api.ProjectService;
import com.arnold.server.service.api.PublicServiceService;
import com.arnold.server.service.api.TrainHasJobService;
import com.arnold.server.service.api.TrainParticipantService;
import com.arnold.server.service.api.TrainService;
import com.arnold.server.service.api.TrainSpeakerService;
import com.arnold.server.service.api.ValigerPostHappenService;
import com.arnold.server.service.api.VillagerService;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

/**
 * 
 * 
 * @description TODO
 */
public class ArnoldService1 extends BaseService {
	
	public Map<String, Object> pagePublicProject(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pagePublicProject(pageNumber, pageSize, ids);
			if(recordModel.getTotalRow()>0){
				for(Record record : recordModel.getList()){
					long endTime = record.getDate("endTime").getTime();
					long realEndTime = record.getDate("realEndTime").getTime();
					if(endTime == realEndTime){
						record.set("status", "按时完成");
					}else if(endTime>realEndTime){
						record.set("status", "提前完成");
					}else if(endTime<realEndTime){
						record.set("status", "未按期完成");
					}
				}
			}
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pagePublicProjectByKindAndKey(int pageNumber, int pageSize, String kind,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pagePublicProjectByKindAndKey(pageNumber, pageSize, kind, keyWord);
			if(recordModel.getTotalRow()>0){
				for(Record record : recordModel.getList()){
					long endTime = record.getDate("endTime").getTime();
					long realEndTime = record.getDate("realEndTime").getTime();
					if(endTime == realEndTime){
						record.set("status", "按时完成");
					}else if(endTime>realEndTime){
						record.set("status", "提前完成");
					}else if(endTime<realEndTime){
						record.set("status", "未按期完成");
					}
				}
			}
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pagePublicProjectByKind(int pageNumber, int pageSize, Integer kind){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pagePublicProjectByKind(pageNumber, pageSize, kind);
		
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
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
	@Deprecated
	public Map<String, Object> queryPublicProjectById(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Record recordModel  = arnold1.queryPublicProjectById(id);
		
			resultMap.put(ConstUtils.R_MODEL, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addPublicProject(String name, String cotentAndArea, String kind, String natureId,
			String villageName, Double fundation, Double departmentMoney, Double selfMoney, int year, String startTime,
			String realStartTime, String endTime, String realEndTime, String writer, String nowWriter){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
//			ProjectService projectService = new ProjectService();
//			projectService.addProject(name, typeId, finalMoney, 
//															year, startTime, endTime, realStartTime, realEndTime, 
//															kind, natureId, personCount, povertyCount);
			PublicServiceService publicServiceService = new PublicServiceService();
			int result = publicServiceService.addPublicService(name, cotentAndArea, kind, natureId, villageName,
					fundation, departmentMoney, selfMoney, year, startTime, 
					realStartTime, endTime, realEndTime,writer,nowWriter);
			if(result == 0){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
				resultMap.put(ConstUtils.R_MSG, "增加成功!");
			}
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> updatePublicProject(String id,String name, String cotentAndArea, String kind, String natureId, String villageName,
			Double fundation, Double departmentMoney, Double selfMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter,int finalMoney,int povertyCount,
			int personCount,int montheMoney,int completeMoney,float allProgress){
		PublicServiceService publicServiceService = new PublicServiceService();
		Map<String, Object> resultMap =  publicServiceService.updatePublicService(id, name, cotentAndArea, kind, natureId, villageName,
				fundation, departmentMoney, selfMoney, year, startTime, 
				realStartTime, endTime, realEndTime, writer, nowWriter,finalMoney,povertyCount,personCount,montheMoney,completeMoney,allProgress);
		return resultMap;
	}
	
	public Map<String, Object> pageProjectInvestByProjectid(int pageNumber, int pageSize, 
			String projectid){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
			Page<ProjectInvestHappen> recordModel = projectInvestHappenService
					.pageProjectInvestByProjectid(pageNumber, pageSize, projectid);
			
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageProjectInvestByid(int pageNumber, int pageSize, 
			String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
			Page<ProjectInvestHappen> recordModel = projectInvestHappenService
					.pageProjectInvestByid(pageNumber, pageSize, id);
			
			resultMap.put(ConstUtils.R_MODEL, recordModel.getList().get(0));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addProjectInvest(String projectid, double investPrice, String investTypeId, Date investDate,
			String company, String  writer, String nowWriter, String attachment){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
		
			projectInvestHappenService.addProjectInvest(projectid, investPrice, 
					investTypeId, investDate, company, writer, nowWriter, attachment);
			
//			PublicServiceService publicServiceService = new PublicServiceService();
//			publicServiceService.updateProjectInvest(projectid);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> updateProjectInvest(String id, String projectid, double investPrice,
			String investTypeId, Date investDate, String company, String nowWriter, String attachment) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
			
			ProjectInvestHappen model = ProjectInvestHappen.dao.findById(id);
			
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个投资明细！");
				return resultMap;
			}
			
			projectInvestHappenService.updateProjectInvest(id, projectid, investPrice, 
					investTypeId, investDate, company, nowWriter, attachment,model);
//			PublicServiceService publicServiceService = new PublicServiceService();
//			publicServiceService.updateProjectInvest(projectid);
//			if(!model.getProjectId().equals(projectid)){
//				publicServiceService.updateProjectInvest(model.getProjectId());
//			}
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> delProjectInvest(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
			ProjectInvestHappen model = ProjectInvestHappen.dao.findById(id);
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个投资明细！");
				return resultMap;
			}
			projectInvestHappenService.delProjectInvest(id,model);
//			PublicServiceService publicServiceService = new PublicServiceService();
//			publicServiceService.updateProjectInvest(model.getProjectId());
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> pageProjectProgressByProjectid(int pageNumber, int pageSize,
			String projectid) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectProgressService publicProjectProgressService = new ProjectProgressService();
			Page<ProjectProgress> recordModel = publicProjectProgressService
					.pageProjectProgressByProjectid(pageNumber, pageSize, projectid);
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> addProjectProgress(String projectid, float progress, String note,
			String writer, String time) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ProjectProgressService publicProjectProgressService = new ProjectProgressService();
			float result = publicProjectProgressService.addProjectProgress(projectid, progress, 
					note, writer, time);
			if(result == 0){
				PublicServiceService publicServiceService = new PublicServiceService();
//				publicServiceService.updateProjectProgress(projectid);
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
				resultMap.put(ConstUtils.R_MSG, "增加成功!");
			}else{
				resultMap.put(ConstUtils.RETURN_CODE, result);
				resultMap.put(ConstUtils.R_MSG, "增加失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> pageHelpProject(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageHelpProject(pageNumber, pageSize, ids);
		
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageHelpProjectByKindAndKey(int pageNumber, int pageSize, String kind,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageHelpProjectByKindAndKey(pageNumber, pageSize, kind, keyWord);

			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageHelpProjectByKind(int pageNumber, int pageSize, Integer kind){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageHelpProjectByKind(pageNumber, pageSize, kind);
		
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}

	public Map<String, Object> addHelpProject(String name, String cotentAndArea, String kind, String helpType,
			String villageName, double fundation, double departmentMoney, double selfMoney, double finalMoney, int year, String startTime,
			String realStartTime, String endTime, String realEndTime, String writer, String nowWriter){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			ProjectHelpService helpProjectService = new ProjectHelpService();
			int result = helpProjectService.addHelpProject(name, cotentAndArea, kind, helpType, villageName,
					fundation, departmentMoney, selfMoney, finalMoney, year, startTime, 
					realStartTime, endTime, realEndTime,writer,nowWriter);
			if(result == 0){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
				resultMap.put(ConstUtils.R_MSG, "增加成功!");
			}
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> updateHelpProject(String id,String name, String cotentAndArea, String kind, String helpType, String villageName,
			double fundation, double departmentMoney, double selfMoney, int year, String startTime, String realStartTime,
			String endTime, String realEndTime, String writer, String nowWriter,double finalMoney,int povertyCount,
			int personCount,int montheMoney,int completeMoney,float allProgress){
		ProjectHelpService helpProjectService = new ProjectHelpService();
		Map<String, Object> resultMap =  helpProjectService.updateHelpProject(id, name, cotentAndArea, kind, helpType, villageName,
				fundation, departmentMoney, selfMoney, year, startTime, 
				realStartTime, endTime, realEndTime, writer, nowWriter,finalMoney,povertyCount,personCount,montheMoney,completeMoney,allProgress);
		return resultMap;
	}
	
	public Map<String, Object> pageCollectiveEconomic(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pagePublicProject(pageNumber, pageSize, ids);
		
			//resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageTrain(int pageNumber, int pageSize, String ids){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageTrain(pageNumber, pageSize, ids);
		
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageTrainBySkillTypeAndKey(int pageNumber, int pageSize, String skillTypeId,String keyWord){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageTrainBySkillTypeAndKey(pageNumber, pageSize, skillTypeId, keyWord);
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> getTrainByid(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			Arnold1 arnold1 = new Arnold1();
			Record model  = arnold1.getTrainByid(id);
		
			resultMap.put(ConstUtils.R_MODEL, model);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> addCollectiveEconomic(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			//Arnold1 arnold1 = new Arnold1();
			//Page<Record> recordModel  = arnold1.pagePublicProject(pageNumber, pageSize, ids);
		
			//resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}
	
	
	
	public Map<String, Object> addHelp(String name, String typeId, int finalMoney,
			int year, String startTime, String endTime, String realStartTime, String realEndTime,
			String kind, String natureId, int personCount, int povertyCount){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProjectService projectService = new ProjectService();
			projectService.addProject(name, typeId, finalMoney, year, startTime, endTime, realStartTime,
					realEndTime, kind, natureId, personCount, povertyCount);
			
			HelpService helpService = new HelpService();
			
			helpService.addHelp(year);
		
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}
	
	
	
	public Map<String, Object> updateHelp(String id, String name, String typeId, int finalMoney,
			int year, String startTime, String endTime, String realStartTime, String realEndTime,
			String kind, String natureId, int personCount, int povertyCount){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			ProjectService projectService = new ProjectService();
			projectService.addProject(name, typeId, finalMoney, year, startTime, endTime, realStartTime,
					realEndTime, kind, natureId, personCount, povertyCount);
			
			HelpService helpService = new HelpService();
			
			helpService.addHelp(year);
		
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> updateCollectiveEconomic(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			//Arnold1 arnold1 = new Arnold1();
			//Page<Record> recordModel  = arnold1.pagePublicProject(pageNumber, pageSize, ids);
		
			//resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "编辑成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "编辑失败!");
			
		}
		
		return resultMap;
	}
	
//	public Map<String, Object> updatePublicProjectInvest(String id, String projectId, String investTypeId,
//			Date investDate, String company, double investPrice, String  userId){
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		
//		try{
//			
//			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
//		
//			projectInvestHappenService.updateProjectInvestHappen(id, projectId, investTypeId, investDate, 
//																	company, investPrice, userId);
//			
//			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
//			resultMap.put(ConstUtils.R_MSG, "更新成功!");
//	
//		
//		}catch(Exception e){
//			
//			e.printStackTrace();
//			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
//			resultMap.put(ConstUtils.R_MSG, "更新失败!");
//			
//		}
//		
//		return resultMap;
//	}
//	
//	public Map<String, Object> pagePublicProjectInvest(int pageNumber, int pageSize, 
//													String projectId, String regionId){
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		
//		try{
//			
//			ProjectInvestHappenService projectInvestHappenService = new ProjectInvestHappenService();
//			Page<ProjectInvestHappen> recordModel  
//					= projectInvestHappenService.pageProjectInvestHappen(pageNumber, pageSize, projectId, regionId);
//		
//			resultMap.put(ConstUtils.R_PAGE, recordModel);
//			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
//			resultMap.put(ConstUtils.R_MSG, "查询成功!");
//	
//		
//		}catch(Exception e){
//			
//			e.printStackTrace();
//			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
//			resultMap.put(ConstUtils.R_MSG, "查询失败!");
//			
//		}
//		
//		return resultMap;
//	}
	
	/*public Map<String, Object> addTrain(String name, String typeId, String postId, String content, 
			int price, int finalMoney,
			Date startTime, Date endTime, String orgId, int count, String userId, String writer,
			String nowWriter, String skillTypeId, Date realStartTime, Date realEndTime, String address){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainService trainService = new TrainService();
			
			trainService.addTrain(name, typeId, postId, content, price, finalMoney, startTime, 
					endTime, orgId, count, userId, writer, nowWriter, skillTypeId, realStartTime, realEndTime, address);
		
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}*/

	/*public Map<String, Object> updateTrain(String id, String name, String typeId, String postId, String content, 
			int price, int finalMoney, Date startTime, Date endTime, String orgId, int count, String userId,
			String nowWriter, String skillTypeId, Date realStartTime, Date realEndTime, String address){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainService trainService = new TrainService();
			
			trainService.updateTrain(id, name, typeId, postId, content, price, finalMoney, startTime, 
					endTime, orgId, count, userId, nowWriter, skillTypeId, realStartTime, realEndTime, address);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
			
		}
		
		return resultMap;
	}*/
	
	public Map<String, Object> delTrain(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainService trainService = new TrainService();
			
			trainService.delTrain(id);
		
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
	
		
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);	
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageTrainSpeakerByTrainid(int pageNumber, int pageSize, 
			String trainid){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainSpeakerService trainSpeakerService = new TrainSpeakerService();
			Page<TrainSpeaker> recordModel = trainSpeakerService
					.pageTrainSpeakerByTrainid(pageNumber, pageSize, trainid);
			
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageTrainSpeaker(int pageNumber, int pageSize, 
			String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainSpeakerService trainSpeakerService = new TrainSpeakerService();
			Page<TrainSpeaker> recordModel = trainSpeakerService
					.pageTrainSpeaker(pageNumber, pageSize, id);
			
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addTrainSpeaker(String trainid, String name, String sex, int age, String telephone, String company ,
			String position, String department, String address, String writer, String nowWriter){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainSpeakerService trainSpeakerService = new TrainSpeakerService();
		
			trainSpeakerService.addTrainSpeaker(trainid, name, sex, age, 
					telephone, company, position, department, address, 
					writer, nowWriter);
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> updateTrainSpeaker(String id, String name, String sex, int age, String telephone,
			String company, String position, String department, String address, String nowWriter) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainSpeakerService trainSpeakerService = new TrainSpeakerService();			
			trainSpeakerService.updateTrainSpeaker(id, name, sex, age, telephone, company, position, department, address, 
					nowWriter);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> delTrainSpeaker(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainSpeakerService trainSpeakerService = new TrainSpeakerService();			
			trainSpeakerService.delTrainSpeaker(id);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
		}
		return resultMap;
	}

	public Map<String, Object> pageTrainParticipantByTrainid(int pageNumber, int pageSize, 
			String trainid){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainParticipantService trainParticipantService = new TrainParticipantService();
			List<Record> recordModel = trainParticipantService
					.pageTrainParticipantByTrainid(pageNumber, pageSize, trainid);
			String sql = "SELECT count(id) FROM tb_train_participant ";
			if(trainid!=null&&!trainid.equals("")){
				sql += "WHERE trainid = '"+trainid+"'";
			}
			Record count = Db.findFirst(sql);
			Map<String,Object> map = new HashMap<>();
			map.put("list", recordModel);
			map.put("totalRow", count.getLong("count(id)"));
			resultMap.put(ConstUtils.R_PAGE, map);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageTrainParticipant(int pageNumber, int pageSize, 
			String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainParticipantService trainParticipantService = new TrainParticipantService();
			Page<TrainParticipant> recordModel = trainParticipantService
					.pageTrainParticipant(pageNumber, pageSize, id);
			
			resultMap.put(ConstUtils.R_MODEL, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addTrainParticipant(String trainid, String joinPersonid,
			String helpPersonid, String joinMoneyType, String writer, String nowWriter){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainParticipantService trainParticipantService = new TrainParticipantService();
			trainParticipantService.addTrainParticipant(trainid, joinPersonid, helpPersonid, joinMoneyType,
					writer, nowWriter);
			
			Db.update("UPDATE tb_train SET "
					+ "count = (SELECT count(id) FROM tb_train_participant WHERE trainid = '"+trainid+"'), "
					+ "finalMoney = (price*(SELECT count(id) FROM tb_train_participant WHERE trainid = '"+trainid+"')) "
					+ "WHERE id = '"+trainid+"'");
			
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> delTrainParticipant(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainParticipantService trainParticipantService = new TrainParticipantService();
			TrainParticipant model = new TrainParticipant();
			model.findById(id);
			trainParticipantService.delTrainParticipant(id);
			Db.update("UPDATE tb_train SET "
					+ "count = (SELECT count(id) FROM tb_train_participant WHERE trainid = '"+model.getTrainid()+"'), "
					+ "finalMoney = (price*(SELECT count(id) FROM tb_train_participant WHERE trainid = '"+model.getTrainid()+"')) "
					+ "WHERE id = '"+model.getTrainid()+"'");
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> pageTrainHasJobByTrainid(int pageNumber, int pageSize, 
			String trainid){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainHasJobService trainHasJobService = new TrainHasJobService();
			Page<TrainHasJob> recordModel = trainHasJobService
					.pageTrainHasJobByTrainid(pageNumber, pageSize, trainid);
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	
	public Map<String, Object> pageTrainHasJobByTrainDateAndPostId(int pageNumber, int pageSize, 
			String startDate,String postId){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			TrainHasJobService trainHasJobService = new TrainHasJobService();
			Page<Record> recordModel = trainHasJobService.pageTrainHasJobByTrainDateAndPostId(pageNumber, pageSize,startDate,postId);
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	
	public Map<String, Object> pageTrainHasJob(int pageNumber, int pageSize, 
			String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			TrainHasJobService trainHasJobService = new TrainHasJobService();
			Page<TrainHasJob> recordModel = trainHasJobService
					.pageTrainHasJob(pageNumber, pageSize, id);
			
			resultMap.put(ConstUtils.R_MODEL, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageEducation(int pageNumber, int pageSize, String ids){

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
		
			EducationService educationService = new EducationService();
			
			Map<String, Object> recordModel  
					= educationService.pageEducationByIds(pageNumber, pageSize, ids);
			
			resultMap.put(ConstUtils.R_PAGE, recordModel.get(ConstUtils.R_PAGE));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		
		
		}catch(Exception e){
		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
		
		}
	
		return resultMap;
	}

	public Map<String, Object> pageEducationByTypeAndKey(int pageNumber, int pageSize, String typeid,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			EducationService educationService = new EducationService();
			
			Map<String, Object> recordModel  
			= educationService.pageEducationByTypeAndKey(pageNumber, pageSize, typeid, keyWord);
			
			resultMap.put(ConstUtils.R_PAGE, recordModel.get(ConstUtils.R_PAGE));
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addEducation(String typeId, String orgId, int number, String startTime, 
			String endTime, int personCount, String className, 
			String name, String major, String address, String writer, String nowWriter){

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
		
			EducationService educationService = new EducationService();
			
			educationService.addEducation(typeId, orgId, number, startTime, endTime, personCount, className, 
					 name, major, address, writer, nowWriter);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		
		
		}catch(Exception e){
		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		
		}
	
		return resultMap;
	}
	
	public Map<String, Object> updateEducation(String id, String typeId, String orgId, int number, 
			String startTime, String endTime, int personCount, String className, 
			String name, String major, String address, String nowWriter){

		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
		
			EducationService educationService = new EducationService();
			
			educationService.updateEducation(id, typeId, orgId, number, startTime, endTime, personCount, className, 
					name, major, address, nowWriter);

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "编辑成功!");
		
		
		}catch(Exception e){
		
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "编辑失败!");
		
		}
	
		return resultMap;
	}
	
	public Map<String, Object> delEducation(String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			EducationService educationService = new EducationService();
			
			educationService.delEducation(id);
		
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageEducationParticipantByEducationid(int pageNumber, int pageSize, 
			String educationid){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			EducationParticipantService educationParticipantService = new EducationParticipantService();
			List<Record> recordModel = educationParticipantService
					.pageEducationParticipantByEducationid(pageNumber, pageSize, educationid);
			String sql = "SELECT count(id) FROM tb_education_participant ";
			if(educationid!=null&&!educationid.equals("")){
				sql += "WHERE educationid = '"+educationid+"'";
			}
			Record count = Db.findFirst(sql);
			Map<String,Object> map = new HashMap<>();
			map.put("list", recordModel);
			map.put("totalRow", count.getLong("count(id)"));
			resultMap.put(ConstUtils.R_PAGE, map);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> pageEducationParticipant(int pageNumber, int pageSize, 
			String id){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			EducationParticipantService educationParticipantService = new EducationParticipantService();
			Page<EducationParticipant> recordModel = educationParticipantService
					.pageEducationParticipant(pageNumber, pageSize, id);
			
			resultMap.put(ConstUtils.R_MODEL, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
			
		}
		
		return resultMap;
	}
	
	public Map<String, Object> addEducationParticipant(String educationid, String joinPersonid,
			String helpPersonid,String joinMoneyType, String writer, String nowWriter){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			EducationParticipantService educationParticipantService = new EducationParticipantService();
			educationParticipantService.addEducationParticipant(educationid, joinPersonid, helpPersonid, joinMoneyType,
					writer, nowWriter);
			
			Db.update("UPDATE tb_education SET "
					+ "personCount = (SELECT count(id) FROM tb_education_participant WHERE educationid = '"+educationid+"') "
					+ "WHERE id = '"+educationid+"'");
			
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "增加成功!");
		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "增加失败!");
		}
		return resultMap;
	}
	
	public Map<String, Object> delEducationParticipant(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			EducationParticipantService educationParticipantService = new EducationParticipantService();
			EducationParticipant model = new EducationParticipant();
			String educationid = model.findById(id).getEducationid();
			educationParticipantService.delEducationParticipant(id);
			Db.update("UPDATE tb_education SET "
					+ "personCount = (SELECT count(id) FROM tb_education_participant WHERE educationid = '"+educationid+"') "
					+ "WHERE id = '"+educationid+"'");
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
	public Map<String, Object> pageValliager(int pageNumber, int pageSize, String typeId,String postId,int isWorking,String keyWord){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
			
			Arnold1 arnold1 = new Arnold1();
			Page<Record> recordModel  = arnold1.pageEmploymentValliager(pageNumber, pageSize, typeId,postId,isWorking,keyWord);
		
			resultMap.put(ConstUtils.R_PAGE, recordModel);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");
	
		
		}catch(Exception e){
			
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");
		}
		
		return resultMap;
	}
	/**
	 * @Description: 添加就业人员
	 * @author Li Bangming;
	 * @date 2017年8月15日 下午2:56:19
	 * @return
	 */
	public Map<String, Object>  addEmploymenValliager(String villagerId,String  postId,String ralationTypeId,String  memberId,
			Double  salary, String welfare,String userId) {
		VillagerService villagerService=new VillagerService();
		Villager villager=villagerService.queryValligerById(villagerId);
		Map<String, Object>  resultMap=new HashMap<String, Object>();
		
		if(villager==null){
			resultMap.put(ConstUtils.RETURN_CODE, ArnoldUtils.NOT_FIND_ERRO);
			resultMap.put(ConstUtils.R_MSG, ArnoldUtils.NOT_FIND_ERROR_STR);
			return resultMap;
		}
		villager.setIsWorking(1);
		villager.update();
		
		//保存流水
		ValigerPostHappenService valigerPostHappenService=new ValigerPostHappenService();
		//valigerPostHappenService.addVillagerPostHappen(villagerId, postId, memberId, ralationTypeId, salary, welfare, userId);
		
		return resultMap;

	}

	public Page<Record> pageHelpInfo(int pageNumber, int pageSize, String type, String keyWord) {
		String select = "SELECT i.*,m.name AS createUserName FROM team_info i LEFT JOIN tb_member m ON m.id = i.createUserId WHERE 1=1 ";
		String sqlExceptSelect = "";
		if(type!=null&&!type.equals("")&&!type.equals("0")){
			select = select + " AND i.informationFillingTypeId = '"+type+"' ";
		}
		if(keyWord!=null&&!keyWord.equals("")){
			select = select + " AND m.name = '"+keyWord+"' ";
		}
		Page<Record> results = Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
		return results;
	}

	public Page<Record> pageHelpInfoDetail(int pageNumber, int pageSize, String type, String createUserId, String keyWord) {
		String select = "SELECT d.*,m.name AS createUserName FROM team_info_detail d "+ 
					    "LEFT JOIN tb_member m ON m.id = d.createUserId WHERE 1=1 AND d.informationFillingTypeId = '"+type+"' "+
//					    "LEFT JOIN team_info i ON d.createUserId = i.createUserId AND i.informationFillingTypeId = d.informationFillingTypeId "+
						"AND d.createUserId = '"+createUserId+"' ";
		String sqlExceptSelect = "";
		if(keyWord!=null&&!keyWord.equals("")){
			select = select + " AND d.title LIKE '%"+keyWord+"%' ";
		}
		Page<Record> results = Db.paginate(pageNumber, pageSize, select, sqlExceptSelect);
		return results;
	}
	
	public Page<Record> pageHelpInfoDetailByUser(int pageNumber, int pageSize, String createUserId, String keyWord) {
//		String select = "SELECT d.*,m.name AS createUserName FROM team_info_detail d "+ 
//					    "LEFT JOIN tb_member m ON m.id = d.createUserId WHERE 1=1 AND d.createUserId = '"+createUserId+"' ";
//		String sqlExceptSelect = "";
		String select = "SELECT d.*,m.name AS createUserName ";
		StringBuffer sqlExceptSelect = new StringBuffer(" FROM team_info_detail d "+ 
			    "LEFT JOIN tb_member m ON m.id = d.createUserId WHERE 1=1 AND d.createUserId = '"+createUserId+"' ");
		
		if(keyWord!=null&&!keyWord.equals("")){
//			select = select + " AND d.title LIKE '%"+keyWord+"%' ";
			sqlExceptSelect.append(" AND d.title LIKE '%"+keyWord+"%' ");
		}
		Page<Record> results = Db.paginate(pageNumber, pageSize, select, sqlExceptSelect.toString());
		System.out.println(select+"信息填报+++"+results);
		return results;
	}

	public int addHelpInfo(String type, String user,String title,String content,String attachment) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String islq1 = "INSERT INTO team_info_detail (id,createUserId,informationFillingTypeId,title,content,attachment,createTime) VALUES  "+ 
				"('"+Utils.create36UUID()+"','"+user+"','"+type+"','"+title+"','"+content+"','"+attachment+"','"+formatter.format(new Date())+"')";
		Db.update(islq1);
		
		String sql = "SELECT * FROM team_info i WHERE 1=1 AND i.createUserId = '"+user+
						"' AND i.informationFillingTypeId = '"+type+"'";
		Record old = Db.findFirst(sql);
		int num = 0;
		if(old!=null){
			num = (Integer)old.get("uploadNum")+1;
			String uslq = "UPDATE team_info SET uploadNum = "+num+",uploadTime = '"+ 
					formatter.format(new Date())+"' WHERE createUserId = '"+user+
						"' AND informationFillingTypeId = '"+type+"'";
			return Db.update(uslq);
		}else{
			String islq = "INSERT INTO team_info (id,createUserId,informationFillingTypeId,uploadNum,uploadTime) VALUES  "+ 
					"('"+Utils.create36UUID()+"','"+user+"','"+type+"',1,'"+formatter.format(new Date())+"')";
			return Db.update(islq);
		}
		
	}

	public int updateHelpInfo(String id, String title, String content, String attachment) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String uslq = "UPDATE team_info_detail SET title = '"+title+"',content = '"+content+"',createTime='"+ 
				formatter.format(new Date())+"',attachment='"+attachment+"' WHERE id = '"+id+"'";
		return Db.update(uslq);
	}

	public int delHelpInfo(String id) {
		String uslq = "DELETE FROM team_info_detail WHERE id = '"+id+"'";
		return Db.update(uslq);
	}

}
