package com.gen.repositories;

//Author: Smita Srivastava

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gen.entities.Library;


@Repository("libraryRepository") // sets role - marks this as DAO -- Data access object
public interface LibraryRepository extends CrudRepository<Library,Integer>{

}