package cn.ling.handler;

import cn.ling.constant.CommonConstants;
import cn.ling.exception.NotFoundException;
import cn.ling.exception.PersistenceException;
import cn.ling.util.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 对Controller层全局异常处理,捕获异常后，返回json数据类型
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 捕获自定义的404异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return Result
	 */
	@ExceptionHandler(NotFoundException.class)
	public Result notFoundExceptionHandler(HttpServletRequest request, NotFoundException e) {
		logger.error(CommonConstants.REQUEST_URL, request.getRequestURL(), e);
		return Result.exception(404, e.getMessage());
	}

	/**
	 * 捕获自定义的持久化异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return Result
	 */
	@ExceptionHandler(PersistenceException.class)
	public Result persistenceExceptionHandler(HttpServletRequest request, PersistenceException e) {
		logger.error(CommonConstants.REQUEST_URL, request.getRequestURL(), e);
		return Result.exception(500, e.getMessage());
	}

	/**
	 * 捕获自定义的登录失败异常
	 *
	 * @param request 请求
	 * @param e       自定义抛出的异常信息
	 * @return Result
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public Result usernameNotFoundExceptionHandler(HttpServletRequest request, UsernameNotFoundException e) {
		logger.error(CommonConstants.REQUEST_URL, request.getRequestURL(), e);
		return Result.exception(401, "用户名或密码错误！");
	}

	/**
	 * 捕获其它异常
	 *
	 * @param request 请求
	 * @param e       异常信息
	 * @return Result
	 */
	@ExceptionHandler(Exception.class)
	public Result exceptionHandler(HttpServletRequest request, Exception e) {
		logger.error(CommonConstants.REQUEST_URL, request.getRequestURL(), e);
		return Result.exception(500, "异常错误");
	}
}
