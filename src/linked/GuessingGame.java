import acm.program.*;
import acm.util.*;

public class GuessingGame extends ConsoleProgram{
  private int myNum;
  public RandomGenerator r= new RandomGenerator();
  int guess;
  int tries;
  public void run(){
    myNum=r.nextInt(1,100);
    while(guess!=myNum){
      int guess=readInt("Enter your guess between 1 and 100");//stores the guess
      if(guess>100||guess<1){//out of bounds
        println("Out of bounds");//doesn't increment guessses
      }else if(guess<myNum){//
        println("Too low");
        tries++;
      }else if(guess>myNum){
        println("Too high");
        tries++;
      }
      if(guess==myNum){//if guess is right
        tries++;
        break;
      }
    }
    println("You got it! You tried "+tries+" times");
  }
}
  