package cn.ling.mapper;

import cn.ling.entity.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客动态持久层接口
 */
@Mapper
@Repository
public interface MomentMapper {
	/**
	 * 查询动态List
	 * @return List<Moment>
	 */
	List<Moment> getMomentList();

	/**
	 * 给动态点赞
	 * @param momentId momentId
	 * @return int
	 */
	int addLikeByMomentId(Long momentId);

	/**
	 * 更新动态公开状态
	 * @param momentId 动态id
	 * @param published 是否公开
	 * @return int
	 */
	int updateMomentPublishedById(Long momentId, Boolean published);

	/**
	 * 根据id查询动态
	 * @param id 动态id
	 * @return Moment
	 */
	Moment getMomentById(Long id);

	/**
	 * 删除动态
	 * @param id 动态id
	 * @return int
	 */
	int deleteMomentById(Long id);

	/**
	 * 发布动态
	 * @param moment 动态实体
	 * @return int
	 */
	int saveMoment(Moment moment);

	/**
	 * 更新动态
	 * @param moment 动态实体
	 * @return int
	 */
	int updateMoment(Moment moment);
}
