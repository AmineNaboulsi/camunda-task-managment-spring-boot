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
        return false;
    }

    public boolean checkEmailOTP_Code(String OTP_Code) {

        /*
            we have two case to verifiy from teh variables locally stored or to user dashy api for the comparison
         */
        return false;
    }
}