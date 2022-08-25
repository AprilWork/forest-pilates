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

@Entity(name="instructors")
@Table(name="instructors")
public class Instructor {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private int id;
@Column(name="name", nullable=false)
private String name;

@OneToMany(mappedBy="instructor", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
private Set<Activity> activities = new HashSet<Activity>();

public Instructor() { }


public Instructor(String name) {
	this.name = name;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}


public Set<Activity> getActivities() {
	return activities;
}


public void addActivitiy(Activity activity) {
	activities.add(activity);
	activity.setInstructor(this);
}

public void removeActivitiy(Activity activity) {
	activities.remove(activity);
	activity.setInstructor(null);
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}



	
}
