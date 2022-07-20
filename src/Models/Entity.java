package Models;

import Global.Constants;
import Global.Draw;
import Global.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected Position position;
    protected ImageIcon image;
    protected boolean passable;
    protected boolean collectible;
    private static final short TIMER = 10;
    private short ticker;
    private boolean dead;

    protected Entity(Position position, String imageSrc, boolean passable, boolean collectible) {
        this.position = position;
        setCollectible(collectible);
        setPassable(passable);
        setImage(imageSrc);
        this.dead = false;
        this.ticker = 0;
    }

    public void setImage(String imageSrc) {
        try {
            this.image = new ImageIcon(new java.io.File(".").
                    getCanonicalPath() + Constants.PATH + imageSrc);
            Image img = image.getImage();

            BufferedImage bi = new BufferedImage(Constants.CELL_SIDE,
                    Constants.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);

            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Constants.CELL_SIDE, Constants.CELL_SIDE, null);
            this.image = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void disintegrationEntity() {
        this.setImage(Constants.DISAPPEAR_PNG_PATH);
    }

    public void setPosition(int row, int col) {
        this.position.setPosition(row, col);
    }

    public Position getPosition() {
        return position;
    }

    public boolean isPassable() {
        return passable;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public boolean isCollectible() {
        return collectible;
    }

    public void setCollectible(boolean collectible) {
        this.collectible = collectible;
    }

    public void setDead(boolean status) {
        this.dead = status;
    }

    public void selfDraw() {
        if(dead) {
           incrementTimer();
           if (this.ticker == TIMER / 2) this.setImage(Constants.DISAPPEAR2_PNG_PATH);
        }
        Draw.draw(this.image, position.getColumn(), position.getRow());
    }

    private void incrementTimer() {
        this.ticker++;
    }

    public short getTicker() {
        return ticker;
    }

    public static short getTIMER() {
        return TIMER;
    }

    public boolean isDead() {
        return dead;
    }

}
