package com.maxwellhaley.war.core.model;

import com.maxwellhaley.war.core.exception.NotEnoughCashException;

/**
 * Represents a player in the game of <b>WAR!</b>.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-14
 */
public class Player {

  /** The players name. */
  private final String name;

  /** The players current amount of cash. Cash is bet in whole values. */
  private int cash;

  /**
   * A players in only created with a name. Their cash value is always set to
   * 1000.
   * 
   * @param name
   */
  public Player(String name) {
    this.name = name;
    cash = 1000;
  }

  /**
   * Get the players current cash value.
   * 
   * @return The players current cash value.
   */
  public int getCash() {
    return cash;
  }

  /**
   * Get the players name.
   * 
   * @return The players name.
   */
  public String getName() {
    return name;
  }

  /**
   * Evaluates the players bet. Subtracts the bet value from their cash. If the
   * bet placed is larger than the current cash value of the player, an
   * exception is raised.
   * 
   * @param bet - The cash value of the players bet.
   * @throws NotEnoughCashException The bet was larger than the players current
   *                                cash value.
   */
  public void bet(int bet) throws NotEnoughCashException {
    if (bet > cash) {
      throw new NotEnoughCashException();
    }

    cash -= bet;
  }
}
