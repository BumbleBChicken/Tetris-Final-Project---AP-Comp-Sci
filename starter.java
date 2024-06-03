/*
 *	Author:  Luke Sauppe
 *  Date: Started on 5/13/24
*/
import pkg.*;
import pkg2.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


class starter implements InputControl, InputKeyControl {

	//main variables
	static Timer timer;
	static KeyController kC;
	static MouseController mC;
	static int HighScore; //to be implemented...
	static ArrayList<BasicCube> border;
	static ArrayList<I> iShape; //0
	static ArrayList<J> jShape; //1
	static ArrayList<L> lShape; //2
	static ArrayList<S> sShape; //3
	static ArrayList<O> oShape; //4
	static ArrayList<T> tShape; //5
	static ArrayList<Z> zShape; //6
	static ArrayList<BasicCube> yBorder; //technically starts at 1 (0 is inside other border)
	static int[] sNum;				//numbers to remember which shape each arraylist is on; [0] is I, [1] is J, etc.
	static Rectangle background;
	static ArrayList<Rectangle> cover;
	static Rectangle belowBlack;
	static boolean isGameRunning;
	static Text title;
	static Text startButton;
	static int[][] grid;

	//other variables
	static int xPoint;
	static int yPoint;
	static int randomShape;
	static boolean didHitYBorder;
	static boolean endGame;
	static int ms;

	public static void main(String args[]) {
		// please leave following line alone, necessary for keyboard/mouse input
		kC = new KeyController(Canvas.getInstance(),new starter());
		mC = new MouseController(Canvas.getInstance(),new starter());
		// Your code goes below here

		//main variable initializations
		iShape = new ArrayList<I>();
		jShape = new ArrayList<J>();
		lShape = new ArrayList<L>();
		sShape = new ArrayList<S>();
		oShape = new ArrayList<O>();
		tShape = new ArrayList<T>();
		zShape = new ArrayList<Z>();
		yBorder = new ArrayList<BasicCube>();
		//createYBorder();
		grid = new int[21][11];
		createGrid();
		sNum = new int[8];
		for(int c=0; c<sNum.length; c++){	
			sNum[c] = 0;
		}
		title = new Text(150, 50, "TETRIS");		//eventually you should make a defineMenu() method or something so it's not all here
		startButton = new Text(150, 300, "Start Game");
		background = new Rectangle(0, 0, 1000, 1000);
		belowBlack = new Rectangle(0, 550, 500, 500);
		background.setColor(new Color(0, 0, 0));
		belowBlack.setColor(new Color(0, 0, 0));
		border = new ArrayList<BasicCube>();
		createBorder();

		//other variable initializations
		xPoint = 150;
		yPoint = 0;
		randomShape = 4;
		didHitYBorder = false;
		ms = 500;

		//actual game
		endGame = false;
		isGameRunning = false;
		drawMenu();
	}

	//draws menu objects--------------
	public static void drawMenu(){	
		title.draw();
		startButton.draw();
	}

	//undraws menu objects------------
	public static void undrawMenu(){
		title.undraw();
		startButton.undraw();
	}

	//when called, generates random shape and animates downward----------------------------------------------------
	public static void runGame() {	
		printGrid();
		int looop = grid.length-1;

		while(checkFullLine()){
			printGrid();
		}

		System.out.println("Has hit top: " + hitTop());

		if(hitTop()==false){
			
			randomShape = (int)(Math.random()*7);

		    if (randomShape == 0) {
		        iShape.add(new I(xPoint, yPoint));
		        iShape.get(sNum[0]).draw();
		        for(int c=0; c<looop; c++) {
		            iShape.get(sNum[0]).translate(0, 25);
		            Canvas.pause(ms);
		            if(hitBorder() || !isGameRunning){
		            	didHitYBorder = true;
		            	break;
		            }
		        }
		        sNum[0]++;
		    }
		    else if(randomShape == 1){
		    	jShape.add(new J(xPoint, yPoint));
		    	jShape.get(sNum[1]).draw();
		    	for(int c=0; c<looop; c++){
		    		jShape.get(sNum[1]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[1]++;
		    }
		    else if(randomShape == 2){
		    	lShape.add(new L(xPoint, yPoint));
		    	lShape.get(sNum[2]).draw();
		    	for(int c=0; c<looop; c++){
		    		lShape.get(sNum[2]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[2]++;
		    }
		    else if(randomShape == 3){
		    	oShape.add(new O(xPoint, yPoint));
		    	oShape.get(sNum[3]).draw();
		    	for(int c=0; c<looop; c++){
		    		oShape.get(sNum[3]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[3]++;
		    }
		    else if(randomShape == 4){
		    	sShape.add(new S(xPoint, yPoint));
		    	sShape.get(sNum[4]).draw();
		    	for(int c=0; c<looop; c++){
		    		sShape.get(sNum[4]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[4]++;
		    }
		    else if(randomShape == 5){
		    	tShape.add(new T(xPoint, yPoint));
		    	tShape.get(sNum[5]).draw();
		    	for(int c=0; c<looop; c++){
		    		tShape.get(sNum[5]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[5]++;
		    }
		    else{	//if(randomShape==6)
		    	zShape.add(new Z(xPoint, yPoint));
		    	zShape.get(sNum[6]).draw();
		    	for(int c=0; c<looop; c++){
		    		zShape.get(sNum[6]).translate(0, 25);
		    		Canvas.pause(ms);
		    		if(hitBorder() || !isGameRunning){
		    			didHitYBorder = true;
		            	break;
		            }
		    	}
		    	sNum[6]++;
		    }
		}
		else{
			endGame = true;
		}
    }

    //draws main game and starts timer loop-------------
	public static void MainGame(){
		background.fill();
		drawBorder();
		isGameRunning = true; //Start the game

		// Use a timer to call runGame periodically
        timer = new Timer();
	        if(didHitYBorder==false){
	        	timer.schedule(new TimerTask() {
	            //@Override  //????? how? what?
	            public void run() {
	                if (isGameRunning) {
	                    runGame();
	                }
	            }
	        }, 0, 500); // Run every 500 milliseconds
        }
	}

	//undraws all main game objects and resets them------
	public static void undrawMainGame(){
		for(int c=0; c<iShape.size(); c++){
			iShape.get(0).undraw();
			sNum[0] = 0;
		}
		iShape.clear();
		for(int c=0; c<jShape.size(); c++){
			jShape.get(0).undraw();
			sNum[1] = 0;
		}
		jShape.clear();
		for(int c=0; c<lShape.size(); c++){
			lShape.get(0).undraw();
			sNum[2] = 0;
		}
		lShape.clear();
		for(int c=0; c<oShape.size(); c++){
			oShape.get(0).undraw();
			sNum[3] = 0;
		}
		oShape.clear();
		for(int c=0; c<sShape.size(); c++){
			sShape.get(0).undraw();
			sNum[4] = 0;
		}
		sShape.clear();
		for(int c=0; c<tShape.size(); c++){
			tShape.get(0).undraw();
			sNum[5] = 0;
		}
		tShape.clear();
		for(int c=0; c<zShape.size(); c++){
			zShape.get(0).undraw();
			sNum[6] = 0;
		}
		zShape.clear();
		for(int c=0; c<border.size(); c++){
			border.get(c).undraw();
		}
		background.undraw();
	}

	//Mouseclick triggers any pause/end/start game code-----------------
	public void onMouseClick(double x, double y) {
        if (isGameRunning == true) {
            isGameRunning = false;
            /*
            if (timer != null) {
                timer.cancel(); // Cancel the timer
            }
            */
            undrawMainGame();
            drawMenu();
        } else {
            isGameRunning = true;
            undrawMenu();
            MainGame();
        }
    }

    //keypress determining which object to rotate/move-----------------
	public void keyPress(String key) {
		// enter code here
		int moveX;
		int row = 0;
		int col = 0;
		int rotation = 0;
		if(key.equals("a")){	//---- "A" key pressed
			moveX = -25;
			if(randomShape==0){
				row = iShape.get(sNum[0]).getY()/25;
				col = iShape.get(sNum[0]).getX()/25;
				rotation = iShape.get(sNum[0]).getRotated();
				if(iShape.get(sNum[0]).getX()-25==0 || (col>1 && ((rotation==1 && grid[row][col-1]>0) || (rotation==2 && (grid[row][col-1]>0 || grid[row+1][col-1]>0 || grid[row+2][col-1]>0 || grid[row+3][col-1]>0))))){
					moveX = 0;
				}
				iShape.get(sNum[0]).translate(moveX, 0);
			}
			else if(randomShape==1){
				row = jShape.get(sNum[1]).getY()/25;
				col = jShape.get(sNum[1]).getX()/25;
				rotation = jShape.get(sNum[1]).getRotated();
				if(jShape.get(sNum[1]).getX()-25==0 || (col>1 && ((rotation==1 && (grid[row][col-1]>0 || grid[row+1][col-1]>0)) || (rotation==2 && (grid[row][col-1]>0 || grid[row+1][col-1]>0 || grid[row+2][col-1]>0)) || (rotation==3 && (grid[row][col-1]>0 || grid[row+1][col+1]>0)) || (rotation==4 && (grid[row][col]>0 || grid[row+1][col]>0 || grid[row+2][col-1]>0))))){
					moveX = 0;
				}
				jShape.get(sNum[1]).translate(moveX, 0);
			}
			else if(randomShape==2){
				row = lShape.get(sNum[2]).getY()/25;
				col = lShape.get(sNum[2]).getX()/25;
				rotation = lShape.get(sNum[2]).getRotated();
				if(lShape.get(sNum[2]).getX()-25==0 || (col>1 && ((rotation==1 && (grid[row+1][col-1]>0 || grid[row][col+1]>0)) || (rotation==2 && (grid[row][col-1]>0 || grid[row+1][col-1]>0 || grid[row+2][col-1]>0)) || (rotation==3 && (grid[row][col-1]>0 || grid[row+1][col-1]>0)) || (rotation==4 && (grid[row][col-1]>0 || grid[row+1][col]>0 || grid[row+2][col]>0))))){
					moveX = 0;
				}
				lShape.get(sNum[2]).translate(moveX, 0);
			}
			else if(randomShape==3){
				row = oShape.get(sNum[3]).getY()/25;
				col = oShape.get(sNum[3]).getX()/25;
				if(oShape.get(sNum[3]).getX()-25==0 || (col>1 && (grid[row][col-1]>0 || grid[row+1][col-1]>0))){
					moveX = 0;
				}
				oShape.get(sNum[3]).translate(moveX, 0);
			}
			else if(randomShape==4){
				row = sShape.get(sNum[4]).getY()/25;
				col = sShape.get(sNum[4]).getX()/25;
				rotation = sShape.get(sNum[4]).getRotated();
				if(sShape.get(sNum[4]).getX()-25==0 || (col>1 && (rotation==1 && (grid[row+1][col-1]>0 || grid[row][col]>0)) || (rotation==2 && (grid[row][col-1]>0 || grid[row+1][col-1]>0 || grid[row+2][col]>0)))){
					moveX = 0;
				}
				sShape.get(sNum[4]).translate(moveX, 0);
			}
			else if(randomShape==5){
				row = tShape.get(sNum[5]).getY()/25;
				col = tShape.get(sNum[5]).getX()/25;
				rotation = tShape.get(sNum[5]).getRotated();
				if(tShape.get(sNum[5]).getX()-25==0 || (col>1 && (rotation==1 && (grid[row+1][col-1]>0 || grid[row][col]>0)) || (rotation==2 && (grid[row][col-1]>0 || grid[row+1][col-1]>0 || grid[row+2][col-1]>0)) || (rotation==3 && (grid[row][col-1]>0 || grid[row+1][col]>0)) || (rotation==4 && (grid[row][col]>0 || grid[row+1][col-1]>0 || grid[row+2][col]>0)))){
					moveX = 0;
				}
				tShape.get(sNum[5]).translate(moveX, 0);
			}
			else{
				row = zShape.get(sNum[6]).getY()/25;
				col = zShape.get(sNum[6]).getX()/25;
				rotation = zShape.get(sNum[6]).getRotated();
				if(zShape.get(sNum[6]).getX()-25==0 || (col>1 && (rotation==1 && (grid[row][col-1]>0 || grid[row+1][col]>0)) || (rotation==2 && (grid[row][col]>0 || grid[row+1][col-1]>0 || grid[row+2][col-1]>0)))){
					moveX = 0;
				}
				zShape.get(sNum[6]).translate(moveX, 0);
			}
		}
		else if(key.equals("d")){	//---- "D" key pressed
			moveX = 25;
			if(randomShape==0){
			row = iShape.get(sNum[0]).getY()/25;
			col = iShape.get(sNum[0]).getX()/25;
			rotation = iShape.get(sNum[0]).getRotated();
				if(iShape.get(sNum[0]).getRotated()==1){
					if(iShape.get(sNum[0]).getX()+50==225){
						moveX = 0;
					}
					else if(grid[row][col+4]>0){
						moveX = 0;
					}
				}
				else{
					if(iShape.get(sNum[0]).getX()-75==175){
						moveX = 0;
					}
					else if(grid[row][col+1]>0 || grid[row+1][col+1]>0 || grid[row+2][col+1]>0 || grid[row+3][col+1]>0){
						moveX = 0;
					}
				}
				iShape.get(sNum[0]).translate(moveX, 0);
			}
			else if(randomShape==1){
				if(jShape.get(sNum[1]).getRotated()==1 || jShape.get(sNum[1]).getRotated()==3){
					if(jShape.get(sNum[1]).getX()+25==225){
						moveX = 0;
					}
					jShape.get(sNum[1]).translate(moveX, 0);
				}
				else{ //if rotated is odd
					if(jShape.get(sNum[1]).getX()+25==250){
						moveX = 0;
					}
					jShape.get(sNum[1]).translate(moveX, 0);
				}
				
			}
			else if(randomShape==2){
				if(lShape.get(sNum[2]).getRotated()%2==0){
					if(lShape.get(sNum[2]).getX()+25==250){
						moveX = 0;
					}
				}
				else if(lShape.get(sNum[2]).getX()+25==225){
					moveX = 0;
				}
				
				lShape.get(sNum[2]).translate(moveX, 0);
			}
			else if(randomShape==3){
				if(oShape.get(sNum[3]).getX()+50==275){
					moveX = 0;
				}
				oShape.get(sNum[3]).translate(moveX, 0);
			}
			else if(randomShape==4){
				if(sShape.get(sNum[4]).getRotated()==1 && sShape.get(sNum[4]).getX()+50==250){
					moveX = 0;
				}
				else if(sShape.get(sNum[4]).getX()+25==250){
					moveX = 0;
				}
				sShape.get(sNum[4]).translate(moveX, 0);
			}
			else if(randomShape==5){
				if(tShape.get(sNum[5]).getRotated()%2!=0 && tShape.get(sNum[5]).getX()+25==225){
					moveX = 0;
				}
				else if(tShape.get(sNum[5]).getX()+25==250){
					moveX = 0;
				}
				tShape.get(sNum[5]).translate(moveX, 0);
			}
			else{
				if(zShape.get(sNum[6]).getRotated()==1 && zShape.get(sNum[6]).getX()+25==225){
					moveX = 0;
				}
				else if(zShape.get(sNum[6]).getX()+25==250){
					moveX = 0;
				}
				zShape.get(sNum[6]).translate(moveX, 0);
			}
		}
		else if(key.equals("w")){	//---- "W" key pressed ----
			if(randomShape==0){	//iShape
				if(iShape.get(sNum[0]).getRotated()==2 && (iShape.get(sNum[0]).getX()+25>250 || grid[row][col+1]>0 || grid[row+1][col+1]>0)){
					iShape.get(sNum[0]).translate(-25-25-25, 0);
				}
				iShape.get(sNum[0]).rotateClockwise();
			}
			else if(randomShape==1){ //jShape
				if(jShape.get(sNum[1]).getRotated()%2==0 && jShape.get(sNum[1]).getX()+25==250){
					jShape.get(sNum[1]).translate(-25, 0);
				}
				jShape.get(sNum[1]).rotateClockwise();
			}
			else if(randomShape==2){ //lShape
				if(lShape.get(sNum[2]).getRotated()%2==0 && lShape.get(sNum[2]).getX()+25==250){
					lShape.get(sNum[2]).translate(-25, 0);
				}
				lShape.get(sNum[2]).rotateClockwise();
			}
			else if(randomShape==3){
				//oShape.get(sNum[3]).rotateClockwise();
			}
			else if(randomShape==4){
				if(sShape.get(sNum[4]).getRotated()==2 && sShape.get(sNum[4]).getX()+25==250){
					sShape.get(sNum[4]).translate(-25, 0);
				}
				sShape.get(sNum[4]).rotateClockwise();
			}
			else if(randomShape==5){
				if(tShape.get(sNum[5]).getRotated()%2==0 && tShape.get(sNum[5]).getX()+25==250){
					tShape.get(sNum[5]).translate(-25, 0);
				}
				tShape.get(sNum[5]).rotateClockwise();
			}
			else{
				if(zShape.get(sNum[6]).getRotated()==2 && zShape.get(sNum[6]).getX()+25==250){
					zShape.get(sNum[6]).translate(-25, 0);
				}
				zShape.get(sNum[6]).rotateClockwise();
			}
		}
		else if(key.equals("s")){	//----- "S" key pressed -----
			int x = 25;
			if(hitBorder()){
				x = 0;
			}
			if(randomShape==0){
				iShape.get(sNum[0]).translate(0, x);
			}
			else if(randomShape==1){
				jShape.get(sNum[1]).translate(0, x);
			}
			else if(randomShape==2){
				lShape.get(sNum[2]).translate(0, x);
			}
			else if(randomShape==3){
				oShape.get(sNum[3]).translate(0, x);
			}
			else if(randomShape==4){
				sShape.get(sNum[4]).translate(0, x);
			}
			else if(randomShape==5){
				tShape.get(sNum[5]).translate(0, x);
			}
			else{
				zShape.get(sNum[6]).translate(0, x);
			}
		}
	}

	//creates gray border---------------------------------
	public static void createBorder(){
		boolean pass = true;
		int x = 0;
		int y = 0;
		for(int i=0; i<2; i++){
			for(int c=0; c<12; c++){
				border.add(new BasicCube(x, y, "border"));
				x+=25;
			}
			pass = false;
			x = 0;
			y = 500;
		}
		pass = true;
		x = 0;
		y = 25;
		for(int i=0; i<2; i++){
			for(int c=0; c<20; c++){
				border.add(new BasicCube(x, y, "border"));
				y+=25;
			}
			pass = false;
			y = 25;
			x = 275;
		}
	}

	//game end checker
	public static boolean hitTop(){
		for(int c=0; c<grid[0].length; c++){
			if(grid[1][c]>0){
				return true;
			}
		}
		return false;
	}


	//checks for full line and moves every single object down
	public static boolean checkFullLine(){
		System.out.println("check line did run");
		int temp = 0;
		for(int row=grid.length-2; row>=0; row--){
			for(int col=0; col<grid[0].length; col++){
				if(grid[row][col]!=0){
					temp+=1;
					System.out.println(temp);
				}
				if(temp>=10){
					//move everything above down
					moveAllDown(row);
					return true;
				}
			}
			temp = 0;
		}
		return false;
	}

	
	//move everything down for line
	public static void moveAllDown(int row){
		/*
		undrawBorder();
		cover.add(new Rectangle(0, (row*25), 25, 250));
		cover.get(sNum[7]).setColor(new Color(0, 0, 0));
		cover.get(sNum[7]).fill();
		sNum[7]++;
		*/

		undrawBorder();
		

		for(int c=0; c<iShape.size(); c++){
		    // Check block one
		    if(iShape.get(c).oneGetY() / 25 < row){
		        iShape.get(c).oneTranslate(0, 25);
		    }
		    else if(iShape.get(c).oneGetY() / 25 == row){
		        iShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(iShape.get(c).twoGetY() / 25 < row){
		        iShape.get(c).twoTranslate(0, 25);
		    }
		    else if(iShape.get(c).twoGetY() / 25 == row){
		        iShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(iShape.get(c).threeGetY() / 25 < row){
		        iShape.get(c).threeTranslate(0, 25);
		    }
		    else if(iShape.get(c).threeGetY() / 25 == row){
		        iShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(iShape.get(c).fourGetY() / 25 < row){
		        iShape.get(c).fourTranslate(0, 25);
		    }
		    else if(iShape.get(c).fourGetY() / 25 == row){
		        iShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<jShape.size(); c++){
		    // Check block one
		    if(jShape.get(c).oneGetY() / 25 < row){
		        jShape.get(c).oneTranslate(0, 25);
		    }
		    else if(jShape.get(c).oneGetY() / 25 == row){
		        jShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(jShape.get(c).twoGetY() / 25 < row){
		        jShape.get(c).twoTranslate(0, 25);
		    }
		    else if(jShape.get(c).twoGetY() / 25 == row){
		        jShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(jShape.get(c).threeGetY() / 25 < row){
		        jShape.get(c).threeTranslate(0, 25);
		    }
		    else if(jShape.get(c).threeGetY() / 25 == row){
		        jShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(jShape.get(c).fourGetY() / 25 < row){
		        jShape.get(c).fourTranslate(0, 25);
		    }
		    else if(jShape.get(c).fourGetY() / 25 == row){
		        jShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<lShape.size(); c++){
		    // Check block one
		    if(lShape.get(c).oneGetY() / 25 < row){
		        lShape.get(c).oneTranslate(0, 25);
		    }
		    else if(lShape.get(c).oneGetY() / 25 == row){
		        lShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(lShape.get(c).twoGetY() / 25 < row){
		        lShape.get(c).twoTranslate(0, 25);
		    }
		    else if(lShape.get(c).twoGetY() / 25 == row){
		        lShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(lShape.get(c).threeGetY() / 25 < row){
		        lShape.get(c).threeTranslate(0, 25);
		    }
		    else if(lShape.get(c).threeGetY() / 25 == row){
		        lShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(lShape.get(c).fourGetY() / 25 < row){
		        lShape.get(c).fourTranslate(0, 25);
		    }
		    else if(lShape.get(c).fourGetY() / 25 == row){
		        lShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<oShape.size(); c++){
		    // Check block one
		    if(oShape.get(c).oneGetY() / 25 < row){
		        oShape.get(c).oneTranslate(0, 25);
		    }
		    else if(oShape.get(c).oneGetY() / 25 == row){
		        oShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(oShape.get(c).twoGetY() / 25 < row){
		        oShape.get(c).twoTranslate(0, 25);
		    }
		    else if(oShape.get(c).twoGetY() / 25 == row){
		        oShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(oShape.get(c).threeGetY() / 25 < row){
		        oShape.get(c).threeTranslate(0, 25);
		    }
		    else if(oShape.get(c).threeGetY() / 25 == row){
		        oShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(oShape.get(c).fourGetY() / 25 < row){
		        oShape.get(c).fourTranslate(0, 25);
		    }
		    else if(oShape.get(c).fourGetY() / 25 == row){
		        oShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<sShape.size(); c++){
		    // Check block one
		    if(sShape.get(c).oneGetY() / 25 < row){
		        sShape.get(c).oneTranslate(0, 25);
		    }
		    else if(sShape.get(c).oneGetY() / 25 == row){
		        sShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(sShape.get(c).twoGetY() / 25 < row){
		        sShape.get(c).twoTranslate(0, 25);
		    }
		    else if(sShape.get(c).twoGetY() / 25 == row){
		        sShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(sShape.get(c).threeGetY() / 25 < row){
		        sShape.get(c).threeTranslate(0, 25);
		    }
		    else if(sShape.get(c).threeGetY() / 25 == row){
		        sShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(sShape.get(c).fourGetY() / 25 < row){
		        sShape.get(c).fourTranslate(0, 25);
		    }
		    else if(sShape.get(c).fourGetY() / 25 == row){
		        sShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<tShape.size(); c++){
		    // Check block one
		    if(tShape.get(c).oneGetY() / 25 < row){
		        tShape.get(c).oneTranslate(0, 25);
		    }
		    else if(tShape.get(c).oneGetY() / 25 == row){
		        tShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(tShape.get(c).twoGetY() / 25 < row){
		        tShape.get(c).twoTranslate(0, 25);
		    }
		    else if(tShape.get(c).twoGetY() / 25 == row){
		        tShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(tShape.get(c).threeGetY() / 25 < row){
		        tShape.get(c).threeTranslate(0, 25);
		    }
		    else if(tShape.get(c).threeGetY() / 25 == row){
		        tShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(tShape.get(c).fourGetY() / 25 < row){
		        tShape.get(c).fourTranslate(0, 25);
		    }
		    else if(tShape.get(c).fourGetY() / 25 == row){
		        tShape.get(c).undrawFour();
		    }
		}
		for(int c=0; c<zShape.size(); c++){
		    // Check block one
		    if(zShape.get(c).oneGetY() / 25 < row){
		        zShape.get(c).oneTranslate(0, 25);
		    }
		    else if(zShape.get(c).oneGetY() / 25 == row){
		        zShape.get(c).undrawOne();
		    }
		    // Check block two
		    if(zShape.get(c).twoGetY() / 25 < row){
		        zShape.get(c).twoTranslate(0, 25);
		    }
		    else if(zShape.get(c).twoGetY() / 25 == row){
		        zShape.get(c).undrawTwo();
		    }
		    // Check block three
		    if(zShape.get(c).threeGetY() / 25 < row){
		        zShape.get(c).threeTranslate(0, 25);
		    }
		    else if(zShape.get(c).threeGetY() / 25 == row){
		        zShape.get(c).undrawThree();
		    }
		    // Check block four
		    if(zShape.get(c).fourGetY() / 25 < row){
		        zShape.get(c).fourTranslate(0, 25);
		    }
		    else if(zShape.get(c).fourGetY() / 25 == row){
		        zShape.get(c).undrawFour();
		    }
		}

		drawBorder();

		row = row*25;
		//reset that row in the grid
		for(int x=0; x<grid[0].length; x++){
			grid[row/25][x] = 0;
		}

		int[] tempNum = new int[grid[0].length];
		int c = 0;
		
		// Move all rows above the completed row down by one
	    for (int y = row/25; y > 0; y--) {
	        for (int x = 0; x < grid[0].length; x++) {
	            grid[y][x] = grid[y - 1][x];
	        }
	    }
	    // Clear the top row
	    for (int x = 0; x < grid[0].length; x++) {
	        grid[0][x] = 0;
	    }
	}

	//draws gray border-------------------------
	public static void drawBorder(){
		for(int c=0; c<border.size(); c++){
			border.get(c).draw();
		}
	}

	//undraws gray border-----------------------
	public static void undrawBorder(){
		for(int c=0; c<border.size(); c++){
			border.get(c).undraw();
		}
	}

	//creates grid, sets all to 0
	public static void createGrid(){
		for(int row=0; row<grid.length; row++){
			for(int col=0; col<grid[0].length; col++){
				if(row==grid.length-1){
					grid[row][col] = 8;
				}
				else{
					grid[row][col] = 0;
				}
			}
		}
	}

	//prints out grid for bug testing
	public static void printGrid(){
		System.out.println();
		System.out.println();
		for(int row=0; row<grid.length; row++){
			for(int col=0; col<grid[0].length; col++){
				System.out.print(grid[row][col]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//if hit border, adds rotated shape to grid and returns true; else, returns false - this is probably the largest method/chunk of the game lmao (cries in mental math)
	public static boolean hitBorder(){
		int row = 0;
		int col = 0;
		if(randomShape==0){
			row = iShape.get(sNum[0]).getY()/25+1;
			col = iShape.get(sNum[0]).getX()/25;
			System.out.print(row + " ");
			if(row<21 && iShape.get(sNum[0]).getRotated()==1 && (grid[row][col]>0 || grid[row][col+1]>0 || grid[row][col+2]>0 || grid[row][col+3]>0)){
				grid[row-1][col]=1;
				grid[row-1][col+1]=1;
				grid[row-1][col+2]=1;
				grid[row-1][col+3]=1;
				return true;
			}
			else if(row<18 && iShape.get(sNum[0]).getRotated()==2 && (grid[row][col]>0 || grid[row+1][col]>0 || grid[row+2][col]>0 || grid[row+3][col]>0)){
				grid[row-1][col]=1;
				grid[row][col]=1;
				grid[row+1][col]=1;
				grid[row+2][col]=1;
				return true;
			}
			else{
				return false;
			}
		}
		else if(randomShape==1){	//jShape
			row = jShape.get(sNum[1]).getY()/25+1;
			col = jShape.get(sNum[1]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && jShape.get(sNum[1]).getRotated()==1 && (grid[row+1][col]>0 || grid[row+1][col+1]>0 || grid[row+1][col+2]>0)){
				grid[row-1][col]=2;
				grid[row][col]=2;
				grid[row][col+1]=2;
				grid[row][col+2]=2;
				return true;
			}
			else if(row<19 && jShape.get(sNum[1]).getRotated()==2 && (grid[row+2][col]>0 || grid[row][col+1]>0)){
				grid[row-1][col]=2;
				grid[row-1][col+1]=2;
				grid[row][col]=2;
				grid[row+1][col]=2;
				return true;
			}
			else if(row<20 && jShape.get(sNum[1]).getRotated()==3 && (grid[row][col]>0 || grid[row][col+1]>0 || grid[row+1][col+2]>0)){
				grid[row-1][col]=2;
				grid[row-1][col+1]=2;
				grid[row-1][col+2]=2;
				grid[row][col+2]=2;
				return true;
			}
			else if(row<19 && jShape.get(sNum[1]).getRotated()==4 && (grid[row+2][col]>0 || grid[row+2][col+1]>0)){
				grid[row+1][col]=2;
				grid[row-1][col+1]=2;
				grid[row][col+1]=2;
				grid[row+1][col+1]=2;
				return true;
			}
			else{
				return false;
			}
		}
		else if(randomShape==2){	//lShape
			row = lShape.get(sNum[2]).getY()/25+1;
			col = lShape.get(sNum[2]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && lShape.get(sNum[2]).getRotated()==1 && (grid[row+1][col]>0 || grid[row+1][col+1]>0 || grid[row+1][col+2]>0)){
				grid[row][col]=3;
				grid[row][col+1]=3;
				grid[row][col+2]=3;
				grid[row-1][col+2]=3;
				return true;
			}
			else if(row<19 && lShape.get(sNum[2]).getRotated()==2 && (grid[row+2][col]>0 || grid[row+2][col+1]>0)){
				grid[row-1][col]=3;
				grid[row][col]=3;
				grid[row+1][col]=3;
				grid[row+1][col+1]=3;
				return true;
			}
			else if(row<20 && lShape.get(sNum[2]).getRotated()==3 && (grid[row+1][col]>0 || grid[row][col+1]>0 || grid[row][col+2]>0)){
				grid[row-1][col]=3;
				grid[row][col]=3;
				grid[row-1][col+1]=3;
				grid[row-1][col+2]=3;
				return true;
			}
			else if(row<19 && lShape.get(sNum[2]).getRotated()==4 && (grid[row][col]>0 || grid[row+2][col+1]>0)){
				grid[row-1][col]=3;
				grid[row-1][col+1]=3;
				grid[row][col+1]=3;
				grid[row+1][col+1]=3;
				return true;
			}
			else{
				return false;
			}
		}
		else if(randomShape==3){	//oShape
			row = oShape.get(sNum[3]).getY()/25+1;
			col = oShape.get(sNum[3]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && grid[row+1][col]>0 || grid[row+1][col+1]>0){
				grid[row-1][col]=4;
				grid[row-1][col+1]=4;
				grid[row][col]=4;
				grid[row][col+1]=4;
				return true;
			}
			else{
				return false;
			}
		}
		else if(randomShape==4){	//sShape
			row = sShape.get(sNum[4]).getY()/25+1;
			col = sShape.get(sNum[4]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && sShape.get(sNum[4]).getRotated()==1 && (grid[row+1][col]>0 || grid[row+1][col+1]>0 || grid[row][col+2]>0)){
				grid[row][col]=5;
				grid[row][col+1]=5;
				grid[row-1][col+1]=5;
				grid[row-1][col+2]=5;
				return true;
			}
			else if(row<19 && sShape.get(sNum[4]).getRotated()==2 && (grid[row+1][col]>0 || grid[row+2][col+1]>0)){
				grid[row-1][col]=5;
				grid[row][col]=5;
				grid[row][col+1]=5;
				grid[row+1][col+1]=5;
				return true;
			}
			else{
				return false;
			}
		}
		else if(randomShape==5){	//tShape
			row = tShape.get(sNum[5]).getY()/25+1;
			col = tShape.get(sNum[5]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && tShape.get(sNum[5]).getRotated()==1 && (grid[row+1][col]>0 || grid[row+1][col+1]>0 || grid[row+1][col+2]>0)){
				grid[row][col]=6;
				grid[row-1][col+1]=6;
				grid[row][col+1]=6;
				grid[row][col+2]=6;
				return true;
			}
			else if(row<19 && tShape.get(sNum[5]).getRotated()==2 && (grid[row+2][col]>0 || grid[row+1][col+1]>0)){
				grid[row-1][col]=6;
				grid[row][col]=6;
				grid[row+1][col]=6;
				grid[row][col+1]=6;
				return true;
			}
			else if(row<20 && tShape.get(sNum[5]).getRotated()==3 && (grid[row][col]>0 || grid[row+1][col+1]>0 || grid[row][col+2]>0)){
				grid[row-1][col]=6;
				grid[row-1][col+1]=6;
				grid[row-1][col+2]=6;
				grid[row][col+1]=6;
				return true;
			}
			else if(row<19 && tShape.get(sNum[5]).getRotated()==4 && (grid[row+1][col]>0 || grid[row+2][col+1]>0)){
				grid[row][col]=6;
				grid[row-1][col+1]=6;
				grid[row][col+1]=6;
				grid[row+1][col+1]=6;
				return true;
			}
			else{
				return false;
			}
		}
		else{ //if(randomShape==6); zShape
			row = zShape.get(sNum[6]).getY()/25+1;
			col = zShape.get(sNum[6]).getX()/25;
			System.out.print(row + " ");
			if(row<20 && zShape.get(sNum[6]).getRotated()==1 && (grid[row][col]>0 || grid[row+1][col+1]>0 || grid[row+1][col+2]>0)){
				grid[row-1][col]=7;
				grid[row-1][col+1]=7;
				grid[row][col+1]=7;
				grid[row][col+2]=7;
				return true;
			}
			else if(row<19 && zShape.get(sNum[6]).getRotated()==2 && (grid[row+2][col]>0 || grid[row+1][col+1]>0)){
				grid[row][col]=7;
				grid[row+1][col]=7;
				grid[row-1][col+1]=7;
				grid[row][col+1]=7;
				return true;
			}
			else{
				return false;
			}
		}	
	}
}
