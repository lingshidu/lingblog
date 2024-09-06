package cn.ling.service.impl;

import cn.ling.constant.RedisKeyConstants;
import cn.ling.entity.Category;
import cn.ling.exception.NotFoundException;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.CategoryMapper;
import cn.ling.service.CategoryService;
import cn.ling.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 博客分类业务层实现
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryMapper categoryMapper;
	private final RedisService redisService;

	public CategoryServiceImpl(CategoryMapper categoryMapper, RedisService redisService) {
		this.categoryMapper = categoryMapper;
		this.redisService = redisService;
	}

	@Override
	public List<Category> getCategoryList() {
		return categoryMapper.getCategoryList();
	}

	@Override
	public List<Category> getCategoryNameList() {
		String redisKey = RedisKeyConstants.CATEGORY_NAME_LIST;
		List<Category> categoryListFromRedis = redisService.getListByValue(redisKey);
		if (categoryListFromRedis != null) {
			return categoryListFromRedis;
		}
		List<Category> categoryList = categoryMapper.getCategoryNameList();
		redisService.saveListToValue(redisKey, categoryList);
		return categoryList;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveCategory(Category category) {
		if (categoryMapper.saveCategory(category) != 1) {
			throw new PersistenceException("分类添加失败");
		}
		redisService.deleteCacheByKey(RedisKeyConstants.CATEGORY_NAME_LIST);
	}

	@Override
	public Category getCategoryById(Long id) {
		Category category = categoryMapper.getCategoryById(id);
		if (category == null) {
			throw new NotFoundException("分类不存在");
		}
		return category;
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryMapper.getCategoryByName(name);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteCategoryById(Long id) {
		if (categoryMapper.deleteCategoryById(id) != 1) {
			throw new PersistenceException("删除分类失败");
		}
		redisService.deleteCacheByKey(RedisKeyConstants.CATEGORY_NAME_LIST);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateCategory(Category category) {
		if (categoryMapper.updateCategory(category) != 1) {
			throw new PersistenceException("分类更新失败");
		}
		redisService.deleteCacheByKey(RedisKeyConstants.CATEGORY_NAME_LIST);
		//修改了分类名，可能有首页文章关联了分类，也要更新首页缓存
		redisService.deleteCacheByKey(RedisKeyConstants.HOME_BLOG_INFO_LIST);
	}



}
