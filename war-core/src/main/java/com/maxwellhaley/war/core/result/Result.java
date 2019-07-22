package com.maxwellhaley.war.core.result;

/**
 * All possible results from the several game phases.
 * 
 * @author Maxwell Haley
 * @version 2.0.0
 * @since 2019-07-22
 */
public enum Result {
  /** P1 has won a phase. */
  PLAYER_1_WIN,

  /** P2 has won a phase. */
  PLAYER_2_WIN,

  /** A tie has resulted from a phase. */
  TIE,

  /** A player took a risk, and won. */
  RISK_WIN,

  /** A player took a risk, and lost. */
  RISK_LOSE,

  /** A player took a risk, and wound up even. */
  RISK_NEUTRAL,

  /** P1 made a bet greater than their total value. */
  PLAYER_1_BET_FAIL,

  /** P2 made a bet greater than their total value. */
  PLAYER_2_BET_FAIL,

  /** P1 and P2 made successful bets. */
  BET_SUCCESS
}
