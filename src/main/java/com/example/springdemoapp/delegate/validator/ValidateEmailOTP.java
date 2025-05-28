package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class ValidateEmailOTP implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailOTP.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Validate Email Link Work \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
        boolean exists = CheckOTPAndValidate((String) delegateExecution.getVariable("email"));
        delegateExecution.setVariable("isOTPEmailValid", exists);
    }

    boolean CheckOTPAndValidate(String email) {
        //
        return true;
    }
}
