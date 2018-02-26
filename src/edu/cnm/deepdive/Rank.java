package edu.cnm.deepdive;

public enum Rank {
  ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

  public static final String[] RANKSYM = {"A", "1", "2", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  @Override
  public String toString() {
    return RANKSYM[ordinal()];
  }

}