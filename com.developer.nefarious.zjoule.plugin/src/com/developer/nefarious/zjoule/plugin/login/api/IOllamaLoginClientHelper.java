package com.developer.nefarious.zjoule.plugin.login.api;

import java.net.URI;

public interface IOllamaLoginClientHelper {
	
	URI createUri(final String endpoint);
	
	GetOllamaModelsResponse parseOllamaModelsResponseToObject(final String responseBody);

}
