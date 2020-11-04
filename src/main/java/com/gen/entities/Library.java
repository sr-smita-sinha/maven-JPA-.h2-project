package com.gen.entities;

//Author: Smita Srivastava

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

// Entity class Represents the record in library table

@Entity
@Table(name="library")
public class Library implements Serializable{ // serializable is optional but good practice to keep it
	
	private static final long serialVersionUID = 1L;
		
	private Integer id;
	private String name;
	private Set<Book> books = new HashSet<Book>(0); // set since many books
	
	// These constructors are needed only if there are gonna be used in @Query("new Library() like....")
	public Library() {}
	
	public Library(String name) {
		this.name = name;
	}
	
	public Library(String name,Set<Book> books) {
		this.name = name;
		this.books = books;
	}	
	
	@Id //specified the primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) //uses the database identity column
	@Column(name="id",unique = true,nullable = false) //name is optional is variable name matches table field name
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name",nullable = false) // nullable checks whether null accepted, before db throws error
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	//Onetomany -- One library may contain many bookes
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "library") //FetchType.Lazy loads the entities only when necessary good when dealing with lots of records
	public Set<Book> getBooks(){
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}