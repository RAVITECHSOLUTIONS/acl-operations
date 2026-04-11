package com.acl.exceptions;

import lombok.Getter;

@Getter
public class InvalidSortParameterException extends RuntimeException {

	private final String propertyName;
	private final String entityName;

	public InvalidSortParameterException(String propertyName, Class<?> entityClass) {
		super("Invalid sort parameter:" + propertyName + " for entity " + entityClass.getSimpleName());
		this.propertyName = propertyName;
		this.entityName = entityClass.getSimpleName();
	}
}
