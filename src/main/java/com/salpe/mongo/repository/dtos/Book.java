package com.salpe.mongo.repository.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;

@CompoundIndex()
public class Book {
	
	@Id
	private String id;
	private String name;
	private String group;
	private int version;
	
	public Book() {
	}
	public Book(String name, String group, int version) {
		super();
		this.name = name;
		this.group = group;
		this.version = version;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", group=" + group + ", version=" + version + "]";
	}
	
}
