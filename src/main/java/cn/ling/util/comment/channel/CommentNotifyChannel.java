package cn.ling.util.comment.channel;

import cn.ling.enums.CommentPageEnum;
import cn.ling.model.dto.CommentDTO;

/**
 * 评论提醒方式
 */
public interface CommentNotifyChannel {
	/**
	 * 通过指定方式通知自己
	 *
	 * @param commentDTO 当前收到的评论
	 */
	void notifyMyself(CommentDTO commentDTO, CommentPageEnum commentPageEnum);
}
