package cn.ling.service.impl;

import cn.ling.common.CommonService;
import cn.ling.entity.OperationLog;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.OperationLogMapper;
import cn.ling.model.temp.LogDTO;
import cn.ling.service.OperationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 操作日志业务层实现
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
	private final OperationLogMapper operationLogMapper;
	private final CommonService commonService;

	public OperationLogServiceImpl(OperationLogMapper operationLogMapper, CommonService commonService) {
		this.operationLogMapper = operationLogMapper;
		this.commonService = commonService;
	}

	@Override
	public List<OperationLog> getOperationLogListByDate(String startDate, String endDate) {
		return operationLogMapper.getOperationLogListByDate(startDate, endDate);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	@Async
	public void saveOperationLog(OperationLog log) {
		LogDTO logDTO = commonService.saveLog(log.getIp(), log.getUserAgent());
		BeanUtils.copyProperties(logDTO, log);
		if (operationLogMapper.saveOperationLog(log) != 1) {
			throw new PersistenceException("日志添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteOperationLogById(Long id) {
		if (operationLogMapper.deleteOperationLogById(id) != 1) {
			throw new PersistenceException("删除日志失败");
		}
	}
}
