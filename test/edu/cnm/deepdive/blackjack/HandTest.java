package edu.cnm.deepdive.blackjack;

import edu.cnm.deepdive.Card;
import edu.cnm.deepdive.Deck;
import edu.cnm.deepdive.Rank;
import edu.cnm.deepdive.Suit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

  private Hand hand;
  private Deck deck;

  @BeforeEach
  void setUp() {
    hand = new Hand();
    deck = new Deck();
    deck.shuffle();
  }

  @Test
  void add() {
    int count = 0;
    Assertions.assertEquals(hand.size(), count);
    for (Card card : deck.getCards()) {
      hand.add(card);
      Assertions.assertEquals(hand.size(), ++count);
    }
    Set<Card> remaining = new HashSet<>(Arrays.asList(deck.getCards()));
    for (Card card : hand) {
      Assertions.assertTrue(remaining.remove(card));
    }
    Assertions.assertTrue(remaining.isEmpty());
  }

  @Test
  void clear() {
    for (Card card : deck.getCards()) {
      hand.add(card);
    }
    hand.clear();
    Assertions.assertEquals(hand.size(), 0);
  }

  @Test
  void isBust() {
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.KING, Suit.DIAMONDS));
    hand.add(new Card(Rank.QUEEN, Suit.CLUBS));
    Assertions.assertTrue(hand.isBust());
    hand.clear();
    Assertions.assertFalse(hand.isBust());
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.ACE, Suit.SPADES));
    hand.add(new Card(Rank.ACE, Suit.CLUBS));
    Assertions.assertFalse(hand.isBust());
  }

  @Test
  void isBlackjack() {
    Assertions.assertFalse(hand.isBlackjack());
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.THREE, Suit.DIAMONDS));
    hand.add(new Card(Rank.EIGHT, Suit.CLUBS));
    Assertions.assertFalse(hand.isBlackjack());
    hand.clear();
    Assertions.assertFalse(hand.isBlackjack());
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.ACE, Suit.SPADES));
    Assertions.assertTrue(hand.isBlackjack());
  }

  @Test
  void value() {
    Assertions.assertFalse(hand.isBlackjack());
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.THREE, Suit.DIAMONDS));
    hand.add(new Card(Rank.EIGHT, Suit.CLUBS));
    Assertions.assertEquals(hand.value(), 21);
    hand.clear();
    hand.add(new Card(Rank.JACK, Suit.HEARTS));
    hand.add(new Card(Rank.THREE, Suit.DIAMONDS));
    hand.add(new Card(Rank.NINE, Suit.CLUBS));
    Assertions.assertEquals(hand.value(), 0);
    hand.clear();
    hand.add(new Card(Rank.FIVE, Suit.HEARTS));
    hand.add(new Card(Rank.ACE, Suit.DIAMONDS));
    hand.add(new Card(Rank.ACE, Suit.CLUBS));
    Assertions.assertEquals(hand.value(), 17);
  }

  @Test
  void compareTo() {
    Hand other = new Hand();
    hand.add(new Card(Rank.JACK, Suit.CLUBS));
    hand.add(new Card(Rank.THREE, Suit.HEARTS));
    hand.add(new Card(Rank.EIGHT, Suit.CLUBS));
    other.add(new Card(Rank.JACK, Suit.CLUBS));
    other.add(new Card(Rank.ACE, Suit.HEARTS));
    Assertions.assertTrue(hand.compareTo(other) < 0);
    Assertions.assertTrue(other.compareTo(hand) > 0);
    hand.clear();
    other.clear();
    hand.add(new Card(Rank.JACK, Suit.CLUBS));
    hand.add(new Card(Rank.THREE, Suit.HEARTS));
    hand.add(new Card(Rank.EIGHT, Suit.CLUBS));
    other.add(new Card(Rank.JACK, Suit.CLUBS));
    other.add(new Card(Rank.FIVE, Suit.HEARTS));
    other.add(new Card(Rank.SIX, Suit.DIAMONDS));
    Assertions.assertEquals(hand.compareTo(other), 0);
    Assertions.assertEquals(other.compareTo(hand), 0);
    hand.clear();
    other.clear();
    hand.add(deck.draw());
    hand.add(deck.draw());
    other.add(deck.draw());
    other.add(deck.draw());
    Assertions.assertEquals(Integer.signum(hand.compareTo(other)),
        -Integer.signum(other.compareTo(hand)));
  }

}