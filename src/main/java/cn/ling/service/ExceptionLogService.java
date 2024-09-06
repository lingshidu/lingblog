package cn.ling.service;

import cn.ling.entity.ExceptionLog;

import java.util.List;


public interface ExceptionLogService {
	/**
	 * 查询日志
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return List<ExceptionLog>
	 */
	List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);


	/**
	 * 添加日志
	 * @param log log
	 */
	void saveExceptionLog(ExceptionLog log);

	/**
	 * 删除日志
	 * @param id id
	 */
	void deleteExceptionLogById(Long id);
}
