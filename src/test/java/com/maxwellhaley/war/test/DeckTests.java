package com.maxwellhaley.war.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.maxwellhaley.war.model.Deck;

public class DeckTests {
  private final Deck deck = new Deck();

  @Test
  void validateDeckSize() {
    assertEquals(52, deck.size(),
            "Deck did not have the correct amount of cards.");
  }
}
