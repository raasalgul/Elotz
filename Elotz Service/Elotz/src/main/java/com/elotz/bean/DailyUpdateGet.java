package com.elotz.bean;


import java.time.LocalDateTime;
import java.util.List;

public class DailyUpdateGet {
public String topic;
public List<String> tasks;
public List<String> time;
public List<Boolean> active;
public List<LocalDateTime> addedLogon;
public DailyUpdateGet()
{
	
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
 * @return the addedLogon
 */
public List<LocalDateTime> getAddedLogon() {
	return addedLogon;
}
/**
 * @param addedLogon the addedLogon to set
 */
public void setAddedLogon(List<LocalDateTime> addedLogon) {
	this.addedLogon = addedLogon;
}

}