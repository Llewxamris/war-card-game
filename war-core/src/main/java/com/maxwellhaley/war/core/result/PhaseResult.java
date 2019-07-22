package com.maxwellhaley.war.core.result;

import java.util.Map;

import com.maxwellhaley.war.core.constant.Player1;
import com.maxwellhaley.war.core.constant.Player2;

/**
 * Represents the base elements needed in a result object. The result of the
 * phase itself, the updated winnings, and the current players cash values.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public abstract class PhaseResult {

  /** The result of the phase. */
  private Result phaseResult;

  /** The current winnings value. */
  private int winnings;

  /** The current players cash values. */
  private Map<String, Integer> playersCash;

  /**
   * Default constructor for all phase results.
   * 
   * @param phaseResult
   * @param winnings
   * @param playersCash
   */
  public PhaseResult(Result phaseResult, int winnings,
          Map<String, Integer> playersCash) {
    this.phaseResult = phaseResult;
    this.winnings = winnings;
    this.playersCash = playersCash;
  }

  /**
   * @return The result of running the phase.
   */
  public Result phaseResult() {
    return phaseResult;
  }

  /**
   * @return The current winnings cash value after the phase.
   */
  public int winnings() {
    return winnings;
  };

  /**
   * @return The amount of cash P1 has after this phase.
   */
  public int p1Cash() {
    return playersCash.get(Player1.CASH);
  }

  /**
   * @return The amount of cash P2 has after this phase.
   */
  public int p2Cash() {
    return playersCash.get(Player2.CASH);
  }

}
