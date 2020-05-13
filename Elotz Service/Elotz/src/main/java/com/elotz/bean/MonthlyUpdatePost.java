package com.elotz.bean;

public class MonthlyUpdatePost {
	public String topic;
	public String task;
	public String time;
	public String month;
	
	/**
	 * 
	 */
	public MonthlyUpdatePost() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param topic
	 * @param task
	 * @param time
	 * @param month
	 */
	public MonthlyUpdatePost(String topic, String task, String time, String month) {
		super();
		this.topic = topic;
		this.task = task;
		this.time = time;
		this.month = month;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MonthlyUpdatePost [topic=" + topic + ", task=" + task + ", time=" + time + ", month=" + month + "]";
	}
	
}
