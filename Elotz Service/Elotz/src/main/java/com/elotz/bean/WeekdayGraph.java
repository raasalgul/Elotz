package com.elotz.bean;

import java.util.List;

public class WeekdayGraph {
private List<String> id;
private List<String> topics;
private List<Integer> points;

/**
 * @return the id
 */
public List<String> getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(List<String> id) {
	this.id = id;
}
/**
 * @return the topics
 */
public List<String> getTopics() {
	return topics;
}
/**
 * @param topics the topics to set
 */
public void setTopics(List<String> topics) {
	this.topics = topics;
}
/**
 * @return the points
 */
public List<Integer> getPoints() {
	return points;
}
/**
 * @param points the points to set
 */
public void setPoints(List<Integer> points) {
	this.points = points;
}

}
