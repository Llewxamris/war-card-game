package com.maxwellhaley.war.core.result;

import com.maxwellhaley.war.core.model.Card;

public abstract class AbstractWarPhaseResult
        extends AbstractStandoffPhaseResult {

  private final Outcome riskOutcome;
  private final Card dealerDealtCard;

  public AbstractWarPhaseResult(Outcome outcome, int potValue,
          int playerOneCashValue, int playerTwoCashValue,
          Card playerOneDealtCard, Card playerTwoDealtCard, Outcome riskOutcome,
          Card dealerDealtCard) {
    super(outcome, potValue, playerOneCashValue, playerTwoCashValue,
            playerOneDealtCard, playerTwoDealtCard);
    this.riskOutcome = riskOutcome;
    this.dealerDealtCard = dealerDealtCard;
    // TODO Auto-generated constructor stub
  }

  public Outcome getRiskOutcome() {
    return riskOutcome;
  }

  public Card getDealerDealtCard() {
    return dealerDealtCard;
  }

}
