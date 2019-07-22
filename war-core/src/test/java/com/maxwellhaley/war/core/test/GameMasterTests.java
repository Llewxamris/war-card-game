package com.maxwellhaley.war.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Rank;
import com.maxwellhaley.war.core.model.Suit;
import com.maxwellhaley.war.core.result.BettingPhaseResult;
import com.maxwellhaley.war.core.result.Result;
import com.maxwellhaley.war.core.result.StandoffPhaseResult;
import com.maxwellhaley.war.core.test.mock.MockDeck;
import com.maxwellhaley.war.core.test.mock.MockGameMaster;

public class GameMasterTests {

  @Test
  void bettingPhaseSuccessfulBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(250, 600);

    assertEquals(850, result.winnings(),
            "Sum of bets did not add up to the correct amount of cash.");
    assertEquals(750, result.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(400, result.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void bettingPhasePlayer1InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(1001, 600);

    assertEquals(Result.PLAYER_1_BET_FAIL, result.phaseResult(),
            "Betting phase result not registered as failure.");
    assertEquals(1000, result.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, result.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.winnings(),
            "Winnings were added despite a betting failure.");
  }

  @Test
  void bettingPhasePlayer2InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(10, 9999);

    assertEquals(Result.PLAYER_2_BET_FAIL, result.phaseResult(),
            "Betting phase result not registered as failure.");
    assertEquals(1000, result.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, result.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.winnings(),
            "Winnings were added despite a betting failure.");
  }

  @Test
  void standOffRoundValidateDealing() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.FIVE, Suit.SPADES));
    cards.add(new Card(Rank.NINE, Suit.CLUBS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");

    StandoffPhaseResult result = mockGm.runStandoffPhase();

    assertEquals(Rank.FIVE, result.p1Card().getRank(),
            "Player #1's card does not have the correct rank.");
    assertEquals(Suit.SPADES, result.p1Card().getSuit(),
            "Player #1's card does not have the correct suit.");
    assertEquals(Rank.NINE, result.p2Card().getRank(),
            "Player #2's card does not have the correct rank.");
    assertEquals(Suit.CLUBS, result.p2Card().getSuit(),
            "Player #2's card does not have the correct suit.");
  }

  @Test
  void standoffRoundPlayer1Wins() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.ACE, Suit.SPADES));
    cards.add(new Card(Rank.EIGHT, Suit.DIAMONDS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");

    mockGm.runBettingPhase(500, 500);
    StandoffPhaseResult str = mockGm.runStandoffPhase();

    assertEquals(1000, str.winnings(),
            "The Pot does not have the correct amount of cash.");
    assertEquals(Result.PLAYER_1_WIN, str.phaseResult(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(1500, str.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(500, str.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void standoffRoundPlayer2Wins() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.THREE, Suit.HEARTS));
    cards.add(new Card(Rank.JACK, Suit.SPADES));

    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");

    mockGm.runBettingPhase(200, 500);
    StandoffPhaseResult result = mockGm.runStandoffPhase();

    assertEquals(Result.PLAYER_2_WIN, result.phaseResult(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(800, result.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1200, result.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void standoffRoundTie() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    StandoffPhaseResult result = mockGm.runStandoffPhase();

    assertEquals(Result.TIE, result.phaseResult(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(0, result.p1Cash(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(0, result.p2Cash(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(2000, result.winnings(),
            "The Pot does not have the correct amount of cash.");
  }

}
