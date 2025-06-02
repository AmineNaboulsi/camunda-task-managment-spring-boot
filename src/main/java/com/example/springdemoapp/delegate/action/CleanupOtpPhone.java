package com.example.springdemoapp.delegate.action;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CleanupOtpPhone implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CleanupOtpPhone.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.removeVariable("phone-OTP-code");
        delegateExecution.removeVariable("phone-Otp-CreatedAt");

        logger.info("Clean up Otp phone Work " );
    }
}