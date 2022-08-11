package com.kukvar.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="class_type")
@Table(name="class_type")
public class Category {
@Id
@Column(name="id")
private int id;
@Column(name="name_type")
private String name;

public Category() { }

public Category(int id, String name) {
	super();
	this.id = id;
	this.name = name;
}

public Category(String name) {
	super();
	this.name = name;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "Category [id=" + id + ", name=" + name + "]";
}
 
}