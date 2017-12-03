package Achievments;

import javafx.scene.image.ImageView;

public class Achievement {

    public Achievement(String name, String desc, boolean isSecret, boolean isUnlocked, ImageView image) {
        this.name = name;
        this.desc = desc;
        this.isSecret = isSecret;
        this.isUnlocked = isUnlocked;
        this.image = image;
    }

    String name;
    String desc;
    ImageView image;
    boolean isSecret;
    boolean isUnlocked;

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public boolean isSecret() {
        return isSecret;
    }

    public void setSecret(boolean secret) {
        isSecret = secret;
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
}
