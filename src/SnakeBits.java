import acm.graphics.GPoint;
import acm.graphics.*;

public class SnakeBits {
	GRect part;
	SnakeBits n;
	int dx;
	int dy;
	public SnakeBits(GRect bit, /*SnakeBits next,*/ int ydx,int ydy){
		dx=ydx;
		dy=ydy;
		part=bit;
		//n=next;
	}
	
	public void move(){
		part.move(dx, dy);
	}
	
	public int getDX(){
		return dx;
	}
	public int getDY(){
		return dy;
	}
	public void setDX(int nDX){
		dx=nDX;
	}
	public void setDY(int nDY){
		dy=nDY;
	}
	public GPoint getLocation(){
		return part.getLocation();
	}
	public GRect getRect(){
		return part;
	}

}
