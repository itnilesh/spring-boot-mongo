package com.salpe.mongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.salpe.mongo.repository.BookRepository;
import com.salpe.mongo.repository.dtos.Book;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		bookRepo.deleteAll();

		bookRepo.save(new Book("name	1", "group1", 1));
		bookRepo.save(new Book("name2", "group2", 2));
		bookRepo.save(new Book("name3", "group2", 1));
		bookRepo.save(new Book("name3", "group3", 3));

		//  Using query, find by field
		bookRepo.findByName("modelName1").stream().forEach(e -> System.out.println(e));
		
		// Using query with mongo data repository 
		System.out.println("Version of books between 3 and 4 >> " + bookRepo.findBooksByVersionBetween(2,4));

		Query query = new Query();
		
		// projection
		query.fields().exclude("group");
		// AND query
		query.addCriteria(Criteria.where("name").is("name3"));
		query.addCriteria(Criteria.where("group").is("group3"));
		query.addCriteria(Criteria.where("version").is(3));

		System.out.println("Using mongo template => " + mongoTemplate.find(query, Book.class));
	}
}
