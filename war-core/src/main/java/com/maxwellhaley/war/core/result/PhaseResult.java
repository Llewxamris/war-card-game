package com.maxwellhaley.war.core.result;

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
  private final Outcome phaseResult;

  /** The current winnings value. */
  private final int potValue;

  /** Player One's cash value. */
  private final int playerOneCashValue;

  /** Player Two's cash value. */
  private final int playerTwoCashValue;

  /**
   * Default constructor for all phase results.
   * 
   * @param phaseResult
   * @param winnings
   * @param playersCash
   */
  public PhaseResult(Outcome phaseResult, int winnings,
          int playerOneCashValue, int playerTwoCashValue) {
    
    this.phaseResult = phaseResult;
    this.potValue = winnings;
    this.playerOneCashValue = playerOneCashValue;
    this.playerTwoCashValue = playerTwoCashValue;
  }

  /**
   * @return The result of running the phase.
   */
  public Outcome phaseResult() {
    return phaseResult;
  }

  /**
   * @return The current winnings cash value after the phase.
   */
  public int potValue() {
    return potValue;
  };
  
  public int playerOneCashValue() {
    return playerOneCashValue;
  }

  public int playerTwoCashValue() {
    return playerTwoCashValue;
  }

}
