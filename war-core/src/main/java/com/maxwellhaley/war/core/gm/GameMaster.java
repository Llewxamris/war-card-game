package com.maxwellhaley.war.core.gm;

import com.maxwellhaley.war.core.exception.NotEnoughCashException;
import com.maxwellhaley.war.core.model.Card;
import com.maxwellhaley.war.core.model.Deck;
import com.maxwellhaley.war.core.model.Player;

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

  /** The first player. Always a human. */
  private Player player1;

  /** The second player. Not always human. */
  private Player player2;

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
   * Add the cash bet during the Betting Stage of a round to the current pot.
   * 
   * @param bettingPhaseTotal - The total value of cash bet during the betting
   *                          stage.
   */
  public void addToPot(int bettingPhaseTotal) {
    thePot += bettingPhaseTotal;
  }

  /**
   * Get the current value of the pot.
   * 
   * @return The value of the pot.
   */
  public int getThePot() {
    return thePot;
  }

  /**
   * Registers the player to the GM. Assumes the second player is a CPU, and
   * generates the CPU player.
   * 
   * @param player1 - The human player
   */
  public void register(Player player1) {
    // TODO Generate the CPU player information
    this.player1 = player1;
    player2 = new Player("CPU");
  }

  /**
   * Registers both players to the GM.
   * 
   * @param player1 - The first human player
   * @param player2 - The second human player
   */
  public void register(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public int runBettingPhase(int p1Bet, int p2Bet)
          throws NotEnoughCashException {
    try {
      thePot += player1.bet(p1Bet);
    } catch (NotEnoughCashException e) {
      thePot = 0;
      NotEnoughCashException cashEx = new NotEnoughCashException(
              String.format("%s does not have enough cash to bet %d.",
                      player1.getName(), p1Bet));
      cashEx.initCause(e);
      throw cashEx;
    }

    try {
      thePot += player2.bet(p2Bet);
    } catch (NotEnoughCashException e) {
      thePot = 0;
      // Return the bet cash to player one
      player1.addCash(p1Bet);
      NotEnoughCashException cashEx = new NotEnoughCashException(
              String.format("%s does not have enough cash to bet %d.",
                      player2.getName(), p2Bet));
      cashEx.initCause(e);
      throw cashEx;
    }
    
    return thePot;
  }

  /**
   * Run the standoff phase of the game.
   * 
   * @return
   */
  public int runStandoffPhase() {
    int result = -99;
    // Deal cards to Players
    player1.setCard(dealCard());
    player2.setCard(dealCard());

    switch (player1.getCard().compareTo(player2.getCard())) {
      case 1:
        result = 1;
        player1.addCash(thePot);
        thePot = 0;
        break;
      case -1:
        result = -1;
        player2.addCash(thePot);
        thePot = 0;
        break;
      case 0:
        result = 0;
        break;
    }

    return result;
  }

  private Card dealCard() {
    if (deck.size() == 0) {
      deck = new Deck();
    }
    return deck.deal();
  }
}
