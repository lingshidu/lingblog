package cn.ling.aspect;

import cn.ling.annotation.OperationLogger;
import cn.ling.annotation.VisitLogger;
import cn.ling.entity.ExceptionLog;
import cn.ling.service.ExceptionLogService;
import cn.ling.util.AopUtils;
import cn.ling.util.IpAddressUtils;
import cn.ling.util.JacksonUtils;
import cn.ling.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * AOP记录异常日志
 */
@Component
@Aspect
public class ExceptionLogAspect {
	private final ExceptionLogService exceptionLogService;

	public ExceptionLogAspect(ExceptionLogService exceptionLogService) {
		this.exceptionLogService = exceptionLogService;
	}

	/**
	 * 配置切入点
	 */
	@Pointcut("execution(* cn.ling.controller..*.*(..))")
	public void logPointcut() {
		// 切入点
	}

	@AfterThrowing(value = "logPointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Exception e) {
		ExceptionLog log = handleLog(joinPoint, e);
		exceptionLogService.saveExceptionLog(log);
	}

	/**
	 * 设置ExceptionLog对象属性
	 *
	 * @return ExceptionLog
	 */
	private ExceptionLog handleLog(JoinPoint joinPoint, Exception e) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
		String uri = request.getRequestURI();
		String method = request.getMethod();
		String ip = IpAddressUtils.getIpAddress(request);
		String userAgent = request.getHeader("User-Agent");
		//todo 使用swagger后，可以直接使用注解上的内容作为 ExceptionLog 的 description
		String description = getDescriptionFromAnnotations(joinPoint);
		String error = StringUtils.getStackTrace(e);
		ExceptionLog log = new ExceptionLog(uri, method, description, error, ip, userAgent);
		Map<String, Object> requestParams = AopUtils.getRequestParams(joinPoint);
		log.setParam(StringUtils.substring(JacksonUtils.writeValueAsString(requestParams), 0, 2000));
		return log;
	}

	private String getDescriptionFromAnnotations(JoinPoint joinPoint) {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		OperationLogger operationLogger = method.getAnnotation(OperationLogger.class);
		if (operationLogger != null) {
			return operationLogger.value();
		}
		VisitLogger visitLogger = method.getAnnotation(VisitLogger.class);
		if (visitLogger != null) {
			return visitLogger.value().getBehavior();
		}
		return "";
	}
}