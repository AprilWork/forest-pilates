package com.kukvar.hibernate.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="activity")
@Table(name="activity")
public class Activity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private long id;

@ManyToOne(cascade = {CascadeType.PERSIST})
@JoinColumn(name="group_id")
private Group group;

@ManyToOne(cascade = {CascadeType.PERSIST})
@JoinColumn(name="instructor_id")
private Instructor instructor;

@Enumerated(value = EnumType.STRING)
@Column(name="day_Of_Week")
private DayOfWeek dayOfWeek;

@Column(name="begin_Activity")
private LocalTime beginActivity;
@Column(name="end_Activity")
private LocalTime endActivity;

@Column(name="capacity")
private int capacity;
@Column(name="booked")
private int booked;

public Activity() { }

public Activity(Group group, Instructor instructor, DayOfWeek dayOfWeek,
		LocalTime beginActivity, LocalTime endActivity, int capacity, int booked) {
	this.group = group;
	this.instructor = instructor;
	this.dayOfWeek = dayOfWeek;
	this.beginActivity = beginActivity;
	this.endActivity = endActivity;
	this.capacity = capacity;
	this.booked = booked;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public Group getGroup() {
	return group;
}

public void setGroup(Group group) {
	this.group = group;
}

public Instructor getInstructor() {
	return instructor;
}

public void setInstructor(Instructor instructor) {
	this.instructor = instructor;
}

public DayOfWeek getDayOfWeek() {
	return dayOfWeek;
}

public void setDayOfWeek(DayOfWeek dayOfWeek) {
	this.dayOfWeek = dayOfWeek;
}

public LocalTime getBeginActivity() {
	return beginActivity;
}

public void setBeginActivity(LocalTime beginActivity) {
	this.beginActivity = beginActivity;
}

public LocalTime getEndActivity() {
	return endActivity;
}

public void setEndActivity(LocalTime endActivity) {
	this.endActivity = endActivity;
}

public int getCapacity() {
	return capacity;
}

public void setCapacity(int capacity) {
	this.capacity = capacity;
}

public int getBooked() {
	return booked;
}

public void setBooked(int booked) {
	this.booked = booked;
}

@Override
public String toString() {
	return "Activity [group=" + group + ", Instructor=" + instructor + ", dayOfWeek=" + dayOfWeek + ", beginActivity="
			+ beginActivity + ", endActivity=" + endActivity + ", capacity=" + capacity + ", booked=" + booked + "]";
}





}
