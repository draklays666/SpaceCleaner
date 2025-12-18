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
import io.github.some_example_name.Components.UpgradesView;
import io.github.some_example_name.GameResources;
import io.github.some_example_name.GameSettings;
import io.github.some_example_name.Managers.MemoryManager;
import io.github.some_example_name.MyGdxGame;

public class UpgradeScreen extends ScreenAdapter {
    MyGdxGame myGdxGame;

    ImageView blackoutImageView;
    ButtonView plusImageView1;
    ButtonView plusImageView2;
    ButtonView plusImageView3;
    ImageView upgrades_bar1;
    ImageView upgrades_bar2;
    ImageView upgrades_bar3;
    ImageView upgrades_icon1;
    ImageView upgrades_icon2;
    ImageView upgrades_icon3;
    TextView titleTextView;
    ButtonView returnButton;
    TextView scoreUpgrades;
    UpgradesView healthUpgrades;
    UpgradesView damageUpgrades;
    UpgradesView rateUpgrades;

    MovingBackgroundView backgroundView;
    public UpgradeScreen(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        titleTextView = new TextView(myGdxGame.largeWhiteFont, 256, 956, "Upgrades");
        backgroundView = new MovingBackgroundView(GameResources.BACKGROUND_IMG_PATH);
        plusImageView1 = new ButtonView(525, 695, 70, 70, GameResources.PLUS_IMG_PATH);
        plusImageView2 = new ButtonView(525,595,70, 70,  GameResources.PLUS_IMG_PATH);
        plusImageView3 = new ButtonView(525,495,70, 70, GameResources.PLUS_IMG_PATH);
        upgrades_bar1 = new ImageView(150, 680,GameResources.UPGRADES_BAR_IMG_PATH);
        upgrades_bar2 = new ImageView(150, 580,GameResources.UPGRADES_BAR_IMG_PATH);
        upgrades_bar3 = new ImageView(150, 480,GameResources.UPGRADES_BAR_IMG_PATH);
        upgrades_icon1 = new ImageView(159,690, GameResources.DAMAGE_IMG_PATH);
        upgrades_icon2 = new ImageView(153,585, GameResources.RATE_IMG_PATH);
        upgrades_icon3 = new ImageView(173,505, GameResources.LIVE_IMG_PATH);
        damageUpgrades = new UpgradesView(145, 705, MemoryManager.saveDamageLevel(), GameResources.DAMAGE_BAR_IMG_PATH);
        rateUpgrades = new UpgradesView(150, 603, MemoryManager.saveRateLevel(), GameResources.RATE_BAR_IMG_PATH);
        healthUpgrades = new UpgradesView(145, 505, MemoryManager.saveHealthLevel(), GameResources.HP_BAR_IMG_PATH);



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
        scoreUpgrades.setText("Score: " + MemoryManager.getTotalScore());

        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        ScreenUtils.clear(Color.CLEAR);

        myGdxGame.batch.begin();

        backgroundView.draw(myGdxGame.batch);
        blackoutImageView.draw(myGdxGame.batch);
        titleTextView.draw(myGdxGame.batch);
        returnButton.draw(myGdxGame.batch);
        plusImageView1.draw(myGdxGame.batch);
        plusImageView2.draw(myGdxGame.batch);
        plusImageView3.draw(myGdxGame.batch);
        upgrades_bar1.draw(myGdxGame.batch);
        upgrades_bar2.draw(myGdxGame.batch);
        upgrades_bar3.draw(myGdxGame.batch);
        healthUpgrades.draw(myGdxGame.batch);
        damageUpgrades.draw(myGdxGame.batch);
        rateUpgrades.draw(myGdxGame.batch);
        upgrades_icon1.draw(myGdxGame.batch);
        upgrades_icon2.draw(myGdxGame.batch);
        upgrades_icon3.draw(myGdxGame.batch);
        scoreUpgrades.draw(myGdxGame.batch);

        myGdxGame.batch.end();
    }

    void handleInput() {
        if (Gdx.input.justTouched()) {
            myGdxGame.touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (returnButton.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                myGdxGame.setScreen(myGdxGame.menuScreen);
            }
            if (plusImageView1.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyDamageUpgrade()) {
                    MemoryManager.buyTotalScore(GameSettings.UPGRADES_COST_UP * (damageUpgrades.getUpgradeLevel() + 1));
                    MemoryManager.loadDamageLevel();
                    damageUpgrades.upgradeLevelUp();
                }
            }
            if (plusImageView2.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyRateUpgrade()) {
                    MemoryManager.buyTotalScore(GameSettings.UPGRADES_COST_UP * (rateUpgrades.getUpgradeLevel() + 1));
                    MemoryManager.loadRateLevel();
                    rateUpgrades.upgradeLevelUp();
                }
            }
            if (plusImageView3.isHit(myGdxGame.touch.x, myGdxGame.touch.y)) {
                if (canBuyHealthUpgrade()) {
                    MemoryManager.buyTotalScore(GameSettings.UPGRADES_COST_UP * (healthUpgrades.getUpgradeLevel() + 1));
                    MemoryManager.loadHealthLevel();
                    healthUpgrades.upgradeLevelUp();
                }
            }
        }
    }
    private boolean canBuyHealthUpgrade() {
        return MemoryManager.saveHealthLevel() < 4 && MemoryManager.getTotalScore() >= GameSettings.UPGRADES_COST_UP * (MemoryManager.saveHealthLevel() + 1);
    }

    private boolean canBuyDamageUpgrade() {
        return MemoryManager.saveDamageLevel() < 4 && MemoryManager.getTotalScore() >= GameSettings.UPGRADES_COST_UP * (MemoryManager.saveDamageLevel() + 1);
    }

    private boolean canBuyRateUpgrade() {
        return MemoryManager.saveRateLevel() < 4 && MemoryManager.getTotalScore() >= GameSettings.UPGRADES_COST_UP * (MemoryManager.saveRateLevel() + 1);
    }
    @Override
    public void dispose() {
        backgroundView.dispose();
        blackoutImageView.dispose();
        healthUpgrades.dispose();
        damageUpgrades.dispose();
        rateUpgrades.dispose();
        returnButton.dispose();
        plusImageView1.dispose();
        plusImageView2.dispose();
        plusImageView3.dispose();
        scoreUpgrades.dispose();
    }
}

