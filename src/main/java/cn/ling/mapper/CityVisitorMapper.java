package cn.ling.mapper;

import cn.ling.entity.CityVisitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 城市访客数量统计持久层接口
 */
@Mapper
@Repository
public interface CityVisitorMapper {
	/**
	 * 查询城市访客数
	 * @return List<CityVisitor>
	 */
	List<CityVisitor> getCityVisitorList();

	/**
	 * 添加访问记录
	 * @param cityVisitor cityVisitor
	 * @return int
	 */
	int saveCityVisitor(CityVisitor cityVisitor);
}
