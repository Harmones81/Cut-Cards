package com.example.cutcardssolution;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

public class CutCards extends Application
{
    public static final String TOTAL_WINS_FORMAT = "%s Total Wins : %d";
    public static final String WINNER_FMT = "%s Wins";
    public static final String TIE = "Tie";
    String name1 = null;
    String name2 = null;

    GameController game = null;
    UIPlayer ui1 = null;
    UIPlayer ui2 = null;
    Card startCard = null;

    GridPane pane = null;
    Label p1TotalWins = null;
    Label p2TotalWins = null;
    Label winnerDisplay = null;
    Label tieDisplay = null;

    Button btnCut = new Button();
    Button btnStartOver = new Button("Start Over");

    @Override
    public void start(Stage stage) throws IOException
    {
        // Get the names of the players
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the two player card cutter!\n");
        System.out.print("Please enter the name of player #1: ");
        name1 = scanner.nextLine();
        System.out.print("Please enter the name of player #2: ");
        name2 = scanner.nextLine();

        // Initialize needed components
        game = new GameController(name1, name2);
        ui1 = new UIPlayer(game.p1);
        ui2 = new UIPlayer(game.p2);
        p1TotalWins = new Label(String.format(TOTAL_WINS_FORMAT, game.p1.name, game.p1.numWins));
        p2TotalWins = new Label(String.format(TOTAL_WINS_FORMAT, game.p2.name, game.p2.numWins));

        pane = new GridPane();
        StackPane ui1Pane = ui1.pane;
        StackPane ui2Pane = ui2.pane;


        // Initialize start phase of the game
        startCard = new Card(CardValue.ACE, Suit.HEARTS);
        ui1.setImage(startCard);
        ui2.setImage(startCard);
        btnCut.setText(name1 + "'s Cut");
        btnStartOver.setDisable(true);

        // Set-up UI structure
        pane.setAlignment(Pos.CENTER);
        pane.add(p1TotalWins, 0, 0);
        pane.add(p2TotalWins, 1, 0);
        pane.add(ui1Pane, 0, 1);
        pane.add(ui2Pane, 1, 1);
        pane.add(btnCut, 0, 2);
        pane.add(btnStartOver, 1, 2);

        // Call events when designated buttons are pressed
        btnCut.setOnAction(event -> takeTurn());
        btnStartOver.setOnAction(event -> startOver());

        Scene scene = new Scene(pane);
        stage.setTitle("Cut Cards!");

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                if(!btnCut.isDisabled())
                {
                    if(event.getCode() == KeyCode.C)
                    {
                        takeTurn();
                    }
                }
                else if(!btnStartOver.isDisabled())
                {
                    if(event.getCode() == KeyCode.S)
                    {
                        startOver();
                    }
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }

    private void startOver()
    {
        game.state = GameController.State.NOT_STARTED;

        ui1.setImage(startCard);
        ui2.setImage(startCard);

        // Get rid of the winner display -> Ex: "Bob Wins"
        if(game.winner != null)
        {
            pane.getChildren().remove(winnerDisplay);
        }
        else
        {
            pane.getChildren().remove(tieDisplay);
        }

        // Disable and Enable appropriate buttons
        btnCut.setDisable(false);
        btnCut.setText(name1 + "'s Cut");
        btnStartOver.setDisable(true);
    }

    private void takeTurn()
    {
        Card card = game.takeTurn();

        if(game.state == GameController.State.PLAYER_1_COMPLETED)
        {
            ui1.setImage(card);
            btnCut.setText(name2 + "'s Cut");
        }
        else if(game.state == GameController.State.PLAYER_2_COMPLETED)
        {
            ui2.setImage(card);
            btnCut.setDisable(true);

            if(game.winner != null)
            {
                winnerDisplay = new Label(String.format(WINNER_FMT, game.winner.name));
                pane.add(winnerDisplay, 0, 3);
            }
            else
            {
                tieDisplay = new Label(TIE);
                pane.add(tieDisplay, 0, 3);
            }

            btnStartOver.setDisable(false);

            p1TotalWins.setText(String.format(TOTAL_WINS_FORMAT, game.p1.name, game.p1.numWins));
            p2TotalWins.setText(String.format(TOTAL_WINS_FORMAT, game.p2.name, game.p2.numWins));
        }
    }
}