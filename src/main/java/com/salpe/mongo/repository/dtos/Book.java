package com.salpe.mongo.repository.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Document
@CompoundIndexes({
		@CompoundIndex(name = "name_group_version", def = "{'name' : 1, 'group': 1, 'version': 1}", unique = true) })
public class Book {

	@Id
	private String id;

	@Indexed
	@Getter
	private String name;

	@Getter
	@Setter
	private String group;
	@Getter
	private int version;
	
	public Book(String name, String group, int version) {
		super();
		this.name = name;
		this.group = group;
		this.version = version;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", group=" + group + ", version=" + version + "]";
	}

}
