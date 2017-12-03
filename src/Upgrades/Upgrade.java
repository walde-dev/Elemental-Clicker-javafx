package Upgrades;

import javafx.scene.image.ImageView;

public class Upgrade {

    public Upgrade(String name, String desc, double cost, ImageView image, boolean isUnlocked, boolean isBought) {
        this.name = name;
        this.desc = desc;
        this.cost = cost;
        this.image = image;
        this.isUnlocked = isUnlocked;
        this.isBought = isBought;
    }

    private String name;
    private String desc;
    private double cost;
    private ImageView image;
    private boolean isUnlocked;
    private boolean isBought;

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
