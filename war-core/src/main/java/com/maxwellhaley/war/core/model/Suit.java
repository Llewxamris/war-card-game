package com.maxwellhaley.war.core.model;

/**
 * Represents the suit of card in the game of WAR!. The suit has no influence on
 * the game itself. There are four possible suits: Clubs, Diamonds, Hearts, and
 * Spades.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-14
 */
public enum Suit {
  CLUBS("Clubs"),
  DIAMONDS("Diamonds"),
  HEARTS("Hearts"),
  SPADES("Spades");

  /** The name of the suite. */
  private final String name;

  /**
   * Suits are entirely predefined, therefore values can never be set other than
   * on creation.
   * 
   * @param name - The name of the suit.
   */
  Suit(String name) {
    this.name = name;
  }

  /**
   * Get the name of the suit.
   * 
   * @return - The name of the suit.
   */
  @Override
  public String toString() {
    return name;
  }
}
