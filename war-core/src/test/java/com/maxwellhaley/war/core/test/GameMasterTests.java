package com.maxwellhaley.war.core.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Rank;
import com.maxwellhaley.war.core.model.Suit;
import com.maxwellhaley.war.core.result.BettingPhaseResult;
import com.maxwellhaley.war.core.result.Outcome;
import com.maxwellhaley.war.core.result.StandoffPhaseResult;
import com.maxwellhaley.war.core.result.WarPhaseResult;
import com.maxwellhaley.war.core.test.mock.MockDeck;
import com.maxwellhaley.war.core.test.mock.MockGameMaster;

public class GameMasterTests {

  @Test
  void bettingPhaseSuccessfulBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(250, 600);

    assertEquals(850, result.potValue(),
            "Sum of bets did not add up to the correct amount of cash.");
    assertEquals(750, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(400, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
  }

  @Test
  void bettingPhasePlayer1InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(1001, 600);

    assertEquals(Outcome.PLAYER_1_BET_FAIL, result.outcome(),
            "Betting phase result not registered as failure.");
    assertEquals(1000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.potValue(),
            "Winnings were added despite a betting failure.");
  }

  @Test
  void bettingPhasePlayer2InvalidBet() {
    MockGameMaster mockGm = new MockGameMaster(null);
    mockGm.register("Jim", "Sally");

    BettingPhaseResult result = mockGm.runBettingPhase(10, 9999);

    assertEquals(Outcome.PLAYER_2_BET_FAIL, result.outcome(),
            "Betting phase result not registered as failure.");
    assertEquals(1000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1000, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.potValue(),
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

    assertEquals(Rank.FIVE, result.playerOneDealtCard().getRank(),
            "Player #1's card does not have the correct rank.");
    assertEquals(Suit.SPADES, result.playerOneDealtCard().getSuit(),
            "Player #1's card does not have the correct suit.");
    assertEquals(Rank.NINE, result.playerTwoDealtCard().getRank(),
            "Player #2's card does not have the correct rank.");
    assertEquals(Suit.CLUBS, result.playerTwoDealtCard().getSuit(),
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

    assertEquals(1000, str.potValue(),
            "The Pot does not have the correct amount of cash.");
    assertEquals(Outcome.PLAYER_1_WIN, str.outcome(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(1500, str.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(500, str.playerTwoCashValue(),
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

    assertEquals(Outcome.PLAYER_2_WIN, result.outcome(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(800, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(1200, result.playerTwoCashValue(),
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

    assertEquals(Outcome.TIE, result.outcome(),
            "Standoff Phase did not result in correct winner.");
    assertEquals(0, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(0, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(2000, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }
  
  @Test
  void warPhaseTie() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));

    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(false, false);

    assertEquals(Outcome.TIE, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(0, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(0, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(2000, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

  @Test
  void warPhasePlayerOneWinsNoRisk() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));

    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.TWO, Suit.HEARTS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(false, false);

    assertEquals(Outcome.PLAYER_1_WIN, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(null, result.riskOutcome(),
            "Risk did not result in correct outcome.");
    assertEquals(2000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(0, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

  @Test
  void warPhasePlayerOneWinsNeutralRisk() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    
    // Card dealt to the dealer
    cards.add(new Card(Rank.TWO, Suit.SPADES));

    // Player's cards
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.THREE, Suit.DIAMONDS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(true, false);

    assertEquals(Outcome.PLAYER_1_WIN, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(Outcome.RISK_NEUTRAL, result.riskOutcome(),
            "Risk did not result in correct outcome.");
    assertEquals(2000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(Rank.TWO, result.dealersDealtCard().getRank(),
            "Dealer did not have the correct card rank.");
    assertEquals(Suit.SPADES, result.dealersDealtCard().getSuit(),
            "Dealer did not have the correct card suit.");
    assertEquals(0, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

  @Test
  void warPhasePlayerOneWinsLoseRisk() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    
    // Card dealt to the dealer
    cards.add(new Card(Rank.ACE, Suit.SPADES));

    // Player's cards
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.THREE, Suit.DIAMONDS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(true, false);

    assertEquals(Outcome.PLAYER_1_WIN, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(Outcome.RISK_LOSE, result.riskOutcome(),
            "Risk did not result in correct outcome.");
    assertEquals(1000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(Rank.ACE, result.dealersDealtCard().getRank(),
            "Dealer did not have the correct card rank.");
    assertEquals(Suit.SPADES, result.dealersDealtCard().getSuit(),
            "Dealer did not have the correct card suit.");
    assertEquals(0, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

  @Test
  void warPhasePlayerOneWinsWinRisk() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    
    // Card dealt to the dealer
    cards.add(new Card(Rank.FOUR, Suit.CLUBS));

    // Player's cards
    cards.add(new Card(Rank.FOUR, Suit.SPADES));
    cards.add(new Card(Rank.THREE, Suit.DIAMONDS));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(true, false);

    assertEquals(Outcome.PLAYER_1_WIN, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(Outcome.RISK_WIN, result.riskOutcome(),
            "Risk did not result in correct outcome.");
    assertEquals(4000, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(Rank.FOUR, result.dealersDealtCard().getRank(),
            "Dealer did not have the correct card rank.");
    assertEquals(Suit.CLUBS, result.dealersDealtCard().getSuit(),
            "Dealer did not have the correct card suit.");
    assertEquals(0, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

  @Test
  void warPhasePlayerTwoWinsNoRisk() {
    MockDeck mockDeck = new MockDeck();
    List<Card> cards = new LinkedList<Card>();

    // First three cards are burned
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));
    cards.add(new Card(Rank.QUEEN, Suit.HEARTS));

    cards.add(new Card(Rank.FIVE, Suit.DIAMONDS));
    cards.add(new Card(Rank.KING, Suit.SPADES));
    mockDeck.setCards(cards);

    MockGameMaster mockGm = new MockGameMaster(mockDeck);
    mockGm.register("Jim", "Sally");
    mockGm.runBettingPhase(1000, 1000);

    WarPhaseResult result = mockGm.runWarPhase(false, false);

    assertEquals(Outcome.PLAYER_2_WIN, result.outcome(),
            "War Phase did not result in correct winner.");
    assertEquals(0, result.playerOneCashValue(),
            "Player #1 does not have the correct amount of cash.");
    assertEquals(2000, result.playerTwoCashValue(),
            "Player #2 does not have the correct amount of cash.");
    assertEquals(0, result.potValue(),
            "The Pot does not have the correct amount of cash.");
  }

}
