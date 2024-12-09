package com.developer.nefarious.zjoule.chat;

import java.io.IOException;
import java.util.List;

public interface IAIClient<T> {
	
	IMessage chatCompletion(final List<T> messages) throws IOException, InterruptedException;

}
