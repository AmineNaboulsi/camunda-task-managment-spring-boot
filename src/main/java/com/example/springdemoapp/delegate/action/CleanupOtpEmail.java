package com.example.springdemoapp.delegate.action;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CleanupOtpEmail implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CreateCompany.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.removeVariable("email-OTP-code");
        delegateExecution.removeVariable("email-Otp-CreatedAt");

        logger.info("Clean up Otp Email Work " );
    }
}