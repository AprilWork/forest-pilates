package com.kukvar.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="class_type")
@Table(name="class_type")
public class Category {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private int id;
@Column(name="name_type", nullable=false)
private String name;

@OneToMany(mappedBy="category", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
private Set<Group> groups = new HashSet<Group>();

public Category() { }

public Category(String name) {
	super();
	this.name = name;
}

public Set<Group> getGroups() {
	return groups;
}

public void addGroup(Group group) {
	groups.add(group);
	group.setCategory(this);
}	

public void removeGroup(Group group) {
		groups.remove(group);
		group.setCategory(null);
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