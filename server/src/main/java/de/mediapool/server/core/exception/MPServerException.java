package de.mediapool.server.core.exception;

public class MPServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ExceptionCode code;

	public MPServerException(ExceptionCode code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}
	
	public MPServerException (ExceptionCode code, String message) {
		this(code, message, null);
	}
	
	@Override
	public String getMessage() {
		return  code + ": " + super.getMessage();
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
