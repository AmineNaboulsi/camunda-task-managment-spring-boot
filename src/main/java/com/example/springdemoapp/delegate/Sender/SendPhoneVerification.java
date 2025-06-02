package com.example.springdemoapp.delegate.Sender;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendPhoneVerification implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SendPhoneVerification.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

         /*
            in this part we need to communicate with dashy api for OTP code sender
            in my case am using a simple 0000 test
         */

        delegateExecution.setVariable("phone-OTP-code", "0000");
        delegateExecution.setVariable("phone-Otp-CreatedAt", System.currentTimeMillis());

        logger.info("Service Send phone verification Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName());
    }
}
