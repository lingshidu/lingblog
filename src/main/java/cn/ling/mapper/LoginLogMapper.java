package cn.ling.mapper;

import cn.ling.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登录日志持久层接口
 */
@Mapper
@Repository
public interface LoginLogMapper {
	/**
	 * 查询日志
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return List<LoginLog>
	 */
	List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

	/**
	 * 保存登录日志
	 * @param log log
	 * @return int
	 */
	int saveLoginLog(LoginLog log);

	/**
	 * 根据id删除登录日志
	 * @param id id
	 * @return int
	 */
	int deleteLoginLogById(Long id);
}
