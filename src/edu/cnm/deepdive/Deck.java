package edu.cnm.deepdive;

import java.util.Random;

public class Deck {

  private Card[] cards;
  private Random rng; //added for shuffle
  private int nextCardToDraw = 0;

  public Card[] getCards() {
    return cards;
  }

  public Deck(){
    rng = new Random(); //added for shuffle
    cards = new Card[Suit.values().length * Rank.values().length]; //more specific than instead of 52
    int slot = 0;
    for (Suit suit : Suit.values()){//create 4 suit first
      for (Rank rank : Rank.values()) { //then create all the ranks
        Card card = new Card(rank, suit);
        cards [slot] = card;
        slot++; // same as cards [slot++] = card;
      }
    }

  }
  public void shuffle(){ //probability of 1/n, mathamaticly proven
    for (int target = cards.length - 1; target > 0; target--){
      int source = rng.nextInt(target +1);
      if (source != target) {
        Card temp = cards[target];
        cards[target] = cards[source];
        cards[source] = temp;
      }
    }
    nextCardToDraw = 0;
  }
  //FIXME - Do somthing smarter with the out-of-bounds problems.
  public Card draw()throws ArrayIndexOutOfBoundsException {
    return cards[nextCardToDraw++]; //draw a card then increment it
  }
  public void sort(){
    cards = sort(cards);
  }

  private Card[] sort(Card[] cards) {
    if (cards.length == 1){
      return cards;
    }
    int midpoint = cards.length / 2;
    Card[] a = new Card[midpoint];
    Card[] b = new Card[cards.length - midpoint];
    System.arraycopy(cards, 0, a, 0, a.length);
    System.arraycopy(cards, midpoint, b, 0, b.length);
    a = sort(a);
    b = sort(b);
    return merge(a,b);
  }

  private Card[] merge(Card[] a, Card[] b) {
    int length = a.length + b.length;
    Card[] result = new Card[length];
    int aindex = 0;
    int bindex = 0;
    int resultIndex = 0;
    while (aindex < a.length && bindex < b.length) {
      if (a[aindex].compareTo(b[bindex]) > 0) {
        result[resultIndex] = b[bindex];
        bindex++;
      } else {
        result[resultIndex] = a[aindex];
        aindex++;
      }
      resultIndex++;
    }
    while (aindex < a.length) {
      result[resultIndex] = a[aindex];
      aindex++;
      resultIndex++;
    }
    while (bindex < b.length) {
      result[resultIndex] = b[bindex];
      bindex++;
      resultIndex++;
    }
    return result;
  }
}