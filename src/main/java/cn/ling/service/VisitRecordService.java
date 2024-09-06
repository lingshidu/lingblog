package cn.ling.service;

import cn.ling.entity.VisitRecord;


public interface VisitRecordService {
	/**
	 * 添加访问记录
	 * @param visitRecord visitRecord
	 */
	void saveVisitRecord(VisitRecord visitRecord);
}
