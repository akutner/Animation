package linked;

import acm.program.GraphicsProgram;
import acm.graphics.*;

public class ClientNode extends GraphicsProgram{
	public static void main(String[] args){
		GRect list;
		list = new GRect(0,0,100,100);
		System.out.println("The node contains: "+ (Integer)list.getValue());
		list.setValue(new Integer(17));
		System.out.println("The node contains: "+ (Integer)list.getValue());
		ListNode list2=new ListNode(new Integer(1),null);
		list.setNext(list2);
		
		
		
	}

}
