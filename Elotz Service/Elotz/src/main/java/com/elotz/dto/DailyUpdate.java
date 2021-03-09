package com.elotz.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DailyUpdate")
public class DailyUpdate{
	public ObjectId _id;
	public String topic;
	public String task;
	public Boolean active;
	public ArrayList<Records> records;
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
	 * @param active
	 * @param records
	 */
	public DailyUpdate(ObjectId _id, String topic, String task, Boolean active, ArrayList<Records> records) {
		super();
		this._id = _id;
		this.topic = topic;
		this.task = task;
		this.active = active;
		this.records = records;
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

	/**
	 * @return the records
	 */
	public ArrayList<Records> getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(ArrayList<Records> records) {
		this.records = records;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DailyUpdate [_id=" + _id + ", topic=" + topic + ", task=" + task + ", active=" + active + ", records="
				+ records + "]";
	}
	}
