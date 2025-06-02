package com.example.springdemoapp.delegate.Sender;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MailAccountCreation implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(MailAccountCreation.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Service Mail Account Creation Work \n(\n by :" +
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
    }

}