import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

public class Snake extends GraphicsProgram{
	GRect curA;//current apple on screen
	GRect background;//black background
	int blocksize=30;//size of the blocks of the snake and the apple, also the size of the movements
	int maxX;//max x on screen for apple
	int maxY;//max y on screen for apple
	GRect head;//head of snake
	SnakeBits h;//head of snake in Snake bit
	int dx=0;//movement in x direciton
	int dy=0;//movement in y direction
	int addCounter=0;//number of snakebits left to add
	String direction;//direction: "U", "D", "R", "L"
	boolean shifted=true;//has the snake been shifted since the last input of the player
	ArrayList<TPoint> turnPoint=new ArrayList<TPoint>();//arraylist of places to turn
	ArrayList<SnakeBits> snake=new ArrayList<SnakeBits>();//arraylist of snake
	RandomGenerator ra= new RandomGenerator();//random generator for location of the apples
	
	public void init(){
		setSize(800,800);//window size
		setTitle("Snake");

		addKeyListener(this);//keylist
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	public void run(){
		addBackground();//adds the background
		makeSnake(blocksize);//makes the head
		maxX=getWidth()/blocksize;
		maxY=getHeight()/blocksize;
		addApple();//add first apple in random location
		while(notHit()){//while it hasn't hit itself or the side
			shift(dx, dy);
			if(h.getLocation().equals(curA.getLocation())){//if the snake hits the apple
				addCounter=1;//number of snakebits to add
				remove(curA);//remove the apple
				addApple();//add the next apple
			}
			if(addCounter>0){//add one of the snakebits remaining each round
				ate();
				addCounter--;
			}
			pause(100);
			
		}
		gameOver();
	
	}
	
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			
			if((h.getDY()==0||snake.size()==1)){
				if(!shifted){//if the snake hasn't shifted since the last input
					pause(70);
				}
				dx=0;
				dy=-blocksize;
				direction="U";
				h.setDX(dx);
				h.setDY(dy);
				if(snake.size()!=1){//if it's not just the head
					TPoint t=new TPoint (head.getLocation(),direction);
					turnPoint.add(t);
					}
				shifted=false;
				}
			
					
			}		  
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			if((h.getDX()==0||snake.size()==1)){
				if(!shifted){
					pause(70);
				}
				dx=blocksize;		   
				dy=0;
				direction="R";
				h.setDX(dx);				
				h.setDY(dy);
				if(snake.size()!=1){
					TPoint t=new TPoint (head.getLocation(),direction);
					turnPoint.add(t);
					}
				shifted=false;
				}
			}		  
		if(e.getKeyCode() == KeyEvent.VK_LEFT){	
			if((h.getDX()==0||snake.size()==1)){
				if(!shifted){
					pause(70);
				}
				dx=-blocksize;		   
				dy=0;
				direction="L";
				h.setDX(dx);
				h.setDY(dy);
				if(snake.size()!=1){
					TPoint t=new TPoint (head.getLocation(),direction);
					turnPoint.add(t);
					}
				shifted=false;
				}
			}		  
		if(e.getKeyCode()== KeyEvent.VK_DOWN){
			if((h.getDY()==0||snake.size()==1)){
				if(!shifted){
					pause(100);
				}			
				dx=0;		   			
				dy=blocksize;			
				direction="D";			
				h.setDX(dx);			
				h.setDY(dy);
				if(snake.size()!=1){
					TPoint t=new TPoint (head.getLocation(),direction);
					turnPoint.add(t);
				}
				shifted=false;
			}
		}		  
	}
	private boolean notHit(){
		for(int i=0;i<snake.size();i++){
			for(int j=i+1;j<snake.size();j++){
				if(snake.get(j).getLocation().equals(snake.get(i).getLocation())){
					return false;
				}
			}
			
		}
		if(h.getRect().getX()<0||h.getRect().getX()+blocksize>background.getWidth()){
			return false;
		}
		if(h.getRect().getY()<0||h.getRect().getY()+blocksize>background.getHeight()){
			return false;
		}
		return true;
	}
	private void addBackground(){
		background=new GRect (0,0,getWidth(), getHeight());
		background.setFilled(true);
		background.setColor(Color.BLACK);
		add(background);
		
	}
	private void makeSnake(int size){
		head=new GRect(size,size,size,size);
		head.setFilled(true);
		head.setFillColor(Color.WHITE);
		h=new SnakeBits(head,dx,dy);
		add(h.getRect());
		snake.add(h);
	}
	
	
	public void shift(int dx, int dy){
		
		
		for(SnakeBits i: snake){

			for(TPoint t: turnPoint){
				if(t.getPoint().equals(i.getLocation())){
					i.setDX(t.getDX()*blocksize);
					i.setDY(t.getDY()*blocksize);
				}
			}
			i.getRect().move(i.getDX(),i.getDY());
			shifted=true;
		}
		for(int i=0;i<turnPoint.size();i++){
			GPoint g=snake.get(snake.size()-1).getLocation();
			if(turnPoint.get(i).getPoint().equals(g)){
				snake.get(snake.size()-1).setDX(turnPoint.get(i).getDX()*blocksize);
				snake.get(snake.size()-1).setDY(turnPoint.get(i).getDY()*blocksize);
				turnPoint.remove(turnPoint.get(i));
				i--;

			}
		}
	
	}
	
	
	//not working
	public void ate(){
		int lastX=(int) snake.get(snake.size()-1).getLocation().getX();

		int lastY=(int)snake.get(snake.size()-1).getLocation().getY();
		int nextX=(-snake.get(snake.size()-1).getDX());
		int nextY=(-snake.get(snake.size()-1).getDY());
		
		GRect g=new GRect(nextX+lastX,nextY+lastY,blocksize,blocksize);
		
		
		g.setFilled(true);
		g.setFillColor(Color.WHITE);
		snake.add(new SnakeBits(g,snake.get(snake.size()-1).getDX(),snake.get(snake.size()-1).getDY()));
		add(g);
		
	}
	
	
	public void addApple(){
		curA=new GRect(ra.nextInt(0,maxX-1)*blocksize,ra.nextInt(0, maxY-1)*blocksize,blocksize,blocksize);
		while(appleInSnake(curA)){//dont put the apple in the same place as the snake 
			curA=new GRect(ra.nextInt(0,maxX-1)*blocksize,ra.nextInt(0, maxY-1)*blocksize,blocksize,blocksize);
		}
		curA.setFilled(true);
		curA.setColor(Color.RED);
		add(curA);
	}
	//returns true if apple is in the snake
	private boolean appleInSnake(GRect apple){
		for(int i = 0;i<snake.size();i++){
			if(apple.getLocation().equals(snake.get(i).getLocation())){
				return true;
			}
		}
		return false;
	}
	//flashes game over after h is off screen
	public void gameOver(){
		GLabel o=new GLabel("GAME OVER");
		o.setFont("Ariel-40");
		o.setLocation((background.getWidth()/2-(o.getWidth()/2)),background.getHeight()/2-(o.getHeight()/2));
		o.setColor(Color.RED);
		add(o);
		removeKeyListener(this);
		int i=0;
		while(true){
			if(i%2==0){
				o.setColor(Color.WHITE);
				pause(500);
				}else{
					o.setColor(Color.RED);
					pause(500);
					}
			i++;
			}
		}
	
}
