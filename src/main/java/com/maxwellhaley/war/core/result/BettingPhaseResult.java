package com.maxwellhaley.war.core.result;

/**
 * The results of running the betting phase. Does not implement anything beyond
 * {@link AbstractPhaseResult}.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public class BettingPhaseResult extends AbstractPhaseResult {

  public BettingPhaseResult(Outcome phaseResult, int winnings,
          int playerOneCashValue, int playerTwoCashValue) {
    super(phaseResult, winnings, playerOneCashValue, playerTwoCashValue);
  }

}
