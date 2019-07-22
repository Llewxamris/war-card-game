package com.maxwellhaley.war.core.result;

import java.util.Map;

import com.maxwellhaley.war.core.constant.Player1;
import com.maxwellhaley.war.core.constant.Player2;
import com.maxwellhaley.war.core.model.Card;

/**
 * The results of running the standoff phase. Adds the cards field to store the
 * cards dealt to the players.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public class StandoffPhaseResult extends PhaseResult {

  /** The cards dealt to the players. */
  private Map<String, Card> cards;

  /**
   * Calls {@link PhaseResult#PhaseResult(Result, int, Map)}, while also setting
   * the players cards.
   * 
   * @param standoffResult
   * @param winnings
   * @param playersCash
   * @param cards
   */
  public StandoffPhaseResult(Result standoffResult, int winnings,
          Map<String, Integer> playersCash,
          Map<String, Card> cards) {
    super(standoffResult, winnings, playersCash);
    this.cards = cards;
  }

  /**
   * @return The card dealt to P1.
   */
  public Card p1Card() {
    return cards.get(Player1.CARD);
  }

  /**
   * @return The card dealt to P2.
   */
  public Card p2Card() {
    return cards.get(Player2.CARD);
  }

}
