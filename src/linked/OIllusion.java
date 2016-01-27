import acm.program.*;
import acm.graphics.*;

public class OIllusion extends GraphicsProgram{
  private final int SQUARES_PER_SIDE=4;
  private final int SQUARE_SIZE=20;
  private final int SPACING=5;
  
  
  
  public void run(){
    int firstX=(getWidth()/2)-((SQUARES_PER_SIDE*(SQUARE_SIZE+SPACING))/2);//centered x
    int firstY=(getHeight()/2)-((SQUARES_PER_SIDE*(SQUARE_SIZE+SPACING))/2);//centered y
    int x=firstX;
    int y=firstY;
    for(int i=0;i<SQUARES_PER_SIDE;i++){
      for(int c=0;c<SQUARES_PER_SIDE;c++){
        add(createSquare(x,y));
        x+=SQUARE_SIZE+SPACING;
      }
      x=firstX;//reset x to left bound
      y+=SQUARE_SIZE+SPACING;//move down
    }
      
  }
  public GRect createSquare(int x, int y){//creates a black square
    GRect s=new GRect(x,y,SQUARE_SIZE,SQUARE_SIZE);
    s.setFilled(true);
    return s;
  }
}
    
  