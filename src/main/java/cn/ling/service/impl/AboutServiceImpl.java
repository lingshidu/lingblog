package cn.ling.service.impl;

import cn.ling.annotation.AopProxy;
import cn.ling.constant.RedisKeyConstants;
import cn.ling.entity.About;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.AboutMapper;
import cn.ling.service.AboutService;
import cn.ling.service.RedisService;
import cn.ling.util.markdown.MarkdownUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 关于我页面业务层实现
 */
@Service
public class AboutServiceImpl implements AboutService, AopProxy<AboutServiceImpl> {

	private final AboutMapper aboutMapper;
	private final RedisService redisService;

	public AboutServiceImpl(AboutMapper aboutMapper, RedisService redisService) {
		this.aboutMapper = aboutMapper;
		this.redisService = redisService;
	}

	@Override
	public Map<String, String> getAboutInfo() {
		String redisKey = RedisKeyConstants.ABOUT_INFO_MAP;
		Map<String, String> aboutInfoMapFromRedis = redisService.getMapByValue(redisKey);
		if (aboutInfoMapFromRedis != null) {
			return aboutInfoMapFromRedis;
		}
		List<About> abouts = aboutMapper.getList();
		Map<String, String> aboutInfoMap = new HashMap<>(16);
		for (About about : abouts) {
			if ("content".equals(about.getNameEn())) {
				about.setValue(MarkdownUtils.markdownToHtmlExtensions(about.getValue()));
			}
			aboutInfoMap.put(about.getNameEn(), about.getValue());
		}
		redisService.saveMapToValue(redisKey, aboutInfoMap);
		return aboutInfoMap;
	}

	@Override
	public Map<String, String> getAboutSetting() {

		List<About> abouts = aboutMapper.getList();

		Map<String, String> map = new HashMap<>(16);
		for (About about : abouts) {
			map.put(about.getNameEn(), about.getValue());
		}
		return map;
	}

	@Override
	public void updateAbout(Map<String, String> map) {
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			self().updateOneAbout(key, map.get(key));
		}
		deleteAboutRedisCache();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateOneAbout(String nameEn, String value) {
		if (aboutMapper.updateAbout(nameEn, value) != 1) {
			throw new PersistenceException("修改失败");
		}
	}

	@Override
	public boolean getAboutCommentEnabled() {
		String commentEnabledString = aboutMapper.getAboutCommentEnabled();
		return Boolean.parseBoolean(commentEnabledString);
	}

	/**
	 * 删除关于我页面缓存
	 */
	private void deleteAboutRedisCache() {
		redisService.deleteCacheByKey(RedisKeyConstants.ABOUT_INFO_MAP);
	}
}
