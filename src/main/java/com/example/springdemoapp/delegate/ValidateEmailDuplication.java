package com.example.springdemoapp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class ValidateEmailDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailDuplication.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Validate Company Work \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
    }

}
