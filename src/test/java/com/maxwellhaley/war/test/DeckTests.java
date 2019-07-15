package com.maxwellhaley.war.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import com.maxwellhaley.war.model.Card;
import com.maxwellhaley.war.model.Deck;
import com.maxwellhaley.war.model.Rank;
import com.maxwellhaley.war.test.mock.MockDeck;

public class DeckTests {

  @Test
  void validateDeckSize() {
    Deck deck = new Deck();
    assertEquals(52, deck.size(),
            "Deck did not have the correct amount of cards.");
  }

  @Test
  void validateDeckContents() {
    List<Card> cards = new MockDeck().getCards();

    for (Rank rank : Rank.values()) {
      Stream<Card> cardsByRank = cards.stream()
              .filter(card -> card.getRank() == rank);
      assertEquals(4, cardsByRank.count(), String.format(
              "Sub-deck for rank %s did not have the corrent amount of cards",
              rank.toString()));
    }
  }
}
