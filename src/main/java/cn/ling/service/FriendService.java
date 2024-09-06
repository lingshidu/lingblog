package cn.ling.service;

import cn.ling.entity.Friend;
import cn.ling.model.dto.FriendDTO;
import cn.ling.model.vo.FriendInfoVO;
import cn.ling.model.vo.FriendVO;

import java.util.List;


public interface FriendService {
	/**
	 * 获取友链list
	 * @return List<Friend>
	 */
	List<Friend> getFriendList();

	/**
	 * 获取友链vo列表
	 * @return List<FriendVO>
	 */
	List<FriendVO> getFriendVOList();

	/**
	 * 更新可发版友链
	 * @param friendId friendId
	 * @param published published
	 */
	void updateFriendPublishedById(Long friendId, Boolean published);

	/**
	 * 保存友链信息
	 * @param friend friend
	 */
	void saveFriend(Friend friend);

	/**
	 * 更新友链信息
	 * @param friendDTO friendDTO
	 */
	void updateFriend(FriendDTO friendDTO);

	/**
	 * 删除友链信息
	 * @param id id
	 */
	void deleteFriend(Long id);

	/**
	 * 按昵称增加友链浏览次数
	 * @param nickname nickname
	 */
	void updateViewsByNickname(String nickname);

	/**
	 * 获取友链页面信息
	 * @param cache 是否开启缓存
	 * @param md cache
	 * @return FriendInfo
	 */
	FriendInfoVO getFriendInfo(boolean cache, boolean md);

	/**
	 * 修改友链页面信息
	 * @param content 具体内容
	 */
	void updateFriendInfoContent(String content);

	/**
	 * 修改友链页面评论开放状态
	 * @param commentEnabled commentEnabled
	 */
	void updateFriendInfoCommentEnabled(Boolean commentEnabled);
}
