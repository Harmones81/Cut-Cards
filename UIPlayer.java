package com.example.cutcardssolution;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class UIPlayer
{
    public ImageView view;
    public StackPane pane;

    public UIPlayer(Player player)
    {
        pane = new StackPane();
        view = new ImageView();
        view.setFitWidth(300);
        view.setFitHeight(350);
        pane.getChildren().add(view);
    }

    public void setImage(Card card)
    {
        view.setImage(card.image());
    }
}
