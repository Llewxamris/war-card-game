package com.maxwellhaley.war.core.model.player;

import com.maxwellhaley.war.core.gm.GameMaster;
import com.maxwellhaley.war.core.model.Card;

/**
 * Represents a single player in the game of <b>WAR!</b>. A player has a name, a
 * current card that has been dealt to them, and an amount of cash they use for
 * betting.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-26
 */
public abstract class AbstractPlayer implements Player {

  /** The player's name. */
  private final String name;

  /** The player's current card. */
  private Card card;

  /** The player's current on-hand cash. */
  private int cash;

  /**
   * Player requires a name in order to be created. The name is immutable.
   * 
   * @param name - The player's name.
   */
  public AbstractPlayer(String name) {
    this.name = name;
    cash = 1000;
    card = null;
  }

  /**
   * @return String - The player's name.
   */
  public String getName() {
    return name;
  }

  /**
   * @return Card - The player's current card.
   */
  public Card getCard() {
    return card;
  }

  /**
   * @param card - New active card for the player.
   * @return 
   */
  public Card setCard(Card card) {
    return this.card = card;
  }

  /**
   * @return int - The player's cash on hand.
   */
  public int getCash() {
    return cash;
  }

  /**
   * Adds some amount of earning to the player's cash value.
   * 
   * @param earnings - Amount of cash won.
   * @return 
   */
  public int addCash(int cash) {
    return this.cash += cash;
  }

  /**
   * Remove some amount of cash from the player based on a bet.
   * 
   * @param bet - Amount of cash bet during the
   *            {@link GameMaster#runBettingPhase(int, int)}
   */
  public int subtractCash(int cash) {
    return this.cash -= cash;
  }

}
