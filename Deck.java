package com.example.cutcardssolution;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> deck;

    public Deck()
    {
        initDeck();
    }

    private void initDeck()
    {
        this.deck = new ArrayList<>();

        for (int i = 0; i < 13; i++)
        {
            CardValue value = CardValue.values()[i];

            for (int j = 0; j < 4; j++)
            {
                Card card = new Card(value, Suit.values()[j]);
                deck.add(card);
            }
        }

        deck.add(new Card(CardValue.JOKER, Color.RED));
        deck.add(new Card(CardValue.JOKER, Color.BLACK));
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card remove()
    {

        if (deck.size() < 2)
        {
            initDeck();
            System.out.println("Deck was to low for a game, getting a new shuffled deck");
        }

        Card top = deck.get(0);
        deck.remove(0);
        return top;
    }
}
