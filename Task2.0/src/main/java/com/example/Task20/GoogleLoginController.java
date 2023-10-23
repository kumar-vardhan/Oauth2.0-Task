package com.example.Task20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleLoginController {

    @Autowired
    private GoogleAuthenticationService googleAuthenticationService;
    @Autowired
    private ContentFetchingService contentFetchingService;

    @GetMapping("/login-with-google")
    public String loginWithGoogle() {
        // Redirect the user to the Google login page for authentication
        String googleAuthUrl = googleAuthenticationService.getGoogleAuthUrl();
        return "redirect:" + googleAuthUrl;
    }

    @GetMapping("/google-callback")
    public String googleCallback(@RequestParam("code") String code) {
        // Exchange the authorization code for an access token
        System.out.println("code "+ code);
        String accessToken = googleAuthenticationService.getAccessToken(code);
        System.out.println("access token"+code);
        // Use the accessToken to fetch content from another API
        String content = contentFetchingService.fetchContentFromOtherAPI(accessToken);
        
        // Display or process the fetched content
        return "Fetched content: " + content;
    }
}
