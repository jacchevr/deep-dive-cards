package edu.cnm.deepdive;

public class Table {

  public static final int CARDS_PER_HAND = 5;
  public static final int NUMBER_OF_HANDS = 5;

  private Card[][] hands;
  private Deck deck;

  public Table(Deck deck) {
    this.deck = deck;
    hands = new Card[NUMBER_OF_HANDS][CARDS_PER_HAND];
    deal();
  }
  public void show() {
    for (Card[] hand : hands) {
      for (Card card : hand) {
        System.out.print(card);
      }
      System.out.println();
    }
  }
  public void deal() {
    deck.shuffle();
    for (Card[] hand : hands) {
      for (int i = 0; i < CARDS_PER_HAND; i++) {
        hand[i] = deck.draw();
      }
    }
  }
}
