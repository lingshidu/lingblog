package cn.ling.util;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * 邮件工具类
 */
@Component
@EnableAsync
public class MailUtils {
	private final JavaMailSender javaMailSender;
	private final MailProperties mailProperties;
	private final TemplateEngine templateEngine;

	public MailUtils(JavaMailSender javaMailSender, MailProperties mailProperties, TemplateEngine templateEngine) {
		this.javaMailSender = javaMailSender;
		this.mailProperties = mailProperties;
		this.templateEngine = templateEngine;
	}

	@Async
	public void sendHtmlTemplateMail(Map<String, Object> map, String toAccount, String subject, String template) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			Context context = new Context();
			context.setVariables(map);
			String process = templateEngine.process(template, context);
			messageHelper.setFrom(mailProperties.getUsername());
			messageHelper.setTo(toAccount);
			messageHelper.setSubject(subject);
			messageHelper.setText(process, true);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
