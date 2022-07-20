package Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import Controller.Game;

public class LostGame extends Screen {

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> {
                this.stop();
                Game game = new Game();
                game.start();
            }
            case KeyEvent.VK_END -> {
                Runtime.getRuntime().exit(0);
            }
            default -> {
            }
        }
    }

    public void drawLostGame() {
        this.addMouseListener(this);
        this.addKeyListener(this);

        this.setSize(Constants.RES * Constants.CELL_SIDE + getInsets().left + getInsets().right,
                Constants.RES * Constants.CELL_SIDE + getInsets().top + getInsets().bottom);
        try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File(String.valueOf(Path.of("imgs").resolve(Constants.LOST_GAME_PNG_PATH))));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override
                public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        this.setVisible(true);
        this.initComponents();
        this.go();
        this.drawLostGame();
    }

    public void stop() {
        this.setVisible(false);
        this.cancel();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2022 - You lost! Play again!");
        setAutoRequestFocus(true);
    }
}
