package com.maxwellhaley.war.test.mock;

import java.util.List;

import com.maxwellhaley.war.model.Card;
import com.maxwellhaley.war.model.Deck;

public class MockDeck extends Deck {

  public MockDeck() {
    super();
  }
  
  public List<Card> getCards() {
    return cards;
  }
}
