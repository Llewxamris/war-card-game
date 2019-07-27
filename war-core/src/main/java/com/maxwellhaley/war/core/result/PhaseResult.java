package com.maxwellhaley.war.core.result;

/**
 * Represents the base elements needed in a result object. The outcome of the
 * phase, the updated winnings, and the current players cash values.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public abstract class PhaseResult {

  /** The outcome of the phase. */
  private final Outcome outcome;

  /** The current winnings value. */
  private final int potValue;

  /** Player One's cash value. */
  private final int playerOneCashValue;

  /** Player Two's cash value. */
  private final int playerTwoCashValue;

  /**
   * Default constructor for all phase results.
   * 
   * @param outcome
   * @param potValue
   * @param playersCash
   */
  public PhaseResult(Outcome outcome, int potValue,
          int playerOneCashValue, int playerTwoCashValue) {

    this.outcome = outcome;
    this.potValue = potValue;
    this.playerOneCashValue = playerOneCashValue;
    this.playerTwoCashValue = playerTwoCashValue;
  }

  /**
   * @return The outcome of running the phase.
   */
  public Outcome outcome() {
    return outcome;
  }

  /**
   * @return The current winnings cash value after the phase.
   */
  public int potValue() {
    return potValue;
  };

  /**
   * Player One's cash value as of the end of the phase.
   * 
   * @return int - Player One's current cash value.
   */
  public int playerOneCashValue() {
    return playerOneCashValue;
  }

  /**
   * Player Two's cash value as of the end of the phase.
   * 
   * @return int - Player Two's current cash value.
   */
  public int playerTwoCashValue() {
    return playerTwoCashValue;
  }

}
