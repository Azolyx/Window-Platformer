import javax.swing.*;
import java.util.LinkedList;

public class Game {
    LinkedList<JFrame> frames = new LinkedList<>();
    Level[] levels = {
            new Level()
                    .addObject(100, 700, 100, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .addObject(300, 700, 100, 100, 100, 100, true, "Textures/1x1BrickTexture.png")
                    .addObject(500, 600, 100, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .addObject(700, 600, 500, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .addObject(1300, 450, 100, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .addObject(700, 300, 500, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .setPlayer(new Player(new GameObject(100, 0, 50, 100, 50, 50, false ,"Textures/1x1BrickTexture.png")))
                    .spawnFlag(new GameObject(700, 200, 100, 100, 100, 100, false, "Textures/GoldFlagSprite.png")),
            new Level()
                    .addObject(100, 700, 100, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .addObject(300, 700, 100, 100, 100, 100, false, "Textures/1x1BrickTexture.png")
                    .setPlayer(new Player(new GameObject(100, 0, 50, 100, 50, 50, false, "Textures/1x1BrickTexture.png")))
                    .spawnFlag(new GameObject(300, 600, 100, 100, 100, 100, false, "Textures/GoldFlagSprite.png"))
    };
    Level currentLoadedLevel;
    int currentLoadedLevelInt;
    public void startGame() {
        loadLevel(0);
    }
    public void tick() {
        for (int i = 0; i < currentLoadedLevel.gameObjects.size(); i++) {
            currentLoadedLevel.gameObjects.get(i).x = frames.get(i).getX();
            currentLoadedLevel.gameObjects.get(i).y = frames.get(i).getY();
        }
        currentLoadedLevel.player.updatePlayer(currentLoadedLevel);
        if (currentLoadedLevel.player.playerInfo.checkCollision(currentLoadedLevel.flag).get(0) != 0) {
            nextLevel();
        }
    }
    public void drawGUI() {
        for (int i = 0; i < currentLoadedLevel.gameObjects.size(); i++) {
            currentLoadedLevel.gameObjects.get(i).drawObject(frames.get(i));
        }
        currentLoadedLevel.player.drawPlayer();
        currentLoadedLevel.flag.drawObject(frames.getLast());
    }
    public void loadLevel(int level) {
        for (JFrame frame : frames) {
            frame.setVisible(false);
            frame.dispose();
        }
        frames = new LinkedList<>();

        if (level > 0) {
            levels[level - 1].player.player.setVisible(false);
            levels[level - 1].player.player.dispose();
        }

        for (GameObject gameObject : levels[level].gameObjects) {
            frames.add(new CreateWindow("", gameObject.x, gameObject.y, gameObject.width, gameObject.height, gameObject.movable));
        }
        levels[level].player.spawnPlayer();
        frames.add(new CreateWindow("", levels[level].flag.x, levels[level].flag.y, levels[level].flag.width, levels[level].flag.height, false));
        currentLoadedLevel = levels[level];
        currentLoadedLevelInt = level;
    }
    public void nextLevel() {
        if (currentLoadedLevelInt < levels.length - 1) {
            loadLevel(currentLoadedLevelInt + 1);
        } else {
            endGame();
        }
    }
    public void endGame() {

    }
}