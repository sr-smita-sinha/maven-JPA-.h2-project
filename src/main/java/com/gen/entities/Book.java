package com.gen.entities;

//Author: Smita Srivastava

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Entity class Represents the record in book table

@Entity
@Table(title="book")
public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String title;
	private String author;
	private Library library; 
	
	public Book() {}
	
	public Book(String title,String author) {
		this.title = title;
		this.author = author;
	}
	
	public Book(Integer id,String title,String author,Library library) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.library = library;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(title="id",unique = true,nullable = false)
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(title="title",nullable = false)
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	@Column(title="author",nullable = false)
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)//Manytoone since many books belong to one library
	@JoinColumn(title="id",nullable = false)
	public Library getLibrary() {
		
		return this.library;
	}
	
	public void setLibrary(Library library) {
		this.library= library;
	}
}