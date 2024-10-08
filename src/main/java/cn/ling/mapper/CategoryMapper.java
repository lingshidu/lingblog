package cn.ling.mapper;

import cn.ling.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 博客分类持久层接口
 */
@Mapper
@Repository
public interface CategoryMapper {
	/**
	 * 获取博客分类列表
	 * @return List<Category>
	 */
	List<Category> getCategoryList();

	/**
	 * 获取分类名list
	 * @return List<Category>
	 */
	List<Category> getCategoryNameList();

	/**
	 * 保存分类
	 * @param category category
	 * @return int
	 */
	int saveCategory(Category category);

	/**
	 * 根据id获取分类信息
	 * @param id id
	 * @return Category
	 */
	Category getCategoryById(Long id);

	/**
	 * 根据名称获取分类信息
	 * @param name name
	 * @return Category
	 */
	Category getCategoryByName(String name);

	/**
	 * 根据id删除分类信息
	 * @param id id
	 * @return int
	 */
	int deleteCategoryById(Long id);

	/**
	 * 更新分类信息
	 * @param category category
	 * @return int
	 */
	int updateCategory(Category category);



}
