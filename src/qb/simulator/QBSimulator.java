//This is a basic engine for a console-based text adventure game with branching choices and multiple endings.

//CSV INSTRUCTIONS:  VERY IMPORTANT:

//The CSV file is divided into lines separated by newline, each line representing a single Query.
//Two delimiters are used: primary delimiter will be semicolon ";", secondary delimiter will be pipe "|", do not use these characters in your text.
//Each line is broken up into strings separated by ";".  Strings can be further broken up into substrings separated by "|".  A bunch of substrings with "|" form a single string.
//The first string in each query is the question being asked.  The second string contains substrings that correspond to the possible next queries.
//The third string contains a boolean value "T" or "F".  Marking this at T will enable custom behavior if an unrecognized response is inputted (more on that later)
//The remaining strings contain the responses and the story being expanded after each response. 
//The first substring in each response string is the expected response, the remaining substrings are the story that would be told after the response is received (line break acts as a newline).
//After reading through the substrings in the story string, the Query will continue to the line corresponding that response branch.  Exception if one of the branch substrings is marked "ENDING_GOOD", or "ENDING_BAD", which causes the ending to start
//The line corresponding to the result branch is marked in the second string in the line
//If the third string is T and custom behavior is enabled, if the user inputted a wrong entry there will be an additional string appended at the end of the line, as well as n additional substring in the second string.  This corresponds to the branch that will happen if input is not recognized

//CSV QUERY LINE EXAMPLE:

//Would you like some cake?;01|02|00;T;Yes|The cake is a lie!|Thank you for visiting Aperture Science;No|OK|Visit us if you still want cake;I did not understand you request|Please write it again

//Here, the user is asked "Would you like some cake?".  If he answers yes, "The cake is a lie!" and "Thank you for visiting Aperture Science" will appear, and the next query will be line 01 (the second line)
//If he answers no, "OK" and "Visit us if you still want cake" will appear, the next query is line 02.
//If he answers anything else, "I did not understand you request" and "Please write it again" will appear, the next query is line 00 (so this one will just be repeated in this instance).  In addition, the counter "loop" will be incremented

//ANOTHER QUERY LINE EXAMPLE:
//Would you like some milk?;00|02|03;F;Yes|You got the milk!|The end!|ENDING_GOOD;No|You did not get the milk!|Keep going!;Maybe|Maybe is a baby|It needs to be coerced until it says yes.

//Here the user is prompted "Would you like some milk?".
//If he answers yes, "You got the milk!" and "The end!" shows.  Because the next prompt is "ENDING_GOOD", the good ending is immediately displayed and the game ends.
//If he answers no, "You did not get the milk", and "Keep going" goes, so the story loops to line 02.  If he answers maybe, the messages from the maybe branch is displayed

package qb.simulator;

import java.io.*;
import java.nio.file.*;

public class QBSimulator {

   public static void main(String[] args)
    {
      try
      {
        //Needed for file IO
        Path p = Paths.get("./story.csv");
        InputStream in = Files.newInputStream(p);
        BufferedReader read = new BufferedReader(new InputStreamReader(in));

        //When the game is running
        GameQuery start = GameQuery.parseCSV(read);
        GameInterpreter game = new GameInterpreter(start);
        boolean replay = false;
        do
        {
              replay = runGame();
        }
        while (!replay);
      }
      catch (IOException e)
      {
          System.out.println("Error: cannot read input file!");
          e.printStackTrace();
      }
    }
    
}
