package uiContainers;

import Player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class DrawStats extends HBox implements Drawable {
    private Text coinAmount;
    private Text coinsPerSecond;
    private Text rubyAmount;
    private Text diamondAmount;
    private ImageView coinImage;
    private ImageView rubyImage;
    private ImageView diamondImage;
    private Player p;

    public DrawStats(Player p) {
        this.p = p;
        uiContainers.DrawMaster.getDrawMaster().add(this);

        coinAmount = new Text(String.format("%.0f", p.getCoins()));
        coinAmount.setStyle("-fx-font-size: 12px;");

        coinsPerSecond = new Text(String.format("%.00f", p.getCoinsPerSecond()));
        coinsPerSecond.setStyle("-fx-font-size: 12px;");

        rubyAmount = new Text(String.valueOf(p.getRubies()));
        rubyAmount.setStyle("-fx-font-size: 12px;");

        diamondAmount = new Text(String.valueOf(p.getDiamonds()));
        diamondAmount.setStyle("-fx-font-size: 12px;");

        coinImage = new ImageView(new Image("images/coin.png"));

        rubyImage = new ImageView(new Image("images/ruby.png"));

        diamondImage = new ImageView(new Image("images/diamond.png"));

        setAlignment(Pos.CENTER_LEFT);
        setStyle(
                "-fx-border-color: black;-fx-background-image: url('images/background_clicks.png');-fx-background-size: 201 100;");
        getChildren().addAll(coinImage, coinAmount, coinsPerSecond, rubyImage, rubyAmount, diamondImage, diamondAmount);


    }




    @Override
    public void fixSize() {
        setStyle(String.format("-fx-border-color: black;" + "-fx-background-image: url('images/background_clicks.png');"
                + "-fx-background-size: %.0f %.0f;", getWidth(), getHeight()));
    }

    @Override
    public void draw() {
        coinAmount.setText(String.format("%.0f", p.getCoins()));
        coinsPerSecond.setText(String.format("%.00f", p.getCoinsPerSecond()));
        rubyAmount.setText(String.valueOf(p.getRubies()));
        diamondAmount.setText(String.valueOf(p.getDiamonds()));
    }

}
