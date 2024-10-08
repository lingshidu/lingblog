package cn.ling.service;

import cn.ling.entity.CityVisitor;

import java.util.List;
import java.util.Map;

public interface DashboardService {
	/**
	 * 今日访问总量
	 * @return int
	 */
	int countVisitLogByToday();

	/**
	 * 查询博客总数
	 * @return int
	 */
	int getBlogCount();

	/**
	 * 获取评论总数
	 * @return int
	 */
	int getCommentCount();

	/**
	 * 获取分类总数
	 * @return Map<String, Object>
	 */
	Map<String, Object> getCategoryBlogCountMap();

	/**
	 * 获取标签下总数
	 * @return Map<String, Object>
	 */
	Map<String, Object> getTagBlogCountMap();

	/**
	 * 获取访客记录
	 * @return Map<String, Object>
	 */
	Map<String, Object> getVisitRecordMap();

	/**
	 * 获取城市访问list
	 * @return Map<String, List>
	 */
	List<CityVisitor> getCityVisitorList();
}
