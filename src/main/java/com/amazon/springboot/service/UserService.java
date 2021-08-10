package com.amazon.springboot.service;

public interface UserService {
	
	void populateVertexMap(String inptStr);
	
	int countPaths(int s, int d, int pathCount);
	
	int retrieveWeight(String vertx);
	
	void resetArrays();
	
}
