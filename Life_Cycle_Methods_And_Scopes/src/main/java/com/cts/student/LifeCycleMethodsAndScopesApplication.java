package com.cts.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LifeCycleMethodsAndScopesApplication {

	public static void main(String[] args) {
		//SpringApplication.run(LifeCycleMethodsAndScopesApplication.class, args);
		
		ApplicationContext context = SpringApplication.run(LifeCycleMethodsAndScopesApplication.class, args);
		
		StudentService studentService = context.getBean(StudentService.class);
		
		// Add a few students
        Student student1 = new Student(1,"Saksham", 85.5);
        Student student2 = new Student(2,"Amit", 60.0);
        Student student3 = new Student(3,"Ankita", 45.5);
        
        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);
        
        // Fetch and display all students
        System.out.println("All Students:");
        studentService.getAllStudents().forEach(System.out::println);

        // Update scores and observe the scoring system logs
        studentService.updateStudentScore(student1.getId(), 95.0);
        studentService.updateStudentScore(student2.getId(), 55.0);
        studentService.updateStudentScore(student3.getId(), 35.0);

        // Remove a student
        studentService.deleteStudent(student2.getId());

        // Fetch and display students after removal
        System.out.println("\nStudents after removal:");
        studentService.getAllStudents().forEach(System.out::println);
	}

}
