/**
 * 
 * @author Wendy Li
 * Student number: 251026390
 * CS2210 Assignment 5
 *
 */
public class Edge {
	
	private Node end1;
	private Node end2;
	private int type; 
	
	/**
	 * Constructor for the class. Sets type to 0 for a public road, 
	 * 1 for a private road and -1 for a reward road
	 * @param u: first end point of the edge
	 * @param v: second end point of the edge
	 * @param type: the type of edge 
	 */
	public Edge(Node u, Node v, int type) {
		this.end1 = u;
		this.end2 = v;
		this.type = type;
	}
	
	/**
	 * This function returns the first end point
	 * @return: returns the first end point of the edge
	 */
	public Node firstEndpoint() {
		return end1;
	}
	
	/**
	 * This function returns the second end point
	 * @return: returns type node of the second end point of edge
	 */
	public Node secondEndpoint() {
		return end2;
	}
	
	/**
	 * Returns the type of the edge
	 * @return: type int. 0 for public road, 1 for private road and 
	 * -1 for reward road
	 */
	public int getType() {
		return type;
	}
}
