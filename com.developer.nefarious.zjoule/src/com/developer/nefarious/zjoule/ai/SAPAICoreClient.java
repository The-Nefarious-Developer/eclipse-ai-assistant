package com.developer.nefarious.zjoule.ai;

import java.net.http.HttpClient;
import java.util.ArrayList;

public class SAPAICoreClient implements ISAPAICoreClient{
	
	private HttpClient httpClient;
	
	public SAPAICoreClient() {
		httpClient = HttpClient.newHttpClient();
	}

	@Override
	public ArrayList<String> getResourceGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
