package com.gen.services;

//Author: Smita Srivastava

import java.util.List;
import java.util.Optional;

import com.gen.entities.Book;
import com.gen.entities.BookJoin;

//Usage of interface for easier and flexible future developments

public interface BookService {
	public List<BookJoin>  join();
	public Book insert(Book student);
	public Book update(Book student);
	public boolean delete(Book student);
	public Iterable<Book> findAll(String libid);
	public void deleteAll();
}
