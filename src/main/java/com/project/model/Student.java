package com.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity //Indeksujemy kolumny, kt�re s� najcz�ciej wykorzystywane do wyszukiwania student�w
@Table(name = "student",
indexes = { @Index(name = "idx_nazwisko", columnList = "nazwisko", unique = false),
 @Index(name = "idx_nr_indeksu", columnList = "nr_indeksu", unique = true) })
public class Student {
	/* TODO Uzupe�nij kod o zmienne reprezentuj�ce pola tabeli student (patrz rys. 8.1),
	. nast�pnie wygeneruj dla nich akcesory (Source -> Generate Getters and Setters)
	*/
	
	@ManyToMany(mappedBy = "studenci")
	private Set<Projekt> projekty;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private Integer studentId;
	
	
	 @NotBlank(message = "Pole imie nie mo�e by� puste!")
	 @Size(min = 3, max = 20, message = "Imie musi zawiera� od {min} do {max} znak�w!")
	 @Column(nullable = false, length = 20)
	private String imie;
	
	 
	 @NotBlank(message = "Pole nazwisko nie mo�e by� puste!")
	 @Size(min = 2, max = 50, message = "Nazwisko musi zawiera� od {min} do {max} znak�w!")
	 @Column(nullable = false, length = 50)
	private String nazwisko;
	
	 @NotBlank(message = "Pole nrIndeksu nie mo�e by� puste!")
	 @Size(min = 1, max = 100, message = "NrIndeksu musi zawiera� od {min} do {max} znak�w!")
	 @Column(nullable = false, length = 100)
	private String nrIndeksu;
	
	 @NotBlank(message = "Pole email nie mo�e by� puste!")
	 @Size(min = 10, max = 100, message = "Email musi zawiera� od {min} do {max} znak�w!")
	 @Column(nullable = false, length = 100)
	private String email;
	
	private boolean stacjonarny;
	
	public Student() {}
	public Student(String imie, String nazwisko, String nrIndeksu, Boolean stacjonarny) {
	this.imie = imie;
	this.nazwisko = nazwisko;
	this.nrIndeksu = nrIndeksu;
	}
	public Student(String imie, String nazwisko, String nrIndeksu, String email, Boolean stacjonarny) {
	this.imie = imie;
	this.nazwisko = nazwisko;
	this.nrIndeksu = nrIndeksu;
	this.email = email;
	this.stacjonarny = stacjonarny;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getNrIndeksu() {
		return nrIndeksu;
	}
	public void setNrIndeksu(String nrIndeksu) {
		this.nrIndeksu = nrIndeksu;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStacjonarny() {
		return stacjonarny;
	}
	public void setStacjonarny(boolean stacjonarny) {
		this.stacjonarny = stacjonarny;
	}

}
