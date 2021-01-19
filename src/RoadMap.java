/**
 * 
 * @author Wendy Li
 * Student number: 251026390
 * CS 2210 Assignment 5
 *
 */

//importing libraries
import java.io.FileReader;
import java.util.Iterator;
import java.util.Stack;
import java.io.BufferedReader;

public class RoadMap {
	
	private int scale;
	private int start;
	private int end;
	private int width;
	private int length; 
	private int initial_budget;
	private int toll;
	private int gain;
	private Graph graph;
	private Stack<Node> stack = new Stack<Node>();

	
	/**
	 * Constructor for RoadMap. It builds a graph from an input
	 * file specified in the parameter. If input file does not exist
	 * it throws a MapException. 
	 * @param inputFile: the file the user wants to open
	 */
	public RoadMap(String inputFile) throws MapException {
		try{
			String line;
			BufferedReader in = new BufferedReader(new FileReader(inputFile)); //reading input file
			
			//reading the file line by line to set variables in constructor
			this.scale = Integer.parseInt(in.readLine()); 
			this.start = Integer.parseInt(in.readLine()); 
			this.end = Integer.parseInt(in.readLine()); 
			this.width = Integer.parseInt(in.readLine());
			this.length = Integer.parseInt(in.readLine());
			this.initial_budget = Integer.parseInt(in.readLine());
			this.toll = Integer.parseInt(in.readLine());
			this.gain = Integer.parseInt(in.readLine());
			
			graph = new Graph(width * length); //initializing the graph
			int count = 0;
			int horizontal_node = 0;
			while((line = in.readLine()) != null) { //while the end of file has not been reached
				
				if ((count % 2) == 0) { //if horizontal road
					for (int j = 0; j < line.length(); j++) {
						if (line.charAt(j) == 'T') { //if road is a toll
							graph.insertEdge(graph.getNode(horizontal_node + (j-1)/2), graph.getNode(horizontal_node + (j-1)/2 + 1), 1);
						}else if (line.charAt(j) == 'F') { //if road is free
							graph.insertEdge(graph.getNode(horizontal_node + (j-1)/2), graph.getNode(horizontal_node + (j-1)/2 + 1), 0);
						}else if (line.charAt(j) == 'C') { //if road is a reward road
							graph.insertEdge(graph.getNode(horizontal_node + (j-1)/2), graph.getNode(horizontal_node + (j-1)/2 + 1), -1);
						}
					}
					horizontal_node += width; //keeps track of the nodes horizontally
				}else { //vertical roads 
					for (int j = 0; j < line.length(); j++) {
						if (line.charAt(j) == 'T') { //if road is a toll
							graph.insertEdge(graph.getNode(horizontal_node + j/2 - width), graph.getNode(horizontal_node + j/2), 1);
						}else if (line.charAt(j) == 'F') { //if road is free
							graph.insertEdge(graph.getNode(horizontal_node + j/2 - width), graph.getNode(horizontal_node + j/2), 0);
						}else if (line.charAt(j) == 'C') { //if road is a reward road 
							graph.insertEdge(graph.getNode(horizontal_node + j/2 - width), graph.getNode(horizontal_node + j/2), -1);
						}
					}
				}
				count += 1; //keeps track of whether the road is a horizontal or vertical road

			}
			
		}catch (Exception e) {
			throw new MapException();
		}
	}
	
	/**
	 * This method returns the graph representing the road map
	 * @return: graph, a type Graph
	 */
	public Graph getGraph() {
		return graph;
	}
	
	/**
	 * This method returns the starting node
	 * @return: start, an int (name of the starting node)
	 */
	public int getStartingNode() {
		return start;
	}
	
	/**
	 * This method returns the destination node
	 * @return: end, int (name of the destination node)
	 */
	public int getDestinationNode() {
		return end;
	}
	
	/**
	 * This method returns the initial amount of money available
	 * to pay tolls
	 * @return: initial_budget, an int
	 */
	public int getInitialMoney() {
		return initial_budget;
	}
	
	/**
	 * This method returns an Iterator containing the nodes of a path from the
	 * start node to the destination node if the path exists. Otherwise, returns null 
	 * @param start: the starting node of path
	 * @param destination: the ending node of path 
	 * @param initialMoney: the initial budget 
	 * @return: an iterator showing the path of the nodes, otherwise return null 
	 */
	public Iterator findPath(int start, int destination, int initialMoney) {
		Node node = new Node(start);
		node.setMark(false);
		boolean path_found = false; //boolean indicating if path exists 
		
		Iterator<Edge> start_edges = graph.incidentEdges(node);
		
		path_found = DFStraversal(node, destination, initialMoney);
	
		if (path_found == true) { //if path exists, return the stack of nodes
			return stack.iterator();
		}else { //if path doesn't exist, return null
			return null;
		}

	}
	
	/**
	 * This method is a recursive function that performs a depth first traversal to find path 
	 * from the starting node to the destination node in the graph. Returns true if the path 
	 * exists, false otherwise
	 * @param current: the current node that the path is at in the graph
	 * @param destination: the destination node of the graph
	 * @param initialMoney: the initial budget 
	 * @return: true if path exists and false if the path does not exist 
	 */
	private boolean DFStraversal(Node current, int destination, int initialMoney) {
		Iterator<Edge> allEdges = graph.incidentEdges(current);
		
		int budget = initialMoney;
		stack.push(current);
		current.setMark(true);
		
		if (current.getName() == destination) { //if the path has reached its destination,
			//return true
			return true;
		}
		
		//recursive call 
		while (allEdges.hasNext()) { //while the node still edges
			Edge currentEdge = allEdges.next(); 
					if (currentEdge.secondEndpoint().getMark() == false) { //if the other end point of the
						//edge is not marked, then traverse the edge
						//checking edge type
						if (currentEdge.getType() == 1) { //if the edge is a toll route
							budget = initialMoney - toll; //subtracting toll amount from initialMoney
						}else if (currentEdge.getType() == -1) { //if edge is a reward route
							budget = initialMoney + gain;  //adding the reward amount to initialMoney
						}
						if (budget >= 0) { //while you still have enough money
							if (DFStraversal(currentEdge.secondEndpoint(), destination, budget) == true) { //recursively
								//call this function
								return true;
							}
						}
					}
		}
		
		current.setMark(false); //unmarking the node so that this algorithm can traverse through other 
		//edges of this node 
		stack.pop();//pops the node so that it doesn't show up in the path
		return false;
	}
}
