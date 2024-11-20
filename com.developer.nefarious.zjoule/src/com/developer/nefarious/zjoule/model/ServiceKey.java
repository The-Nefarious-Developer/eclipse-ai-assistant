package com.developer.nefarious.zjoule.model;

public class ServiceKey {
	
	private ServiceUrls serviceurls;
	private String clientid;
    private String clientsecret;
    private String url;
    
	public String getClientId() {
		return clientid;
	}
//	public void setClientId(String clientid) {
//		this.clientid = clientid;
//	}
//	public String getClientSecret() {
//		return clientsecret;
//	}
//	public void setClientSecret(String clientsecret) {
//		this.clientsecret = clientsecret;
//	}
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}

}

class ServiceUrls {
	
    private String AI_API_URL;

    public String getAI_API_URL() {
        return AI_API_URL;
    }

    public void setAI_API_URL(String AI_API_URL) {
        this.AI_API_URL = AI_API_URL;
    }
    
}