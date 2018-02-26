package edu.cnm.deepdive;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class War {

  private static final String WIN_FORMAT = "Player %d wins %d cards!%n";
  private static final String STANDALONE_DRAW_FORMAT = WIN_FORMAT + "%n";
  private static final String WAR_ANNOUNCEMENT = "I hope you know\u2026 this means war!";
  private static final String DRAW_FORMAT = "P1: %s\tP2; %s\t";
  private static final String GAME_OVER_FORMAT = "Game over, P1:%d, P2: %d%n";
  private boolean aceHigh;
  private boolean suicide;
  private List<Card> hand1;
  private List<Card> hand2;
  private int tally1;
  private int tally2;
  private Comparator<Card> comp;


  public War (boolean aceHigh, boolean suicide) {
    this.aceHigh = aceHigh;
    this.suicide = suicide;
    reset();
  }

  public War (boolean aceHigh) {
    this(aceHigh, false);
  }

  public War() {
    this(true);
  }

  public void reset() {
    comp = new WarComparator();
    Deck deck = new Deck();
    hand1 = new LinkedList<>();
    hand2 = new LinkedList<>();
    deck.shuffle();
    for (int i = 0; i < deck.getCards().length; i++) {
      if (i % 2 == 1){
        hand1.add(deck.draw());
      } else {
        hand2.add(deck.draw());
      }
    }
    tally1 = 0;
    tally2 = 0;
  }

  public void play() {
    while (!hand1.isEmpty()) {
      Card card1 = hand1.remove(0);
      Card card2 = hand2.remove(0);
      int comparison = comp.compare(card1,card2);
      System.out.printf(DRAW_FORMAT, card1, card2);
      if (comparison > 0) {
        tally1 += 2;
        System.out.printf(WIN_FORMAT, 1, 2);
      } else if (comparison < 0) {
        tally2 += 2;
        System.out.printf(WIN_FORMAT, 2, 2);
      } else {
        //war
        System.out.println(WAR_ANNOUNCEMENT);
        int pile = 2;
        while (comparison == 0 && !hand1.isEmpty()) {
          int turn = Math.min(4, hand1.size());
          for (int i = 0; i < turn; i++) {
            card1 = hand1.remove(0);
            card2 = hand2.remove(0);
            pile +=2;
            System.out.printf(STANDALONE_DRAW_FORMAT, card1, card2);
          }
          comparison = comp.compare(card1, card2);
        }
        if (comparison > 0) {
          tally1 += pile;
          System.out.printf(WIN_FORMAT, 1, pile);
        } else if (comparison < 0) {
          tally2 += pile;
          System.out.printf(WIN_FORMAT, 2, pile);
        }
      }
    }
    System.out.printf(GAME_OVER_FORMAT, tally1, tally2);
  }

  private class WarComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
      int result = 0;
      if (!aceHigh) {
        result = card1.getRank().compareTo(card2.getRank());
      } else if (card1.getRank() == Rank.ACE) {
        result = Math.abs(card1.getRank().compareTo(card2.getRank()));
      } else if (card2.getRank() == Rank.ACE) {
        result = -1;
      } else {
        result = card1.getRank().compareTo(card2.getRank());
      }
      if (suicide) {
        result = -result;
      }
      return result;
    }
  }

}