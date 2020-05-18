package com.elotz.bean;

import java.util.List;

public class GraphData {
private String label;
private List<String> data;
private String backgroundColor;

/**
 * 
 */
public GraphData() {
	super();
	// TODO Auto-generated constructor stub
}
/**
 * @param label
 * @param data
 * @param backgroundColor
 */
public GraphData(String label, List<String> data, String backgroundColor) {
	super();
	this.label = label;
	this.data = data;
	this.backgroundColor = backgroundColor;
}
/**
 * @return the label
 */
public String getLabel() {
	return label;
}
/**
 * @param label the label to set
 */
public void setLabel(String label) {
	this.label = label;
}
/**
 * @return the data
 */
public List<String> getData() {
	return data;
}
/**
 * @param data the data to set
 */
public void setData(List<String> data) {
	this.data = data;
}
/**
 * @return the backgroundColor
 */
public String getBackgroundColor() {
	return backgroundColor;
}
/**
 * @param backgroundColor the backgroundColor to set
 */
public void setBackgroundColor(String backgroundColor) {
	this.backgroundColor = backgroundColor;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "GraphData [label=" + label + ", data=" + data + ", backgroundColor=" + backgroundColor + "]";
}

}
