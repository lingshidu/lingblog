package cn.ling.mapper;

import cn.ling.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 访问记录持久层接口
 */
@Mapper
@Repository
public interface VisitRecordMapper {
	/**
	 * 按天数查询访问记录
	 * @param limit limit
	 * @return List<VisitRecord>
	 */
	List<VisitRecord> getVisitRecordListByLimit(Integer limit);

	/**
	 * 添加访问记录
	 * @param visitRecord visitRecord
	 * @return int
	 */
	int saveVisitRecord(VisitRecord visitRecord);
}
