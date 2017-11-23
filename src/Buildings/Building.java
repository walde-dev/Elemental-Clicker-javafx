package Buildings;


public class Building {

    public Building(String name, int cost, int coinsPerSecond) {
        this.name = name;
        this.cost = cost;
        this.coinsPerSecond = coinsPerSecond;
        this.amount = 0;
    }

    private String name;
    private String url;
    private int cost;
    private double coinsPerSecond;
    private int amount;

    public double getProportion(double p){
        if(p==0) return 0;
        return 100*(getTotalProduction()/p);
    }

    public double getTotalProduction(){
        if(amount == 0) return 0;
        if(amount == 1) return coinsPerSecond;
        return -23.0/3.0 * Math.pow(20, -amount) * (Math.pow(20, amount) - Math.pow(23, amount)) * coinsPerSecond;

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

    public double getCost() {
        return (cost * Math.pow(1.15, amount));
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getCoinsPerSecond() {
        return coinsPerSecond;
    }
    
    public void draw() {
    	
    }

}
