package com.maxwellhaley;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private List<Card> cards = new LinkedList<Card>();

    Deck() {
        /*
         * Constructor for Deck. Creates all the cards, and adds the to an
         * unsorted collection called unsortedDeckOfCards.
         */
        for (Rank rank: Rank.values()) {
          cards.add(new Card(rank, Suit.CLUBS));
          cards.add(new Card(rank, Suit.DIAMONDS));
          cards.add(new Card(rank, Suit.HEARTS));
          cards.add(new Card(rank, Suit.SPADES));
        }
        
        Collections.shuffle(cards);
    } // Deck()

    public Card deal() {
        /*
         * Takes the first Card object from the Queue shuffledDeckOfCards and
         * returns it.
         */
        if (cards.isEmpty()) {
            return cards.get(cards.size() - 1);
        } else {
            return cards.remove(0);
        }
    }// deal()

    public int size() {
        /* Returns the size of the queue shuffledDeckOfCards */
        return cards.size();
    } // size()

    protected void rigTheGame(String[] cheatCards) {
        /* "Rigs" the card game by overriding the cards in the deck. */
        cards.clear();
        for (int k = 0; k < cheatCards.length; k++) {
            cards.add(new Card(Rank.values()[k], Suit.SPADES));
        }
    } // rigTheGame
}
