package com.developer.nefarious.zjoule.auth;

public class ServiceKey {
	
	private ServiceUrls serviceurls;
	private String clientid;
    private String clientsecret;
    private String url;
    
	public String getServiceURL() {
		return serviceurls.getAI_API_URL() + "/v2";
	}
    
    public String getClientId() {
		return clientid;
	}
    
    public String getClientSecret() {
    	return clientsecret;
    }
	
    public String getTokenURL() {
    	return url + "/oauth/token";
    }
    
    public Boolean isValid() {
    	return serviceurls != null && 
    		serviceurls.getAI_API_URL() != null && !serviceurls.getAI_API_URL().isEmpty() &&
            clientid != null && !clientid.isEmpty() &&
            clientsecret != null && !clientsecret.isEmpty() &&
            url != null && !url.isEmpty();
    }

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