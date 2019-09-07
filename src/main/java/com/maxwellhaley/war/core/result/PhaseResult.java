package com.maxwellhaley.war.core.result;

public interface PhaseResult {
  public Outcome getOutcome();

  public int getPotValue();

  public int getPlayerOneCashValue();

  public int getPlayerTwoCashValue();
}
