/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qb.simulator;

import java.util.Scanner;

/**
 *
 * @author Leo
 */
public class GameInterpreter {

  Scanner scan;
  GameQuery current; 
  
  public GameInterpreter(GameQuery initial) //This is the GameQuery that will start our game
  {
    current = initial;
    scan = new Scanner(System.in);
  }
  
  public void runGame() //Might just include the majority of our game code
  {
    boolean good_end = false;
    boolean stop = false;
    
    //Intro text
    //Sarcastic Simulator Of Being A Kyuubey 2016 2: Coder's Edition: KILL ALL THE FUCKING KYUUBEYS (DLC)
    //now hey, THAT'S a way to get more of that karma going
    System.out.println("ONCE UPON A TIME THERE WERE KYUBEYS");
    scan.next();
    System.out.println("THEY CREATED THE UNIVERSE");
    scan.next();
    System.out.println("NOW THEY MUST DIE");
    scan.next();
    
     while (!stop) //Each loop is one GameQuery
     {
       //Prints question
        System.out.println(current.getQuery()); //Prints question, maybe add a second println as a buffer | probably
        System.out.println();
        String input_string;
       
       //Retrives response
        input_string = scan.next();
        int result_index = -1;
        for (int i=0; i<current.getResults().length; i++)
        {
          if (input_string.equals(current.getResults()[i][0]))
              {
                result_index = i;
                break;
              }
        }
        while (result_index == -1)
        {
          System.out.println("Invalid response, could you repeat?"); //Or whatever you want to put when their input doesn't match a response
          input_string = scan.next();
          for (int i=0; i<current.getResults().length; i++)
          {
            if (input_string.equals(current.getResults()[i][0]))
                {
                  result_index = i;
                  break;
                }
          }
        }
                
        //Prints result text or get ending if necessary
        String nextText; //Can't declare variables in for loops in java IIRC 
        //So now we know which ending to pursue
        for (int i=1; i<current.getResults()[result_index].length; i++)
        {
          nextText = current.getResults()[result_index][i];
          if (nextText.equals("ENDING_GOOD"))
              {
                 good_end = true;
                 break;
              }
          else if (nextText.equals("ENDING_BAD"))
              {
                 good_end = false;
                 break;
              }
                   //you know we have more endings than that
                   //That's just for the ending splash
                   //The actual text is merged with the result text
                   //We could always add more flags if we want more splashes
            else
            {
            	System.out.println(nextText);
            	scan.next(); //Pauses execution and gives the player time to read
            }
        }
                   
        //Next query
        current = current.getNextQueries()[result_index];
        
     }
                   
    if (good_end) //It's a good end
    {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("GONE OUT WITH A BANG (A.K.A. COOL) ENDING ／人◕ ‿‿ ◕人＼");
      System.out.println(); // the biggest problem is...
      System.out.println(); // THERE'S NO COMMODORE GRAPHICS KYUUBEY
      System.out.println(); // BRB HANGING MYSELF
    }
    else //It's a bad end
    {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("BAD ENDING."); //Or whatever we want the bad end to be
      System.out.println(); // the biggest problem is...
      System.out.println(); // THERE'S NO COMMODORE GRAPHICS KYUUBEY
      System.out.println(); // we need a good for loop or something that'd fill the screen with "THE END IS NEVER THE END."
      //K i'm back
    }
  }   
}
