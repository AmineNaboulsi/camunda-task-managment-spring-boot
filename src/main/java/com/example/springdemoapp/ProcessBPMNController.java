package com.example.springdemoapp;

import com.example.springdemoapp.delegate.ValidatePhoneDuplication;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProcessBPMNController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatePhoneDuplication.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/test")
    public String Test() {
        return "Test" ;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startSignup(@RequestBody Map<String, Object> payload) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                "Process_0kiyfro", //
                payload
        );
        return ResponseEntity.ok("Started process with ID: " + instance.getId());
    }
    @PostMapping("/complete-task/{taskId}")
    public ResponseEntity<?> startSignup(
            @PathVariable String taskId ,
            @RequestBody(required = false) Map<String, Object> variables) {
        logger.info("Lol");
        try {
            if (variables != null) {
                taskService.complete(taskId, variables);
            } else {
                taskService.complete(taskId);
            }
            return ResponseEntity.ok("Task " + taskId + " completed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to complete task: " + e.getMessage());
        }
    }

}
