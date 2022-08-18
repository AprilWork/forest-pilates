package com.kukvar.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="classes")
@Table(name="classes")
public class Group {
	static final String DEFAULT_IMAGE = "default.jpg";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name", nullable=false)
	private String name;	
	@Column(name="description")
	private String description;	
	@Column(name="file_image_name")
	private String nameImageFile;
	@ManyToOne
	@JoinColumn(name="type_id")
	private Category category;
	
	protected Group() {	}

	public Group(String name, String description, String nameImageFile, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.nameImageFile = imageNameHelper(nameImageFile);
		this.category = category;
	}

	/*
	public Group(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.nameImageFile = imageNameHelper("");
	}
	*/
	
	private String imageNameHelper(String image) {
		if (image == null || image.isBlank()) { return "default.jpg"; }
		else return image;
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description=" + description + ", nameImageFile=" + nameImageFile
				+ ", category=" + category.getName() + "]";
	}
	
}
