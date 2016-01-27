import acm.program.*;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.*;

public class Snowfall2 extends GraphicsProgram {
	
	public void run(){
		ArrayList<GOval> mylisto = new ArrayList<GOval>();//arraylist that will contain the ovals of flakes
		ArrayList<Flake> mylist= new ArrayList<Flake>();//arraylist of flakes that store the x,y, rate and size
		ArrayList<GOval> remains=  new ArrayList<GOval>();
		ArrayList<GRect> grass=new ArrayList<GRect>();
		ArrayList<GRect> tempgrass=new ArrayList<GRect>();
		int p=0;//counter
		setSize(500,500);
		pause(10);
		int counter2=1;
		//blue background
		GRect background=new GRect(0,0,getWidth(),getHeight());
		background.setFilled(true);
		background.setColor(Color.blue);
		add(background);
		
		//clouds
		GOval cloud1=new GOval(-100,-225,500,300);
		GOval cloud2=new GOval(-150,-150,300,225);
		GOval cloud3= new GOval(150,-135,400,210);
		
		cloud1.setFilled(true);
		cloud2.setFilled(true);
		cloud3.setFilled(true);
		
		cloud1.setFillColor(Color.white);
		cloud2.setFillColor(Color.white);
		cloud3.setFillColor(Color.white);
		
		add(cloud1);
		add(cloud2);
		add(cloud3);
		double whitebottom=-2.5;
		
		int counter=0;
		
		while(true){//infinite loop
			if(counter<500){
			if(p%1==0){//every 1 loops adds another flake
				//variables for snowflake
				double lowestRate=1.5;
				double lowestSize=10.0;
				double rate=Math.random()*4+lowestRate;
				int size=(int) (Math.random()*6+lowestSize);
				double x=(int) (Math.random()*(getWidth()));
				if(p>50&&p<80){//preparing for wind adds flakes to left side
					x=(int)(Math.random()*(getWidth())-250);
				}
				//makes a new Flake storing the random rate, size and col
				Flake unique = new Flake(rate,size,x);
				//makes a oval with the stored rates sizes and cols
				GOval uni=new GOval(x,0,size,size);
				//set inside of flake color to white
				uni.setFilled(true);
				uni.setFillColor(Color.white);
				//adds oval and flake to respective arraylists
				mylisto.add(uni);
				mylist.add(unique);
				
				//adds oval to screen
				add(uni);
				uni.sendToBack();
				uni.sendForward();
				
				
			}
			
			//moving flakes
			
			for(int i =0; i<mylist.size();i++){
				double curRate=mylist.get(i).getrate();
				double randomXMove=0;
				while(randomXMove>-1&&randomXMove<1){
					randomXMove=Math.random()*6-3;
				}
				//wind
				if(p>80&&p<100){//moves right btw p=80 and p=95
					randomXMove=(double)Math.random()*4.0+3.0;
				}
				if(p>100&&p<115){//to make it look less forced: ease off wind
					randomXMove=(double)Math.random()*4.0+1.0;
				}
				if(p>130&&p<150){//moves left btw 110 and 125
					randomXMove=(-(double)Math.random()*4.0-3.0);
				}
				if(p>150&&p<160){
					randomXMove=(-(double)Math.random()*4.0-1.0);
				}
				if(p==161){
					p=0;
				}
				mylisto.get(i).move(randomXMove, curRate);
				mylist.get(i).move();
				
				//if it hits the bottom
				double curY=mylist.get(i).location().getY();
				//increases base .1 every numloops loops
				int numloops=80;
				if(p%numloops==0){
					whitebottom+=.01;
				}
				
				if(!(curY+whitebottom<=getHeight())){
					mylisto.get(i).setColor(Color.white);//gets rid of black boundary
					remains.add(mylisto.get(i));
					mylisto.remove(i);//removes oval from arraylist
					mylist.remove(i);//removes flake from arraylist
					i--;//to make sure the for loop doesn't skip over the next flake
				}
			}
			pause(40);//pausing between 
			p++;//counters
			counter++;//
			}else{
				//get rid of rest of flakes
				while(mylist.size()>=1){
				for(int i =0; i<mylist.size();i++){
					double curRate=mylist.get(i).getrate();
					double randomXMove=0;
					while(randomXMove>-1&&randomXMove<1){
						randomXMove=Math.random()*6-3;
					}
					mylisto.get(i).move(randomXMove, curRate);
					mylist.get(i).move();
					double curY=mylist.get(i).location().getY();
					if(!(curY+whitebottom<=getHeight())){
						mylisto.get(i).setColor(Color.white);//gets rid of black boundary
						remains.add(mylisto.get(i));
						mylisto.remove(i);//removes oval from arraylist
						mylist.remove(i);//removes flake from arraylist
						i--;//to make sure the for loop doesn't skip over the next flake
					}
					
					
				}
				pause(40);
				}
				//part clouds
				for(int o=0; o<70;o++){
					cloud1.move(-10, 0);
					cloud2.move(10, 0);
					cloud3.move(-10, 0);
					pause(20);
				}
				//sun emerges
				GOval sun=new GOval(-250,-125,250,250);//corner sun!
				sun.setFilled(true);
				sun.setColor(Color.yellow);
				add(sun);
				for(int i=0; i<25;i++){
					sun.move(5, 0);
					pause(7);
				}
				//melting
				for(int q=remains.size()-1; q>=0;q--){
					remove(remains.get(q));
					remains.remove(q);
					pause(10);
				}
				
				//grass
				if(counter2==1){//only the first time this is executed
					counter2++;
					for(int i=0; i<getWidth();i+=5){//makes list of grass
						double height=Math.random()*40+50;//blades have random heights
						GRect blades=new GRect(i,getHeight(),5, height);//blades start below screen
						blades.setFilled(true);
						blades.setColor(Color.green);
						grass.add(blades);
						add(blades);
					}
					
					//randomizes order in grass
					for(int i=0; i<grass.size();i++){
						int rand=(int)(Math.random()*grass.size());
						tempgrass.add(grass.get(rand));
						grass.remove(rand);
						i--;
					}
					for(GRect each: tempgrass){
						grass.add(each);
						}
				}
				//grass grows
				for(int i=0;i<=9;i++){
					for(GRect blades: grass){
						blades.move(0, -blades.getHeight()/10);
						pause(5);
						}
				}
				
				//reset variables for next snowfall
				counter=0;
				whitebottom=-2.5;
				//return clouds
				//send sun behind clouds
				sun.sendBackward();
				sun.sendBackward();
				sun.sendBackward();
				for(int o=0; o<70;o++){
					sun.move(0, -1);//sun disapears to top of screen as clouds move in
					cloud1.move(10, 0);
					cloud2.move(-10, 0);
					cloud3.move(10, 0);
					pause(20);
				}
				//so clouds are in front of snow
				cloud1.sendToFront();
				cloud2.sendToFront();
				cloud3.sendToFront();
				remove(sun);
				for(int i=0; i<10;i++){
					for(int c=0;c<grass.size();c++){
						grass.get(c).move(0, grass.get(c).getHeight()/8);
						pause(5);
					}
				}
								
			}
			
		
			
			
		}
		
	}
}

