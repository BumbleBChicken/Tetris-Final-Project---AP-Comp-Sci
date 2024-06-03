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

	private int xInsideBlock;
	private int yInsideBlock;
	private int xBlock;
	private int yBlock;
	private String color;
	private Rectangle OutsideRect;
	private Rectangle InsideRect;
	private Color OutsideColor;
	private Color InsideColor;

	public BasicCube(int x, int y, String type){
		color = type;
		xBlock = 25;
		yBlock = 25;
		xInsideBlock = 21;
		yInsideBlock = 21;
		OutsideRect = new Rectangle(x, y, xBlock, yBlock);
		InsideRect = new Rectangle(x+2, y+2, xInsideBlock, yInsideBlock);
		//OutsideColor = colorPicker(type);
		//InsideColor = secColorPicker(type);
		OutsideRect.setColor(colorPicker(type)/*OutsideColor*/);
		InsideRect.setColor(secColorPicker(type)/*InsideColor*/);
	}

	public void draw(){
		OutsideRect.fill();
		InsideRect.fill();
	}

	public void undraw(){
		OutsideRect.undraw();
		InsideRect.undraw();
	}

	public void translate(double x, double y){
		xBlock+=x;
		yBlock+=y;
		xInsideBlock+=x;
		yInsideBlock+=y;
		OutsideRect.translate(x, y);
		InsideRect.translate(x, y);
	}

	public int getX(){
		return (int) xBlock;
	}

	public int getY(){
		return (int) yBlock;
	}

	private Color colorPicker(String colour){
		if(colour.equalsIgnoreCase("I")){
			return new Color(0, 240, 240);
		}
		else if(colour.equalsIgnoreCase("J")){
			return new Color(0, 0, 240);
		}
		else if(colour.equalsIgnoreCase("L")){
			return new Color(240, 160, 0);
		}
		else if(colour.equalsIgnoreCase("O")){
			return new Color(240, 240, 0);
		}
		else if(colour.equalsIgnoreCase("S")){
			return new Color(0, 240, 0);
		}
		else if(colour.equalsIgnoreCase("T")){
			return new Color(160, 0, 240);
		}
		else if(colour.equalsIgnoreCase("border")){
			return new Color(119-20, 119-20, 119-20);
		}
		else{ //if "Z"
			return new Color(240, 0, 0);
		}
	}

	private Color secColorPicker(String colour){
		if(colour.equalsIgnoreCase("I")){
			return new Color(0, 240-20, 240-20);
		}
		else if(colour.equalsIgnoreCase("J")){
			return new Color(0, 0, 240-20);
		}
		else if(colour.equalsIgnoreCase("L")){
			return new Color(240-20, 160-20, 0);
		}
		else if(colour.equalsIgnoreCase("O")){
			return new Color(240-20, 240-20, 0);
		}
		else if(colour.equalsIgnoreCase("S")){
			return new Color(0, 240-20, 0);
		}
		else if(colour.equalsIgnoreCase("T")){
			return new Color(160-20, 0, 240-20);
		}
		else if(colour.equalsIgnoreCase("border")){
			return new Color(119, 119, 119);
		}
		else{ //if "Z"
			return new Color(240-20, 0, 0);
		}
	}
}
