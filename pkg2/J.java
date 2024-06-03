/*
 *	Author:  
 *  Date: 
*/

package pkg2;
import java.util.*;
import java.util.Scanner;
import java.util.Random;


public class J {

	private String type;
	private int x;
	private int y;
	private BasicCube iShape1;
	private BasicCube iShape2;
	private BasicCube iShape3;
	private BasicCube iShape4;
	private int rotated; //rotated increases on every clockwise rotation (4 max, 1 default)

	public J(int x, int y) {
		this.x = x;
		this.y = y;
		type = "J";
		rotated = 1;
		iShape1 = new BasicCube(this.x, this.y, type);		//1
		iShape2 = new BasicCube(this.x, this.y+25, type);	//234
		iShape3 = new BasicCube(this.x+25, this.y+25, type);
		iShape4 = new BasicCube(this.x+25+25, this.y+25, type);
	}
	public void rotateClockwise(){			//from 1    to  21
		if(rotated==1){						//     234      3
			iShape1.translate(25, 0);		//				4
			iShape2.translate(0, -25);
			iShape3.translate(-25, 0);
			iShape4.translate(-25-25, 25);
			rotated = 2;
		}
		else if(rotated==2){
			iShape1.translate(25, 25);
			iShape2.translate(25+25, 0);	//from 21  to  432
			iShape3.translate(25, -25);		//	   3         1
			iShape4.translate(0, -25-25);	//     4
			rotated = 3;
		}
		else if(rotated==3){
			iShape1.translate(-25-25, 25);		//from 432  to  4
			iShape2.translate(-25, 25+25);		//       1      3
			iShape3.translate(0, 25);			//             12
			iShape4.translate(25, 0);
			rotated = 4;
		}
		else{ //if(rotated==4)
			iShape1.translate(0, -25-25);
			iShape2.translate(-25, -25);
			//iShape3.translate(0, 0);
			iShape4.translate(25, 25);
			rotated = 1;
		}
	}

	public void rotateCounterClockwise(){
		for(int c=0; c<3; c++){
			this.rotateClockwise();
		}
	}

	public int getRotated(){
		return rotated;
	}

	public void oneTranslate(int x, int y){
		iShape1.translate(x, y);
	}

	public void twoTranslate(int x, int y){
		iShape2.translate(x, y);
	}

	public void threeTranslate(int x, int y){
		iShape3.translate(x, y);
	}

	public void fourTranslate(int x, int y){
		iShape4.translate(x, y);
	}

	public int oneGetX(){
		return iShape1.getX();
	}

	public int oneGetY(){
		return iShape1.getY();
	}

	public int twoGetX(){
		return iShape2.getX();
	}

	public int twoGetY(){
		return iShape2.getY();
	}

	public int threeGetX(){
		return iShape3.getX();
	}

	public int threeGetY(){
		return iShape3.getY();
	}

	public int fourGetX(){
		return iShape4.getX();
	}

	public int fourGetY(){
		return iShape4.getY();
	}

	public void undrawOne(){
		iShape1.undraw();
	}

	public void undrawTwo(){
		iShape2.undraw();
	}

	public void undrawThree(){
		iShape3.undraw();
	}

	public void undrawFour(){
		iShape4.undraw();
	}

	public void draw(){
		iShape1.draw();
		iShape2.draw();
		iShape3.draw();
		iShape4.draw();
	}

	public void undraw(){
		iShape1.undraw();
		iShape2.undraw();
		iShape3.undraw();
		iShape4.undraw();
	}

	public void translate(double x, double y){
		this.x+=x;
		this.y+=y;
		iShape1.translate(x, y);
		iShape2.translate(x, y);
		iShape3.translate(x, y);
		iShape4.translate(x, y);
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
