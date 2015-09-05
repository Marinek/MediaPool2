package de.mediapool.server.core.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

	NULL_VALUE(1000, ""),
	
	EXTERN_API_CALL(5000, "Der Aufruf einer externen API ist fehlgeschlagen!");
	
	private String message;
	
	private Integer code;
	
	private ExceptionCode (Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] - %s", code, message);
	}
}
