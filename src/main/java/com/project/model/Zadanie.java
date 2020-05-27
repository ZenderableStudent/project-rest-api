package com.project.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="zadanie")
public class Zadanie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="zadanie_id")
	private Integer zadanieId;
	@NotBlank(message = "Pole nazwa nie mo�e by� puste!")    
	   @Size(min = 3, max = 50, message = "Nazwa musi zawiera� od {min} do {max} znak�w!")
	@Column(name="nazwa",nullable = false,length=50)
	private String nazwa;
	@Column(name="kolejnosc")
	private Integer kolejnosc;
	@Column(name="opis",length=1000)
	private String opis;
	@CreationTimestamp
	@Column(name="dataczas_dodania",nullable = false,updatable = false)
	private LocalDateTime dataCzasDodania;
	@ManyToOne
	@JoinColumn(name="projekt_id")
	private Projekt projekt;
	
	public Projekt getProjekt() {
		return projekt;
	}
	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}
	public Integer getZadanieId() {
		return zadanieId;
	}
	public void setZadanieId(Integer zadanieId) {
		this.zadanieId = zadanieId;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public Integer getKolejnosc() {
		return kolejnosc;
	}
	public void setKolejnosc(Integer kolejnosc) {
		this.kolejnosc = kolejnosc;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public LocalDateTime getDataCzasDodania() {
		return dataCzasDodania;
	}
	public void setDataCzasDodania(LocalDateTime dataCzasDodania) {
		this.dataCzasDodania = dataCzasDodania;
	}
	public Zadanie() {
		super();
	}
	public Zadanie(String nazwa, Integer kolejnosc, String opis) {
		super();
		this.nazwa = nazwa;
		this.kolejnosc = kolejnosc;
		this.opis = opis;
	}
	
}
