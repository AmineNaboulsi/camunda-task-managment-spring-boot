package com.example.springdemoapp.delegate.validator;

import com.example.springdemoapp.service.PhoneClientSerivce;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePhoneDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatePhoneDuplication.class);

    private final PhoneClientSerivce phoneClientSerivce;

    public ValidatePhoneDuplication(PhoneClientSerivce phoneClientSerivce) {
        this.phoneClientSerivce = phoneClientSerivce;
    }
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String phone = ((String) delegateExecution.getVariable("phone")) != null
                ? (String) delegateExecution.getVariable("phone")
                : "";

        boolean exists = phoneClientSerivce.checkPhoneDuplicate(phone);
        delegateExecution.setVariable("isPhoneExists", exists);
        delegateExecution.setVariable("errorMessage", exists ? "Phone already exists" : null);

        logger.info("Service Validate Company Work \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }
}
