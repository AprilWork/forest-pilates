package com.kukvar.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="classes")
@Table(name="classes")
public class Group {
	@Id
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;	
	@Column(name="description")
	private String description;	
	@Column(name="file_image_name")
	private String nameImageFile;
	
	protected Group() {	}

	protected Group(int id, String name, String description, String nameImageFile) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.nameImageFile = nameImageFile;
	}

	public Group(String name, String description, String nameImageFile) {
		super();
		this.name = name;
		this.description = description;
		this.nameImageFile = nameImageFile;
	}
	
	public Group(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameImageFile() {
		return nameImageFile;
	}

	public void setNameImageFile(String nameImageFile) {
		this.nameImageFile = nameImageFile;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description=" + description + ", nameImageFile=" + nameImageFile
				+ "]";
	}
	
}
