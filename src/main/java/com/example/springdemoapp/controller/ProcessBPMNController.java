package com.example.springdemoapp.controller;

import com.example.springdemoapp.delegate.validator.ValidatePhoneDuplication;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProcessBPMNController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(ValidatePhoneDuplication.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @PostMapping("/start/{processname}")
    public ResponseEntity<?> startProcess(@PathVariable String processname ,  @RequestBody Map<String, Object> payload) {

        /*
            We start by getting the user info UserId, email are requires for identification
            to continu on teh process
         */
        try {
            String userId = (String) payload.get("userId");
            String email = (String) payload.get("email");
            if(userId == null)
                userId = UUID.randomUUID().toString();

            if (userId == null || email == null)
                return ResponseEntity.badRequest().body("userId and email are required");

            /*
            Start my local newProcess-SignUp.bpmn file as an instance
             */

            ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                    processname,
                    Map.of("userId", userId, "email", email)
            );
            logger.info("Started process with ID: {}", instance.getId());
            return ResponseEntity.ok(Map.of(
                    "processInstanceId", instance.getId(),
                    "userId", userId,
                    "message", "Process started successfully"
            ));
        } catch (Exception e) {
            logger.error("Failed to start process: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Failed to start process: " + e.getMessage());
        }
    }

    @GetMapping("/tasks/user/{userId}")
    public ResponseEntity<?> getUserTasks(@PathVariable String userId) {

        /*
        search FOR all active task assigneed to teh user passed from the path variable
            @return
                id,
                name,
                assignee,
                created,
                processInstanceId
         */
        try {
            List<Task> tasks = taskService.createTaskQuery()
                    .or()
                    .taskAssignee(userId)
                    .taskCandidateUser(userId)
                    .endOr()
                    .active()
                    .list();

            List<Map<String, Object>> taskDtos = tasks.stream().map(task -> {
                Map<String, Object> dto = new HashMap<>();
                dto.put("id", task.getId());
                dto.put("definitionKey", task.getTaskDefinitionKey());
                dto.put("name", task.getName());
                dto.put("assignee", task.getAssignee());
                dto.put("created", task.getCreateTime());
                dto.put("processInstanceId", task.getProcessInstanceId());
                return dto;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(taskDtos);
        } catch (Exception e) {
            logger.error("Failed to fetch tasks for user {}: {}", userId, e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to fetch tasks: " + e.getMessage()));
        }
    }

    @PostMapping("/task/{taskId}/complete")
    public ResponseEntity<?> completeTask(
            @PathVariable String taskId,
            @RequestBody(required = false) Map<String, Object> variables) {

          /*
            Complete the user task by the Id :
            (
                we can search for task Id from the /tasks/user/{userId} for any task active assigneed
            )
         */

        try {
            if (variables != null) {
                taskService.complete(taskId, variables);
            } else {
                taskService.complete(taskId);
            }
            logger.info("Task {} completed successfully", taskId);

            return ResponseEntity.ok("Task completed successfully");
        } catch (Exception e) {
            logger.error("Failed to complete task {}: {}", taskId, e.getMessage());
            return ResponseEntity.badRequest().body("Failed to complete task: " + e.getMessage());
        }
    }
    @GetMapping("/process/{processInstanceId}/current-task")
    public ResponseEntity<?> getProcessCurrentTask(@PathVariable String processInstanceId) {
        /*
            Retrieve all active tasks for a given process instance
            @return
                id,
                definitionKey,
                name,
                assignee,
                created,
                processInstanceId
         */
        try {
            List<Task> tasks = taskService.createTaskQuery()
                    .processInstanceId(processInstanceId)
                    .active()
                    .list();

            List<Map<String, Object>> taskDtos = tasks.stream().map(task -> {
                Map<String, Object> dto = new HashMap<>();
                dto.put("id", task.getId());
                dto.put("definitionKey", task.getTaskDefinitionKey());
                dto.put("name", task.getName());
                dto.put("assignee", task.getAssignee() != null ? task.getAssignee() : "Unassigned");
                dto.put("created", task.getCreateTime());
                dto.put("processInstanceId", task.getProcessInstanceId());
                return dto;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(taskDtos);
        } catch (Exception e) {
            logger.error("Failed to fetch tasks for process instance {}: {}", processInstanceId, e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to fetch tasks: " + e.getMessage()));
        }
    }

    @GetMapping("/process/{processInstanceId}/variables")
    public ResponseEntity<?> getProcessVariables(@PathVariable String processInstanceId) {
        try {
            Map<String, Object> variables = runtimeService.getVariables(processInstanceId);
            logger.info("Retrieved variables for process instance {}: {}", processInstanceId, variables);
            return ResponseEntity.ok(variables);
        } catch (Exception e) {
            logger.error("Failed to fetch variables for process instance {}: {}", processInstanceId, e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error", "Failed to fetch variables: " + e.getMessage()));
        }
    }
}
