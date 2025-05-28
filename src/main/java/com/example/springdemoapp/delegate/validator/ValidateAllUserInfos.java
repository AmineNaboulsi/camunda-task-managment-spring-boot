package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class ValidateAllUserInfos implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateAllUserInfos.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        logger.info("Service Validate All User Infos Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }
}
