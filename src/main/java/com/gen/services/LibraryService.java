package com.gen.services;

//Author: Smita Srivastava

import java.util.Optional;

import com.gen.entities.Library;

//Usage of interface for easier and flexible future developments

//Defines the methods used to Controller to service the requests
public interface LibraryService {
	public Optional<Library> find(Integer id);
}
