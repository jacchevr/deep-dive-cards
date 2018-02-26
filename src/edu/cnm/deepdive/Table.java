
package edu.cnm.deepdive;

public class Table {

  public static final int NUMBER_OF_HANDS = 5;
  public static final int CARDS_PER_HAND = 5;
  private Card[][] hands;
  private Deck deck;

  public Table(Deck deck) { //a table and slap deck of card
    this.deck = deck;
    hands = new Card[NUMBER_OF_HANDS][CARDS_PER_HAND]; //define a constant
    deal();
  }

  public void deal() {
    deck.shuffle();
    for (Card[] hand : hands ){ //bring cards to current hand
      for (int i = 0; i < CARDS_PER_HAND; i++){
        hand[i] = deck.draw();
      }
    }
  }

  public void show(){
    for (Card[] hand : hands) {
      for (Card card : hand) {
        System.out.print(card);
      }
      System.out.println();
    }
  }

}