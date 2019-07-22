package com.maxwellhaley.war.core.result;

import java.util.Map;

/**
 * The results of running the betting phase. Does not implement anything beyond
 * {@link PhaseResult}.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public class BettingPhaseResult extends PhaseResult {

  /**
   * @param phaseResult
   * @param winnings
   * @param playersCash
   */
  public BettingPhaseResult(Result phaseResult, int winnings,
          Map<String, Integer> playersCash) {
    super(phaseResult, winnings, playersCash);
  }

}
