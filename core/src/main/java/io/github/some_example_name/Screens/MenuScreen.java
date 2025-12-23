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
import io.github.some_example_name.GameMode;
import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSession;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.MyGdxGame;

public class MenuScreen extends ScreenAdapter {

    MyGdxGame myGdxGame;

    MovingBackgroundView backgroundView;
    TextView titleView;
    ButtonView startButtonView;
    ButtonView settingsButtonView;
    ButtonView exitButtonView;
    ButtonView upgradeButtonView;
    ButtonView survivalButton;
    ButtonView huntersButton;

    public MenuScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        titleView = new TextView(myGdxGame.largeWhiteFont, 180, 960, "Space Cleaner");

        startButtonView = new ButtonView(140, 646, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Play");
        survivalButton = new ButtonView(140, 556, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Survival");
        huntersButton = new ButtonView(140, 466, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Hunters Only");
        settingsButtonView = new ButtonView(140, 286, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Settings");
        exitButtonView = new ButtonView(140, 196, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Exit");
        upgradeButtonView = new ButtonView(140, 376, 440, 70, myGdxGame.commonBlackFont, GameResources.BUTTON_LONG_BG_IMG_PATH, "Upgrade");}

    @Override
    public void render(float delta) {

        handleInput();

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        titleView.draw(myGdxGame.batch);
        survivalButton.draw(myGdxGame.batch);
        huntersButton.draw(myGdxGame.batch);
        upgradeButtonView.draw(myGdxGame.batch);
        exitButtonView.draw(myGdxGame.batch);
        settingsButtonView.draw(myGdxGame.batch);
        startButtonView.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (startButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.gameScreen);
                myGdxGame.gameScreen.startNewGame(GameMode.NORMAL);
            }
            if (survivalButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.gameScreen.startNewGame(GameMode.SURVIVAL);
                myGdxGame.setScreen(myGdxGame.gameScreen);
            }

            if (huntersButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.gameScreen.startNewGame(GameMode.HUNTERS_ONLY);
                myGdxGame.setScreen(myGdxGame.gameScreen);
            }
            if (exitButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                Gdx.app.exit();
            }
            if (settingsButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.settingsScreen);
            }
            if (upgradeButtonView.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.upgradeScreen);
            }
        }
    }
}
