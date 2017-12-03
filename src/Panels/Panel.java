package Panels;

import javafx.scene.image.ImageView;

public class Panel {

    public Panel(String name, ImageView image, boolean isUnlocked) {
        this.name = name;
        this.image = image;
        this.isUnlocked = isUnlocked;
    }

    private String name;
    private ImageView image;
    private boolean isUnlocked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
