package Player;

public class Player {

    public Player(double coins, double coinsPerSecond, double coinsPerClick, int rubies, int diamonds) {
        this.coins = coins;
        this.coinsPerSecond = coinsPerSecond;
        this.coinsPerClick = coinsPerClick;
        this.rubies = rubies;
        this.diamonds = diamonds;
    }

    double coins;
    double coinsPerSecond;
    double coinsPerClick;
    int rubies;
    int diamonds;

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public double getCoinsPerSecond() {
        return coinsPerSecond;
    }

    public void setCoinsPerSecond(double coinsPerSecond) {
        this.coinsPerSecond = coinsPerSecond;
    }

    public int getRubies() {
        return rubies;
    }

    public void setRubies(int rubies) {
        this.rubies = rubies;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    public double getCoinsPerClick() {
        return coinsPerClick;
    }

    public void setCoinsPerClick(double coinsPerClick) {
        this.coinsPerClick = coinsPerClick;
    }
}
