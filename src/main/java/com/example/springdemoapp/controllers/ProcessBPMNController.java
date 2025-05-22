package com.example.springdemoapp.controllers;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/validation")
public class ProcessBPMNController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/test")
    public String test() {
        System.out.println("✅ test loaded");
        return "test";
    }

    @PostMapping("/start")
    public String startProcess(@RequestParam String businessKey) {
        runtimeService.startProcessInstanceByKey("task_validation_process", businessKey);
        return "Process started with businessKey = " + businessKey;
    }

    @GetMapping("/tasks")
    public List<String> getTasks(@RequestParam String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list()
                .stream()
                .map(task -> "Task ID: " + task.getId() + ", Name: " + task.getName())
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public String completeTask(@RequestParam String taskId) {
        taskService.complete(taskId);
        return "Task " + taskId + " completed.";
    }
}
