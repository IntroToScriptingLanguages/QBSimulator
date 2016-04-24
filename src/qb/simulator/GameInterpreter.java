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
class GameInterpreter{
	
  Scanner scan;
  GameQuery current; 
  
  public GameInterpreter(GameQuery initial) //This is the GameQuery that will start our game
  {
    current = initial;
    scan = new Scanner(System.in);
  }
  
  public boolean runGame() //Returns true if replay, false otherwise
  {
    boolean good_end;
    int loops = 0; //Four of these nets you a bad end
    
    //Intro text
    System.out.println("ONCE UPON A TIME");
    scan.next();
    System.out.println("POINTLESS BUT ENTERTAINING EXPOSITION");
    scan.next();
    System.out.println("MORE POINTLESS BUT ENTERTAINING EXPOSITION");
    scan.next();
    
     while (true) //Each loop is one GameQuery
     {
       //Prints question
        System.out.println(current.getQuery()); //Prints question, maybe add a second println as a buffer | probably
        System.out.println();
        String input_string;
       
       //Retrives response
        input_string = scan.nextLine();
        int result_index = -1;
        for (int i=0; i<current.getResults().length; i++)
        {
          if (input_string.equals(current.getResults()[i][0]))
              {
                result_index = i;
                break;
              }
        }
              
        //Check if has custom retry behavior, if it does use the final array in results
        if (current.customRetryBehavior() && result_index == -1)
        {
             //add one to incrementor:
            loops++;
          
          	String nextText;
            int retryIndex = current.getResults().length;
          
          for (int i=1; i<current.getResults()[retryIndex].length; i++)
          {
            nextText = current.getResults()[retryIndex][i];
            if (nextText.equals("ENDING_GOOD"))
                {
              	  if (loops >= 4)
                  {
                     good_end = false;
                  }
                  else
                  {
                       good_end = true;
                       break;
                  }
                }
            else if (nextText.equals("ENDING_BAD"))
                {
                   good_end = false;
                   break;
                }
              else
              {
                System.out.println(nextText);
                scan.next(); //Pauses execution and gives the player time to read
              }
          }
                            
        //Next query
        current = current.getNextQueries()[retryIndex];
                     
        }
        else
        {
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
              else
              {
                System.out.println(nextText);
                scan.next(); //Pauses execution and gives the player time to read
              }
          }
                            
        //Next query
        current = current.getNextQueries()[result_index];
       }
     }
                   
    if (good_end) //It's a good end
    {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("GONE OUT WITH A BANG (A.K.A. COOL) ENDING ／人◕ ‿‿ ◕人＼");
      System.out.println(); 
      System.out.println(); 
      System.out.println(); 
    }
    else //It's a bad end
    {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println("BAD ENDING."); 
      System.out.println(); 
      System.out.println(); 
      System.out.println(); 
    }
  }
}