package com.developer.nefarious.zjoule.test.login.api;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import java.net.http.HttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import com.developer.nefarious.zjoule.auth.IAuthClient;
import com.developer.nefarious.zjoule.login.api.ILoginClient;
import com.developer.nefarious.zjoule.login.api.ILoginClientHelper;
import com.developer.nefarious.zjoule.login.api.LoginClient;

public class LoginClientTest {
	
	private ILoginClient cut;
	
	@Mock
	private HttpClient mockHttpClient;
	
	@Mock
	private IAuthClient mockAuthClient;
	
	@Mock
	private ILoginClientHelper mockLoginClientHelper;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<HttpClient> httpClientStatic = mockStatic(HttpClient.class)) {
			httpClientStatic.when(() -> HttpClient.newHttpClient()).thenReturn(mockHttpClient);
			cut = spy(new LoginClient(mockLoginClientHelper, mockAuthClient));
		}
		
	}
	
	//TODO: Add test for fetching resource groups
	
}
