package com.developer.nefarious.zjoule.chat.openai;

import java.io.IOException;
import java.util.List;

public interface IOpenIAIClient {
	
	public String chatCompletion(final List<OpenAIMessage> messages) throws IOException, InterruptedException;

}
