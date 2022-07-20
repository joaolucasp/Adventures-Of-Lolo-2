package Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import Controller.Game;
import Global.Constants;

public class EndGame extends Screen {

    Image backgroundImage;


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.setSecondImage();
        }
    }

    public void drawImageOne(){
        this.addMouseListener(this);
        this.addKeyListener(this);

        this.setSize(Constants.RES * Constants.CELL_SIDE + getInsets().left + getInsets().right,
                Constants.RES * Constants.CELL_SIDE + getInsets().top + getInsets().bottom);

        setContentPane(new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(backgroundImage, 0, 0, null);
            }
        });

    }

    public void setFirstImage(){
        try {
            this.backgroundImage = javax.imageio.ImageIO.read(new File(String.valueOf(Path.of("imgs").resolve(Constants.END_GAME1_PNG_PATH))));

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void setSecondImage(){
        try {
            this.backgroundImage = javax.imageio.ImageIO.read(new File(String.valueOf(Path.of("imgs").resolve(Constants.END_GAME2_PNG_PATH))));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }

    public void start() {
        this.setVisible(true);
        this.initComponents();
        this.go();
        this.setFirstImage();
        this.drawImageOne();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2022 - Thanks for playing our game!");
        setAutoRequestFocus(true);
    }
}
