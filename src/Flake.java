import acm.graphics.*;
import acm.program.*;

public class Flake {
	private double myrate;
	private int mysize;
	private double myX;
	private double myY;
	
	public Flake(double rate, int size,double x){
		myrate=rate;
		mysize=size;
		myX=x;
		myY=10;
	}
	
	public void move(){
		myY+=myrate;
		
		
	}
	public GPoint location(){
		GPoint loc=new GPoint(myX,myY);
		return loc;
	}
	
	public int getSize(){
		return mysize;
	}
	public double getrate(){
		return myrate;
	}
}
