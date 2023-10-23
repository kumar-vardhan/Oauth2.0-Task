package com.example.Task20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class GoogleAuthenticationService {

    @Value("${google.clientId}")
    private String clientId;
    @Value("${google.clientSecret}")
    private String clientSecret;
    @Autowired
    private  RestTemplate restTemplate;

//  //  public GoogleAuthenticationService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public String getGoogleAuthUrl() {
        // Construct the Google OAuth 2.0 authorization URL
        String authUrl = "<a href=https://accounts.google.com/o/oauth2/auth" +
                        "?client_id=" + clientId +
                        "&redirect_uri=http://localhost:9999/google-callback" +
                        "&response_type=code" +
                        "&scope=https://www.googleapis.com/auth/userinfo.email" +
                        "&access_type=offline>link<a/>";
        return authUrl;
    }

    public String getAccessToken(String code) {
        try {

            // Exchange the authorization code for an access token
            String tokenUrl = "https://accounts.google.com/o/oauth2/token";
            String requestBody = "code=" + code +
                    "&client_id=" + clientId +
                    "&client_secret=" + clientSecret +
                    "&redirect_uri=http://localhost:9999" +"/google-callback" +
                    "&grant_type=authorization_code";
            Map<String, String> params = new HashMap<>();
            params.put("client_id", clientId);
            params.put("client_secret", clientSecret);
            params.put("redirect_uri", "http://localhost:9999/google-callback");
            params.put("grant_type", "authorization_code");
            params.put("code", code);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<?> request = new HttpEntity<Object>(params,httpHeaders);

            GoogleTokenResponse response = restTemplate.postForObject(tokenUrl, request, GoogleTokenResponse.class);
            return response.getAccess_token();
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
