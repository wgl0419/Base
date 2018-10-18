package com.alphawizard.hdwallet.alphahdwallet.data.entiry;

public class ServiceException extends Exception {
	public final ErrorEnvelope error;

	public ServiceException(String message) {
		super(message);

		error = new ErrorEnvelope(message);
	}
}
