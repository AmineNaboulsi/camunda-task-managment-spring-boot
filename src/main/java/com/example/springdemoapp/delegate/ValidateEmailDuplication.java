package com.example.springdemoapp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.connect.Connectors;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.HttpRequest;
import org.camunda.connect.httpclient.HttpResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public class ValidateEmailDuplication implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidateEmailDuplication.class);

    @Value("${shared.auth-servie}")
    private String AuthServiceUrl;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String email = ((String) delegateExecution.getVariable("email")) != null
                ? (String) delegateExecution.getVariable("email")
                : "";

        boolean exists = checkEmailExists(email);

        delegateExecution.setVariable("isEmailExists", exists);

        logger.info("Service Validate Email Dupliaction  \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "activityid : " + delegateExecution.getCurrentActivityName()+
                "varibales : " + delegateExecution.getVariables() + "\n)\n");

    }
    boolean checkEmailExists(String email){

    //        HttpConnector http = Connectors.getConnector(HttpConnector.ID);
    //        HttpRequest req =  http.createRequest();
    //        String FullUrl = "http://localhost:8080/api/auth/validate-email?email="+email;
    //        req.post().url(FullUrl).header("Content-Type", "application/json");
    //        HttpResponse response =  req.execute();
    //        int statusCode = response.getStatusCode();
    //        logger.info("\nFullUrl :" +FullUrl +"\n");
    //        logger.info("\n response : " + response);
    //        return (statusCode == 200) ? false : true;
        return false;
    }

}
