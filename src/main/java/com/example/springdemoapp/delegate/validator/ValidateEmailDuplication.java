package com.example.springdemoapp.delegate.validator;

import com.example.springdemoapp.service.EmailClientService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValidateEmailDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailDuplication.class);

    private final EmailClientService emailClient;

    public ValidateEmailDuplication(EmailClientService emailClient) {
        this.emailClient = emailClient;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String email = ((String) delegateExecution.getVariable("email")) != null
                ? (String) delegateExecution.getVariable("email")
                : "";


        boolean exists = emailClient.checkEmailDuplicate(email);
        delegateExecution.setVariable("isEmailExists", exists);
        delegateExecution.setVariable("errorMessage", exists ? "Email already exists" : null);


        logger.info("Service Validate Email Dupliaction  \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }
}
