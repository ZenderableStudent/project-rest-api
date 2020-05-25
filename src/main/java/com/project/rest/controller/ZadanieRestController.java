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
import com.project.model.Zadanie;
import com.project.service.ZadanieService;

@RestController
@RequestMapping("/api") 
public class ZadanieRestController {

	private ZadanieService zadanieService;
	
	@Autowired
	 public ZadanieRestController(ZadanieService zadanieService) {
	 this.zadanieService = zadanieService;
	}
	 
	 @GetMapping("/projekty/{zadanieId}")
	 ResponseEntity<Zadanie> getZadanie(@PathVariable Integer zadanieId) {// @PathVariable oznacza, �e warto��
	 return ResponseEntity.of(zadanieService.getZadanie(zadanieId)); // parametru przekazywana jest w �cie�ce
	 }
	 
	// @Valid w��cza automatyczn� walidacj� na podstawie adnotacji zawartych
		 // w modelu np. NotNull, Size, NotEmpty itp. (z javax.validation.constraints.*)
		 @PostMapping(path = "/projekty")
		 ResponseEntity<Void> createZadanie(@Valid @RequestBody Zadanie zadanie) {// @RequestBody oznacza, �e dane
		 // projektu (w formacie JSON) s�
		 Zadanie createdZadanie = zadanieService.setZadanie(zadanie); // przekazywane w ciele ��dania
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest() // link wskazuj�cy utworzony projekt
		 .path("/{zadanieId}").buildAndExpand(createdZadanie.getZadanieId()).toUri();
		 return ResponseEntity.created(location).build(); // zwracany jest kod odpowiedzi 201 - Created
		 } // z linkiem location w nag��wku
		 
		 @PutMapping("/projekty/{zadanieId}")
		 public ResponseEntity<Void> updateZadanie(@Valid @RequestBody Zadanie zadanie,
		 @PathVariable Integer zadanieId) {
		 return zadanieService.getZadanie(zadanieId)
		 .map(p -> {
		 zadanieService.setZadanie(zadanie);
		 return new ResponseEntity<Void>(HttpStatus.OK); // 200 (mo�na te� zwraca� 204 - No content)
		 })
		 .orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
		 }
		 
		 @DeleteMapping("/projekty/{zadanieId}")
		 public ResponseEntity<Void> deleteZadanie(@PathVariable Integer zadanieId) {
		 return zadanieService.getZadanie(zadanieId).map(p -> {
		 zadanieService.deleteZadanie(zadanieId);
		 return new ResponseEntity<Void>(HttpStatus.OK); // 200
		 }).orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
		 }
		 
			//Przyk�ad ��dania wywo�uj�cego metod�: http://localhost:8080/api/projekty?page=0&size=10&sort=nazwa,desc
		 @GetMapping(value = "/projekty")
		 Page<Zadanie> getZadanie(Pageable pageable) { // @RequestHeader HttpHeaders headers � je�eli potrzebny
		 return zadanieService.getZadanie(pageable); // by�by nag��wek, wystarczy doda� drug� zmienn� z adnotacj�
		 }
		 
			// Przyk�ad ��dania wywo�uj�cego metod�: GET http://localhost:8080/api/projekty?nazwa=webowa
		 // Metoda zostanie wywo�ana tylko, gdy w ��daniu b�dzie przesy�ana warto�� parametru nazwa.
		 @GetMapping(value = "/projekty", params="nazwa")
		 Page<Zadanie> getZadanieByNazwa(@RequestParam String nazwa, Pageable pageable) {
		 return zadanieService.searchByNazwa(nazwa, pageable);
		 }

}

