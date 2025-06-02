package com.example.springdemoapp.delegate.validator;

import com.example.springdemoapp.service.CampanyClientService;
import com.example.springdemoapp.service.EmailClientService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateCampanyNameDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailDuplication.class);

    private final CampanyClientService campanyClientService;

    public ValidateCampanyNameDuplication(CampanyClientService campanyClientService) {
        this.campanyClientService = campanyClientService;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String email = ((String) delegateExecution.getVariable("campanyName")) != null
                ? (String) delegateExecution.getVariable("campanyName")
                : "";


        boolean exists = campanyClientService.checkCampanyNameDuplicate(email);
        delegateExecution.setVariable("isCampanyExists", exists);
        delegateExecution.setVariable("errorMessage", exists ? "Campany Name Exists" : null);


        logger.info("Service Validate Email Dupliaction  \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }

}