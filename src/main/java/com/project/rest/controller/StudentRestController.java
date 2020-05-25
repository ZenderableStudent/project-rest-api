package com.project.rest.controller;

import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.project.model.Student;
import com.project.service.StudentService;

@RestController
@RequestMapping("/api") 
public class StudentRestController {

private StudentService studentService;
	
	@Autowired
	 public StudentRestController(StudentService studentService) {
	 this.studentService = studentService;
	}
	
	@GetMapping("/projekty/{studentId}")
	 ResponseEntity<Student> getStudent(@PathVariable Integer studentId) {// @PathVariable oznacza, ¿e wartoœæ
	 return ResponseEntity.of(studentService.getStudent(studentId)); // parametru przekazywana jest w œcie¿ce
	 }
	
	@PostMapping(path = "/projekty")
	 ResponseEntity<Void> createStudent(@Valid @RequestBody Student student) {// @RequestBody oznacza, ¿e dane
	 // projektu (w formacie JSON) s¹
	 Student createdStudent = studentService.setStudent(student); // przekazywane w ciele ¿¹dania
	 URI location = ServletUriComponentsBuilder.fromCurrentRequest() // link wskazuj¹cy utworzony projekt
	 .path("/{studentId}").buildAndExpand(createdStudent.getStudentId()).toUri();
	 return ResponseEntity.created(location).build(); // zwracany jest kod odpowiedzi 201 - Created
	 } // z linkiem location w nag³ówku

	 @PutMapping("/projekty/{studentId}")
	 public ResponseEntity<Void> updateStudent(@Valid @RequestBody Student student,
	 @PathVariable Integer studentId) {
	 return studentService.getStudent(studentId)
	 .map(p -> {
	 studentService.setStudent(student);
	 return new ResponseEntity<Void>(HttpStatus.OK); // 200 (mo¿na te¿ zwracaæ 204 - No content)
	 })
	 .orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
	 }
	 
	 //Przyk³ad ¿¹dania wywo³uj¹cego metodê: http://localhost:8080/api/projekty?page=0&size=10&sort=nazwa,desc
	 @GetMapping(value = "/projekty")
	 Page<Student> getStudent(Pageable pageable) { // @RequestHeader HttpHeaders headers – je¿eli potrzebny
	 return studentService.getStudent(pageable); // by³by nag³ówek, wystarczy dodaæ drug¹ zmienn¹ z adnotacj¹
	 }
	 
	 // Przyk³ad ¿¹dania wywo³uj¹cego metodê: GET http://localhost:8080/api/projekty?nazwa=webowa
	 // Metoda zostanie wywo³ana tylko, gdy w ¿¹daniu bêdzie przesy³ana wartoœæ parametru nazwa.
	 @GetMapping(value = "/projekty", params="nazwa")
	 Page<Student> getStudentByNazwa(@RequestParam String nazwa, Pageable pageable) {
	 return studentService.searchByNazwa(nazwa, pageable);
	 }

}
