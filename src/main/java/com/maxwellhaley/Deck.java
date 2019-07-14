package com.maxwellhaley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    // unshuffledDeckOfCards is an ArrayList that contains the cards in no
    // particular order
    private ArrayList<Card> unshuffledDeckOfCards = new ArrayList<Card>();
    // shuffledDeckOfCards is a Queue that contains unshuffledDeckOfCards in a
    // random order
    private Queue<Card> shuffledDeckOfCards = new LinkedList<Card>();

    Deck() {
        /*
         * Constructor for Deck. Creates all the cards, and adds the to an
         * unsorted collection called unsortedDeckOfCards.
         */
        for (Rank rank: Rank.values()) {
          unshuffledDeckOfCards.add(new Card(rank, Suit.CLUBS));
          unshuffledDeckOfCards.add(new Card(rank, Suit.DIAMONDS));
          unshuffledDeckOfCards.add(new Card(rank, Suit.HEARTS));
          unshuffledDeckOfCards.add(new Card(rank, Suit.SPADES));
        }
    } // Deck()

    protected void shuffle() {
        /*
         * Takes the collection unshuffledDeckOfCards, shuffles it, and adds
         * each card object stored into a stack of shuffled cards.
         */
        Collections.shuffle(unshuffledDeckOfCards);

        for (int i = 0; i < unshuffledDeckOfCards.size(); i++) {
            shuffledDeckOfCards.add(unshuffledDeckOfCards.get(i));
        }
    } // shuffle()

    protected Card deal() {
        /*
         * Takes the first Card object from the Queue shuffledDeckOfCards and
         * returns it.
         */
        if (shuffledDeckOfCards.isEmpty()) {
            return unshuffledDeckOfCards.get(unshuffledDeckOfCards.size() - 1);
        } else {
            return shuffledDeckOfCards.remove();
        }
    }// deal()

    protected int size() {
        /* Returns the size of the queue shuffledDeckOfCards */
        return shuffledDeckOfCards.size();
    } // size()

    protected void rigTheGame(String[] cheatCards) {
        /* "Rigs" the card game by overriding the cards in the deck. */
        shuffledDeckOfCards.clear();
        for (int k = 0; k < cheatCards.length; k++) {
            shuffledDeckOfCards.add(new Card(Rank.values()[k], Suit.SPADES));
        }
    } // rigTheGame
}
