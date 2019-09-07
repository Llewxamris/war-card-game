package com.maxwellhaley.war.core.model.player;

import com.maxwellhaley.war.core.model.Card;

public interface Player {
  public String getName();
  public Card getCard();
  public Card setCard(Card card);
  public int getCash();
  public int addCash(int cash);
  public int subtractCash(int cash);
}
