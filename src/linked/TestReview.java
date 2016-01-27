package linked;

public class TestReview {
	
		  private ListNode front;    // first node of this list (null if empty)

		  private int listSize;      // the number of elements in this list

		  private int remIndex;      // the index of the remembered node

		  private ListNode remNode;  // a reference to the node accessed by most recent call to get

		  /**  Constructs an empty APList.
		   */
		  public TestReview()
		  {
		    front = null;
		    remIndex = -1;
		    remNode = null;
		    listSize = 0;
		  }
	public Object get(int n){
		int startIndex=0;
		ListNode startNode=front;
		if(n>remIndex){
			startIndex=remIndex;
			startNode=remNode.getNext();
		}
		while(startIndex!=n){
			startNode=startNode.getNext();
			startIndex++;
			
		}
		
		remNode=startNode;
		remIndex=startIndex;
		return remNode;
	}
	public void addFirst(Object obj){
		ListNode n= new ListNode(obj,front);
		front=n;
		listSize++;
		if(remIndex>0){
			remIndex++;
		}
	}
	public int size(){
		return listSize;
	}

}
