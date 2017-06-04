package errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends ServiceException {

	private static String defaultMessage = "Resource Not Found";

	public NotFoundException() {
		super(defaultMessage);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Integer code) {
		super(code);
	}

	public NotFoundException(String message, Integer code) {
		super(message, code);
	}
}
