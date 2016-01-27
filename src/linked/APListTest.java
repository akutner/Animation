package linked;

public class APListTest
{
  public static void printForward(TestReview myList)
  {
    int n = myList.size();
    for (int k = 0; k < n; k++)
    {
      Object obj = myList.get(k);
      System.out.println(obj);
    }
  }

  public static void printReverse(TestReview myList)
  {
    int n = myList.size();
    for (int k = n-1; k >= 0; k--)
    {
      Object obj = myList.get(k);
      System.out.println(obj);
    }
  }

  /*** Part (c) ***/
  //                         printForward      printReverse
  // LinkedList<Object>          O(n^2)            O(n^2)
  // APList                      O(n)              O(n^2)

  public static void main(String[] args)
  {
    TestReview myList = new TestReview();
    myList.addFirst("E");
    myList.addFirst("D");
    myList.addFirst("C");
    myList.addFirst("B");
    myList.addFirst("A");
    System.out.println(myList.get(myList.size() - 1) + "\n");
    printForward(myList);
    System.out.println();
    printReverse(myList);
    System.out.println();
  }
}
