package com.example.springdemoapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PhoneClientSerivce {


    @Value("${shared.auth-servie}")
    private String AuthServiceUrl;

    public boolean checkPhoneDuplicate(String phone) {

        /*
            in this case we need to fetch from dashy api and look for
             the phone if he's exists or no

         */
            return false;
        }
}
