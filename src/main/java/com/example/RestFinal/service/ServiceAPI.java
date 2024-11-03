package com.example.RestFinal.service;

import com.example.RestFinal.model.User;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ServiceAPI {
    private final RestTemplate restTemplate;
    private final String apiURL = "http://94.198.50.185:7081/api/users";
    private String sessionId;

    @Autowired
    public ServiceAPI(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity(headers);
        ResponseEntity<String> response = this.restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.GET, request, String.class, new Object[0]);
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        if (cookies != null) {
            Iterator var5 = cookies.iterator();

            while(var5.hasNext()) {
                String cookie = (String)var5.next();
                if (cookie.startsWith("JSESSIONID=")) {
                    this.sessionId = cookie.split(";", 2)[0];
                    break;
                }
            }
        }

    }

    public String saveUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", this.sessionId);
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Born");
        user.setAge((byte)30);
        HttpEntity<User> request = new HttpEntity(user, headers);
        ResponseEntity<String> response = this.restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.POST, request, String.class, new Object[0]);
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + (String)response.getBody());
        return (String)response.getBody();
    }

    public String updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", this.sessionId);
        User user = new User();
        user.setId(3L);
        user.setName("Tomas");
        user.setLastName("Shelby");
        user.setAge((byte)35);
        HttpEntity<User> request = new HttpEntity(user, headers);
        ResponseEntity<String> response = this.restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, request, String.class, new Object[0]);
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + (String)response.getBody());
        return (String)response.getBody();
    }

    public String deleteUser() {
        String apiURLId = "http://94.198.50.185:7081/api/users/3";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", this.sessionId);
        HttpEntity<String> request = new HttpEntity(headers);
        ResponseEntity<String> response = this.restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, request, String.class, new Object[0]);
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response body: " + (String)response.getBody());
        return (String)response.getBody();
    }

    public String getFinalCode() {
        this.getAllUsers();
        String codePart1 = this.saveUser();
        String codePart2 = this.updateUser();
        String codePart3 = this.deleteUser();
        return codePart1 + codePart2 + codePart3;
    }
}
