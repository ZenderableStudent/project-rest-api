package com.project.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.project.model.Zadanie;
import com.project.repository.ZadanieRepository; 

@Service
public class ZadanieServiceImpl implements ZadanieService{
	private ZadanieRepository zadanieRepository;
	 @Autowired
	 public ZadanieServiceImpl(ZadanieRepository zadanieRepository) {
	 this.zadanieRepository = zadanieRepository;
	 }
	 @Override
	 public Optional<Zadanie> getZadanie(Integer zadanieId) {
	 return zadanieRepository.findById(zadanieId);
	 }
	 @Override
	 public Zadanie setZadanie(Zadanie zadanie) {
	 //TODO
	 return null;
	 }
	 
	 @Override
	 @Transactional
	 public void deleteZadanie(Integer zadanieId) {
	 for (Zadanie zadanie : zadanieRepository.findZadaniaProjektu(zadanieId)) {
	 zadanieRepository.delete(zadanie);
	 }
	 zadanieRepository.deleteById(zadanieId);
	 }
	 
	 @Override
	 public Page<Zadanie> getZadanie(Pageable pageable) {
	 //TODO
	 return null;
	 }
	 
	 @Override
	 public Page<Zadanie> searchByNazwa(String nazwa, Pageable pageable) {
	 //TODO
	 return null;
	 }
}
