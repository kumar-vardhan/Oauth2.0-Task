package com.example.Task20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ContentFetchingService {

    @Value("${content.api.url}")
    private String contentApiUrl; // The URL of the content API

//    @Autowired
//    private RestTemplateConfig restTemplateConfig;

    @Autowired
      RestTemplate restTemplate = new RestTemplate();

//    public ContentFetchingService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public String fetchContentFromOtherAPI(String accessToken) {
        // Set up the HTTP headers with the access token for authentication
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        // Create an HTTP request entity with the headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make a GET request to the content API
//        ResponseEntity<String> response = restTemplate.exchange(contentApiUrl, HttpMethod
//                .GET, entity, String.class);

//        if (response.getStatusCode() == HttpStatus.OK) {
//            return response.getBody(); // Return the content fetched from the other API
//        } else {
//            // Handle error responses here
//            // You can log the error, throw an exception, or return an appropriate error message.
//            return "Error: Unable to fetch content from the API";
//        }
        return "Hello hiiii";
    }
}
