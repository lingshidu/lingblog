package cn.ling.exception;

/**
 * 非法请求异常
 */

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}




}
