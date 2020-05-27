package com.project.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.model.Zadanie; 

public interface ZadanieService { 
   Optional<Zadanie> getZadanie(Integer Id);
   Zadanie setZadanie(Zadanie zadanie);
   void deleteZadanie(Integer Id);
   Page<Zadanie> getZadania(Pageable pageable);
   Page<Zadanie> searchByNazwa(Integer id, Pageable pageable); 
} 