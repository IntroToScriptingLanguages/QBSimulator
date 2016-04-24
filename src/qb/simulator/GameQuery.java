/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qb.simulator;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Leo
 */
public class GameQuery{
  private String query;
  private String[][] results; //First element is expected response, rest are messages
  private GameQuery[] next_queries;
  private boolean CUSTOM_RETRY_BEHAVIOR; //Retries will be handled by the last compartment of results, one will be added to incrementor in that case
  
  public GameQuery (String q, String[][] r, GameQuery[] n_q, boolean CRB)
  {
    query = q;
    results = r;
    next_queries = n_q;
    CUSTOM_RETRY_BEHAVIOR = CRB;
  }
  
  //ReadOnly here
  public String getQuery()
  {
    return query;
  }
  
  public String[][] getResults()
  {
		return results;
  }
  
  public GameQuery[] getNextQueries()
    {
      return next_queries;
    }
    
    public boolean customRetryBehavior()
    {
      return CUSTOM_RETRY_BEHAVIOR;
    }
  
  public static GameQuery parseCSV(BufferedReader read) throws IOException
    {
        //Each line in a CSV is a GameQuery
      	String line = null;
        
        while ((line = read.readLine()) != null)
        {
          //Index 0: query, Index 1: array of next queries, Index 2: Whether custom response text is enabled, Index 3 to 3 + (number of responses-1): response arrays
          //If custom response text is enabled, we go up to index 3 + number of responses instead.
           String[] gameQueryData = line.split(";"); //Change delimiter as needed
           String query = gameQueryData[0];
           String[] nextQueries = gameQueryData[1].split("|"); //The number of entires in this should be the number of responses if customText is false, and the number of responses plus 1 if customText is true
           boolean customText = (gameQueryData[2].equals("T") || gameQueryData[2].equals("True"));
          
           String[][] results = new String[nextQueries.length][];
            
           //Storing the responses into the result array
              for (int i=0; i<nextQueries.length - 1; i++)
              {
                  results[i] = gameQueryData[3+i].split("|");
              }
          
          //Next game queries:
          //TODO
        }
    }
  
  //Recursive helper function the creates all the gameQueries
  private static GameQuery createGameQuery(String info)
  {
    //TODO
  }
}
