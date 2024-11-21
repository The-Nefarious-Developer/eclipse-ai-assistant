package com.developer.nefarious.zjoule.auth;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private long expiresIn;

	@SerializedName("scope")
	private String scope;

	@SerializedName("jti")
	private String jti;

	// Additional field to store the time the token is received
	private Date receivedAt;
	
	public Boolean isValid() {
		if (receivedAt == null) {
            return false;
        }
        long currentTime = new Date().getTime();
        long expirationTime = receivedAt.getTime() + (expiresIn * 1000); // expiresIn is in seconds, convert to milliseconds
        return currentTime < expirationTime;
	}

	// Constructor to set receivedAt to current time when creating the object
	public AccessToken() {
		this.receivedAt = new Date();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public Date getReceivedAt() {
		return receivedAt;
	}

	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

}
