package com.gen.entities;

//Author: Smita Srivastava

import java.io.Serializable;

// This entity represents the join between book and library

public class BookJoin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String author;
	private String libraryName;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getLibraryName() {
		return this.libraryName;
	}
	
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	
	public BookJoin(){
		super();
	}
	
	public BookJoin(Integer id,String title,String author,String libraryName) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.libraryName = libraryName;
	}
}