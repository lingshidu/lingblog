package cn.ling.mapper;

import cn.ling.entity.Visitor;
import cn.ling.model.dto.VisitLogUuidTimeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 访客统计持久层接口
 */
@Mapper
@Repository
public interface VisitorMapper {
	/**
	 * 查询访客
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return List<Visitor>
	 */
	List<Visitor> getVisitorListByDate(String startDate, String endDate);

	/**
	 * 查询昨天的所有新增访客的ip来源
	 * @return List<String>
	 */
	List<String> getNewVisitorIpSourceByYesterday();

	/**
	 * 查询是否存在某个uuid
	 * @param uuid uuid
	 * @return int
	 */
	int hasUuid(String uuid);

	/**
	 * 添加访客
	 * @param visitor visitor
	 * @return int
	 */
	int saveVisitor(Visitor visitor);

	/**
	 * 更新访客pv和最后访问时间
	 * @param dto dto
	 * @return int
	 */
	int updatePvAndLastTimeByUuid(VisitLogUuidTimeDTO dto);

	/**
	 * 删除访客
	 * @param id id
	 * @return int
	 */
	int deleteVisitorById(Long id);
}
