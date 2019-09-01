package com.maxwellhaley.war.core.result;

import com.maxwellhaley.war.core.model.Card;

/**
 * The results of running the standoff phase. Adds the cards field to store the
 * cards dealt to the players.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public class StandoffPhaseResult extends AbstractStandoffPhaseResult {

  /**
   * Calls {@link AbstractStandoffPhaseResult}
   * 
   * @param phaseResult
   * @param winnings
   * @param playerOneCashValue
   * @param playerTwoCashValue
   * @param playerOneDealtCard
   * @param playerTwoDealtCard
   */
  public StandoffPhaseResult(Outcome outcome, int potValue,
          int playerOneCashValue, int playerTwoCashValue,
          Card playerOneDealtCard, Card playerTwoDealtCard) {
    super(outcome, potValue, playerOneCashValue, playerTwoCashValue,
            playerOneDealtCard, playerTwoDealtCard);
  }
}
