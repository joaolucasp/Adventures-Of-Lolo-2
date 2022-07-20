package Global;

import Controller.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Menu extends Screen {

    Image backgroundImage;
    private int reference;

    public Menu() {
        this.reference = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> {
                this.reference++;
                setImage(reference);
            }
            case KeyEvent.VK_END -> {
                Runtime.getRuntime().exit(0);
            }
            default -> {
            }
        }
    }
    public void drawMenu(){
        this.addMouseListener(this);
        this.addKeyListener(this);

        this.setSize(Constants.RES * Constants.CELL_SIDE + getInsets().left + getInsets().right,
                Constants.RES * Constants.CELL_SIDE + getInsets().top + getInsets().bottom);

        setImage(reference);
        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });
    }

    public void setImage(int reference) {
        Path path = Path.of("imgs");

        try {
            switch (reference) {
                case 0 -> this.backgroundImage = ImageIO.read(new File(String.valueOf(path.resolve(Constants.MENU_PNG_PATH))));
                case 1 -> this.backgroundImage = ImageIO.read(new File(String.valueOf(path.resolve(Constants.OBJECTIVE_PNG_PATH))));
                case 2 -> this.backgroundImage = ImageIO.read(new File(String.valueOf(path.resolve(Constants.INSTRUCTION_PNG_PATH))));
                case 3 -> this.backgroundImage = ImageIO.read(new File(String.valueOf(path.resolve(Constants.COMMANDS_PNG_PATH))));
                default -> {
                    this.stop();
                    Game game = new Game();
                    game.start();
                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void start() {
        java.awt.EventQueue.invokeLater(() -> {
            this.setVisible(true);
            this.initComponents();
            this.go();
            this.drawMenu();
        });
    }

    public void stop() {
        this.setVisible(false);
        this.cancel();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2022 - Welcome");
        setAutoRequestFocus(true);
    }

}
