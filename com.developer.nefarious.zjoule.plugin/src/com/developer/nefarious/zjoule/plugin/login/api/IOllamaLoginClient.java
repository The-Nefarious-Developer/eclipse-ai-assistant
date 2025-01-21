package com.developer.nefarious.zjoule.plugin.login.api;

import java.io.IOException;

public interface IOllamaLoginClient {
	
	GetOllamaModelsResponse getModels(final String endpoint) throws IOException, InterruptedException;
	
}
