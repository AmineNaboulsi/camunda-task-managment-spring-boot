package com.example.springdemoapp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class SendEmailVerification implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SendEmailVerification.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Send email verification  Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
    }

}
