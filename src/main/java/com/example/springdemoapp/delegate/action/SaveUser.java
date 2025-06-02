package com.example.springdemoapp.delegate.action;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SaveUser implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SaveUser.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Save user Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
    }
}
