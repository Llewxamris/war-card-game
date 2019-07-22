package com.maxwellhaley.war.core.gm;

import java.util.HashMap;
import java.util.Map;

import com.maxwellhaley.war.core.constant.Player1;
import com.maxwellhaley.war.core.constant.Player2;
import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Deck;
import com.maxwellhaley.war.core.result.BettingPhaseResult;
import com.maxwellhaley.war.core.result.Result;
import com.maxwellhaley.war.core.result.StandoffPhaseResult;
import com.maxwellhaley.war.core.result.WarPhaseResult;

/**
 * The GM manages the state and status of the game. After receiving inputs from
 * the player(s), the UI layer will call the GM to process that information. For
 * example: After the UI has taken bets inputs from the players, the UI should
 * pass the bet values to the GM to increase the pot value.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-16
 */
public class GameMaster {
  /** The deck of cards. */
  protected Deck deck;

  /** The first players name */
  private String p1Name;

  /** The second players name */
  private String p2Name;

  /** The current cash value of the players */
  private Map<String, Integer> playersCash;

  /** The current possible winnings for a round. */
  private int thePot;

  /** Can not instantiate the Game Master typically. */
  protected GameMaster() {
    deck = new Deck();
  }

  /** Sync-less Singleton implementation. */
  private static class GameMasterHelper {
    private static final GameMaster INSTANCE = new GameMaster();
  }

  /**
   * Get the instance of the Game Master.
   * 
   * @return The Game Master itself.
   */
  public static GameMaster getInstance() {
    return GameMasterHelper.INSTANCE;
  }

  /**
   * @return Player 1's Name
   */
  public String p1Name() {
    return p1Name;
  }

  /**
   * @return Player 2's Name
   */
  public String p2Name() {
    return p2Name;
  }

  /**
   * Registers the player to the GM. Assumes the second player is a CPU, and
   * generates the CPU players name.
   * 
   * @param p1Name - The human player's name
   */
  public void register(String p1Name) {
    // TODO Generate the CPU player name
    register(p1Name, "BOT");
  }

  /**
   * Sets both names and creates the map containing the players cash amounts.
   * 
   * @see Player1
   * @see Player2
   * @param p1Name - The first player's nameDuring this phase, three cards are
    * burnt and each player is dealt two new cards. 
   * @param p2Name - The second player's name
   */
  public void register(String p1Name, String p2Name) {
    this.p1Name = p1Name;
    this.p2Name = p2Name;

    playersCash = new HashMap<>();
    playersCash.put(Player1.CASH, 1000);
    playersCash.put(Player2.CASH, 1000);
  }

  /**
   * Run the betting phase of the game. During this phase, the players bets are
   * evaluated to determine if they are valid. If they are valid, they are
   * removed from their total cash pool and added to the pot.
   * 
   * @param p1Bet - Player 1's bet
   * @param p2Bet - Player 2's bet
   * @see Result
   * @return The result of the betting phase
   */
  public BettingPhaseResult runBettingPhase(int p1Bet, int p2Bet) {
    BettingPhaseResult result = null;

    if (p1Bet > playersCash.get(Player1.CASH)) {
      result = new BettingPhaseResult(Result.PLAYER_1_BET_FAIL, 0, playersCash);
    } else if (p2Bet > playersCash.get(Player2.CASH)) {
      result = new BettingPhaseResult(Result.PLAYER_2_BET_FAIL, 0, playersCash);
    } else {
      playersCash.put(Player1.CASH, playersCash.get(Player1.CASH) - p1Bet);
      playersCash.put(Player2.CASH, playersCash.get(Player2.CASH) - p2Bet);
      thePot += p1Bet + p2Bet;
      result = new BettingPhaseResult(Result.BET_SUCCESS, thePot, playersCash);
    }

    return result;
  }

  /**
   * Run the standoff phase of the game. During this phase, each player is dealt
   * a card. If P1 has a greater card than P2, P1 wins the standoff. If P2 has a
   * greater card, P2 wins the standoff. If both cards are equal, the
   * <b>WAR!</b> phase begins.
   * 
   * @see Card
   * @see Result
   * @see GameMaster#runWarPhase(boolean, boolean)
   * @return Result of the standoff phase
   */
  public StandoffPhaseResult runStandoffPhase() {
    StandoffPhaseResult result = null;
    // Deal cards to Players
    Map<String, Card> cards = new HashMap<>();
    cards.put(Player1.CARD, dealCard());
    cards.put(Player2.CARD, dealCard());

    switch (cards.get(Player1.CARD).compareTo(cards.get(Player2.CARD))) {
      case 1:
        playersCash.put(Player1.CASH, playersCash.get(Player1.CASH) + thePot);
        result = new StandoffPhaseResult(Result.PLAYER_1_WIN, thePot,
                playersCash, cards);
        thePot = 0;
        break;
      case -1:
        playersCash.put(Player2.CASH, playersCash.get(Player2.CASH) + thePot);
        result = new StandoffPhaseResult(Result.PLAYER_2_WIN, thePot,
                playersCash, cards);
        thePot = 0;
        break;
      case 0:
        result = new StandoffPhaseResult(Result.TIE, thePot, playersCash,
                cards);
        break;
    }

    return result;
  }

  /**
   * Run the <b>WAR!</b> phase of the game. During this phase, three cards are
   * burnt and each player is dealt two new cards. The rest of the phase plays
   * out similar to the {@link GameMaster#runStandoffPhase()}, except each
   * player has the option of taking a risk. If the winner took the risk, the
   * dealer draws a card. If the winners card is greater than the dealers card,
   * nothing happens. If the winners card is less than the dealers card, the
   * winnings are halved. If the winners card is the same as the dealers card,
   * the winnings are doubled. A tie during <b>WAR!</b> should result in another
   * <b>WAR!</b> being ran.
   * 
   * @param p1Risk - Is P1 taking a risk?
   * @param p2Risk - Is P2 taking a risk?
   * @return The <b>WAR!</b> phase results
   * @see GameMaster#runStandoffPhase()
   * @see Result
   */
  public WarPhaseResult runWarPhase(boolean p1Risk, boolean p2Risk) {
    WarPhaseResult result = null;

    // Burn three cards
    dealCard();
    dealCard();
    dealCard();

    // Deal dealers card if a risk was taken
    Card dealersCard = null;
    if (p1Risk || p2Risk) {
      dealersCard = dealCard();
    }

    // Deal players cards
    Map<String, Card> cards = new HashMap<>();
    cards.put(Player1.CARD, dealCard());
    cards.put(Player2.CARD, dealCard());

    // Determine the winner
    Result winner = null;
    Result riskResult = null;
    switch (cards.get(Player1.CARD).compareTo(cards.get(Player2.CARD))) {
      case 1:
        if (p1Risk) {
          riskResult = getRiskResult(cards.get(Player1.CARD), dealersCard);
        }
        result = new WarPhaseResult(Result.PLAYER_1_WIN, thePot, playersCash,
                cards, riskResult, dealersCard);
        break;
      case -1:
        if (p2Risk) {
          riskResult = getRiskResult(cards.get(Player2.CARD), dealersCard);
        }
        result = new WarPhaseResult(Result.PLAYER_2_WIN, thePot, playersCash,
                cards, riskResult, dealersCard);
        break;
      case 0:
        result = new WarPhaseResult(winner, 0, playersCash, cards, riskResult,
                dealersCard);
        break;
    }

    return result;
  }

  /**
   * Runs through the risk possibilities and returns the risk result.
   * 
   * @param playerCard
   * @param dealersCard
   * @return The risk result
   */
  private Result getRiskResult(Card playerCard, Card dealersCard) {
    // TODO: Need to refactor this with GameMaster#runWarPhase(). Calling this
    // method to mutate the pot outside of the control of the calling method is
    // wrong.
    switch (playerCard.compareTo(dealersCard)) {
      case 1:
        return Result.RISK_NEUTRAL;
      case -1:
        thePot /= 2;
        return Result.RISK_LOSE;
      default:
        thePot *= 2;
        return Result.RISK_WIN;
    }
  }

  /**
   * @return The next card in the deck.
   */
  private Card dealCard() {
    if (deck.size() == 0) {
      deck = new Deck();
    }
    return deck.deal();
  }
}
