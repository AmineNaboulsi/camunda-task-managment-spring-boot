package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class ValidateCampanyNameDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateCampanyNameDuplication.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        logger.info("Service Validate Campany Name Duplication Infos Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }
}