package cn.ling.exception;

/**
 * 持久化异常
 */

public class PersistenceException extends RuntimeException {

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}
