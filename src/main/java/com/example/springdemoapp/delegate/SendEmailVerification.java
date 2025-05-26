package com.example.springdemoapp.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.connect.Connectors;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.HttpRequest;
import org.camunda.connect.httpclient.HttpResponse;
import org.slf4j.LoggerFactory;

public class SendEmailVerification implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SendEmailVerification.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpConnector http = Connectors.getConnector(HttpConnector.ID);
        HttpRequest req =  http.createRequest();
        req.post().url("http://localhost:8080/api/auth/send-email-validation").header("Content-Type", "application/json");
        HttpResponse response =  req.execute();
        logger.info("Service Send phone verification Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "varibales : " + delegateExecution.getVariables() +
                "response : " + response.getResponse() +"\n)\n");
    }

}
