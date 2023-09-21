package com.cts.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements InitializingBean, DisposableBean {
private List<Student> studentList= new ArrayList<>();
private int nextStudentId = 1;

//Add a new Student

public Student addStudent(Student student)
{
	student.setId(nextStudentId++);
	studentList.add(student);
	return student;
}
//Fetch all students
public List<Student> getAllStudents(){
	return studentList;
}
// Fetch a student by ID
public Optional<Student> getStudentById(int id) {
    return studentList.stream()
            .filter(student -> student.getId() == id)
            .findFirst();
}

// Update a student's score
public boolean updateStudentScore(int id, double newScore) {
    Optional<Student> studentOptional = getStudentById(id);
    if (studentOptional.isPresent()) {
        Student student = studentOptional.get();
        student.setScore(newScore);
        
     // Log messages based on the score
        if (newScore < 50) {
            System.out.println("Student " + student.getName() + " is Below Average.");
        } else if (newScore >= 50 && newScore <= 75) {
            System.out.println("Student " + student.getName() + " is Average.");
        } else {
            System.out.println("Student " + student.getName() + " is Above Average.");
        }
        return true;
    }
    return false;
}

// Delete a student
public boolean deleteStudent(int id) {
    Optional<Student> studentOptional = getStudentById(id);
    if (studentOptional.isPresent()) {
        studentList.remove(studentOptional.get());
        return true;
    }
    return false;
}
@Override
public void afterPropertiesSet() throws Exception {
	System.out.println("StudentService has been initialized.");
	//custom initialization logic 
	
}
@Override
public void destroy() throws Exception {
	System.out.println("StudentService is being destroyed.");
	//custom cleanup or destruction logic here
	
}

}

