package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateEmailOTP implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailOTP.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Validate Email Link Work \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName());

        boolean exists = CheckOTPAndValidate(delegateExecution);

        if(!exists)
            delegateExecution.setVariable("errorMessage" , "Email OTP is not valid");

        delegateExecution.setVariable("isOTPEmailValid", exists);

    }

    public boolean CheckOTPAndValidate(DelegateExecution delegateExecution) {
    /*
        in this place we need to communicate with api Dashy for the otp validation
        or we can use the variables for the otp validation without storing — Camunda handles it for us.
     */

        Object emailOTPObj = delegateExecution.getVariable("email-OTP-code");
        Object codeSubmittedObj = delegateExecution.getVariable("codesubmitedEmail");

        String emailOTP = emailOTPObj != null ? emailOTPObj.toString() : "";
        String codesubmitedEmail = codeSubmittedObj != null ? codeSubmittedObj.toString() : "";

        if (emailOTP.isEmpty() || codesubmitedEmail.isEmpty()) {
            return false;
        }

        return emailOTP.equalsIgnoreCase(codesubmitedEmail);
    }

}
