package io.github.some_example_name.Objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.Random;
import io.github.some_example_name.GameSettings;

public class HunterTrashObject extends TrashObject {
    public HunterTrashObject(int width, int height, String texturePath, World world) {
        super(width, height, texturePath, world);
    }
}
