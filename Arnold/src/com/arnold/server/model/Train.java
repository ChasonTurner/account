package com.arnold.server.model;

import com.arnold.server.model.base.BaseTrain;
import com.jfinal.plugin.activerecord.Page;
import com.pallas.utils.Utils;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Train extends BaseTrain<Train> {
	public static final Train dao = new Train();

	public Page<Train> pageTrainByIds(int pageNumber, int pageSize, String ids) {

		String sql = "SELECT *";

		String sqlExceptSelect = " FROM tb_train t1 ";

		if (!Utils.isBlankOrEmpty(ids)) {

			String[] idStrArr = ids.split(",");
			int i = 0;
			for (String str : idStrArr) {
				if (i == 0) {
					sqlExceptSelect += " WHERE t1.id = '" + str + "'";
				} else {
					sqlExceptSelect += " OR t1.id = '" + str + "'";
				}
				i++;
				
			}

		}

		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect);

	}

	public Page<Train> pageTrain(int pageNumber, int pageSize) {
		String tableName = "tb_train";

		String sql = "select id,name,typeId,postId,content,price,finalMoney,startTime,endTime,orgId,count,userId,writer,createTime,nowWriter,nowCreateTime,skillTypeId,realStartTime,realEndTime,address,isValid";

		String sqlExceptSelect = " from " + tableName + " where isValid=?";

		return dao.paginate(pageNumber, pageSize, sql, sqlExceptSelect, 0);
	}

}