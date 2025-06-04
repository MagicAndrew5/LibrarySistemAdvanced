package com.unimib.librarysystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.unimib.librarysystem.model.Genre;

/**
 * The repository for the genre entity.
 * This interface extends CrudRepository and provides methods to perform CRUD operations on the genre entity.
 */
public interface GenreRepository extends CrudRepository<Genre, Integer> {
}
