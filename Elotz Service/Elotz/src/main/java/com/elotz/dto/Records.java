package com.elotz.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Records {
	public String time;
	public LocalDateTime addedLogon;
	public LocalDate addedDate;
	/**
	 * 
	 */
	public Records() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param time
	 * @param addedLogon
	 * @param addedDate
	 */
	public Records(String time, LocalDateTime addedLogon, LocalDate addedDate) {
		super();
		this.time = time;
		this.addedLogon = addedLogon;
		this.addedDate = addedDate;
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
		return "Records [time=" + time + ", addedLogon=" + addedLogon + ", addedDate=" + addedDate + "]";
	}
	
}
