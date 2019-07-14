package com.maxwellhaley;

public enum Suit {
  CLUBS("Clubs"),
  DIAMONDS("Diamonds"),
  HEARTS("Hearts"),
  SPADES("Spades");
  
  private final String name;
  
  Suit(String name) {
    this.name = name;
  }
  
  @Override
  public String toString(){
    return name;
  }
}
