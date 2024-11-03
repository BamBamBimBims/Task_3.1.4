package com.example.RestFinal.controller;

import com.example.RestFinal.service.ServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final ServiceAPI serviceAPI;

    @Autowired
    public UserController(ServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    @GetMapping({"/code"})
    public String getFinalCode() {
        return this.serviceAPI.getFinalCode();
    }
}