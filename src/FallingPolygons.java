import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

//Triangles, squares, and cirlces are falling, click on related(in color or shape) to make them dissappear
public class FallingPolygons extends GraphicsProgram{
	private int score=0;
	private Color c1= Color.blue;
	private Color c2= Color.red;
	private Color c3= Color.green;
	RandomGenerator r= new RandomGenerator();
	
	
	
	public void init(){
		setSize(800,800);
		setTitle("Falling Polygons");
	}
	
	public void run(){
		GPolygon test= gTriangle(100,100,50);
		test.setColor(c1);
		add(test);
		while(true){
			test.
			pause(5);
		}
		
	}
	
	public GPolygon gTriangle(int x, int y, int scale){
		GPoint a=new GPoint (x,y);
		GPoint b= new GPoint(x-scale*((Math.sin(Math.PI/6))),y+scale*(Math.cos(Math.PI/6)));
		GPoint c=new GPoint(x+(scale*(Math.sin(Math.PI/6))),y+(scale*(Math.cos(Math.PI/6))));
		GPoint[] array={a,b,c};
		GPolygon r=new GPolygon(array);
		
		r.setFilled(true);
		return(r);
	}
	

	
	

}
