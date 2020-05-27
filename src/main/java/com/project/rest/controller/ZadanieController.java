package com.project.rest.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.model.Zadanie;
import com.project.service.ZadanieService;

public class ZadanieController {
	 private ZadanieService zadanieService;
		
	 @Autowired    
	 public ZadanieController(ZadanieService zadanieService) 
	 {  
		 this.zadanieService = zadanieService;
	 	} 
	 
	 @GetMapping("/zadania/{zadanieId}")
	 ResponseEntity<Zadanie> getZadanie(@PathVariable Integer studentId)
	 {// @PathVariable oznacza, ¿e wartoœæ  
		 return ResponseEntity.of(zadanieService.getZadanie(studentId)); 
		 // parametru przekazywana jest w œcie¿ce    }
	 }
	 
	  @PostMapping(path = "/zadania")
	  ResponseEntity<Void> createZadanie(@Valid @RequestBody Zadanie zadanie) 
	  {                                                                            
		  Zadanie newZadanie = zadanieService.setZadanie(zadanie);
	  
	      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zadanieId}").buildAndExpand(newZadanie).toUri();
	      return ResponseEntity.created(location).build(); 
	
	  }
	  
	  @PutMapping("/zadania/{zadanieId}")   
	  public  ResponseEntity<Void> updateZadanie(@Valid @RequestBody Zadanie zadanie,@PathVariable Integer zadanieId)
	  {   
		  return zadanieService.getZadanie(zadanieId).map(p -> 
		  {                   zadanieService.setZadanie(zadanie);         
		  return new ResponseEntity<Void>(HttpStatus.OK);        
		  })    
			.orElseGet(() -> ResponseEntity.notFound().build()); 
	  }
	  
	  @DeleteMapping("/zadania/{zadanieId}")
	  public ResponseEntity<Void> deleteZadanie(@PathVariable Integer zadanieId)
	  {      
		  return zadanieService.getZadanie(zadanieId).map(p -> 
		  {          zadanieService.deleteZadanie(zadanieId); 
		  return new ResponseEntity<Void>(HttpStatus.OK); 
		  }).orElseGet(() -> ResponseEntity.notFound().build()); 
		  }
		  
}
