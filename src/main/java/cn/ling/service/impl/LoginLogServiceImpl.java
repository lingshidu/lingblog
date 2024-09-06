package cn.ling.service.impl;

import cn.ling.common.CommonService;
import cn.ling.entity.LoginLog;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.LoginLogMapper;
import cn.ling.model.temp.LogDTO;
import cn.ling.service.LoginLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 登录日志业务层实现
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
	private final LoginLogMapper loginLogMapper;
	private final CommonService commonService;


	public LoginLogServiceImpl(LoginLogMapper loginLogMapper, CommonService commonService) {
		this.loginLogMapper = loginLogMapper;
		this.commonService = commonService;
	}

	@Override
	public List<LoginLog> getLoginLogListByDate(String startDate, String endDate) {
		return loginLogMapper.getLoginLogListByDate(startDate, endDate);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	@Async
	public void saveLoginLog(LoginLog log) {
		LogDTO logDTO = commonService.saveLog(log.getIp(), log.getUserAgent());
		BeanUtils.copyProperties(logDTO, log);
		if (loginLogMapper.saveLoginLog(log) != 1) {
			throw new PersistenceException("日志添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteLoginLogById(Long id) {
		if (loginLogMapper.deleteLoginLogById(id) != 1) {
			throw new PersistenceException("删除日志失败");
		}
	}
}
