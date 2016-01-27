import java.util.*;
import java.io.*;
public class Test5{
  private final static String FILE_NAME="mobydick.txt";
  public static void main(String [] args){
    HashSet<String> set1=new HashSet<String>();
    set1.add("1");
    set1.add("2");
    set1.add("3");
    set1.add("4");
    set1.add("5");
    HashSet<String> set2=new HashSet<String>();
    set2.add("2");
    set2.add("3");
    set2.add("6");
    set2.add("7");
    
    System.out.println("Set1");
    for(String s: set1){
      System.out.print(s+", ");
      
    }
    System.out.println();
    System.out.println("Set2");
    for(String s: set2){
      System.out.print(s+ ", ");
    }
    System.out.println();
    
    int a= totalElements(set1,set2);
    System.out.println(a+" total elements of set 1 and 2");//should return 7
    
    Set<String> set=new HashSet<String>();
    set.add("123");
    set.add("12");
    set.add("2");
    set.add("4");
    set.add("123456");
    set.add("12345");
    set.add("1357");
    
    System.out.println("Before removing the even length strings");
    for(String s: set){
      System.out.print(s+", ");
    }
    System.out.println();
    removeEvenLength(set);
    System.out.println("After removing the even length strings");
    for(String s: set){
      System.out.print(s+", ");//only odd length strings print
    }
    System.out.println();
      
    Set<String> thre=threes();
    System.out.println("All the 3 letter unique words in mobydick.txt");
    for(String s: thre){
      System.out.println(s);
    }
                             
    Set<String> r=morethan2k().keySet();
    Map<String, Integer> p=morethan2k();
    System.out.println("All the words that occur more than 2000 times in mobydick.txt");
    for(String s:r){
      System.out.println(s+" occurs "+p.get(s)+" times ");
    }
  }
  
  
  //@returns # of unique elements contained in either set1 or set2
  public static int totalElements(Set<String> set1, Set<String> set2){
    Set<String> r=new HashSet<String>(set1);
    r.addAll(set2);//union of set 1 and 2
    return r.size();
  }
  
  public static void removeEvenLength(Set<String> set){
    Set<String> evens=new HashSet<String>();
    for(String s:set){
      if(s.length()%2==0){
        evens.add(s);//stores all the evens in one set
      }
    }
    set.removeAll(evens);//removes all the evens from the set
  }
  public static Set<String> threes(){
    TreeSet<String> words= new TreeSet<String>();
    try{
      BufferedReader br=new BufferedReader(new FileReader(FILE_NAME));//reads mobydick.txt
      while(true){
        String line=br.readLine();
        if(line==null){//if there are no more lines
          break;
        }else{
          StringTokenizer st=new StringTokenizer(line);
          while(st.hasMoreTokens()){
            String cur=st.nextToken();
            cur=cur.toLowerCase();//makes all lowercase to compare
            if(cur.length()==3&&!containsOthers(cur)){//if the length is 3 and doesnt contain a nonletter
              words.add(cur);
            }
          }
        }
            
      }
      br.close();
    }catch(IOException e){
      System.out.println("Can't read file");
    }
    return words;
  }
  public static Map<String,Integer> morethan2k(){
    TreeMap<String, Integer> words= new TreeMap<String, Integer>();//map for all the words
    Map<String,Integer> morethan2k=new TreeMap<String,Integer>();//map for words whose value>2000
    try{
      BufferedReader br=new BufferedReader(new FileReader(FILE_NAME));
      while(true){
        String line=br.readLine();
        if(line==null){
          break;
        }else{
          StringTokenizer st=new StringTokenizer(line);
          while(st.hasMoreTokens()){
            int cN=0;
            String cur=st.nextToken();
            cur=cur.toLowerCase();
            if(words.containsKey(cur)){//if it is already in the map
              cN=words.get(cur);
            }
            cN++;
            if(cN>2000){
              morethan2k.put(cur,cN);
            }
            words.put(cur,cN);
            
          }
        }
            
      }
      br.close();
    }catch(IOException e){
      System.out.println("Can't read file");
    }
    return morethan2k;
  }
  
  //returns whether or not a string contains a non-letter symbol
  public static boolean containsOthers(String cur){
    String delims=new String(",./\";1234567890()?:!'*");
    char[] dlimCs=delims.toCharArray();
    for(char c:dlimCs){
      for(char a:cur.toCharArray()){
        if(a==c){
          return true;
        }
      }
    }
    return false;
  }
}
    
  