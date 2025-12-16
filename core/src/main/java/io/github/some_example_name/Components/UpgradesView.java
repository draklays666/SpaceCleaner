package io.github.some_example_name.Components;


public class UpgradesView extends View {

    private int upgradeLevel;


    public UpgradesView(float x, float y, float width, float height) {
        super(x, y);

    }


    public void upgradeLevelUp() {
        this.upgradeLevel++;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }
}
