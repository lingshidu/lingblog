package cn.ling.util.comment.channel;

import cn.ling.constant.CommentConstants;
import cn.ling.exception.NotFoundException;
import cn.ling.util.common.SpringContextUtils;

/**
 * 评论提醒方式
 */
public class ChannelFactory {
	private ChannelFactory(){}
	/**
	 * 创建评论提醒方式
	 *
	 * @param channelName 方式名称
	 * @return CommentNotifyChannel
	 */
	public static CommentNotifyChannel getChannel(String channelName) {
		if (CommentConstants.TELEGRAM.equalsIgnoreCase(channelName)) {
			return SpringContextUtils.getBean("telegramChannel", CommentNotifyChannel.class);
		} else if (CommentConstants.MAIL.equalsIgnoreCase(channelName)) {
			return SpringContextUtils.getBean("mailChannel", CommentNotifyChannel.class);
		}
		throw new NotFoundException("Unsupported value in [application.yml]: [comment.notify.channel]");
	}
}
