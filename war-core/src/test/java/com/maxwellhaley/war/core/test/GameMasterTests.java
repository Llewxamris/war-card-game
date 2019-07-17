package com.maxwellhaley.war.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.maxwellhaley.war.core.exception.NotEnoughCashException;
import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Player;
import com.maxwellhaley.war.core.model.Rank;
import com.maxwellhaley.war.core.model.Suit;
import com.maxwellhaley.war.core.test.mock.MockDeck;
import com.maxwellhaley.war.core.test.mock.MockGameMaster;

public class GameMasterTests {

  @Test
  void bettingPhaseSuccessfulBet() throws NotEnoughCashException {
    MockGameMaster mockGm = new MockGameMaster(null);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    int bets = mockGm.runBettingPhase(250, 600);

    assertEquals(850, bets,
            "Sum of bets did not add up to the correct amount of cash.");
    assertEquals(750, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(400, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void bettingPhasePlayer1InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    assertThrows(NotEnoughCashException.class, () -> {
      mockGm.runBettingPhase(1001, 600);
    }, "Player #1 bet more than they had without error.");

    assertEquals(1000, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, mockGm.getThePot(), "The Pot did not reset on error.");
  }

  @Test
  void bettingPhasePlayer2InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    assertThrows(NotEnoughCashException.class, () -> {
      mockGm.runBettingPhase(10, 9999);
    }, "Player #2 bet more than they had without error.");

    assertEquals(1000, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, mockGm.getThePot(), "The Pot did not reset on error.");
  }

  @Test
  void standOffRoundValidateDealing() {
    MockDeck mockDeck = new MockDeck();

    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.FIVE, Suit.SPADES));
    cards.add(new Card(Rank.NINE, Suit.CLUBS));

    mockDeck.setCards(cards);
    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);

    assertNull(p1.getCard(), "Player #1 had a card before standoff phase.");
    assertNull(p2.getCard(), "Player #2 had a card before standoff phase.");

    mockGm.runStandoffPhase();

    assertEquals(Rank.FIVE, p1.getCard().getRank(),
            "Player #1's card does not have the correct rank.");
    assertEquals(Suit.SPADES, p1.getCard().getSuit(),
            "Player #1's card does not have the correct suit.");
    assertEquals(Rank.NINE, p2.getCard().getRank(),
            "Player #2's card does not have the correct rank.");
    assertEquals(Suit.CLUBS, p2.getCard().getSuit(),
            "Player #2's card does not have the correct suit.");
  }

  @Test
  void standoffRoundPlayer1Wins() throws NotEnoughCashException {
    MockDeck mockDeck = new MockDeck();

    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.ACE, Suit.SPADES));
    cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));

    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    mockGm.runBettingPhase(500, 500);

    int standoffPhaseResult = mockGm.runStandoffPhase();

    assertEquals(0, mockGm.getThePot(),
            "The Pot does not have the correct amount of cash.");
    assertEquals(1, standoffPhaseResult,
            "Standoff Phase did not result in correct winner.");
    assertEquals(1500, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(500, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void standoffRoundPlayer2Wins() throws NotEnoughCashException {
    MockDeck mockDeck = new MockDeck();

    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.THREE, Suit.HEARTS));
    cards.add(new Card(Rank.JACK, Suit.SPADES));

    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    mockGm.runBettingPhase(200, 500);

    assertEquals(700, mockGm.getThePot(),
            "The Pot does not have the correct amount of cash.");

    int standoffPhaseResult = mockGm.runStandoffPhase();

    assertEquals(-1, standoffPhaseResult,
            "Standoff Phase did not result in correct winner.");
    assertEquals(800, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1200, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void standoffRoundTie() throws NotEnoughCashException {
    MockDeck mockDeck = new MockDeck();

    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));

    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    Player p1 = new Player("Jim");
    Player p2 = new Player("Sally");

    mockGm.register(p1, p2);
    mockGm.runBettingPhase(1000, 1000);

    int standoffPhaseResult = mockGm.runStandoffPhase();

    assertEquals(0, standoffPhaseResult,
            "Standoff Phase did not result in correct winner.");
    assertEquals(0, p1.getCash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(0, p2.getCash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(2000, mockGm.getThePot(),
            "The Pot does not have the correct amount of cash.");
  }

}
