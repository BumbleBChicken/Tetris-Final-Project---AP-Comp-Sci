/*
 *	Author:  
 *  Date: 
*/
package pkg2;
import pkg.*;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class BasicCube {

	private int x;
	private int y;
	private String color;
	private Picture block;

	public BasicCube(int x, int y, String type){
		color = type;
		block = getPicture(type);
		block.translate(x, y);
	}

	public double getWidth(){
		return block.getWidth();
	}

	public double getHeight(){
		return block.getHeight();
	}

	public void draw(){
		block.draw();
	}

	public void undraw(){
		block.undraw();
	}

	public void translate(double x, double y){
		this.x+=x;
		this.y+=y;
		block.translate(x, y);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public String getColor(){
		return color;
	}

	public Picture getPicture(String colour){
		if(colour.equalsIgnoreCase("I")){
			return new Picture("light_blue.jpg");
		}
		else if(colour.equalsIgnoreCase("J")){
			return new Picture("blue.jpg");
		}
		else if(colour.equalsIgnoreCase("L")){
			return new Picture("orange.jpg");
		}
		else if(colour.equalsIgnoreCase("O")){
			return new Picture("yellow.jpg");
		}
		else if(colour.equalsIgnoreCase("S")){
			return new Picture("green.jpg");
		}
		else if(colour.equalsIgnoreCase("T")){
			return new Picture("purple.jpg");
		}
		else if(colour.equalsIgnoreCase("border")){
			return new Picture("gray.jpg");
		}
		else{ //if "Z"
			return new Picture("red.jpg");
		}
	}
}