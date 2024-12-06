package com.developer.nefarious.zjoule.models;

public enum Role {

	USER("user"), 
	ASSISTANT("assistant"), 
	SYSTEM("system");

	private final String value;

	Role(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

}
