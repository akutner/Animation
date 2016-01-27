
import acm.program.*;
import acm.graphics.*;
public class CTimer extends GraphicsProgram{
  private final int START_TIME= 10000;//start time
  private final int UNIT_TIME=72;//decremeting time
  public void run(){
    int cTime=START_TIME;//ctime is the current time
    int ms=cTime%1000;//milliseconds
    String msS=getMS(ms,cTime);
    GLabel time =new GLabel(msS);
    time.setFont("SansSerif-144");//font
    double x=(getWidth()/2)-(time.getWidth()/2);//centered in x direction
    double y=(getHeight()/2)-(time.getHeight()/2);
    time.setLocation(x,y);
    add(time);
    while(cTime>0){
      pause(UNIT_TIME);
      cTime-=UNIT_TIME;
      ms=cTime%1000;
      msS=getMS(ms,cTime);     
      time.setLabel(msS);//set the new label
    }
    
  }
  public String getMS(int ms, int cTime){//returns the string to put in the glabel
    return (cTime/1000+":"+(ms/100)+""+(ms/10-(ms/100*10)));
  }
}