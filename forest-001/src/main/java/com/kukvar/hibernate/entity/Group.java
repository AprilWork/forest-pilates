package com.kukvar.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
	@JoinColumn(name="type_id" )
	private Category category;
	
	@OneToMany(mappedBy="group", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Activity> activities = new HashSet<Activity>();

	protected Group() {	}

	public Group(String name, String description, String nameImageFile, Category category) {
		super();
		this.name = name;
		this.description = description;
		this.nameImageFile = imageNameHelper(nameImageFile);
		this.category = category;
	}


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
	
	
	public Set<Activity> getActivities() {
		return activities;
	}

	public void addActivitiy(Activity activity) {
		activities.add(activity);
		activity.setGroup(this);
	}

	public void removeActivitiy(Activity activity) {
		activities.remove(activity);
		activity.setGroup(null);
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", description=" + description + ", nameImageFile=" + nameImageFile + ", category="
				+ category + ", activities=" + activities + "]";
	}



}
