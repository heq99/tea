package qianghe.teasales.service;

public class TeaSalesException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TeaSalesException(String message) {
		super(message);
	}

	public TeaSalesException(String message, Throwable cause) {
		super(message, cause);
	}
}
