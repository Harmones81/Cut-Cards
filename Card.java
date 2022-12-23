package com.example.cutcardssolution;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Card implements Comparable<Card>
{
    public static final String IMGS_PREFIX = "file:images/cards/";
    private Suit suit;
    private CardValue cardValue;
    private Image image;
    public Color color;


    public Card(CardValue cardValue, Color color)  // only used for Jokers
    {
        this.cardValue = cardValue;
        this.suit = null;
        this.color = color;

        if (cardValue.toString().equals("JOKER"))
        {
            String fName = IMGS_PREFIX + (color == Color.RED ? "red_joker_ggc.png" : "black_joker_ggc.png");
            this.image = new Image(fName);
            return;
        }
    }

    public Card(CardValue cardValue, Suit suit)
    {
        this.cardValue = cardValue;
        this.suit = suit;
        color = (this.suit == Suit.SPADES || this.suit == Suit.CLUBS) ?
                Color.BLACK : Color.RED;

        String suffix = ".png";
        String rank; // i.e. "QUEEN" becomes "queen"
        if (cardValue.getCardValue() <= 10)
        {
            rank = String.valueOf(cardValue.getCardValue()); // i.e. "TWO" becomes "2"
        }
        else if (cardValue.getCardValue() > 10 && cardValue.getCardValue() < 14)  // face cards only
        {
            rank = cardValue().toString().toLowerCase();
            suffix = "2.png"; // this suffix used to render richer images on face cards
        }
        else  // Aces only
        {
            rank = cardValue().toString().toLowerCase();
        }

        String lcSuit = suit().toString().toLowerCase(); // "HEARTS" becomes "hearts"
        image = new Image(IMGS_PREFIX + rank + "_of_" + lcSuit + suffix);
    }

    @Override
    public String toString()
    {
        StringBuilder b = new StringBuilder(cardValue.toString());
        if (cardValue == CardValue.JOKER)
            b.append(", color=" + ((color == Color.BLACK) ? "BLACK" : "RED"));
        else
            b.append((cardValue != CardValue.JOKER) ? (" of " + suit) : "");

        return b.toString();
    }

    public Suit suit() {return suit;}
    public CardValue cardValue() {return cardValue;}
    public Image image() {return image;}


    @Override
    public int compareTo(Card other)
    {
        int result = 0; // assumes they are equal
        if (cardValue.getCardValue() > other.cardValue.getCardValue()) // I'm bigger!
            result = 1;
        else if (cardValue.getCardValue() < other.cardValue.getCardValue()) // other's bigger
            result = -1;

        return result;
    }
}
