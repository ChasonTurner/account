package com.arnold.server.service.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.arnold.server.bean.request.RequestAddTrain;
import com.arnold.server.bean.request.RequestUpdateTrain;
import com.arnold.server.model.Train;
import com.arnold.server.service.BaseService;
import com.arnold.server.util.ErrorCodeConst;
import com.huntersun.tool.ConstUtils;
import com.huntersun.tool.Utils;
import com.jfinal.plugin.activerecord.Page;

public class TrainService extends BaseService {

	/*public Map<String, Object> addTrain(String name, String typeId, String postId, String content, int price,
			int finalMoney, Date startTime, Date endTime, String orgId, int count, String userId, String writer,
			String nowWriter, String skillTypeId, Date realStartTime, Date realEndTime, String address) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Train trainModel = new Train();
		trainModel.setId(Utils.create36UUID());
		trainModel.setName(name);
		trainModel.setTypeId(typeId);
		trainModel.setPostId(postId);
		trainModel.setContent(content);
		if(-1 == price) price =0;
		trainModel.setPrice(price);
		
		if(-1 == finalMoney) finalMoney =0;
		
		trainModel.setFinalMoney(finalMoney);

		trainModel.setStartTime(startTime);
		trainModel.setEndTime(endTime);

		trainModel.setOrgId(orgId);
		trainModel.setCount(count);
		trainModel.setUserId(userId);
		trainModel.setWriter(writer);
		trainModel.setCreateTime(new Date());
		trainModel.setNowWriter(nowWriter);
		trainModel.setNowCreateTime(new Date());
		trainModel.setSkillTypeId(skillTypeId);
		trainModel.setRealStartTime(realStartTime);
		trainModel.setRealEndTime(realEndTime);
		trainModel.setAddress(address);

		trainModel.save();

		resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}*/
	
	public Map<String, Object> addTrain(RequestAddTrain pAddTrain) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Train trainModel = new Train();
		trainModel.setId(Utils.create36UUID());
		trainModel.setName(pAddTrain.getName());
		trainModel.setTypeId(pAddTrain.getTypeId());
		trainModel.setPostId(pAddTrain.getPostId());
		trainModel.setContent(pAddTrain.getContent());
		if(null == pAddTrain.getPrice()) 
			pAddTrain.setPrice(0d);
		
		trainModel.setPrice(pAddTrain.getPrice());
		
		if(null == pAddTrain.getFinalMoney()) 
			pAddTrain.setFinalMoney(0d);;
		
		trainModel.setFinalMoney(pAddTrain.getFinalMoney());

		trainModel.setStartTime(pAddTrain.getStartTime());
		trainModel.setEndTime(pAddTrain.getEndTime());

		trainModel.setOrgId(pAddTrain.getOrgId());
		trainModel.setCount(pAddTrain.getCount());
		trainModel.setUserId(pAddTrain.getUserId());
		trainModel.setWriter(pAddTrain.getWriter());
		trainModel.setCreateTime(new Date());
		trainModel.setNowWriter(pAddTrain.getNowWriter());
		trainModel.setNowCreateTime(new Date());
		trainModel.setSkillTypeId(pAddTrain.getSkillTypeId());
		trainModel.setRealStartTime(pAddTrain.getRealStartTime());
		trainModel.setRealEndTime(pAddTrain.getRealEndTime());
		trainModel.setAddress(pAddTrain.getAddress());

		trainModel.save();
		
		resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_PAGE, ConstUtils.DEAL_SUCCESS);
		resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);

		return resultMap;
	}

	public Map<String, Object> pageTrain(int pageNumber, int pageSize) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Train train = new Train();

			Page<Train> page = train.pageTrain(pageNumber, pageSize);

			resultMap.put(ConstUtils.R_PAGE, page);
			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

	public Map<String, Object> pageTrainByIds(int pageNumber, int pageSize, String ids) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Train train = new Train();

			Page<Train> trainModel = train.pageTrainByIds(pageNumber, pageSize, ids);

			resultMap.put(ConstUtils.R_PAGE, trainModel);
			resultMap.put(ConstUtils.R_MSG, "查询成功!");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "查询失败!");

		}

		return resultMap;
	}

	public Map<String, Object> updateTrain(RequestUpdateTrain pUpdateTrain) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {

			Train trainModel = Train.dao.findById(pUpdateTrain.getId());

			if (trainModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, "没有这个培训！");
				return resultMap;
			}

			if (!Utils.isBlankOrEmpty(pUpdateTrain.getName())) {
				trainModel.setName(pUpdateTrain.getName());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getTypeId())) {
				trainModel.setTypeId(pUpdateTrain.getTypeId());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getPostId())) {
				trainModel.setPostId(pUpdateTrain.getPostId());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getContent())) {
				trainModel.setContent(pUpdateTrain.getContent());
			}
			if (null == pUpdateTrain.getPrice())
				pUpdateTrain.setPrice(0d);;

			trainModel.setPrice(pUpdateTrain.getPrice());
			if (null == pUpdateTrain.getFinalMoney())
				pUpdateTrain.setFinalMoney(0d);;
			
			trainModel.setFinalMoney(pUpdateTrain.getFinalMoney());

			if (pUpdateTrain.getStartTime() != null) {
				trainModel.setStartTime(pUpdateTrain.getStartTime());
			}
			if (pUpdateTrain.getEndTime() != null) {
				trainModel.setEndTime(pUpdateTrain.getEndTime());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getOrgId())) {
				trainModel.setOrgId(pUpdateTrain.getOrgId());
			}
			if (null != pUpdateTrain.getCount()) {
				trainModel.setCount(pUpdateTrain.getCount());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getUserId())) {
				trainModel.setUserId(pUpdateTrain.getUserId());
			}
			trainModel.setCreateTime(new Date());

			if (!Utils.isBlankOrEmpty(pUpdateTrain.getNowWriter())) {
				trainModel.setNowWriter(pUpdateTrain.getNowWriter());
			}

			trainModel.setNowCreateTime(new Date());

			if (!Utils.isBlankOrEmpty(pUpdateTrain.getSkillTypeId())) {
				trainModel.setSkillTypeId(pUpdateTrain.getSkillTypeId());
			}
			if (pUpdateTrain.getRealStartTime() != null) {
				trainModel.setRealStartTime(pUpdateTrain.getRealStartTime());
			}
			if (pUpdateTrain.getRealEndTime() != null) {
				trainModel.setRealEndTime(pUpdateTrain.getRealEndTime());
			}
			if (!Utils.isBlankOrEmpty(pUpdateTrain.getAddress())) {
				trainModel.setAddress(pUpdateTrain.getAddress());
			}

			trainModel.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, "更新成功！");

		} catch (Exception e) {

			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, "更新失败！");

		}

		return resultMap;
	}

	public Map<String, Object> delTrain(String id) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			Train trainModel = Train.dao.findById(id);

			if (trainModel == null) {
				resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.PARAM_ERROR);
				resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.INFO_NOT_EXIST_CODE_STR);
				return resultMap;
			}

			// TODO:逻辑删除 0正常，-1删除
			trainModel.setIsValid(-1);
			trainModel.update();

			resultMap.put(ConstUtils.RETURN_CODE, ConstUtils.DEAL_SUCCESS);
			resultMap.put(ConstUtils.R_MSG, ConstUtils.DEAL_SUCCESS_STR);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ConstUtils.RETURN_CODE, ErrorCodeConst.SYSTEM_OUT);
			resultMap.put(ConstUtils.R_MSG, ErrorCodeConst.SYSTEM_OUT_STR);
		}

		return resultMap;
	}

}
