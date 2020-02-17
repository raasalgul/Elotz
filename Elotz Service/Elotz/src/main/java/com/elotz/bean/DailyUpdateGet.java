package com.elotz.bean;

import java.util.List;

public class DailyUpdateGet {
public String topic;
public List<String> tasks;
public List<String> time;
public DailyUpdateGet()
{
	
}
public DailyUpdateGet(String topic, List<String> tasks, List<String> time) {
	super();
	this.topic = topic;
	this.tasks = tasks;
	this.time = time;
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
 * @param react the tasks to set
 */
public void setTasks(List<String> react) {
	this.tasks = react;
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


}
