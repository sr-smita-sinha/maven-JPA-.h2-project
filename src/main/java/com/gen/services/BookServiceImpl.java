package com.gen.services;

//Author: Smita Srivastava

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gen.entities.Book;
import com.gen.entities.BookJoin;
import com.gen.repositories.BookRepository;

@Transactional
@Service("bookService")
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<BookJoin> join(){
		System.out.println("\nrunning service");
		return bookRepository.join();
	}
	

	@Override
	public Book insert(Book book) {
		
		return this.bookRepository.save(book);
	}

	@Override
	public Book update(Book book) {

		return this.bookRepository.save(book);
	}

	@Override
	public boolean delete(Book book) {
		this.bookRepository.delete(book);
		return true;
	}

	@Override
	public Iterable<Book> findAll(Integer libid) {
		return this.bookRepository.findAll(libid);
	}

	@Override
	public Iterable<Book> find(Integer id) {
		return this.bookRepository.find(id);
	}

	@Override
	public void deleteAll(Integer libid) {
		this.bookRepository.deleteAll(libid);
	}
}
