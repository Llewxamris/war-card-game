package com.maxwellhaley.war.core.model;

public class Pot {

  private int value;
  
  public Pot() {
    value = 0;
  }

  public void addCash(int bet) {
    value += bet;
  }

  public void clearValue() {
    value = 0;
  }

  public int getValue() {
    return value;
  }
  
  public void doubleValue() {
    value *= 2;
  }
  
  public void halveValue() {
    value /= 2;
  }

}
