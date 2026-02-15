package com.restapi.student.controller;

import com.restapi.student.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private List<Student> students = new ArrayList<>();
    private Long nextId = 26974L;

    public StudentController() {
        students.add(new Student(26965L, "Irakoze", "Micomyiza Rodrigue", "irakoze.rodrigue@email.com", "Computer Science", 3.9));
        students.add(new Student(26966L, "Emma", "Wilson", "emma.wilson@email.com", "Computer Science", 3.7));
        students.add(new Student(26967L, "Liam", "Anderson", "liam.a@email.com", "Computer Science", 3.9));
        students.add(new Student(26968L, "Olivia", "Martinez", "olivia.m@email.com", "Business Administration", 3.3));
        students.add(new Student(26969L, "Noah", "Taylor", "noah.t@email.com", "Mechanical Engineering", 3.8));
        students.add(new Student(26970L, "Ava", "Thomas", "ava.thomas@email.com", "Computer Science", 3.5));
        students.add(new Student(26971L, "Sophia", "Lee", "sophia.lee@email.com", "Computer Science", 3.6));
        students.add(new Student(26972L, "James", "White", "james.w@email.com", "Business Administration", 3.4));
        students.add(new Student(26973L, "Isabella", "Harris", "isabella.h@email.com", "Electrical Engineering", 3.7));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        List<Student> result = students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return ResponseEntity.ok(updatedStudent);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
