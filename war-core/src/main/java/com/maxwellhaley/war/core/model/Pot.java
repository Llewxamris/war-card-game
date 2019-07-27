package com.maxwellhaley.war.core.model;

/**
 * The pot stores the current possible earnings during a round of <b>WAR!</b>.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-26
 */
public class Pot {

  /** Current cash value of the pot. */
  private int value;

  /** The pot begins empty. */
  public Pot() {
    value = 0;
  }

  /**
   * Add a cash value to the pots total value.
   * 
   * @param bet - Bets from the players.
   */
  public void addCash(int bet) {
    value += bet;
  }

  /** Set the cash value to 0. */
  public void clearValue() {
    value = 0;
  }

  /**
   * @return The pot's cash value.
   */
  public int getValue() {
    return value;
  }

  /** Double the pot's cash value. */
  public void doubleValue() {
    value *= 2;
  }

  /** Halve the pot's cash value. */
  public void halveValue() {
    value /= 2;
  }

}
