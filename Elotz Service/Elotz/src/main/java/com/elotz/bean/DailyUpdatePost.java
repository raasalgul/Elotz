package com.elotz.bean;

public class DailyUpdatePost {
public String topic;
public String task;
public String time;
public Boolean active;
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
 * @return the task
 */
public String getTask() {
	return task;
}
/**
 * @param task the task to set
 */
public void setTask(String task) {
	this.task = task;
}
/**
 * @return the time
 */
public String getTime() {
	return time;
}
/**
 * @param time the time to set
 */
public void setTime(String time) {
	this.time = time;
}
/**
 * @return the active
 */
public Boolean getActive() {
	return active;
}
/**
 * @param active the active to set
 */
public void setActive(Boolean active) {
	this.active = active;
}

}
