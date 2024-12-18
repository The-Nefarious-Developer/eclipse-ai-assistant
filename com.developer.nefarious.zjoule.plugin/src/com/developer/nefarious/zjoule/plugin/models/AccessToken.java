package com.developer.nefarious.zjoule.plugin.models;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * Represents an OAuth2 access token along with its metadata and expiration details.
 * <p>
 * The {@code AccessToken} class is used to store information about an OAuth2 access token, 
 * including the token string, its type, expiration time, scope, and additional metadata.
 * It also tracks the time the token was received, allowing for validation of its validity 
 * based on the expiration time.
 * </p>
 */
public class AccessToken {

    /** The access token string. */
    @SerializedName("access_token")
    private String accessToken;

    /** The type of the token (e.g., Bearer). */
    @SerializedName("token_type")
    private String tokenType;

    /** The token's expiration time in seconds. */
    @SerializedName("expires_in")
    private long expiresIn;

    /** The scope of the token. */
    @SerializedName("scope")
    private String scope;

    /** The token's unique identifier. */
    @SerializedName("jti")
    private String jti;

    /** The time the token was received or generated. */
    private Date receivedAt;

    /**
     * Default constructor that initializes the {@code receivedAt} field to the current time.
     * <p>
     * The {@code receivedAt} field is used to calculate the token's validity duration
     * based on its {@code expiresIn} value.
     * </p>
     */
    public AccessToken() {
        this.receivedAt = new Date();
    }

    /**
     * Retrieves the access token string.
     *
     * @return the access token string.
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Retrieves the expiration time in seconds.
     *
     * @return the expiration time in seconds.
     */
    public long getExpiresIn() {
        return expiresIn;
    }

    /**
     * Retrieves the unique token identifier.
     *
     * @return the token's unique identifier.
     */
    public String getJti() {
        return jti;
    }

    /**
     * Retrieves the time the token was received.
     *
     * @return the {@link Date} when the token was received.
     */
    public Date getReceivedAt() {
        return receivedAt;
    }

    /**
     * Retrieves the scope of the token.
     *
     * @return the token's scope.
     */
    public String getScope() {
        return scope;
    }

    /**
     * Retrieves the token type (e.g., Bearer).
     *
     * @return the token type.
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Checks if the access token is still valid based on its expiration time and 
     * the time it was received.
     *
     * @return {@code true} if the token is still valid; {@code false} otherwise.
     */
    public Boolean isValid() {
        if (receivedAt == null) {
            return false;
        }
        long currentTime = new Date().getTime();
        // CHECKSTYLE:OFF - Avoid checkstyle MagicNumber
        // expiresIn is in seconds, convert to milliseconds
        long expirationTime = receivedAt.getTime() + (expiresIn * 1000);
        // CHECKSTYLE:ON
        return currentTime < expirationTime;
    }

    /**
     * Sets the access token string.
     *
     * @param accessToken the access token string to set.
     */
    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Sets the expiration time in seconds.
     *
     * @param expiresIn the expiration time in seconds to set.
     */
    public void setExpiresIn(final long expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * Sets the unique token identifier.
     *
     * @param jti the token's unique identifier to set.
     */
    public void setJti(final String jti) {
        this.jti = jti;
    }

    /**
     * Sets the time the token was received.
     *
     * @param receivedAt the {@link Date} when the token was received.
     */
    public void setReceivedAt(final Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    /**
     * Sets the scope of the token.
     *
     * @param scope the token's scope to set.
     */
    public void setScope(final String scope) {
        this.scope = scope;
    }

    /**
     * Sets the token type (e.g., Bearer).
     *
     * @param tokenType the token type to set.
     */
    public void setTokenType(final String tokenType) {
        this.tokenType = tokenType;
    }
}
