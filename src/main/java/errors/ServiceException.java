package errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

	private static String defaultMessage = "Service error";

	private String message;

	private int code;

	public ServiceException() {
		super(defaultMessage);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message) {
		super(message);
		this.message = message;
	}

	public ServiceException(Integer code) {
		this(defaultMessage, code);
	}

	public ServiceException(String message, Integer code) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	@Override
	public final String getMessage() {
		return message != null ? message : defaultMessage;
	}
}
