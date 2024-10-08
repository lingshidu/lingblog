package cn.ling.mapper;

import cn.ling.entity.Tag;
import cn.ling.model.vo.TagBlogCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客标签持久层接口
 */
@Mapper
@Repository
public interface TagMapper {
	/**
	 * 获取博客标签列表
	 * @return List<Tag>
	 */
	List<Tag> getTagList();

	/**
	 * 获取所有标签List不查询id
	 * @return List<Tag>
	 */
	List<Tag> getTagListNotId();

	/**
	 * 按博客id查询List
	 * @param blogId blogId
	 * @return List<Tag>
	 */
	List<Tag> getTagListByBlogId(Long blogId);

	/**
	 * 添加标签
	 * @param tag tag
	 * @return int
	 */
	int saveTag(Tag tag);

	/**
	 * 按id查询标签
	 * @param id id
	 * @return Tag
	 */
	Tag getTagById(Long id);

	/**
	 * 按name查询标签
	 * @param name name
	 * @return Tag
	 */
	Tag getTagByName(String name);

	/**
	 * 按id删除标签
	 * @param id id
	 * @return int
	 */
	int deleteTagById(Long id);

	/**
	 * 更新标签
	 * @param tag tag
	 * @return int
	 */
	int updateTag(Tag tag);

	/**
	 * 查询每个标签的博客数量
	 * @return List<TagBlogCountVO>
	 */
	List<TagBlogCountVO> getTagBlogCount();
}
