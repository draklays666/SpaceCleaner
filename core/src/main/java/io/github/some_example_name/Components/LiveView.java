package io.github.some_example_name.Components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.some_example_name.GameResources;

public class LiveView extends View {

    private final static int livePadding = 6;

    private Texture texture;

    private int leftLives;
    int maxLives = 6;

    public LiveView(float x, float y) {
        super(x, y);
        texture = new Texture(GameResources.LIVE_IMG_PATH);
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        leftLives = 0;
    }

    public void setLeftLives(int leftLives) {
        this.leftLives = leftLives;
    }

    @Override
    public void draw(SpriteBatch batch) {
        float currentX = x;
        int maxLivesToShow = 5;

        for (int i = 0; i < Math.min(leftLives, maxLivesToShow); i++) {
            batch.draw(texture, currentX, y, width, height);
            currentX += (width + 5);
        }
    }

    @Override
    public void dispose() {
        texture.dispose();
    }


}
