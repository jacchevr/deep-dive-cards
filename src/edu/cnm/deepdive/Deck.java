package edu.cnm.deepdive;

import java.util.Random;

public class Deck {

  private Card[] cards;
  private Random rng;
  private int nextCardToDraw = 0;

  public Card[] getCards() {
    return cards;
  }

  public Deck() {
    rng = new Random();
    cards = new Card[Suit.values().length * Rank.values().length];
    int slot = 0;
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        Card card = new Card(rank, suit);
        cards[slot] = card;
        slot++;
      }
    }
  }
  public void shuffle() {
    for (int target = cards.length - 1; target > 0; target--){
      int source = rng.nextInt(target + 1);
      if (source != target) {
        Card temp = cards[target];
        cards[target] = cards[source];
        cards[source] = temp;
      }
    }
    nextCardToDraw = 0;
  }
  // FIXME - Do something smarter with the out-of-bounds problem
  public Card draw() throws ArrayIndexOutOfBoundsException {
  return cards[nextCardToDraw++];
  }

}
