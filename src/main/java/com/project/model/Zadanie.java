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
	/*TODO Uzupe³nij kod o zmienne reprezentuj¹ce pozosta³e pola tabeli zadanie (patrz rys. 8.1),
	. nastêpnie wygeneruj dla nich akcesory (Source -> Generate Getters and Setters),
	. ponadto dodaj pusty konstruktor oraz konstruktor ze zmiennymi nazwa, opis i kolejnosc.
	*/
	
	 @NotBlank(message = "Pole nazwa nie mo¿e byæ puste!")
	 @Size(min = 3, max = 20, message = "Nazwa musi zawieraæ od {min} do {max} znaków!")
	 @Column(nullable = false, length = 20)
	private String nazwa;
	
	@Column(name="kolejnosc")
	private Integer kolejnosc;
	
	@Column(name="opis")
	private String opis;
	
	 @CreationTimestamp
	 @Column(name = "dataczas_dodania", nullable = false, updatable = false)
	 private LocalDateTime dataCzasDodania;
	 
	 @ManyToOne
	 @JoinColumn(name = "projekt_id")
	 private Projekt projekt;
	 
	 public Projekt getProjekt() {
		return projekt;
	}

	public void setProjekt(Projekt projekt) {
		this.projekt = projekt;
	}

	public Zadanie() {	 
	 }
	 
	 public Zadanie(String nazwa, String opis, Integer kolejnosc) {
		 this.nazwa = nazwa;
		 this.opis = opis;
		 this.kolejnosc = kolejnosc;
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
}
