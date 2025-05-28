package com.example.springdemoapp.delegate.Sender;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class SendPhoneVerification implements JavaDelegate {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(SendPhoneVerification.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //HttpConnector http = Connectors.getConnector(HttpConnector.ID);
        //HttpRequest req =  http.createRequest();
        //req.post().url("http://localhost:8080/send-otp-validation").header("Content-Type", "application/json");
        //HttpResponse response =  req.execute();

        delegateExecution.setVariable("phone-OTP-code", "0000");

        logger.info("Service Send phone verification Works \n(\n by :" +
                "activity name : " + delegateExecution.getCurrentActivityName() +
                "varibales : " + delegateExecution.getVariables() + "\n)\n");
    }
}
