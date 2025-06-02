package com.example.springdemoapp.delegate.validator;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateAllUserInfos implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateAllUserInfos.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        /*
        in this part we verifiy in all infos required are in teh variables instrance currentlly runing

         */

        boolean isValid = isNotBlank(delegateExecution, "userName") &&
                isNotBlank(delegateExecution, "companyName") &&
                isNotBlank(delegateExecution, "phone") &&
                isNotBlank(delegateExecution, "email") &&
                isNotBlank(delegateExecution, "password");

        delegateExecution.setVariable("AllVerifyed", isValid);

        logger.info("Service Validate All User Infos Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                " variables : " + delegateExecution.getVariables() + "\n)\n");

    }
    private boolean isNotBlank(DelegateExecution execution, String variableName) {
        Object value = execution.getVariable(variableName);
        if (value instanceof String) {
            return !((String) value).trim().isEmpty();
        }
        return value != null;
    }
}
