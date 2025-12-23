package io.github.some_example_name.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.Random;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.Managers.MemoryManager;

public class HunterTrashObject extends GameObject {
     private float minValue = -5f;
    private float maxValue = 5f;
    private int livesLeft;
     private int livesLeftH = 2;
    public HunterTrashObject(int width, int height, String texturePath, World world) {
        super(
            texturePath,
            width / 2 + GameSettings.PADDING_HORIZONTAL + (new Random()).nextInt((GameSettings.SCREEN_WIDTH - 2 * GameSettings.PADDING_HORIZONTAL - width)),
            GameSettings.SCREEN_HEIGHT + height / 2,
            width, height + 20,
            GameSettings.TRASH_BIT,
            world
        );
        body.setLinearVelocity(new Vector2((float) (minValue + Math.random() * (maxValue - minValue + 1)), -GameSettings.TRASH_HUNTER_VELOCITY));
        livesLeft = livesLeftH;
    }
    public boolean isAlive() {
        return livesLeft > 0;
    }

    public boolean isInFrame() {
        return getY() + height / 2 > 0;
    }

    @Override
    public void hit() {
        livesLeft -= MemoryManager.getDamageLevel() + 1;
    }
}
