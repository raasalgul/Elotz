package com.elotz.bean;


import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DailyUpdate")
public class DailyUpdate{
	public ObjectId _id;
	public String topic;
	public String task;
	public String time;
	public Boolean active;
	public LocalDateTime addedLogon;
	
	/**
	 * 
	 */
	public DailyUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param _id
	 * @param topic
	 * @param task
	 * @param time
	 * @param active
	 * @param addedLogon
	 */
	public DailyUpdate(ObjectId _id, String topic, String task, String time, Boolean active, LocalDateTime addedLogon) {
		super();
		this._id = _id;
		this.topic = topic;
		this.task = task;
		this.time = time;
		this.active = active;
		this.addedLogon = addedLogon;
	}

	/**
	 * @return the _id
	 */
	public ObjectId get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(ObjectId _id) {
		this._id = _id;
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
	public Boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * @return the addedLogon
	 */
	public LocalDateTime getAddedLogon() {
		return addedLogon;
	}

	/**
	 * @param addedLogon the addedLogon to set
	 */
	public void setAddedLogon(LocalDateTime addedLogon) {
		this.addedLogon = addedLogon;
	}
	
	
}
