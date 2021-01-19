/**
 * @author Wendy Li
 * Student number: 251016390
 * CS2210 Assignment 5
 */

//importing libraries
import java.util.Iterator;
import java.util.ArrayList;

public class Graph implements GraphADT{
	private int numNodes;
	private Node[] Nodes;
	private Edge [][] Matrix;
	

	/**
	 * Constructor for the class
	 * @param n: the number of nodes
	 */
	public Graph(int n) {
		Matrix = new Edge[n][n]; //stores the edges
		Nodes = new Node[n]; //stores the nodes 
		numNodes = n;
		
		for (int i = 0; i < n; i++) {
			Nodes[i] = new Node(i); //numbering the nodes
		}
	}
	

	/**
	 * This function returns the node of the name passed through the 
	 * parameter. Throws a graph exception if node does not exist 
	 * @param name:the name of the node
	 * @return returns the node with the name 
	 */
	public Node getNode(int name) throws GraphException{
		if (numNodes < name) { //if the node we are looking for doesn't exist, we throw an 
			//exception
			throw new GraphException(); //throw graph exception
		}else {
			return Nodes[name];
		}
	}
	
	/**
	 * This function creates a new edge between two endpoints node u and 
	 * node v and specifies the edgetype. If the edge already exists
	 * this function throws a graph exception.
	 * @param u: a node in the graph
	 * @param v: a node in the graph
	 * @param edgeType: the type of edge between nodes u and v
	 * @return 
	 */
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException{
		if ((u.getName() >= numNodes) || (v.getName() >= numNodes)){ //if node does not exist
			//throw a graph exception
			throw new GraphException();
		}
		
		if ((Matrix[u.getName()][v.getName()] != null) && (Matrix[v.getName()][u.getName()] != null)){
			throw new GraphException(); //if edge already exists between the two nodes throw a graph exception
		}else {
			Edge edge1 = new Edge(u, v, edgeType); //initializing a new edge
			Edge edge2 = new Edge(v, u, edgeType);
			Matrix[u.getName()][v.getName()] = edge1; //setting the edge into the matrix 
			Matrix[v.getName()][u.getName()] = edge2;
		}
	}
	
	/**
	 * This function returns an iterator of array list of all the edges
	 * that are connected to the node passed through the parameters. 
	 * If the node does not exist, throw a new graph exception
	 * @param node: a node 
	 * @return incidents: returns an iterator of the edges of node u
	 */
	public Iterator incidentEdges(Node u) throws GraphException{
		if (u.getName() >= numNodes) { //if node does not exist throw a new graph exception
			throw new GraphException();
		}
		ArrayList<Edge> incidents = new ArrayList<Edge>();
		for (int i = 0; i < numNodes; i++) { //iterating through all the edges of node u 
			if(Matrix[u.getName()][i] != null) {
				incidents.add(Matrix[u.getName()][i]); //adding the edges to the array list incidents
			}
		}
		
		if (incidents.isEmpty()) { //if there are no edges attached the node return null
			return null;
		}else{
			return incidents.iterator();
		}
	}
	
	/**
	 * This function returns the edge connecting nodes u and v. It throws a graph exception if there
	 * is no edge between u and v
	 * @param u: one end point of the edge
	 * @param v: second end point of the edge
	 * @return edge: the edge connecting nodes u and v  
	 */
	public Edge getEdge(Node u, Node v) throws GraphException{
		if ((u.getName() >= numNodes) || (v.getName() >= numNodes)) { //if the node does not exist
			throw new GraphException();
		}
		if ((Matrix[u.getName()][v.getName()] == null) && (Matrix[v.getName()][u.getName()]) == null) {
			//if the edge does not exist throw a graph exception
			throw new GraphException();
		}else {
			return Matrix[u.getName()][v.getName()]; //returning the edge between nodes u and v
		}
	}

	/**
	 * This function returns true if odes u and v are adjacent. Otherwise it returns false
	 * @param u: one end point of the edge
	 * @param v: a second end point of edge
	 * @return: returns true if there is an edge between the two nodes, otherwise return false
	 */
	public boolean areAdjacent(Node u, Node v) {
		if ((u.getName() >= numNodes) || (v.getName() >= numNodes)) { //if one of the nodes does not exist
			//throw a graph exception 
			throw new GraphException();
		}
		if ((Matrix[u.getName()][v.getName()] == null) && (Matrix[v.getName()][u.getName()] == null)) {
			return false; //if the edge does not exist between node u and node v return false
		}else {
			return true;
		}
	}
}
