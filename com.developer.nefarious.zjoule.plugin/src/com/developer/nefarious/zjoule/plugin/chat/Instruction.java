package com.developer.nefarious.zjoule.plugin.chat;

public abstract class Instruction {
	
	private Instruction() { }
	
	private static final String SYSTEM_MESSAGE_TEXT = 
			"Act as a friendly and informative SAP ABAP " 
			+ "Developer expert, always considering the "
			+ "best practices and clean-code guidelines "
			+ "when providing answers.";
	
	public static String getMessage() {
		return SYSTEM_MESSAGE_TEXT;
	}

}
