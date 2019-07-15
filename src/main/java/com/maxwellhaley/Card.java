package com.maxwellhaley;

/**
 * Represents a single card during a game of WAR!. A Card has two attributes: a
 * {@link Rank}, and a {@link Suit}. The Joker card is not present in the game
 * of WAR!.
 * 
 * Cards are stored as a collection inside a {@link Deck}.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2016-11-03
 */
public class Card {
  /** The cards rank */
  private Rank rank;
  /** The cards suit */
  private Suit suit;

  /**
   * The cards rank and suit are assigned at creation. During normal play, there
   * is no reason to change either value.
   * 
   * @param rank - The cards assigned rank
   * @param suit - The cards assigned suit
   */
  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  /**
   * @return The cards rank
   */
  public Rank getRank() {
    return rank;
  }

  /**
   * @return The cards suit
   */
  public Suit getSuit() {
    return suit;
  }
}