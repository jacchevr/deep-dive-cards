package edu.cnm.deepdive;

public enum Rank {
  ACE, TWO, THREE, FOUR, FIVE, SIX,SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KINGS;



    private static final String[] RANK = {"A  ", "2  ", "3  ", "4  ", "5  ", "6  ", "7  ", "8  ",
        "9  ", "10 ", "J  ", "Q  ", "K  "};


    @Override
    public String toString() {
      return RANK[ordinal()];
  }
}

