package com.example.springdemoapp;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessBPMNController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/start-process")
    public String startProcess(){
        runtimeService.startProcessInstanceByKey("Process_03kzvgw");
        return "Process Started";
    }


}
