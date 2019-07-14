package com.maxwellhaley;

public class Card {
  private Rank rank;
  private Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  protected Rank getRank() {
    return rank;
  }

  protected Suit getSuit() {
    return suit;
  }
}