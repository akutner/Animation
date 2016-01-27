package linked;

import acm.graphics.GRect;

public class ListNode {
	private Object value;
	private ListNode next;
	ListNode first;
	ListNode last;
	public ListNode(Object initValue, ListNode initNext){
		value=initValue;
		next=initNext;
	}
	
	public void signal(Object sigV){
		
	}
	//getters and setters
	public Object getValue(){
		return value;
	}
	
	public ListNode getNext(){
		return next;
	}
	public void setValue(Object newVal){
		value=newVal;
	}
	public void setNext(ListNode newNext){
		next=newNext;
	}
	public String toString(){
		return (String) value;
	}
	
	
	
	
	

}
