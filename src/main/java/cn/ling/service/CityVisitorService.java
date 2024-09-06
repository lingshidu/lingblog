package cn.ling.service;

import cn.ling.entity.CityVisitor;


public interface CityVisitorService {
	/**
	 * 添加访问记录
	 * @param cityVisitor cityVisitor
	 */
	void saveCityVisitor(CityVisitor cityVisitor);
}
