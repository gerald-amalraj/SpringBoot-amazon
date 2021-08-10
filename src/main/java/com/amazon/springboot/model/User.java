package com.amazon.springboot.model;

public class User {

	private String graphPath;
	private int totalPath;
	private String weight_1;
	private String weight_2;
	private String weight_3;
	
	public String getGraphPath() {
		return graphPath;
	}
	public void setGraphPath(String graphPath) {
		this.graphPath = graphPath;
	}
	public int getTotalPath() {
		return totalPath;
	}
	public void setTotalPath(int totalPath) {
		this.totalPath = totalPath;
	}
	public String getWeight_1() {
		return weight_1;
	}
	public void setWeight_1(String weight_1) {
		this.weight_1 = weight_1;
	}
	public String getWeight_2() {
		return weight_2;
	}
	public void setWeight_2(String weight_2) {
		this.weight_2 = weight_2;
	}
	public String getWeight_3() {
		return weight_3;
	}
	public void setWeight_3(String weight_3) {
		this.weight_3 = weight_3;
	}
	@Override
	public String toString() {
		return "User [graphPath=" + graphPath + ", totalPath=" + totalPath + ", weight_1=" + weight_1 + ", weight_2="
				+ weight_2 + ", weight_3=" + weight_3 + "]";
	}
}
