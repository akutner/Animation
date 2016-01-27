package linked;
import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExamples {
	public static void main (String[] args){
		LinkedList<String> words = new LinkedList<String>();
		words.add("oh");
		words.add("hello");
		words.add("how");
		words.add("are");
		words.add("you");
	}
	public static String longestfirstversion(List<String> list){
		Iterator<String> itr= list.iterator();
		String longest= itr.next();
		while (itr.hasNext()){
			if (itr.next().length()> longest.length()){
				longest=itr.next();
			}
		}
		return longest;
	}
	
	public static String longest(List<String> list){
		Iterator<String> itr= list.iterator();
		String result= itr.next(); //storing the first element here
		while (itr.hasNext()){
			String next=itr.next(); //storing the 2nd element
			if (next.length()> result.length()){
				result=next;
			}
		}
		return result;
	}
	
	public static void removeEvenLength(LinkedList <String> list){
		
	}
	
	public static void removeOddLength(LinkedList <String> list){
		
	}
	
}