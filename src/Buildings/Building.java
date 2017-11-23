package Buildings;

public class Building {

    public Building(String name, String url, int cost, int coinsPerSecond, int amount) {
        this.name = name;
        this.url = url;
        this.cost = cost;
        this.coinsPerSecond = coinsPerSecond;
        this.amount = amount;
        this.production = 0;
    }

    private String name;
    private String url;
    private int cost;
    private double coinsPerSecond;
    private int amount;
    private double production;

    public double getProportion(double p){
        System.out.println(production + " " + p);
        if(p==0) return 0;
        return 100*(production/p);
    }

    public void setProduction(){
        this.production = coinsPerSecond*amount;
    }

    public String getName() {
        return name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getCost() {
        return (cost * Math.pow(1.15, amount));
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getCoinsPerSecond() {
        return coinsPerSecond;
    }

}
