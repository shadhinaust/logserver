package com.dovetail.logserver.log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
	private String message;
	private Exception cause;
	private Object[] messageParameters;

	public static Message msg(String message, Object... parameters) {
		return msg(null, message, parameters);
	}

	public static Message msg(Exception cause, String message, Object... parameters) {
		Message result = new Message();
		result.setCause(cause);
		result.setMessage(message);
		result.setMessageParameters(parameters);
		return result;
	}

	public String getFormatedMessage() {
		return String.format(message, messageParameters);
	}
}
