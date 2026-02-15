package com.restapi.task.controller;

import com.restapi.task.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 26974L;

    public TaskController() {
        tasks.add(new Task(26965L, "Complete RESTful API project", "Finish Spring Boot assignment by Irakoze", false, "HIGH", "2024-12-20"));
        tasks.add(new Task(26966L, "Prepare presentation slides", "Create slides for project demo", false, "HIGH", "2024-12-22"));
        tasks.add(new Task(26967L, "Database backup", "Backup production database", false, "MEDIUM", "2024-12-28"));
        tasks.add(new Task(26968L, "Pay utility bills", "Electricity and water bills", true, "LOW", "2024-12-16"));
        tasks.add(new Task(26969L, "Code refactoring", "Refactor authentication module", false, "MEDIUM", "2024-12-20"));
        tasks.add(new Task(26970L, "Client call", "Discuss project requirements", true, "HIGH", "2024-12-13"));
        tasks.add(new Task(26971L, "Update documentation", "Update API documentation", false, "LOW", "2024-12-30"));
        tasks.add(new Task(26972L, "Security audit", "Perform security vulnerability scan", false, "HIGH", "2024-12-19"));
        tasks.add(new Task(26973L, "Deploy to staging", "Deploy latest build to staging server", true, "MEDIUM", "2024-12-15"));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        return tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = tasks.stream()
                .filter(t -> t.isCompleted() == completed)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setTaskId(nextId++);
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                updatedTask.setTaskId(taskId);
                tasks.set(i, updatedTask);
                return ResponseEntity.ok(updatedTask);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean removed = tasks.removeIf(t -> t.getTaskId().equals(taskId));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
