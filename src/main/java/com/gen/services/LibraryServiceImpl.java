package com.gen.services;

//Author: Smita Srivastava

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gen.repositories.LibraryRepository;
import com.gen.entities.Library;


@Transactional //Spring dynamically creates a proxy that implements the same interface(s) as the class that gets annotated
@Service("libraryService") // to detected by @Autowired
public class LibraryServiceImpl implements LibraryService{

	@Autowired //automatically injects the implementation of crudrepo
	private LibraryRepository libraryRepository;
	
	@Override
	public Optional<Library> find(Integer id) {
		return this.libraryRepository.findById(id); //default crudrepo methods
	}
}