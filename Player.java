package com.example.cutcardssolution;

public class Player
{
    Card lastCard;
    public int numWins;
    public String name;

    public Player(String name)
    {
        this.numWins = 0;
        this.lastCard = null;
        this.name = name;
    }

    public void incrementWins() {
        numWins++;
    }
}
