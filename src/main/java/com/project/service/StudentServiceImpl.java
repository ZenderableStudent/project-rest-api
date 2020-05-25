package com.project.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.model.Student;
import com.project.repository.StudentRepository; 

@Service
public class StudentServiceImpl implements StudentService{
	private StudentRepository studentRepository;
	 @Autowired
	 public StudentServiceImpl(StudentRepository studentRepository) {
	 this.studentRepository = studentRepository;
	 }
	 @Override
	 public Optional<Student> getStudent(Integer studentId) {
	 return studentRepository.findById(studentId);
	 }
	 @Override
	 public Student setStudent(Student student) {
	 //TODO
	 return null;
	 }

	 @Override
	 public Page<Student> getStudent(Pageable pageable) {
	 //TODO
	 return null;
	 }
	 
	 @Override
	 public Page<Student> searchByNazwa(String nazwa, Pageable pageable) {
	 //TODO
	 return null;
	 }
}
