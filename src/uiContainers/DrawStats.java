package uiContainers;

import Player.Player;
import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DrawStats extends HBox implements Drawable {
    private Text coinAmount;
    private Text coinsPerSecond;
    private Text rubyAmount;
    private Text diamondAmount;
    private ImageView coinImage;
    private ImageView coinsPerSecondImage;
    private ImageView rubyImage;
    private ImageView diamondImage;
    private Player p;

    public DrawStats(Player p) {
        this.p = p;
        uiContainers.DrawMaster.getDrawMaster().add(this);

        coinAmount = new Text(String.format("%.0f", p.getCoins()));
        coinAmount.setStyle("-fx-font-size: 28px;");

        coinsPerSecond = new Text(String.format("%.00f", p.getCoinsPerSecond()) + " /s");
        coinsPerSecond.setStyle("-fx-font-size: 28px;");

        rubyAmount = new Text(String.valueOf(p.getRubies()));
        rubyAmount.setStyle("-fx-font-size: 28px;");

        diamondAmount = new Text(String.valueOf(p.getDiamonds()));
        diamondAmount.setStyle("-fx-font-size: 28px;");

        coinImage = new ImageView(new Image("images/coin.png"));
        coinImage.setFitHeight(35);
        coinImage.setFitWidth(35);

        coinsPerSecondImage = new ImageView(new Image("images/hourglass.png"));
        coinsPerSecondImage.setFitWidth(35);
        coinsPerSecondImage.setFitHeight(35);


        rubyImage = new ImageView(new Image("images/ruby.png"));
        rubyImage.setFitHeight(35);
        rubyImage.setFitWidth(30);

        diamondImage = new ImageView(new Image("images/diamond.png"));
        diamondImage.setFitHeight(35);
        diamondImage.setFitWidth(35);

        HBox hbCoin = new HBox();
        HBox hbCoinsPerSecond = new HBox();
        HBox hbRuby = new HBox();
        HBox hbDiamond = new HBox();
        HBox hbSpacer = new HBox();
        HBox hbJewels = new HBox();
        HBox hbCPSImage = new HBox();

        hbSpacer.setPrefWidth(1000);

        hbCPSImage.getChildren().addAll(coinsPerSecondImage);
        hbCPSImage.setAlignment(Pos.CENTER_LEFT);

        hbCoin.getChildren().addAll(coinImage,coinAmount);
        hbCoin.setSpacing(2);
        hbCoin.setAlignment(Pos.CENTER);
        hbCoin.setMinWidth(150);
        hbCoin.setAlignment(Pos.CENTER_LEFT);

        hbCoinsPerSecond.getChildren().addAll(hbCPSImage, coinsPerSecond);
        hbCoinsPerSecond.setAlignment(Pos.CENTER_RIGHT);
        hbCoinsPerSecond.setMinWidth(180);

        hbRuby.getChildren().addAll(rubyImage,rubyAmount);
        hbRuby.setSpacing(2);
        hbRuby.setAlignment(Pos.CENTER);

        hbDiamond.getChildren().addAll(diamondImage, diamondAmount);
        hbDiamond.setSpacing(2);
        hbDiamond.setAlignment(Pos.CENTER);

        hbJewels.getChildren().addAll(hbRuby, hbDiamond);
        hbJewels.setSpacing(30);
        hbJewels.setAlignment(Pos.CENTER_RIGHT);

        setPadding(new Insets(5));
        setSpacing(130);
        setAlignment(Pos.CENTER_LEFT);
        setStyle(
                "-fx-border-color: black;-fx-background-size: 201 100; -fx-background-color: #FFFFFF;-fx-border-width: 0 0 5 0;");
        getChildren().addAll(hbCoin, hbCoinsPerSecond, hbSpacer, hbJewels);


    }




    @Override
    public void fixSize() {
        setStyle(String.format("-fx-border-color: black;"
                + "-fx-background-size: %.0f %.0f; -fx-background-color: #FFFFFF;-fx-border-width: 0 0 5 0;", getWidth(), getHeight()));
    }

    @Override
    public void draw() {
        coinAmount.setText(formartiermich(p.getCoins()));
        coinsPerSecond.setText(formartiermich(p.getCoinsPerSecond()) + " /s");
        rubyAmount.setText(formartiermich(p.getRubies()));
        diamondAmount.setText(formartiermich(p.getDiamonds()));
    }

    private static final NavigableMap<Double, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000D, "k");
        suffixes.put(1_000_000D, "M");
        suffixes.put(1_000_000_000D, "B");
        suffixes.put(1_000_000_000_000D, "T");
        suffixes.put(1_000_000_000_000_000D, "Qa");
        suffixes.put(1_000_000_000_000_000_000D, "Qi");
        suffixes.put(1_000_000_000_000_000_000_000D, "Sx");
        suffixes.put(1_000_000_000_000_000_000_000_000D, "Sp");
        suffixes.put(1_000_000_000_000_000_000_000_000_000D, "Oc");
        suffixes.put(1_000_000_000_000_000_000_000_000_000_000D, "No");
        suffixes.put(1_000_000_000_000_000_000_000_000_000_000_000D, "Dc");
        suffixes.put(1_000_000_000_000_000_000_000_000_000_000_000_000D, "Ud");
    }

    public static String formartiermich(double value) {
        if (value == Double.MIN_VALUE)
            return formartiermich(Double.MIN_VALUE + 1);
        if (value < 0)
            return "-" + formartiermich(-value);
        if (value < 1000)
            return String.format("%.0f", value);

        Map.Entry<Double, String> e = suffixes.floorEntry(value);
        Double divideBy = e.getKey();
        String suffix = e.getValue();

        double truncated = value / (divideBy / 10);
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return (hasDecimal ? String.format("%.2f", (truncated / 10d)) + suffix
                : String.format("%.2f", (truncated / 10)) + suffix).replace(",", ".");
    }

}
