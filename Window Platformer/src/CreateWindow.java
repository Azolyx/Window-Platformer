import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CreateWindow extends JFrame implements KeyListener {
    boolean wPressed = false;
    boolean aPressed = false;
    boolean sPressed = false;
    boolean dPressed = false;
    boolean spacePressed = false;

    public CreateWindow(String windowName, int x, int y, int windowWidth, int windowHeight, boolean titleVisible) {
        setTitle(windowName);
        setSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(x, y);
        setUndecorated(!titleVisible);
        setVisible(true);
        createBufferStrategy(3);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) { wPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_A) { aPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_S) { sPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_D) { dPressed = true; }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { spacePressed = true; }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) { wPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_A) { aPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_S) { sPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_D) { dPressed = false; }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) { spacePressed = false; }
    }
}
