package io.github.some_example_name;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import io.github.some_example_name.Managers.MemoryManager;
import io.github.some_example_name.Objects.GameObject;

public class GameSession {

    public GameState state;
    long nextTrashSpawnTime;
    long nextHunterTrashSpawnTime;
    long sessionStartTime;
    long pauseStartTime;
    private int score;
    int destructedTrashNumber;
    private long lastAnyTrashSpawn;

    public GameSession() {
    }

    public void startGame() {
        state = GameState.PLAYING;
        score = 20000;
        destructedTrashNumber = 0;
        sessionStartTime = TimeUtils.millis();
        nextTrashSpawnTime = sessionStartTime + (long) (GameSettings.STARTING_TRASH_APPEARANCE_COOL_DOWN
            * getTrashPeriodCoolDown());
        nextHunterTrashSpawnTime = sessionStartTime + (long) (GameSettings.STARTING_HUNTER_TRASH_APPEARANCE_COOL_DOWN
            * getTrashPeriodCoolDown());
    }

    public void pauseGame() {
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();
    }

    public void resumeGame() {
        state = GameState.PLAYING;
        sessionStartTime += TimeUtils.millis() - pauseStartTime;
    }

    public void endGame() {
        updateScore();
        state = GameState.ENDED;
        ArrayList<Integer> recordsTable = MemoryManager.loadRecordsTable();
        if (recordsTable == null) {
            recordsTable = new ArrayList<>();
        }
        int foundIdx = 0;
        for (; foundIdx < recordsTable.size(); foundIdx++) {
            if (recordsTable.get(foundIdx) < getScore()) break;
        }
        recordsTable.add(foundIdx, getScore());
        MemoryManager.saveTableOfRecords(recordsTable);
        MemoryManager.upgradeTotalScore(score);
    }

    public void destructionRegistration() {
        destructedTrashNumber += 1;
    }

    public void updateScore() {
        score = (int) (TimeUtils.millis() - sessionStartTime) / 100 + destructedTrashNumber * 100;
    }

    public int getScore() {
        return score;
    }

    public boolean shouldSpawnTrash() {
        long now = TimeUtils.millis();
        if (now - lastAnyTrashSpawn < GameSettings.MIN_SPAWN_GAP) return false;
        if (nextTrashSpawnTime <= now) {
            nextTrashSpawnTime = now + (long)(GameSettings.STARTING_TRASH_APPEARANCE_COOL_DOWN * getTrashPeriodCoolDown());
            lastAnyTrashSpawn = now;
            return true;
        }
        return false;
    }
    public boolean shouldSpawnHunterTrash() {
        long now = TimeUtils.millis();

        if (now - lastAnyTrashSpawn < GameSettings.MIN_SPAWN_GAP) {
            return false;
        }
        if (now >= nextHunterTrashSpawnTime) {
            lastAnyTrashSpawn = now;
            nextHunterTrashSpawnTime = now + (long)(GameSettings.STARTING_HUNTER_TRASH_APPEARANCE_COOL_DOWN * getTrashPeriodCoolDown());
            return true;
        }

        return false;
    }
    private float getTrashPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
}
