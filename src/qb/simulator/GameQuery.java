/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qb.simulator;

/**
 *
 * @author Leo
 */
public class GameQuery {
  private String query;
  private String[][] results;
  private GameQuery[] next_queries;
  
  public GameQuery(String q, String[][] r, GameQuery[] n_q)
  {
    query = q;
    results = r;
    next_queries = n_q;
  }
  
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
  
  //GameQuery should probably be read-only, so no setters
}
