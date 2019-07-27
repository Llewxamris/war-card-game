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
public class StandoffPhaseResult extends PhaseResult {

  /** The card dealt to Player One during this phase. */
  private final Card playerOneDealtCard;

  /** The card dealt to Player Two during this phase. */
  private final Card playerTwoDealtCard;

  /**
   * Calls {@link PhaseResult}, while also setting the players cards.
   * 
   * @param phaseResult
   * @param winnings
   * @param playerOneCashValue
   * @param playerTwoCashValue
   * @param playerOneDealtCard
   * @param playerTwoDealtCard
   */
  public StandoffPhaseResult(Outcome phaseResult, int winnings,
          int playerOneCashValue, int playerTwoCashValue,
          Card playerOneDealtCard, Card playerTwoDealtCard) {
    super(phaseResult, winnings, playerOneCashValue, playerTwoCashValue);
    this.playerOneDealtCard = playerOneDealtCard;
    this.playerTwoDealtCard = playerTwoDealtCard;
  }

  /**
   * @return The card dealt to Player One.
   */
  public Card playerOneDealtCard() {
    return playerOneDealtCard;
  }

  /**
   * @return The card dealt to Player Two.
   */
  public Card playerTwoDealtCard() {
    return playerTwoDealtCard;
  }

}
