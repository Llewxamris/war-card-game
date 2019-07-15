package com.maxwellhaley;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the deck during a game of WAR!. The deck contains a collection of
 * {@link Card}s from which the dealer deals from. The deck must contain one and
 * only one card for each possible combination of {@link Rank} and {@link Rank}.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2016-11-03
 */
public class Deck {
  /** The cards held within the deck */
  private List<Card> cards = new LinkedList<Card>();

  /**
   * Upon instantiating a deck, it automatically fills itself with the correct
   * cards and shuffles.
   */
  Deck() {
    for (Rank rank : Rank.values()) {
      cards.add(new Card(rank, Suit.CLUBS));
      cards.add(new Card(rank, Suit.DIAMONDS));
      cards.add(new Card(rank, Suit.HEARTS));
      cards.add(new Card(rank, Suit.SPADES));
    }

    Collections.shuffle(cards);
  }

  /**
   * Deals the topmost card. XXX No idea what that {@code (cards.size() -1)} is
   * for.
   * 
   * @return The card that is currently on the top of the deck.
   */
  public Card deal() {
    if (cards.isEmpty()) {
      return cards.get(cards.size() - 1);
    } else {
      return cards.remove(0);
    }
  }

  /**
   * Returns the remaining amount of cards in the deck.
   * 
   * @return The remaining size of the deck
   */
  public int size() {
    return cards.size();
  }

  /**
   * @deprecated Overrides the cards used in the game in some terrible way. TODO
   *             rewrite this whole mess to make testing possible.
   * @param cheatCards
   */
  @Deprecated
  protected void rigTheGame(String[] cheatCards) {
    cards.clear();
    for (int k = 0; k < cheatCards.length; k++) {
      cards.add(new Card(Rank.values()[k], Suit.SPADES));
    }
  }
}
