package edu.utvt.attendance.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.utvt.attendance.persistence.entities.Student;

import edu.utvt.attendance.persistence.service.StudentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public List<Student> get() {
		return this.studentService.getAll();
	}
	
	 @PostMapping
	    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
	        try {
	            Student savedStudent = studentService.save(student);
	            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> update(@PathVariable("id") String id, @RequestBody Student student) {
		return ResponseEntity.ok(this.studentService.update(id, student));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> delete(@PathVariable("id") String studentId) {
		return this.studentService.deleteById(studentId);
	}
	
	
	
}