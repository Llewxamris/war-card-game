package com.maxwellhaley.war.core.test.mock;

import com.maxwellhaley.war.core.gm.GameMaster;
import com.maxwellhaley.war.core.model.Deck;

public class MockGameMaster extends GameMaster {

  public MockGameMaster(Deck deck) {
    this.deck = deck;
  }
}
