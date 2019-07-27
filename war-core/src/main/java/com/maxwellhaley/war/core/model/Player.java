package com.maxwellhaley.war.core.model;

public class Player {

  private final String name;
  private Card card;
  private int cash;

  public Player(String name) {
    this.name = name;
    cash = 1000;
    card = null;
  }

  public String getName() {
    return name;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public int getCash() {
    return cash;
  }

  public void addCash(int earnings) {
    cash += earnings;
  }

  public void subtractCash(int bet) {
    cash -= bet;
  }

}
