package com.project.service; 
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.model.Projekt;
import com.project.repository.ProjektRepository;

@Service  public class ProjektServiceImpl implements ProjektService { 
	
   private ProjektRepository projektRepository; 
   
   @Autowired    public ProjektServiceImpl(ProjektRepository projektRepository)
   {     
	   this.projektRepository = projektRepository;  
	   } 
 
   @Override    public Optional<Projekt> getProjekt(Integer projektId)
   {    
	   return projektRepository.findById(projektId);     
   }
 
   @Override    public Projekt setProjekt(Projekt projekt)
   {    
	         Projekt pr = new Projekt();
	         pr.setNazwa(projekt.getNazwa());
	         pr.setOpis(projekt.getOpis());
	         pr.setDataOddania(projekt.getDataOddania());
	         pr.setDataCzasUtworzenia(LocalDateTime.now());
	         projektRepository.save(pr);
	         return projekt;    
   }
 
  @Override  
  @Transactional
  public void deleteProjekt(Integer projektId)
  
  { 
	 projektRepository.deleteById(projektId);
  }
 
  @Override   public Page<Projekt> getProjekty(Pageable pageable)
  {  
	  Page<Projekt> pr = projektRepository.findAll(pageable);
	  return pr;
  }
   @Override    public Page<Projekt> searchByNazwa(String nazwa, Pageable pageable)
   {  
	   Page<Projekt> pr = projektRepository.findByNazwaContainingIgnoreCase(nazwa, pageable);
	   return pr;
   }
}
   
   
   
   
   