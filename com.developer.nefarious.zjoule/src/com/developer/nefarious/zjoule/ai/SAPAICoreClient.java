package com.developer.nefarious.zjoule.ai;

import java.net.http.HttpClient;
import java.util.ArrayList;

public class SAPAICoreClient implements ISAPAICoreClient{
	
	private HttpClient httpClient;
	
	public SAPAICoreClient() {
		httpClient = HttpClient.newHttpClient();
	}
	
	/*
	 * Constructor used for unit tests
	 */
	public SAPAICoreClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public ArrayList<String> getResourceGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getDeployments() {
		// TODO Auto-generated method stub
		return null;
	}

}
