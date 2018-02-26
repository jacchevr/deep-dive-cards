package edu.cnm.deepdive;

public enum Suit {
  HEARTS, CLUBS, SPADES, DIAMONDS;

  private static final String[] SYMBOLS = {"\u2665", "\u2665", "\u2660", "\u2666"};

  @Override
  public String toString() {
    return SYMBOLS[ordinal()];

  }



}