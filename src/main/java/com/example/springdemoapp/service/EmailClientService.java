package com.example.springdemoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailClientService {

    @Value("${shared.auth-servie}")
    private String AuthServiceUrl;

    public boolean checkEmailDuplicate(String email) {

        /*
        in this case we need to fetch from dashy api and look for teh email and if he's exists or noo

         */
        return true;
    }
}