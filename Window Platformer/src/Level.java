import javax.swing.*;
import java.util.LinkedList;

public class Level {
    LinkedList<GameObject> gameObjects = new LinkedList<>();
    Player player;
    GameObject flag;
    public Level addObject(int x, int y, int width, int height, int tileWidth, int tileHeight, boolean movable, String imagePathName) {
        gameObjects.add(new GameObject(x, y, width, height, tileWidth, tileHeight, movable, imagePathName));
        return this;
    }
    public Level setPlayer(Player player) {
        this.player = player;
        return this;
    }
    public Level spawnFlag(GameObject flag) {
        this.flag = flag;
        return this;
    }
}
