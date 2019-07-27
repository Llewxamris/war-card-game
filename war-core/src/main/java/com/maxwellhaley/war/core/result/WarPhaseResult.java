package com.maxwellhaley.war.core.result;

import com.maxwellhaley.war.core.model.Card;

/**
 * The results of running the <b>WAR!</b> phase. Extends the
 * {@link StandoffPhaseResult} instead of the {@link PhaseResult} to get access
 * to the {@link StandoffPhaseResult} cards functionality.
 * 
 * Adds fields to store the result of a possible risk, as well as the dealers
 * card associated with that risk.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public class WarPhaseResult extends StandoffPhaseResult {

  /** The outcome of taking a risk. */
  private Outcome riskOutcome;

  /** The card drawn by the dealer in the case of taking a risk. */
  private Card dealersDealtCard;

  /**
   * Calls {@link StandoffPhaseResult}, while also setting the risk outcome and
   * dealers card.
   * 
   * @param outcome
   * @param potValue
   * @param playerOneCashValue
   * @param playerTwoCashValue
   * @param playerOneDealtCard
   * @param playerTwoDealtCard
   * @param riskOutcome
   * @param dealersDealtCard
   */
  public WarPhaseResult(Outcome outcome, int potValue,
          int playerOneCashValue, int playerTwoCashValue,
          Card playerOneDealtCard, Card playerTwoDealtCard, Outcome riskOutcome,
          Card dealersDealtCard) {
    super(outcome, potValue, playerOneCashValue, playerTwoCashValue,
            playerOneDealtCard, playerTwoDealtCard);
    this.riskOutcome = riskOutcome;
    this.dealersDealtCard = dealersDealtCard;
  }

  /**
   * @return The outcome of taking the risk.
   */
  public Outcome riskOutcome() {
    return riskOutcome;
  }

  /**
   * @return The dealers card drawn in the case of a risk.
   */
  public Card dealersDealtCard() {
    return dealersDealtCard;
  }

}
