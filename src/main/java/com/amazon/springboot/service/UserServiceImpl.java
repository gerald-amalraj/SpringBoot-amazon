package com.amazon.springboot.service;

import static com.amazon.springboot.helper.Constants.COMMA_SEPRATED;
import static com.amazon.springboot.helper.Constants.HYPHEN_SEPRATED;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazon.springboot.helper.Helper;

@Service("userService")
public class UserServiceImpl implements UserService{

	private int terminateCount = 0;
	private LinkedList<Integer> adj[] =	Helper.getAdjcentList();
	private Map<String, Integer> vertexMap;
	int pathCount;
	
	public int retrieveWeight(String vertx) {
		int sumCount = 0;
		String[] testVertices = vertx.split(HYPHEN_SEPRATED);
		for (int i = 0; i < testVertices.length - 1; i++) {
			if (i + 1 != testVertices.length) {
				if (vertexMap.get(testVertices[i] + testVertices[i + 1]) != null)
					sumCount = sumCount + vertexMap.get(testVertices[i] + testVertices[i + 1]);
				else
					sumCount = 0;
			}
		}
		return sumCount;
	}
	
	public void populateVertexMap(String inptStr) {
		vertexMap = new HashMap<String, Integer>();	
		String[] vertices = inptStr.split(COMMA_SEPRATED);

		for (int j = 0; j < vertices.length; j++) {
			if (vertices[j].length() == 3) {
				String[] vertex = vertices[j].split("");
				vertexMap.put(vertex[0] + vertex[1], Integer.valueOf(vertex[2]));
				addEdge(Integer.valueOf(Helper.getMapObject().get(vertex[0])),
						Integer.valueOf(Helper.getMapObject().get(vertex[1])));
			}
		}
	}
	
	public int countPaths(int s, int d, int pathCount) {
		this.pathCount = pathCount;
		boolean visited[] = new boolean[Helper.getMapObject().size()];
		Arrays.fill(visited, false);
		pathCount = countPathsUtil(s, d, visited, pathCount);
		return pathCount;
	}
	
	private int countPathsUtil(int u, int d, boolean visited[], int pathCount) {
		visited[u] = true;
		if (u == d || terminateCount > 2) {
			if (terminateCount <= d)
				pathCount++;
			terminateCount = 0;
		} else {
			
			terminateCount++;
			Iterator<Integer> i = adj[u].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					pathCount = countPathsUtil(n, d, visited, pathCount);
				}
			}
		}
		visited[u] = false;
		return pathCount;
	}
	
	private void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public void resetArrays(){
		adj =	Helper.getAdjcentList();
	}
	
	/*public static void main(String[] args) {
		String str = "AB3, BC9, CD3, DE6, AD4, DA5, CE2, AE4, EB1";
		UserServiceImpl usr =  new UserServiceImpl();
		usr.populateVertexMap(str);

		String[] edges = new String[] { "A-B-C", "A-E-B-C-D", "A-E-D" };
		for (String edge : edges) {
			int weight = usr.retrieveWeight(edge);
			if (weight != 0)
				System.out.println(edge + " :: " + weight);
			else
				System.out.println(edge + " :: " + "Path Not Found");
		}
		int s = 0, d = 2, pathCount = 0;
		System.out.println("Total Path:: " + usr.countPaths(s, d, pathCount));
	}*/
}
