package cn.ling.service.impl;

import cn.ling.common.CommonService;
import cn.ling.entity.ExceptionLog;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.ExceptionLogMapper;
import cn.ling.model.temp.LogDTO;
import cn.ling.service.ExceptionLogService;
import cn.ling.util.UserAgentUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 异常日志业务层实现
 */
@Service
public class ExceptionLogServiceImpl implements ExceptionLogService {
	private final ExceptionLogMapper exceptionLogMapper;
	private final UserAgentUtils userAgentUtils;
	private final CommonService commonService;

	public ExceptionLogServiceImpl(ExceptionLogMapper exceptionLogMapper, UserAgentUtils userAgentUtils, CommonService commonService) {
		this.exceptionLogMapper = exceptionLogMapper;
		this.userAgentUtils = userAgentUtils;
		this.commonService = commonService;
	}

	@Override
	public List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate) {
		return exceptionLogMapper.getExceptionLogListByDate(startDate, endDate);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	@Async
	public void saveExceptionLog(ExceptionLog log) {
		LogDTO logDTO = commonService.saveLog(log.getIp(), log.getUserAgent());
		BeanUtils.copyProperties(logDTO, log);
		if (exceptionLogMapper.saveExceptionLog(log) != 1) {
			throw new PersistenceException("日志添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteExceptionLogById(Long id) {
		if (exceptionLogMapper.deleteExceptionLogById(id) != 1) {
			throw new PersistenceException("删除日志失败");
		}
	}
}
