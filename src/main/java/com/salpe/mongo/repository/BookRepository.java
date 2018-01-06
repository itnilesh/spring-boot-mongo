package com.salpe.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.salpe.mongo.repository.dtos.Book;


public interface BookRepository extends MongoRepository<Book, String> {

	public Book findById(String id);

	public List<Book> findByName(String modelName);
	
	@Query("{ 'version' : { $gt: ?0, $lt: ?1 } }")
	List<Book> findBooksByVersionBetween(int versionGT, int versionLT);
}