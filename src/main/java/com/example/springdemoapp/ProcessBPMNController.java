package com.example.springdemoapp;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProcessBPMNController {

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/start-signup")
    public ResponseEntity<String> startSignup(@RequestBody Map<String, Object> payload) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "Process_0kiyfro",
                payload
        );
        return ResponseEntity.ok("Started process with ID: " + instance.getId());
    }


}
