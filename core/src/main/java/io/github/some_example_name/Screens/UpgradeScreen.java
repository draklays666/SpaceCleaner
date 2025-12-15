package io.github.some_example_name.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


import io.github.some_example_name.Components.ButtonView;
import io.github.some_example_name.Components.ImageView;
import io.github.some_example_name.Components.MovingBackgroundView;
import io.github.some_example_name.Components.TextView;
import io.github.some_example_name.GameResources;
import io.github.some_example_name.Managers.MemoryManager;
import io.github.some_example_name.MyGdxGame;

public class UpgradeScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;

    ImageView blackoutImageView;
    ButtonView plusImageView;
    ButtonView plusImageView2;
    ButtonView plusImageView3;
    ImageView upgrades_bar1;
    ImageView upgrades_bar2;
    ImageView upgrades_bar3;
    TextView titleTextView;
    TextView scoreUpgrades;
    ButtonView returnButton;

    MovingBackgroundView backgroundView;
    public UpgradeScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        titleTextView = new TextView(myGdxGame.largeWhiteFont, 256, 956, "Upgrades");
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        plusImageView = new ButtonView(525, 695, 70, 70, GameResources.PLUS_IMG_PATH);
        plusImageView2 = new ButtonView(525,595,70, 70,  GameResources.PLUS_IMG_PATH);
        plusImageView3 = new ButtonView(525,495,70, 70, GameResources.PLUS_IMG_PATH);
        upgrades_bar1 = new ImageView(150, 680,GameResources.UPGRADES_BAR_IMG_PATH);
        upgrades_bar2 = new ImageView(150, 580,GameResources.UPGRADES_BAR_IMG_PATH);
        upgrades_bar3 = new ImageView(150, 480,GameResources.UPGRADES_BAR_IMG_PATH);
        scoreUpgrades = new TextView(myGdxGame.commonWhiteFont, 360, 870, "Score: " + MemoryManager.getTotalScore());


        blackoutImageView = new ImageView(85, 365, GameResources.BLACKOUT_MIDDLE_IMG_PATH);
        returnButton = new ButtonView(
            280, 387,
            160, 70,
            myGdxGame.commonBlackFont,
            GameResources.BUTTON_SHORT_BG_IMG_PATH,
            "return"
        );
    }


    @Override
    public void render(float delta) {

        handleInput();

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        blackoutImageView.draw(myGdxGame.batch);
        titleTextView.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);
        plusImageView.draw(myGdxGame.batch);
        plusImageView2.draw(myGdxGame.batch);
        plusImageView3.draw(myGdxGame.batch);
        upgrades_bar1.draw(myGdxGame.batch);
        upgrades_bar2.draw(myGdxGame.batch);
        upgrades_bar3.draw(myGdxGame.batch);
        scoreUpgrades.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
        }
    }
}

