/**
 * 
 * @author Wendy Li
 * Student number: 251026390
 * CS2210 Assignment 5
 *
 */
public class Node {
	private int name;
	private boolean mark;
	
	/**
	 * Constructor for the class. Creates a node with the name passed through
	 * the parameters 
	 * @param name: the name of the node 
	 */
	public Node(int name) {
		this.name = name;
	}
	
	/**
	 * Marks the node with either true or false
	 * @param mark: a boolean 
	 */
	public void setMark(boolean mark) {
		this.mark = mark;
	}
	
	/**
	 * This function returns the value true if the node has been marked
	 * false otherwise 
	 * @return: a boolean
	 */
	public boolean getMark() {
		return mark;
	}
	
	/**
	 * This function returns the name of the node
	 * @return: an integer
	 */
	public int getName() {
		return name;
	}
}
