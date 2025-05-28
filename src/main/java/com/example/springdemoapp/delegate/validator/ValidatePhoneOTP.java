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

        boolean exists = CheckValidatePhoneOTP((String) delegateExecution.getVariable("email"));
        delegateExecution.setVariable("isOTPPhoneValid", exists);
    }

    boolean CheckValidatePhoneOTP(String email) {
        /*
        In this place we need to communicate with api dashy for teh OTP VALIDATION
        or we can use teh variables for teh OTP validation without storing them manually camunda does it for us
         */
        return true;
    }
}