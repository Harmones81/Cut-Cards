package com.example.cutcardssolution;

public class GameController
{
    private Deck deck;
    public Player p1;
    public Player p2;
    public enum State { NOT_STARTED, PLAYER_1_COMPLETED, PLAYER_2_COMPLETED };
    public State state;
    public Player winner;

    public GameController(String name1, String name2)
    {
        deck = new Deck();
        p1 = new Player(name1);
        p2 = new Player(name2);
        state = State.NOT_STARTED;
    }

    public Card takeTurn()
    {

        Card pick = deck.remove();

        if (state == State.NOT_STARTED)
        {
            p1.lastCard = pick;
            state = State.PLAYER_1_COMPLETED;
        }
        else
        {
            p2.lastCard = pick;
            state = State.PLAYER_2_COMPLETED;
        }
        if (state == State.PLAYER_2_COMPLETED)
        {
            if (p1.lastCard.compareTo(p2.lastCard) == 0)
            {
                winner = null;
            }
            else if (p1.lastCard.compareTo(p2.lastCard) > 0)
            {
                p1.incrementWins();
                winner = p1;
            }
            else
            {
                p2.incrementWins();
                winner = p2;
            }
        }

        return pick;
    }
}
