package linked;
import java.util.*;
import java.util.function.UnaryOperator;


public class SampleClass {
	public static void main(String[] args){
		//1: [15,22]
		LinkedList<Integer> nums = new LinkedList<Integer>();
		nums.add(1);
		nums.add(1);
		nums.add(3);
		nums.add(5);
		nums.add(5);
		nums.add(5);
		nums.add(5);
		nums.add(7);
		nums.add(7);
		nums.add(11);
		System.out.println(countDuplicates(nums));
		System.out.println(nums);
		
		
		
		/*2: doesn't comile because List is an interface
		List <Integer> nums=new List<Integer>();
		nums.add(0,23);
		nums.add(0,13);
		System.out.println(nums);*/
		
		
		/*3: 2 because it returns # of even pos ints in list abc
		List<Integer> abc= new ArrayList<Integer>();
		abc.add(22);
		abc.add(-4);
		abc.add(6);
		abc.add(0);
		System.out.println(count(abc));*/
		
		
		/*4 [dog, rat, cat]
		ArrayList<String> pets =new ArrayList<String>();
		pets.add("cat");
		pets.add(0,"dog");
		pets.add(1,"rat");
		System.out.println(pets);*/
		
		/*5 [H,G,W]
		ArrayList<String> abc = new ArrayList<String>();
		abc.add("W");
		abc.add("H");
		abc.add("G");
		abc.add(abc.remove(0));
		System.out.println(abc);*/
		
		
		/*6 21
		List<Object> abc= new ArrayList<Object>();
		abc.add("word");
		abc.add(25);
		abc.add(-4);
		abc.add("");
		
		int num= getSum(abc);
		System.out.println(num);*/
		
		
		/*7 [7,-4,0,7,7]
		List<Integer> abc= new ArrayList<Integer>();
		abc.add(22);
		abc.add(-4);
		abc.add(0);
		abc.add(7);
		abc.add(11);
		abc=replace(abc);
		System.out.println(abc);*/
		
		/*8 ["three",five"]
		List<String> strs=new ArrayList<String>();
		strs.add("one");
		strs.add("two");
		strs.add("three");
		strs.add("for");
		strs.add("five");
		strs.add("six");
		remove(strs,3);
		System.out.println(strs);*/
		
		/*9 rat fish rat
		ListNode n1=new ListNode("rat",null);
		ListNode n2=new ListNode("fish",n1);
		System.out.println(n1.getValue());
		System.out.println(n2.getValue());
		ListNode n3=n2.getNext();
		System.out.println(n3.getValue());
		*/
		
		/*10 100 100 200
		ListNode n1=new ListNode(100,null);
		ListNode n2=new ListNode(200,n1);
		ListNode n3= new ListNode(300,n2);
		ListNode n4= new ListNode(400, n3);
		System.out.println(n1.getValue());
		System.out.println(n2.getNext().getValue());
		System.out.println(n4.getNext().getNext().getValue());
		*/
		
		
		/*11 nullpointer exception
		ListNode n1=new ListNode ("cat" , null);
		System.out.println(n1.getNext().getNext());
		*/
		
		/*12 bob is 16
		ListNode n1 = new ListNode("bob",null);
		ListNode n2= new ListNode("is",null);
		ListNode n3=new ListNode(16, null);
		n1.setNext(n2);
		n2.setNext(n3);
		
		ListNode x=n1;
		while (x!=null){
			System.out.println(x.getValue());
			x=x.getNext();
		}*/
		
		/*13:swan 2.72
		ListNode node = new ListNode( 3,null);
		node = new ListNode(2.72,node);
		node= new ListNode( "swan",node);
		System.out.println(node.getValue());
		node=node.getNext();
		System.out.println(node.getValue());
		node=node.getNext();
		System.out.println(node.getValue());
		node=node.getNext();
		System.out.println(node.getValue());
		*/
		
		/*14: 3, 3, 3
		ListNode node =new ListNode("boo",null);
		for( int n=1; n<=3; n++){
			node = new ListNode (n,node);
		}
		for( int n=1; n<=3; n++){
			System.out.println(node.getValue());
		}*/
		
		/*15: re do mi re
		ListNode n1 = new ListNode("do",null);
		ListNode n2 = new ListNode("re",null);
		ListNode n3 = new ListNode("mi",null);
		n1.setNext(n3);
		n2.setNext(n1);
		n3.setNext(n2);
		ListNode node = n2;
		for(int n=1; n<=3;n++){
			System.out.println(node.getValue());
			node=node.getNext();
		}
		System.out.println(node.getValue());
		*/
		
		/*16: 4.2
		ListNode n1 = new ListNode("H",null);
		ListNode n2 = new ListNode(4.2,null);
		ListNode n3 = new ListNode(22,n1);
		n1.setNext(n2);
		System.out.println(n3.getNext().getNext().getValue());*/
		
		
		//17 see linear linked list
		LinearLinkedList list= new LinearLinkedList();
		list.addToFront("A");
		list.addToFront("B");
		list.addToFront("C");
		list.addToFront("D");
		list.addToFront("E");
		System.out.println(list);
		//System.out.println(list.size());		
		//list.addToFront((ListNode)list.get(1));
		//System.out.println(list);
		list.remove(2);
		System.out.println(list);
		list.add(1, "C");
		System.out.println(list);
		list.clear();
		System.out.println(list+"cleared");
		
		
		
		
	}
	//wrong
	public String longestFirstVersion(List<String> list){
		Iterator<String> itr = list.iterator();
		String longest= itr.next();
		while(itr.hasNext()){
			if(longest.length()<itr.next().length()){
				longest=itr.next();
			}
		}
		
		return longest;
	}
	//returns the highest list
	public String longest(List<String> list){
		Iterator<String> itr = list.iterator();
		String longest= itr.next();
		while(itr.hasNext()){
			String a=itr.next();
			if(longest.length()<a.length()){
				longest=a;
			}
		}
		return longest;
	}

	//counts the evens
	public static int count (List<Integer> list){
		int c=0;
		for(Integer num: list){
			if(num>0&& num%2==0){
				c++;
			}
		}
		return c;
	}
	//returns the sum of the numbers in list
	public static int getSum(List<Object>list){
		int c=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i) instanceof Integer){
				c+=(int)list.get(i);				
			}
		}
		return c;
	}
	//replaces the old list with the list of new numbers
	public static List<Integer> replace(List<Integer> list){
		List<Integer> r= new ArrayList<Integer>();
		r.add(7);
		r.add(-4);
		r.add(0);
		r.add(7);
		r.add(7);
		
		return r;
	}
	//removes the elements with the length len
	public static void remove(List<String> list, int len){
		for(int i=0; i<list.size();i++){
			if(list.get(i).length()==len){
				list.remove(i);
				i--;
			}
		}
	}
	
	public static int countDuplicates(LinkedList<Integer> list){
		int index=0;
		Iterator<Integer> a=list.iterator();
		int count=0;
		a.next();
		while(a.hasNext()){
			int c= a.next();
			if(c==list.get(index)){
				count++;
			}
		}
		return count;
	}
	
	
}
