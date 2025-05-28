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

    public boolean checkEmailOTP_Code(String OTP_Code) {

        /*
            we have two case to verifiy from teh variables locally stored or to user dashy api for the comparison
         */
        return false;
    }

}
