package com.maxwellhaley;

public class Card {
    private char suit; // The cards suit
    private String rank; // The cards value

    Card() {
        suit = 'X'; // Default suit, indicates card has been set in debug mode
        rank = "0"; // Default rank, indicates that the card hasn't had any
                    // values set
    }

    Card(String value) {
        suit = 'X';
        rank = value;
    } // Card(...)

    Card(String value, char type) {
        suit = type;
        rank = value;
    } // Card(...)

    protected char getSuit() {
        /* Returns the value of suit. */
        return suit;
    } // getSuit()

    protected void setSuit(char suit) {
        /* Sets the value of suit to the parameter suit. */
        this.suit = suit;
    } // setSuit(...)

    protected int getRank() {
        /* Returns the value of rank. */
        switch (rank) {
            case "A":
                return 14;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            default:
                return Integer.valueOf(rank);
        }
    } // getRank()

    protected String getRankString() {
        return rank;
    }

    protected void setRank(String rank) {
        /* Sets the value of rank to the parameter rank. */
        this.rank = rank;
    } // setRank(...)

    public String toString() {
        return rank + suit;
    }
}