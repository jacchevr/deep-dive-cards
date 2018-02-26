package edu.cnm.deepdive;

public class DeckTest {

  public static void main(String[] args){
    Deck deck = new Deck(); //constructor
    deck.shuffle();
    for (Card card : deck.getCards()){//get all the cards
      System.out.println(card); //print that toString
    }
    System.out.println("------Sorted------");
    deck.sort();
    for (Card card : deck.getCards()) {//get all the cards
      System.out.println(card);
    }
  }
}