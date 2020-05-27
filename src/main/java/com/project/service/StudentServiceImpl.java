package com.project.service;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.model.Student;

import com.project.repository.StudentRepository;

public class StudentServiceImpl  implements StudentService {
	
	  private StudentRepository studentRepository; 
	
	 @Autowired  
	 public StudentServiceImpl(StudentRepository studentRepository)
	   {     
		   this.studentRepository = studentRepository;  
		   } 
	 
	   @Override    
	   public Optional<Student> getStudent(Integer projektId)
	   {    
		   return studentRepository.findById(projektId);     
	   }
	 

	  public Page<Student> getStudenci(Pageable pageable)
	  {  
		  Page<Student> pr = studentRepository.findAll(pageable);
		  return pr;
	  }
	     
	   public Page<Student> searchByNazwisko(String nazwa, Pageable pageable)
	   {  
		   Page<Student> pr = studentRepository.findByNazwiskoStartsWithIgnoreCase(nazwa, pageable);
		   return pr;
	   }

	@Override
	public Student setStudent(Student student) {
		   Student st = student;
	         studentRepository.save(st);
	         
	         return st;
	         
		
	}

	@Override
	 @Transactional
	public void deleteStudent(Integer Id) {
		 studentRepository.deleteById(Id);
		
	}

	

	
}
