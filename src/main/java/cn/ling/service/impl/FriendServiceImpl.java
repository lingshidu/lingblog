package cn.ling.service.impl;

import cn.ling.constant.RedisKeyConstants;
import cn.ling.entity.Friend;
import cn.ling.entity.SiteSetting;
import cn.ling.exception.PersistenceException;
import cn.ling.mapper.FriendMapper;
import cn.ling.mapper.SiteSettingMapper;
import cn.ling.model.dto.FriendDTO;
import cn.ling.model.vo.FriendInfoVO;
import cn.ling.model.vo.FriendVO;
import cn.ling.service.FriendService;
import cn.ling.service.RedisService;
import cn.ling.util.markdown.MarkdownUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 友链业务层实现
 */
@Service
public class FriendServiceImpl implements FriendService {
	private final FriendMapper friendMapper;
	private final SiteSettingMapper siteSettingMapper;
	private final RedisService redisService;

	public FriendServiceImpl(FriendMapper friendMapper, SiteSettingMapper siteSettingMapper, RedisService redisService) {
		this.friendMapper = friendMapper;
		this.siteSettingMapper = siteSettingMapper;
		this.redisService = redisService;
	}

	@Override
	public List<Friend> getFriendList() {
		return friendMapper.getFriendList();
	}

	@Override
	public List<FriendVO> getFriendVOList() {
		return friendMapper.getFriendVOList();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateFriendPublishedById(Long friendId, Boolean published) {
		if (friendMapper.updateFriendPublishedById(friendId, published) != 1) {
			throw new PersistenceException("操作失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveFriend(Friend friend) {
		friend.setViews(0);
		friend.setCreateTime(new Date());
		if (friendMapper.saveFriend(friend) != 1) {
			throw new PersistenceException("添加失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateFriend(FriendDTO friendDTO) {
		if (friendMapper.updateFriend(friendDTO) != 1) {
			throw new PersistenceException("修改失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void deleteFriend(Long id) {
		if (friendMapper.deleteFriend(id) != 1) {
			throw new PersistenceException("删除失败");
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateViewsByNickname(String nickname) {
		if (friendMapper.updateViewsByNickname(nickname) != 1) {
			throw new PersistenceException("操作失败");
		}
	}

	@Override
	public FriendInfoVO getFriendInfo(boolean cache, boolean md) {
		//友链页面信息key
		String redisKey = RedisKeyConstants.FRIEND_INFO_MAP;
		if (cache) {
			FriendInfoVO friendInfoVOFromRedis = redisService.getObjectByValue(redisKey, FriendInfoVO.class);
			if (friendInfoVOFromRedis != null) {
				return friendInfoVOFromRedis;
			}
		}
		List<SiteSetting> siteSettings = siteSettingMapper.getFriendInfo();
		FriendInfoVO friendInfoVO = new FriendInfoVO();
		for (SiteSetting siteSetting : siteSettings) {
			if ("friendContent".equals(siteSetting.getNameEn())) {
				if (md) {
					friendInfoVO.setContent(MarkdownUtils.markdownToHtmlExtensions(siteSetting.getValue()));
				} else {
					friendInfoVO.setContent(siteSetting.getValue());
				}
			} else if ("friendCommentEnabled".equals(siteSetting.getNameEn())) {
				friendInfoVO.setCommentEnabled("1".equals(siteSetting.getValue()));
			}
		}
		if (cache && md) {
			redisService.saveObjectToValue(redisKey, friendInfoVO);
		}
		return friendInfoVO;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateFriendInfoContent(String content) {
		if (siteSettingMapper.updateFriendInfoContent(content) != 1) {
			throw new PersistenceException("修改失败");
		}
		deleteFriendInfoRedisCache();
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateFriendInfoCommentEnabled(Boolean commentEnabled) {
		if (siteSettingMapper.updateFriendInfoCommentEnabled(commentEnabled) != 1) {
			throw new PersistenceException("修改失败");
		}
		deleteFriendInfoRedisCache();
	}

	/**
	 * 删除友链页面缓存
	 */
	private void deleteFriendInfoRedisCache() {
		redisService.deleteCacheByKey(RedisKeyConstants.FRIEND_INFO_MAP);
	}
}
