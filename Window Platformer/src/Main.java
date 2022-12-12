import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main extends Canvas {
    boolean running = false;
    boolean leftMousePressed = false;
    boolean rightMousePressed = false;

    Thread thread;
    Game game = new Game();

    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { leftMousePressed = true; }
                if (e.getButton() == MouseEvent.BUTTON3) { rightMousePressed = true; }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { leftMousePressed = false; }
                if (e.getButton() == MouseEvent.BUTTON3) { rightMousePressed = false; }
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        EventQueue.invokeLater(this::start);
    }
    public void start() {
        game.startGame();
        if (thread != null) {
            stop();
        }
        thread = new Thread(this::running);
        thread.start();
        running = true;
    }
    public void stop() {
        try {
            running = false;
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        thread = null;
    }
    public void running() {
        long lastTime = System.nanoTime();
        double fps = 60.0;
        double ns = 100000000 / fps;
        double delta = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta > 1) {
                tick();
            }

            render();
        }
        stop();
    }

    public void tick() {
        game.tick();
    }
    public void render() {
        game.drawGUI();
    }
}