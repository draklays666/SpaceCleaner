package io.github.some_example_name.Managers;

public class WaveManager {
    public final int waveNumber;           // номер волны (1..5)
    public final int trashToDestroy;      // сколько мусора нужно убить, чтобы перейти дальше
    public final float baseSpawnInterval;  // начальный интервал между спавнами (в секундах)
    public final float debrisSpeed;        // скорость падения мусора (пикселей/сек)
    public final float bossHealth;         // 0 = мусор, >0 = босс с таким HP
    public final String description;       // текст для игрока (можно показывать при старте волны)
    private WaveManager(int wave, int toDestroy, float interval, float speed, float bossHealth, String description) {
        this.waveNumber = wave;
        this.trashToDestroy = toDestroy;
        this.baseSpawnInterval = interval;
        this.debrisSpeed = speed;
        this.bossHealth = bossHealth;
        this.description = description;
    }
    public static final WaveManager[] WAVES = {
        new WaveManager(1, 5, 1.2f, 120f, 0f, "Очистка орбиты: базовый мусор"),
        new WaveManager(2, 5, 0.9f, 140f, 0f, "Появились вращающиеся фрагменты"),
        new WaveManager(3, 5, 0.6f, 160f, 0f, "Активированы автономные модули"),
        new WaveManager(4, 5, 0.5f, 180f, 0f, "Минное поле! Двигайтесь осторожно"),
        new WaveManager(5,  0, 99999f,   0f, 15f, "Финал: уничтожьте спутник-нарушитель!")
    };
    public static WaveManager get(int waveNumber) {
        return WAVES[waveNumber - 1];
    }
    public boolean hasBoss() {
        return bossHealth > 0;
    }
}
