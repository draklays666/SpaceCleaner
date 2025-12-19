package io.github.some_example_name;

public class GameSettings {

    // Device settings

    public static final int SCREEN_WIDTH = 720;
    public static final int SCREEN_HEIGHT = 1280;

    // Physics settings

    public static final float STEP_TIME = 1f / 60f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 6;
    public static final float SCALE = 0.05f;
    public static final int PADDING_HORIZONTAL = 30;

    public static float SHIP_FORCE_RATIO = 10;
    public static float TRASH_VELOCITY = 15;
    public static float TRASH_HUNTER_VELOCITY = 15;
    public static long STARTING_TRASH_APPEARANCE_COOL_DOWN = 2300; // in [ms] - milliseconds
    public static long STARTING_HUNTER_TRASH_APPEARANCE_COOL_DOWN = 4000;
    public static int BULLET_VELOCITY = 200; // in [m/s] - meter per second
    public static final long MIN_SPAWN_GAP = 500;

    public static final short TRASH_BIT = 2;
    public static final short SHIP_BIT = 4;
    public static final short BULLET_BIT = 8;

    // Object sizes

    public static final int SHIP_WIDTH = 150;
    public static final int SHIP_HEIGHT = 150;
    public static final int TRASH_WIDTH = 140;
    public static final int TRASH_HEIGHT = 100;
    public static final int BULLET_WIDTH = 15;
    public static final int BULLET_HEIGHT = 45;
    public static final int MAX_LEVEL = 5;
    public static final int X_BAR = 85;
    //  upgrades
    public static final int UPGRADES_COST_UP = 10;

}
