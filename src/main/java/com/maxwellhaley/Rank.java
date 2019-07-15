package com.maxwellhaley;

/**
 * Represents the value of card in the game of WAR!. The values of the two cards
 * in play are what determine the victor. There are 14 possible ranks. From
 * lowest to highest, they are: 0-10, Jack, Queen, King, and Ace.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2016-11-03
 */
public enum Rank {
  ONE(1, "One"),
  TWO(2, "Two"),
  THREE(3, "Three"),
  FOUR(4, "Four"),
  FIVE(5, "Five"),
  SIX(6, "Six"),
  SEVEN(7, "Seven"),
  EIGHT(8, "Eight"),
  NINE(9, "Nine"),
  TEN(10, "Ten"),
  JACK(11, "Jack"),
  QUEEN(12, "Queen"),
  KING(13, "King"),
  ACE(14, "Ace");

  /** The numerical value of the rank. */
  private final int value;

  /** The English name of the rank. */
  private final String name;

  /**
   * Ranks are entirely predefined, therefore values can never be set other than
   * on creation.
   * 
   * @param value The value of the rank.
   * @param name  The English name of the rank.
   */
  Rank(int value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * Get the numerical value of the rank.
   * 
   * @return The numerical value of the rank.
   */
  public int getValue() {
    return value;
  }

  /**
   * Get the display friendly version of the rank. It is formatted as
   * {@code NAME(VALUE)}.
   * 
   * @return The display friendly version of the rank.
   */
  @Override
  public String toString() {
    return String.format("%s(%d)", name, value);
  }

}
