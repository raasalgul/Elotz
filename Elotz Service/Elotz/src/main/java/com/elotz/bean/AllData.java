package com.elotz.bean;


import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DailyUpdate")
public class AllData{
	public ObjectId _id;
	public String topic;
	public String task;
	public String time;
	public Boolean active;
	public String year;
	public String month;
	public LocalDateTime addedLogon;
	public LocalDate addedDate;
	/**
	 * 
	 */
	public AllData() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param _id
	 * @param topic
	 * @param task
	 * @param time
	 * @param active
	 * @param year
	 * @param month
	 * @param addedLogon
	 * @param addedDate
	 */
	public AllData(ObjectId _id, String topic, String task, String time, Boolean active, String year, String month,
			LocalDateTime addedLogon, LocalDate addedDate) {
		super();
		this._id = _id;
		this.topic = topic;
		this.task = task;
		this.time = time;
		this.active = active;
		this.year = year;
		this.month = month;
		this.addedLogon = addedLogon;
		this.addedDate = addedDate;
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
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
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
	/**
	 * @return the addedDate
	 */
	public LocalDate getAddedDate() {
		return addedDate;
	}
	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AllData [_id=" + _id + ", topic=" + topic + ", task=" + task + ", time=" + time + ", active=" + active
				+ ", year=" + year + ", month=" + month + ", addedLogon=" + addedLogon + ", addedDate=" + addedDate
				+ "]";
	}
		
}
