package com.boilerplate.boilerplateapi.exceptionhandler.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	// the type of the requested resource.
	private String resourceType;

	// The id of the requested resource.
	private Object identifier;

	public ResourceNotFoundException(Class resourceType, Object identifier) {
		this.resourceType = resourceType.getSimpleName();
		this.identifier = identifier;
	}
}
