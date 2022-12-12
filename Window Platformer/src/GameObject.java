import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GameObject extends Collision {
    int x, y, width, height, tileWidth, tileHeight;
    String imagePathName;
    boolean movable;
    public GameObject(int x, int y, int width, int height, int tileWidth, int tileHeight, boolean movable, String imagePathName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.imagePathName = imagePathName;
        this.movable = movable;
    }
    public LinkedList<Integer> checkCollision(GameObject obj) {
        return super.checkCollision(this, obj);
    }
    public LinkedList<Integer> checkLevelCollision(Level level) {
        return super.checkLevelCollision(level, this);
    }
    public void drawObject(JFrame frame) {
        BufferStrategy bs = frame.getBufferStrategy();

        do {
            do {
                Graphics g = bs.getDrawGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

                try {
                    for (int x = 0; x <= width - tileWidth; x += tileWidth) {
                        for (int y = 0; y <= height - tileHeight; y += tileHeight) {
                            g.drawImage(ImageIO.read(new File(imagePathName)), x, y, tileWidth, tileHeight, null);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                g.dispose();
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }
}
