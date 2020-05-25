package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="projekt") //potrzebne tylko je¿eli nazwa tabeli w bazie danych ma byæ inna od nazwy klasy
public class Projekt {


		 private String opis;
		 private LocalDate dataOddania;
		 
			@ManyToMany
			 @JoinTable(name = "projekt_student",
			 joinColumns = {@JoinColumn(name="projekt_id")},
			 inverseJoinColumns = {@JoinColumn(name="student_id")})
			 private Set<Student> studenci;
		 
		 @Id
		 @GeneratedValue(strategy = GenerationType.IDENTITY)
		 @Column(name="projekt_id") //tylko je¿eli nazwa kolumny w bazie danych ma byæ inna od nazwy zmiennej
		 private Integer projektId;
		 
		 @NotBlank(message = "Pole nazwa nie mo¿e byæ puste!")
		 @Size(min = 3, max = 50, message = "Imie musi zawieraæ od {min} do {max} znaków!")
		 @Column(nullable = false, length = 50)
		 private String nazwa;
		 
		 @CreationTimestamp
		 @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
		 private LocalDateTime dataCzasUtworzenia;
		 
		 @UpdateTimestamp
		 @Column(name = "dataczas_modyfikacji", nullable = false)
		 private LocalDateTime dataCzasModyfikacji;
		 
		 @OneToMany(mappedBy = "projekt")
		 private List<Zadanie> zadania;



		 
		 public LocalDateTime getDataCzasModyfikacji() {
			return dataCzasModyfikacji;
		}

		public void setDataCzasModyfikacji(LocalDateTime dataCzasModyfikacji) {
			this.dataCzasModyfikacji = dataCzasModyfikacji;
		}

		public List<Zadanie> getZadania() {
			return zadania;
		}

		public void setZadania(List<Zadanie> zadania) {
			this.zadania = zadania;
		}

		public Projekt() {
		 }
		 
		 public Projekt(Integer projektId, String nazwa, String opis, LocalDateTime dataCzasUtworzenia, LocalDate dataOddania) 
		 {
			 this.projektId = projektId;
			 this.nazwa = nazwa;
			 this.opis = opis;
			 this.dataCzasUtworzenia = dataCzasUtworzenia;
			 this.dataOddania = dataOddania;
		 }
		 
		 public Projekt(String nazwa, String opis, LocalDate dataOddania) {
			 this.nazwa = nazwa;
			 this.opis = opis;
			 this.dataOddania = dataOddania;
		 }
		 
		public Integer getProjektId() {
			return projektId;
		}
		public void setProjektId(Integer projektId) {
			this.projektId = projektId;
		}
		public String getNazwa() {
			return nazwa;
		}
		public void setNazwa(String nazwa) {
			this.nazwa = nazwa;
		}
		public String getOpis() {
			return opis;
		}
		public void setOpis(String opis) {
			this.opis = opis;
		}
		public LocalDateTime getDataCzasUtworzenia() {
			return dataCzasUtworzenia;
		}
		public void setDataCzasUtworzenia(LocalDateTime dataCzasUtworzenia) {
			this.dataCzasUtworzenia = dataCzasUtworzenia;
		}
		public LocalDate getDataOddania() {
			return dataOddania;
		}
		public void setDataOddania(LocalDate dataOddania) {
			this.dataOddania = dataOddania;
		}

		public boolean delete() {
			// TODO Auto-generated method stub
			return false;
		}

}
