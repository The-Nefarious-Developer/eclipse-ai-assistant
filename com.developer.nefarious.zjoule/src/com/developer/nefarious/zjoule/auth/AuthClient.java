package com.developer.nefarious.zjoule.auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import com.developer.nefarious.zjoule.memory.IMemoryAccessToken;
import com.developer.nefarious.zjoule.memory.IMemoryServiceKey;
import com.developer.nefarious.zjoule.memory.MemoryAccessToken;
import com.developer.nefarious.zjoule.memory.MemoryServiceKey;

/**
 * The {@code AuthClient} class handles the authentication process for obtaining
 * access tokens. It interacts with memory storage to save and load tokens and
 * service keys, and it makes HTTP requests to authentication endpoints.
 * 
 * <p>
 * This class is used to simplify the process of managing access tokens for the
 * authentication flow. It includes constructors for general use and for unit
 * testing purposes.
 * </p>
 */
public class AuthClient implements IAuthClient {

	private IMemoryAccessToken memoryAccessToken;
	
	private IMemoryServiceKey memoryServiceKey;
	
	private IAuthClientHelper authClientHelper;

	/**
	 * Default constructor for {@code AuthClient}.
	 * <p>
	 * This constructor initializes the {@code memoryAccessToken},
	 * {@code memoryServiceKey}, and {@code authClientHelper} instances for common
	 * chat operations.
	 * </p>
	 */
	public AuthClient() {
		memoryAccessToken = new MemoryAccessToken();
		memoryServiceKey = new MemoryServiceKey();
		authClientHelper = new AuthClientHelper();
	}

	/**
	 * Constructor for {@code AuthClient} with injected dependencies for testing
	 * purposes.
	 * <p>
	 * This constructor allows the injection of mock implementations during unit
	 * tests, making it easier to verify behavior without relying on actual data.
	 * </p>
	 *
	 * @param memoryAccessToken The memory storage implementation for access tokens.
	 * @param memoryServiceKey  The memory storage implementation for service keys.
	 * @param authClientHelper  Helper class for handling authentication requests
	 *                          and response parsing.
	 */
	public AuthClient(
			final IMemoryAccessToken memoryAccessToken, 
			final IMemoryServiceKey memoryServiceKey,
			final IAuthClientHelper authClientHelper) {
		this.memoryAccessToken = memoryAccessToken;
		this.memoryServiceKey = memoryServiceKey;
		this.authClientHelper = authClientHelper;
	}

	/**
	 * Constructor for {@code AuthClient} specifically for the login wizard process.
	 * <p>
	 * This constructor is used when {@code memoryAccessToken} and
	 * {@code memoryServiceKey} instances are provided, but a default
	 * {@code AuthClientHelper} instance should be created.
	 * </p>
	 *
	 * @param memoryAccessToken The memory storage implementation for access tokens.
	 * @param memoryServiceKey  The memory storage implementation for service keys.
	 */
	public AuthClient(final IMemoryAccessToken memoryAccessToken, final IMemoryServiceKey memoryServiceKey) {
		this.memoryAccessToken = memoryAccessToken;
		this.memoryServiceKey = memoryServiceKey;
		this.authClientHelper = new AuthClientHelper();
	}

	/**
	 * Retrieves an access token.
	 * <p>
	 * This method checks whether the last access token is still valid. If it is
	 * valid, it returns the existing token. Otherwise, it generates a new access
	 * token by contacting the authentication server.
	 * </p>
	 *
	 * @return A valid access token as a {@code String}.
	 * @throws IOException          If an I/O error occurs during the HTTP request.
	 * @throws InterruptedException If the operation is interrupted.
	 */
	@Override
	public String getAccessToken() throws IOException, InterruptedException {
		AccessToken lastTokenResponse = memoryAccessToken.load();
		if (lastTokenResponse.isValid()) {
			return lastTokenResponse.getAccessToken();
		} else {
			return getNewAccessToken();
		}
	}

	/**
	 * Generates a new access token by making an HTTP request to the authentication
	 * endpoint.
	 * <p>
	 * This method is called when the existing access token is expired or invalid.
	 * It constructs an HTTP request using the service key credentials, sends it to
	 * the authentication server, and saves the newly obtained access token in
	 * memory.
	 * </p>
	 *
	 * @return A newly generated access token as a {@code String}.
	 * @throws IOException          If an I/O error occurs during the HTTP request.
	 * @throws InterruptedException If the operation is interrupted.
	 */
	private String getNewAccessToken() throws IOException, InterruptedException {
		ServiceKey serviceKey = memoryServiceKey.load();

		HttpClient httpClient = HttpClient.newHttpClient();

		URI endpoint = authClientHelper.createAuthUri(serviceKey.getTokenURL());
		BodyPublisher requestBody = authClientHelper.createRequestBody(serviceKey.getClientId(),
				serviceKey.getClientSecret());

		HttpRequest request = HttpRequest.newBuilder().uri(endpoint)
				.header("Content-Type", "application/x-www-form-urlencoded").POST(requestBody).build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		AccessToken newAccessToken = authClientHelper.convertResponseToObject(response.body());
		memoryAccessToken.save(newAccessToken);

		return newAccessToken.getAccessToken();
	}

}
