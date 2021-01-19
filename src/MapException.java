/**
 * 
 * @author Wendy Li 
 * Student number: 251026390
 * CS 2210 Assignment 5 
 *
 */
public class MapException extends RuntimeException{
	
	//catches the exception
	public MapException() {
		super ("This map does not exist"); //prints this message 
	}
}
