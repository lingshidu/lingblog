package cn.ling.service;

import cn.ling.model.dto.CommentDTO;

import java.util.Map;

public interface MailService {
    /**
     * 发送邮件提醒我自己
     * @param commentDTO commentDTO
     */
    void sendMailToMe(CommentDTO commentDTO);

    /**
     * 发送邮件
     * @param map map
     * @param toAccount toAccount
     * @param subject subject
     * @param template template
     */
    void sendHtmlTemplateMail(Map<String, Object> map, String toAccount, String subject, String template);

}
