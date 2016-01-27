package linked;

public class LinearLinkedList {
	private ListNode head;
	
	public LinearLinkedList(){
		head=new ListNode(null,null);
	}
	
	public ListNode getFirstNode(){
		return head.getNext();
	}
	
	
	public void replaceFront(Object obj){
		ListNode newNode=new ListNode(obj,null);
		ListNode oldFirst= head.getNext();
		head.setNext(newNode);
		if(oldFirst!=null){
			ListNode secondNode= oldFirst.getNext();
			newNode.setNext(secondNode);
		}
	}
	
	public void addToFront(Object obj){
		ListNode newNode = new ListNode(obj,null);
		ListNode oldFirst = head.getNext();
		head.setNext(newNode);
		if(oldFirst!=null){
			newNode.setNext(oldFirst);
		}
		
	}
	
	public String toString(){
		String s="";
		ListNode node= head.getNext();
		while(node!=null){
			s+=node.getValue().toString() + ",";
			node=node.getNext();
		}
		return s;
	}
	//counts the number of comas to give size of list
	public int size(){
		int count=0;
		String s= this.toString();
		for(int i=0; i<s.length(); i++){
			if(s.substring(i,i+1).equals(",")){
				count++;
			}
		}
		return count;
	}
	//returns the element at index
	public Object get(int index){
		ListNode last= getFirstNode();
		for(int i=0;i<index;i++){
			last=last.getNext();
		}
		return last;
	}
	//removes and returns object at index
	public Object remove(int index){
		ListNode prev=(ListNode) get(index-1);
		if(index!=size()){
			ListNode next=(ListNode) get(index);
			prev.setNext(next.getNext());
			for(int i=index;i<size()-1;i++){
				prev=next;
				next=(ListNode) get(i+1);
				prev.setNext(next.getNext());
			}
		}else{
			prev.setNext(null);
		}
		return get(index);
		
		
	}
	//adds object at index
	public void add(int index, Object o){
		ListNode j=new ListNode(o,null);
		if(index!=0){
			ListNode prev=(ListNode) this.get(index-1);
			j.setNext(prev.getNext());
			prev.setNext(j);
		}else{
			j.setNext((ListNode) this.get(index+1));
		}
	}
	//clears all values in list
	public void clear(){
		head.setNext(null);
	}
}


