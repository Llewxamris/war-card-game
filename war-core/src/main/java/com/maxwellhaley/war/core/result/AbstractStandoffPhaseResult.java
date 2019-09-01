package com.maxwellhaley.war.core.result;

import com.maxwellhaley.war.core.model.Card;

public abstract class AbstractStandoffPhaseResult extends AbstractPhaseResult {

  private final Card playerOneDealtCard;
  private final Card playerTwoDealtCard;

  public AbstractStandoffPhaseResult(Outcome outcome, int potValue,
          int playerOneCashValue, int playerTwoCashValue,
          Card playerOneDealtCard, Card playerTwoDealtCard) {
    super(outcome, potValue, playerOneCashValue, playerTwoCashValue);
    this.playerOneDealtCard = playerOneDealtCard;
    this.playerTwoDealtCard = playerTwoDealtCard;
  }

  public Card getPlayerOneDealtCard() {
    return playerOneDealtCard;
  }

  public Card getPlayerTwoDealtCard() {
    return playerTwoDealtCard;
  }

}
