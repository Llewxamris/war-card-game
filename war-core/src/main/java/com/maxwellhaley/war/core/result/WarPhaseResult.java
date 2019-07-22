package com.maxwellhaley.war.core.result;

import java.util.Map;

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

  /** The result of taking a risk. */
  private Result riskResult;

  /** The card drawn by the dealer in the case of taking a risk. */
  private Card dealersCard;

  /**
   * Calls
   * {@link StandoffPhaseResult#StandoffPhaseResult(Result, int, Map, Map)},
   * while also setting the risk result and dealers card. the players cards.
   * 
   * @param phaseResult
   * @param winnings
   * @param playersCash
   * @param playerCards
   * @param riskResult
   * @param dealersCard
   */
  public WarPhaseResult(Result phaseResult, int winnings,
          Map<String, Integer> playersCash, Map<String, Card> playerCards,
          Result riskResult, Card dealersCard) {
    super(phaseResult, winnings, playersCash, playerCards);
    this.riskResult = riskResult;
    this.dealersCard = dealersCard;
  }

  /**
   * @return The result of taking the risk.
   */
  public Result riskResult() {
    return riskResult;
  }

  /**
   * @return The dealers card drawn in the case of a risk.
   */
  public Card dealersCard() {
    return dealersCard;
  }

}
