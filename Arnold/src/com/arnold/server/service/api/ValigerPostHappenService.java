package com.arnold.server.service.api;

import com.arnold.server.bean.request.RequestDelPostVillagerRalationType;
import com.arnold.server.model.VillagerPostHappen;
import com.arnold.server.model.VillagerPostHappenBak;
import com.arnold.server.util.ArnoldUtils;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 村名岗位流水Service
 * @author Li Bangming;
 * @email:1715592561@qq.com
 * @date 2017年8月10日 下午8:26:45
 */
public class ValigerPostHappenService {
	/**
	 * @Description:保存村名岗位流水
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public int  addVillagerPostHappen(String valigerId,String postId,String memberId,Integer ralationType,
			Double salary,Double averageIncome,String companyProject,String jobCategory,Date postLeaveDate,String reason,
			String createUserId,Integer isEat,Integer isEatEncase,Integer isEncase,Integer isFiveFund,Integer isLunch,Integer isOther,
			String department,boolean isEdit,String jobAdress,String id){
		VillagerPostHappen  fsih=new VillagerPostHappen();
		if(!Utils.isBlankOrEmpty(id)) {
			fsih.setEditUserId(createUserId);
			fsih.setEditDate(new Date());
		}else {
			fsih.setCreateTime(new Date());
			fsih.setCreateUserId(createUserId);
			fsih.setEditUserId(createUserId);
			fsih.setEditDate(new Date());
		}
		id = Utils.isBlankOrEmpty(id)?Utils.create36UUID():"";
		fsih.setId(id);
		fsih.setVillagerId(valigerId);
		fsih.setPostId(postId);
		fsih.setMemberId(memberId);
		fsih.setReason(reason);
		fsih.setRalationType(ralationType);
		fsih.setPostLeaveDate(postLeaveDate);
		fsih.setSalary(salary);
		fsih.setDepartment(department);
		fsih.setAverageIncome(averageIncome);
		fsih.setCompanyProject(companyProject);
		fsih.setIsEat(isEat);
		fsih.setIsEatEncase(isEatEncase);
		fsih.setIsEncase(isEncase);
		fsih.setIsFiveFund(isFiveFund);
		fsih.setIsLunch(isLunch);
		fsih.setJobCategory(jobCategory);
		fsih.setIsOther(isOther);
		fsih.set("jobAddress", jobAdress);
		try {
			fsih.saveVillagerPostHappen(fsih);
		} catch (Exception e) {
			ArnoldUtils.logger.error(e.getLocalizedMessage());
			return ConstUtils.DATA_OPERATE_ERROR;
		}
		return ConstUtils.DEAL_SUCCESS;
	}
	
	/**
	 * @Description:分页查询
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public Page<VillagerPostHappen>  pageVillagerPostHappen(int pageNumber,int pageSize,String valigerId,String trainId){
		return VillagerPostHappen.pageVillagerPostHappen(pageNumber, pageSize, valigerId,trainId);
	}
	/**
	 * @author Li Bangming;
	 * @date 2017年8月10日 下午2:39:39
	 * @return
	 */
	public VillagerPostHappen  queryVillagerPostHappen(String valigerId){
		return VillagerPostHappen.queryLastVillagerPostHappenByVillagerId(valigerId);
	}
	
	public Page<VillagerPostHappen>  pageVillagerPostHappen(int pageNumber, int pageSize, String valigerId){
		return VillagerPostHappen.pageLastVillagerPostHappenByVillagerId(pageNumber, pageSize, valigerId);
	}

	public Page<Record> pagePostVillagerByPostIdJson(int pageNumber, int pageSize, String postId, int ralationType) {
		String select = "SELECT " +
				"vp.id," +
				"v.NAME AS vName," +
				"v.sexId," +
				"f.phone," +
				"v.birthday," +
				"v.IDnumber," +
				"v.raceId," +
				"v.educationalId," +
				" f.hamletId," +
				" f.ralationTypeId," +
				" m.name AS mName," +
				"vp.createUserId," +
				"vp.createTime," +
				"vp.editUserId," +
				" vp.editDate, "+
				" v.familyId , "+
				" vp.reason , "+
				" vp.ralationType ";
		String sqlExceptSelect = " FROM tb_villager_post_happen vp " +
				"LEFT JOIN tb_villager v ON v.id = vp.villagerId " +
				"LEFT JOIN tb_member m ON m.id = vp.memberId " +
				"LEFT JOIN tb_family f ON v.familyId = f.id WHERE vp.postId=? " ;

		if(ralationType==1){
			sqlExceptSelect+=" AND vp.ralationType IN(1,2,3) ";
		}else{
			sqlExceptSelect+=" AND vp.ralationType= "+ralationType;
		}

		sqlExceptSelect+=" ORDER BY vp.editDate desc ";
		return Db.paginate(pageNumber, pageSize, select, sqlExceptSelect ,postId);
	}


	public Map<String,Object> updatePostVillagerRalationType(String id, int ralationType, String reason, String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			VillagerPostHappen model = VillagerPostHappen.dao.findById(id);
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个申请人！");
				return resultMap;
			}
			model.setReason(reason);
			model.setRalationType(ralationType);
			model.setEditUserId(userId);
			model.setEditDate(new Date());


			model.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");

		}catch(Exception e){

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");

		}

		return resultMap;


	}
	
	public Map<String, Object> delPostVillagerRelationType(RequestDelPostVillagerRalationType requestBean) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		if(null==requestBean || null==requestBean.getHappenId() || requestBean.getHappenId().length()==0){
			resultMap.put(ConstUtils.R_MSG, "删除成功(未指定删除的就业信息)");
			return resultMap;
		}
		
		//新增备份表记录
		VillagerPostHappen historyInfo = VillagerPostHappen.dao.findById(requestBean.getHappenId());
		if(VillagerPostHappenBak.dao.saveBak(historyInfo)){
			
			//删除表记录
			if(VillagerPostHappen.dao.deleteById(requestBean.getHappenId())){
				
				resultMap.put(ConstUtils.R_MSG, "删除成功");
			}else{
				resultMap.put(ConstUtils.R_MSG, "已备份，但未成功删除就业信息，请联系管理员删除");
			}
			
		}else{
			resultMap.put(ConstUtils.R_MSG, "删除失败，请稍后重新尝试删除");
		}
		
		return resultMap;
	}
	
	public Map<String,Object> updatePostVillagerRalationType(String villagerId, String postId, String memberId,
			Integer ralationType, Double salary, Double averageIncome, String companyProject, String jobCategory,
			Date postLeaveDate, Object object, String userId, Integer isEat, Integer isEatEncase, Integer isEncase,
			Integer isFiveFund, Integer isLunch, Integer isOther, String department, Boolean isEdit, String jobAdress,
			String id, Boolean isTrain, String phone) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			VillagerPostHappen model = VillagerPostHappen.dao.findById(id);
			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个申请人！");
				return resultMap;
			}
//			model.setMemberId(memberId);
			model.setPostId(postId);
			model.setPostLeaveDate(postLeaveDate);
			model.setJobCategory(jobCategory);
			model.setCompanyProject(companyProject);
			model.setRalationType(ralationType);
			model.setDepartment(department);
			model.setSalary(averageIncome);
			model.setAverageIncome(averageIncome);
			model.setIsEat(isEat);
			model.setIsEatEncase(isEatEncase);
			model.setIsEncase(isEncase);
			model.setIsFiveFund(isFiveFund);
			model.setIsLunch(isLunch);
			model.setIsOther(isOther);
			model.setEditUserId(userId);
			model.setEditDate(new Date());
			model.set("jobAddress", jobAdress);

			model.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");

		}catch(Exception e){

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");

		}

		return resultMap;


	}

	public Map<String,Object> deletePostVillageById(String id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try{

			VillagerPostHappen model = VillagerPostHappen.dao.findById(id);

			if(model == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个申请人！");
				return resultMap;
			}

			//TODO:逻辑删除
			model.delete();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "删除成功!");

		}catch(Exception e){

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "删除失败!");

		}

		return resultMap;
	}
	
	public Map<String,Object> getVilagerPostHappen(String villagerId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			List<VillagerPostHappen> models = VillagerPostHappen.getVilagerPostHappen(villagerId);
			if(models == null){
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有数据");
				return resultMap;
			}
			resultMap.put(ConstUtils.R_LIST, models);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "获取成功");

		}catch(Exception e){
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "没有数据");
		}
		return resultMap;
	}
}

