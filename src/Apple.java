import java.awt.Color;

import acm.graphics.*;

public class Apple {
	GPoint location;
	GRect aple;
	public Apple(int size,GPoint loc){
		aple=new GRect( size, size, loc.getX(),loc.getY());
		aple.setFilled(true);
		aple.setColor(Color.RED);
	}
	
	public GPoint getLocation(){
		return location;
	}
	

}
