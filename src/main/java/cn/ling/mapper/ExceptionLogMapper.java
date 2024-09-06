package cn.ling.mapper;

import cn.ling.entity.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 异常日志持久层接口
 */
@Mapper
@Repository
public interface ExceptionLogMapper {
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
	 * @return int
	 */
	int saveExceptionLog(ExceptionLog log);

	/**
	 * 删除日志
	 * @param id id
	 * @return int
	 */
	int deleteExceptionLogById(Long id);
}
