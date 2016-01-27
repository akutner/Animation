import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Pong extends GraphicsProgram{
	
	public GRect myLine;
	public GRect background;
	public GOval ball;
	
	//ball size(diameter
	public int size=40;
	
	//Permanent X speed
	private double permDx=3;
	
	//varying X speed based off angle hits off platform
	private double dx=-permDx;
	
	private double dxStart=-3;
	
	//falling speed
	private double dy=3;
	
	// where myLine's Y is
	private int lineY;
	
	//tried to do mouseClicking but failed, now just used as playing
	private boolean mouseClicked=true;
	
	//total number of balls is 4
	private int ballsRemaining=3;
	
	//starts at level 1 each level increase leads to another layer of bricks
	private int level=1;
	
	//random generator for colors for bricks
	private RandomGenerator r= new RandomGenerator();
	
	//label with remaining bricks
	private GLabel bRemain;
	
	//width of myLine(platform)
	private int lineWidth=100;
	
	//number of bricks destroyed: shown at end screen
	private int bricksDestroyed=0;
	
	//the higher this number the faster the permX is as levels increase
	private double rateOfDifficultyIncrease=.3;
	
	//number of bricks per line
	private int bricksPerLine=5;
	
	//arraylist of bricks
	private ArrayList<GRect> bricks= new ArrayList<GRect>();
	
	//brick height(20 looks best)
	private int brickHeight=20;
	
	
	//time between movement of ball
	private int pauseTime=5;
	
	
	
	public void init(){
		//it is possible to vary screen size but it is probably a good idea to change ball size and platfom
		//size as well
		setSize(800,800);
		setTitle("Brick Breaker");//hopefully no one sues....
		
	}
	
	
	public void run(){
		addBackground();//add black background behind everything
		addBall();//add ball to middle and side of screen
		lineY=getHeight()-40;//setting Y dimension of platform
		createMyLine();//creates platform
		addMouseListeners();//adds listeners so can get input on where to move platform
		addBricks(level);//adds bricks starting given level
		ballsRemainingLabel(ballsRemaining);//adds label at bottom right
		
		
		while(true){//game
			if(mouseClicked){
				//increasing x speed for each level
				permDx=level*(rateOfDifficultyIncrease)+3;
				ball.move(dx,dy);
				pause(pauseTime);//possible to change to vary difficulty
				//if it hits a wall bounce off
				if(ball.getX()<0||ball.getX()+size>getWidth()){
					dx=-dx;
					ball.move(dx, dy);
					}
				
				
				
				//if it hits the ceiling bounce off
				if(ball.getY()<0){
					dy=-dy;
					ball.move(dx,dy);
					}
				
				
				
				//if ball collides with line
				if(myLine.contains(ball.getX(),ball.getY()+size-5)){
					dy=-dy;				
					
					//the resulting x direction is proportional to where it lies on the myLine
					/*
					 * The problem presented was that in the original game of Pong and with all other
					 * games that require the use of a ball bouncing off a platform the player needed
					 * control over where the ball would go. This control is given by having the ball
					 * bounce left when it hits the left side of the platform, straight when hitting the
					 * middle and right when it hits the right side. This of course isn't regular physics
					 * and essentially makes the platform into a parabola so that the closer to the ends
					 * the higher the absolute value of dx is(and therefore the more horizontal the angle)
					 * 
					 *  To achieve this I got the proportion of the ball's center location relative to the
					 *  center of the platform so that when it hits the left side the right side of the 
					 *  statment equals -1 and if it hits the right side the right side of the statement 
					 *  equals 1. This times the permanent dx creates this "pong" effect
					 */
					dx=permDx*(((ball.getX()+size/2-myLine.getX())-(myLine.getWidth()/2))/(lineWidth/2));
				}
				
				//if it collides with a brick
				for(int i=0; i< bricks.size(); i++){			
					//for some reason intersects only takes type GRectangle not GRect so I had to change the data type
					GRectangle why= new GRectangle(bricks.get(i).getLocation(),bricks.get(i).getSize());
					if(collides(ball,why)){//if the ball and brick collide
						remove(bricks.get(i));//removes brick from screen
						bricks.remove(i);//removes brick from list
						bricksDestroyed++;//increases score
						i--;
						dy=-dy;//changes ball direction
						ball.move(dx, dy);//moves ball away
						if(bricksDestroyed%15==0){//for every 15 bricks destroyed add another ball
							ballsRemaining++;
						}
					}
				}
				
				//checks if game is in play
				if(ball.getY()>getHeight()||bricks.size()==0){
					mouseClicked=false;
				}
			}else {
				//if there are still balls to be played
				if(ballsRemaining>0){
					remove(bRemain);//remove the current label with the number of balls
					ballsRemaining--;//decrease the balls remaining
					ballsRemainingLabel(ballsRemaining);//add a new label with the updated ballsRemaining
					dx=permDx;//reset speed
					ball.setLocation(0,getHeight()/2);//reset ball location
					mouseClicked=true;//go back to beginning
				}
				
				//if there are no more bricks in play
				if(bricks.size()==0){
					level++;//move to next level
					addBricks(level);//add bricks to level
					
					//reset ball location and speed
					ball.setLocation(0,getHeight()/2);
					dx=dxStart;
					dy=Math.abs(dy);
					
					pause(200);
					mouseClicked=true;//go back to beginning
				}
				
				
				//game over screen
				if(ballsRemaining==0&&bricks.size()>0&&ball.getY()>getHeight()){
					GLabel gOver= gameOver();
					add(gOver);
					add(bricksDown());
					int i=0;
					pause(300);
					while(true){//flashing game over in Red and white
						if(i%2==1){
							gOver.setColor(Color.red);
							pause (300);
						}else{
							gOver.setColor(Color.white);
							pause(300);
						}
						i++;
						if(i==200){
							i=0;
						}
					}
				}
				
			}
		}
	}
	
	
	//label saying how many balls there are in stock
	public void ballsRemainingLabel(int i){
		bRemain=new GLabel(ballsRemaining+" ball(s)");
		bRemain.setFont("Ariel-15");
		bRemain.setColor(Color.WHITE);
		bRemain.setLocation(getWidth()-bRemain.getWidth(),getHeight()-bRemain.getHeight());
		add(bRemain);
	}
	
	//creates the platform line that you control
	public void createMyLine(){
		myLine= new GRect(getWidth()/2-50,lineY, lineWidth,20);
		myLine.setFilled(true);
		myLine.setColor(Color.WHITE);
		add(myLine);
	}
	
	//black background
	public void addBackground(){
		background= new GRect(0,0, getWidth(), getHeight());
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
	}
	
	//adds the ball to the screen
	public void addBall(){
		ball=new GOval(0,getHeight()/2, size,size);
		ball.setFilled(true);
		ball.setColor(Color.WHITE);
		add(ball);
	}
	
	
	//adds bricks to screen
	//each level adds another layer
	public void addBricks(int level){
		Color col;
		for(int c=1; c<=level;c++){
			col=r.nextColor();
			for(int i=0; i<getWidth(); i+=getWidth()/bricksPerLine){
				GRect b=new GRect(i,c*brickHeight, getWidth()/bricksPerLine,brickHeight);
				b.setFilled(true);
				b.setFillColor(col);
				add(b);
				bricks.add(b);
			}
			
		}
	}
	
	
	//GAME OVER label
	public GLabel gameOver(){
		GLabel gOver= new GLabel("GAME OVER");
		gOver.setFont("Ariel-40");
		gOver.setColor(Color.white);
		double x=(getWidth()/2-gOver.getWidth()/2);
		double y=getHeight()/2-gOver.getHeight()/2;
		gOver.setLocation(x,y);
		return gOver;
	}
	
	
	//result under GAME OVER label
	public GLabel bricksDown(){
		GLabel gOver= new GLabel("You destroyed "+bricksDestroyed+ " bricks");
		gOver.setFont("Ariel-20");
		gOver.setColor(Color.white);
		double x=(getWidth()/2-gOver.getWidth()/2);
		double y=getHeight()/2-gOver.getHeight()/2+gameOver().getHeight();
		gOver.setLocation(x,y);
		return gOver;
	}
	//isn't really working rn ignore
	/*public void keyTyped(KeyEvent e){
		if(true){
			ball.setLocation(0,getHeight()/2);
		}
	}*/
	
	
	//moves platform
	public void mouseMoved(MouseEvent e){
		GPoint newLoc;
		if(e.getX()-50>0&&e.getX()+50<getWidth()){//if it's not on the end
			newLoc= new GPoint(e.getX()-50, getHeight()-40);//sets middle of platform location to cursor
		
		}else if(e.getX()-50<0){// if the platform would be off the screen just sets it to against side
			newLoc= new GPoint(0, getHeight()-40);
		}else{// if the platform would be off the screen just sets it to against side
			newLoc= new GPoint(getWidth()-100, getHeight()-40);
		}
		myLine.setLocation(newLoc);
	}
	
	public boolean collides(GOval b, GRectangle l){
		return(b.getBounds().intersects(l));//makes ball into rectangle to check if collides with brick
	}
	
	
	//doesn't work ignore
	/*public void mousePressed(MouseEvent e){
		mouseClicked=true;
	}*/
	
}
	
	


