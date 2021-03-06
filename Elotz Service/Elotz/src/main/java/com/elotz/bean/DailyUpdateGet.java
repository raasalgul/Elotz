package com.elotz.bean;


import java.time.LocalDate;
import java.util.List;

public class DailyUpdateGet {
public String topic;
public List<String> tasks;
public List<String> time;
public List<Boolean> active;
public List<LocalDate> date;

/**
 * 
 */
public DailyUpdateGet() {
	super();
	// TODO Auto-generated constructor stub
}

/**
 * @param topic
 * @param tasks
 * @param time
 * @param active
 * @param date
 */
public DailyUpdateGet(String topic, List<String> tasks, List<String> time, List<Boolean> active, List<LocalDate> date) {
	super();
	this.topic = topic;
	this.tasks = tasks;
	this.time = time;
	this.active = active;
	this.date = date;
}

/**
 * @return the topic
 */
public String getTopic() {
	return topic;
}

/**
 * @param topic the topic to set
 */
public void setTopic(String topic) {
	this.topic = topic;
}

/**
 * @return the tasks
 */
public List<String> getTasks() {
	return tasks;
}

/**
 * @param tasks the tasks to set
 */
public void setTasks(List<String> tasks) {
	this.tasks = tasks;
}

/**
 * @return the time
 */
public List<String> getTime() {
	return time;
}

/**
 * @param time the time to set
 */
public void setTime(List<String> time) {
	this.time = time;
}

/**
 * @return the active
 */
public List<Boolean> getActive() {
	return active;
}

/**
 * @param active the active to set
 */
public void setActive(List<Boolean> active) {
	this.active = active;
}

/**
 * @return the date
 */
public List<LocalDate> getDate() {
	return date;
}

/**
 * @param date the date to set
 */
public void setDate(List<LocalDate> date) {
	this.date = date;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "DailyUpdateGet [topic=" + topic + ", tasks=" + tasks + ", time=" + time + ", active=" + active + ", date="
			+ date + "]";
}

}