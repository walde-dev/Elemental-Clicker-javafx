package Player;

public class Player {

    private static Player myPlayer;

    public Player(double coins, double coinsPerSecond, double coinsPerClick, double rubies, int diamonds) {
        this.coins = coins;
        this.coinsPerSecond = coinsPerSecond;
        this.coinsPerClick = coinsPerClick;
        this.rubies = rubies;
        this.diamonds = diamonds;
    }

    public static Player getPlayer() {
        if (myPlayer == null) {
            myPlayer = new Player(0,0,1,0,0);
        }
        return myPlayer;
    }

    double coins;
    double coinsPerSecond;
    double coinsPerClick;
    double rubies;
    int diamonds;


    public void reduceCoins(double amount){
        coins-=amount;
    }

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

    public double getRubies() {
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
