package cn.ling.mapper;

import cn.ling.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关于我持久层接口
 */
@Mapper
@Repository
public interface AboutMapper {
	/**
	 * 获取关于我list
	 * @return List<About>
	 */
	List<About> getList();

	/**
	 * 更新关于我页面
	 * @param nameEn nameEn
	 * @param value value
	 * @return int
	 */
	int updateAbout(String nameEn, String value);

	/**
	 * 判断评论是否已关闭
	 * @return String
	 */
	String getAboutCommentEnabled();
}
