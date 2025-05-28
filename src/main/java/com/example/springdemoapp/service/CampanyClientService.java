package com.example.springdemoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CampanyClientService {

    @Value("${shared.auth-servie}")
    private String AuthServiceUrl;

    public boolean checkCampanyNameDuplicate(String email) {

        /*
            in this case we need to fetch from dashy api and check if campany name are exists or not

         */
        return false;
    }
}
