package io.github.some_example_name.Components;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSettings;

public class UpgradesView extends View {
    private Texture textureBar;
    private int upgradeLevel;
    float widthBar;
    float heightBar;

    public UpgradesView(float x, float y,
                            int upgradeLevel, String pathToTextureBar) {
        super(x, y);
        textureBar = new Texture(pathToTextureBar);
        widthBar = textureBar.getWidth();
        heightBar = textureBar.getHeight();
        this.upgradeLevel = upgradeLevel;
    }

    public void upgradeLevelUp() {
        if (upgradeLevel < GameSettings.MAX_LEVEL) upgradeLevel++;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (true) batch.draw(textureBar, x + GameSettings.X_BAR, y, widthBar, heightBar);
        if (upgradeLevel >= 1) batch.draw(textureBar, x + GameSettings.X_BAR + widthBar, y, widthBar, heightBar);
        if (upgradeLevel >= 2) batch.draw(textureBar, x + GameSettings.X_BAR + 2 * widthBar , y, widthBar, heightBar);
        if (upgradeLevel >= 3) batch.draw(textureBar, x + GameSettings.X_BAR + 3 * widthBar, y, widthBar, heightBar);
        if (upgradeLevel >= 4) batch.draw(textureBar, x + GameSettings.X_BAR + 4 * widthBar, y, widthBar, heightBar);
    }

    @Override
    public void dispose() {
        textureBar.dispose();
    }
}
