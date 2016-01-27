import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class AnimatedSquare extends GraphicsProgram{
	
	private static int numSteps=1000;
	private int squareSize=25;
	
	public void run(){
		setSize(400,400);
		pause(10);
		
		//square from top left
		GRect square= new GRect(0,0,squareSize,squareSize);
		square.setFilled(true);
		Color pink=new Color(255,20,147);
		square.setColor(pink);
		add(square);
		
		double dx=(double)(getWidth()-squareSize)/numSteps;
		double dy=(double)(getWidth()-squareSize)/numSteps;
		
		//square from top right 
		GRect square2=new GRect(getWidth()-squareSize,0,squareSize,squareSize);
		square2.setFilled(true);
		square2.setColor(Color.black);
		add(square2);
		
		//square from bottom left
		GRect square3=new GRect(0,getHeight()-squareSize,squareSize,squareSize);
		square3.setFilled(true);
		square3.setColor(Color.blue);
		add(square3);
		
		
		//square from bottom right
		GRect square4=new GRect(getWidth()-squareSize,getHeight()-squareSize,squareSize,squareSize);
		square4.setFilled(true);
		square4.setColor(Color.green);
		add(square4);
		boolean in=true;
		//starting animation
		double acc=0;
		while(true){

		for(int i=0; i< numSteps;i++){
			
			if(in){
				acc+=.01;
				dx+=acc;
				dy+=acc;
			}else{
				acc-=.01;
				dx+=acc;
				dy+=acc;
			}
			square.move(dx, dy);
			square2.move(-dx, dy);
			square3.move(dx,-dy);
			square4.move(-dx, -dy);
			
			pause(10);
			//squares bounce off each other
			if(square.getX()+squareSize>=getWidth()/2){
				dx=-dx;
				dy=-dy;
			}
			
		}
		dx=-dx;
		dy=-dy;
		}
		
	}
	
}
