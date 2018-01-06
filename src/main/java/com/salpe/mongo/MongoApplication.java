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

		bookRepo.save(new Book("modelName1", "namespace1", 1));
		bookRepo.save(new Book("modelName2", "namespace1", 2));
		bookRepo.save(new Book("modelName2", "namespace2", 1));
		bookRepo.save(new Book("modelName3", "namespace2", 3));

		bookRepo.findByName("modelName1").stream().forEach(e -> System.out.println(e));

		Query query = new Query();
		query.addCriteria(Criteria.where("modelName").is("modelName2"));
		query.addCriteria(Criteria.where("namespace").is("namespace2"));
		query.addCriteria(Criteria.where("version").is(1));

		System.out.println("Using mongo template => " + mongoTemplate.find(query, Book.class));
		System.out.println("Version of books between 3 and 4" + bookRepo.findBooksByVersionBetween(2,4));

	}
}
