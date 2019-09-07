package com.maxwellhaley.war.core.test.mock;

import java.util.List;

import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Deck;

public class MockDeck extends Deck {

  public MockDeck() {
    super();
  }
  
  public List<Card> getCards() {
    return cards;
  }
  
  public void setCards(List<Card> cards) {
    this.cards = cards;
  }
}
