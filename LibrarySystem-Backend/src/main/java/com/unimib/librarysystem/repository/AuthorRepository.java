package com.unimib.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.unimib.librarysystem.model.Author;


/**
 * The repository for the author entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the Author entity.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
