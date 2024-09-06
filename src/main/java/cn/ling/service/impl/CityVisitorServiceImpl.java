package cn.ling.service.impl;

import cn.ling.entity.CityVisitor;
import cn.ling.mapper.CityVisitorMapper;
import cn.ling.service.CityVisitorService;
import org.springframework.stereotype.Service;

/**
 * 城市访客数量统计业务层实现
 */
@Service
public class CityVisitorServiceImpl implements CityVisitorService {
	private final CityVisitorMapper cityVisitorMapper;

	public CityVisitorServiceImpl(CityVisitorMapper cityVisitorMapper) {
		this.cityVisitorMapper = cityVisitorMapper;
	}

	@Override
	public void saveCityVisitor(CityVisitor cityVisitor) {
		cityVisitorMapper.saveCityVisitor(cityVisitor);
	}
}
