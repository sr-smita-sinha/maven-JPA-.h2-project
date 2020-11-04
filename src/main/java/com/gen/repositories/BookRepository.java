package com.gen.repositories;

//Author: Smita Srivastava
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gen.entitieb.*;

@Repository("bookRepository") // sets role - marks this as DAO -- Data access object
public interface BookRepository extends CrudRepository<Book,Integer>{
	
	// @Query and new methods are used when some operation cannot be done by simple CrudRepository methods alone
	
	// performs join between book and library
	// uses bookjoin to hold the projection from the join
	@Query("select new com.gen.entities.BookJoin(b.id,b.title,b.author,l.name) from Book b join Library l on b.library.id =l.id") // and inner join
	public List<BookJoin> join();

	@Query("select new com.gen.entities.BookJoin(b.id,b.title,b.author,l.name) from Book b join Library l on b.library.id =l.id where b.library.id =:libid")
	public List<BookJoin> findAll(@Param("libid") Integer libid);
	
	@Query("delete from Book where b.library.id =:libid")
	public List<BookJoin> deleteAll(@Param("libid") Integer libid);
	
}