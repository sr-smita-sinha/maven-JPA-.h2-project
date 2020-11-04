package com.gen.base.rest;

//Author: Smita Srivastava

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gen.entities.*;
import com.gen.services.*;

@RestController //is a convenience annotation that adds @Controller and @ResponseBody annotations
public class JPAwithh2Controller {
	
	@Autowired //tells the spring that, injection need to occur here, then spring searches for that BookService component and injects it here
	BookService bookService; // remember @Service is also sub type of @Component
	
	@Autowired
	LibraryService libraryService;
	
	// This prints the joined view of Library and Book tables`
	@RequestMapping(value="/jointable")  
	public List<BookJoin> displayJoinTable(){
		return this.bookService.join();     
	}
	
	
	
	//Finds Book details for the given id 
	@RequestMapping(value="/book/find/{id}")
	public Map<String,String> find(@PathVariable Integer id){
		//@PathVariable takes the part of url as value her {id} taken as id value
		Map<String,String> message = new LinkedHashMap<>(); // to store book details
		Book foundBook;
		//this.bookService.find(id) returns Optional<> whose methods are isPresent and get()
		if(this.bookService.find(id).isPresent()) { // if the given book id is present
			foundBook = this.bookService.find(id).get(); //fetches the book record from the database
			//Getting book detail with help of getter methods
			message.put("ID", foundBook.getId().toString());
			message.put("Title", foundBook.getTitle());
			message.put("Author", foundBook.getAuthor());
		}
		else { 
			message.put("Error","Cannot find book with id "+id);
		}
		return message;
	}
	
	@RequestMapping(value="/book/findall/{libid}")// Lists all the books record in the Book table for a library
	public List<Map<String,String>> findAll(@PathVariable Integer libid){
		Map<String,String> message ;
		List<Map<String,String>> listOfMessages = new LinkedList<>();
		Book foundBook;
		Iterator<Book> iterator = this.bookService.findAll(libid).iterator(); 
		if(iterator.hasNext()) { // if table is not empty
			while(iterator.hasNext()) {
				foundBook = iterator.next(); // fetch record one by one
				message = new LinkedHashMap<>(); // to store invidual's info
				message.put("ID", foundBook.getId().toString());
				message.put("Title", foundBook.getTitle());
				message.put("Author", foundBook.getAuthor());
				message.put("LibraryName", foundBook.getLibrary().getName())
				listOfMessages.add(message); // list of book's info
			}
		}
		else { 
			message = new LinkedHashMap<>();
			message.put("Error","No books found!");
			listOfMessages.add(message);
		}
		return listOfMessages;
	}
	
	
	@RequestMapping(value="/book/deleteAll/{libid}") // truncates the book table of a library
	public List<Map<String,String>> deleteAll(@PathVariable Integer libid){
		Map<String,String> message ;
		List<Map<String,String>> listOfMessages = new LinkedList<>();
		Book foundBook;
		Iterator<Book> iterator = this.bookService.findAll(libid).iterator();
		if(iterator.hasNext()) {
			message = new LinkedHashMap<>(); //LinkedHashMap to preserve the message order
			message.put("Success", "List of deleted book list");
			listOfMessages.add(message);
			while(iterator.hasNext()) {
				foundBook = iterator.next();
				message = new LinkedHashMap<>();
				message.put("ID", foundBook.getId().toString());
				message.put("Title", foundBook.getTitle());
				message.put("Author", foundBook.getAuthor());
				message.put("LibraryName", foundBook.getLibrary().getName());
				listOfMessages.add(message);
			}
			this.bookService.deleteAll(libid);
		}
		else { 
			message = new LinkedHashMap<>();
			message.put("Error","No books found!");
			listOfMessages.add(message);
		}
		return listOfMessages;
	}
	
	
	@RequestMapping(value="/book/insert")//inserts new book record into the table
	public Map<String,String> insertBook(@RequestParam("title") String name,@RequestParam("author") String author,@RequestParam("libid") int libid){
		//RequestParam gets the value from url
		Map<String,String> message = new LinkedHashMap<>();
		Book newBook;
		if(this.libraryService.find(libid).isPresent()) {
			newBook = new Book();
			newBook.setTitle(title);
			newBook.setAuthor(author);
			newBook.setLibrary(this.libraryService.find(libid).get());
			
			if(this.bookService.insert(newBook)!=null) {
				message.put("Success", "New book successfully added!");
				message.put("ID",newBook.getId().toString());
				message.put("Title", name);
				message.put("Author", author+"");
				message.put("LibraryName", this.libraryService.find(libid).getName());
			}
			else
				message.put("Error", "Error cannot add new book");
		}
		else {
			message.put("Error", "Library id "+libid+" is not found");
		}
		return message;
	}
	
	@RequestMapping(value="/book/update") //Updates book table
	public Map<String,String> updateBook(@RequestParam("id") Integer id,@RequestParam("title") String name,@RequestParam("author") String author,@RequestParam("libid") int libid){
		System.out.println("\nrunning update");
		Map<String,String> message = new LinkedHashMap<>();
		Book newBook;
		if(this.bookService.find(id).isPresent()) {
			if(this.libraryService.find(libid).isPresent()) {
				newBook = this.bookService.find(id).get();
				newBook.setTitle(title);
				newBook.setAuthor(author);
				newBook.setLibraryName(this.libraryService.find(libid).getName());
				
				if(this.bookService.update(newBook)!=null) {
					message.put("Success", "Book details successfully updated");
					message.put("ID",newBook.getId().toString());
					message.put("Title", name);
					message.put("Author", author+"");
					message.put("LibraryName", this.libraryService.find(libid).getName());
				}
				else
					message.put("Error", "Error cannot update book");
			}
			else 
				message.put("Error", "Library id "+libid+" is not found");
		}
		else
			message.put("Error", "Book id "+id+" is not found");
		
		return message;
	}
	
	
	@RequestMapping(value="/book/delete/{id}")//deletes record from book table for the given id
	public Map<String,String> deleteBook(@PathVariable("id") Integer id){
		System.out.println("\nrunning delete");
		Map<String,String> message = new LinkedHashMap<>();
		Book oldBook;
		if(this.bookService.find(id).isPresent()) {	
			oldBook = this.bookService.find(id).get();
			
			message.put("ID", oldBook.getId().toString());
			message.put("Title", oldBook.getName());
			message.put("Author", oldBook.getAuthor().toString());
			message.put("LibraryName", oldBook.getLibrary().getName());
			
			if(this.bookService.delete(oldBook)) 
				message.put("Success", "Book successfully removed");
			else {
				message.clear();
				message.put("Error", "Error cannot delete book");
			}
		}
		else
			message.put("Error", "Book id "+id+" is not found");
		return message;
	}
}