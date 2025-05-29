package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class ValidatePhoneOTP implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatePhoneOTP.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Validate Phone OTP Link Work \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

        boolean exists = CheckOTPAndValidate(delegateExecution);

        if(!exists)
            delegateExecution.setVariable("errorMessage" , "Phone OTP is not valid");


        delegateExecution.setVariable("isOTPPhoneValid", exists);
    }


    public boolean CheckOTPAndValidate(DelegateExecution delegateExecution) {
    /*
        in this place we need to communicate with api Dashy for the otp validation
        or we can use the variables for the otp validation without storing — Camunda handles it for us.
     */

        Object phoneOTPObj = delegateExecution.getVariable("phone-OTP-code");
        Object codeSubmittedObj = delegateExecution.getVariable("codesubmitedPhone");

        String phoneOTP = phoneOTPObj != null ? phoneOTPObj.toString() : "";
        String codesubmitedEmail = codeSubmittedObj != null ? codeSubmittedObj.toString() : "";

        if (phoneOTP.isEmpty() || codesubmitedEmail.isEmpty()) {
            return false;
        }

        return phoneOTP.equalsIgnoreCase(codesubmitedEmail);
    }
}