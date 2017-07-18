package mHaley_B31_A03_WAR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Deck {
    // RANKS contains all possible values for the Card class
    private final String[] RANKS = { "A", "2", "3", "4", "5", "6", "7", "8",
            "9", "10", "J", "Q", "K" };
    // SUITS contains all possible suits for the Card class
    private final char[] SUITS = { 'S', 'C', 'H', 'D' };
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
        for (int k = 0; k < RANKS.length; k++) {
            for (int j = 0; j < SUITS.length; j++) {
                Card singleCard = new Card(RANKS[k], SUITS[j]);
                unshuffledDeckOfCards.add(singleCard);
            }
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
            Card singleCard = new Card();
            singleCard.setRank(cheatCards[k]);
            shuffledDeckOfCards.add(singleCard);
        }
    } // righTheGame
}
