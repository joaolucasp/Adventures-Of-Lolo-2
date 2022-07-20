package Global;

import Controller.LivesListener;
import Controller.PhaseCompleteListener;
import Models.Direction;
import Models.Entities.Collectible.OpenChest;
import Models.Entities.Enemy.Enemy;
import Models.Entities.Enemy.GreenMonster;
import Models.Entities.Obstacle.Chest;
import Models.Entities.Obstacle.Door;
import Models.Entities.Obstacle.Tree;
import Models.Entities.Obstacle.Wall.*;
import Models.Entity;
import Models.Entities.Collectible.Heart;
import Models.Entities.Lolo;
import Models.MoveManager;
import Models.Stalker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Phase extends Screen {

    protected MoveManager moveManager;
    public Lolo lolo;
    private final PhaseCompleteListener phaseCompleteListener;
    private final LivesListener livesListener;
    protected ArrayList<Entity> entities;
    protected Graphics graphics;
    protected int hearts;
    protected Door door;
    protected int power;
    protected int lives;

    public Phase(PhaseCompleteListener phaseCompleteListener, LivesListener livesListener) {
        this.phaseCompleteListener = phaseCompleteListener;
        this.livesListener = livesListener;
        this.entities = new ArrayList<>(Constants.RES * Constants.RES - 1);
        this.moveManager = new MoveManager(entities);
        this.power = 0;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public void start() {
        this.setVisible(true);
        this.createBufferStrategy(2);
        Draw.setScenery(this);
        this.go();
    }

    public void stop() {
        this.setEnemiesAsDead();
        this.setVisible(false);
        clear();
        this.cancel();
    }

    private void clear() {
        this.entities.clear();
        this.moveManager = null;
        this.hearts = 0;
    }

    public void reset() {
        this.lolo.kill();
    }

    public void startPhase() {
        initComponents();
        createAllEntities();
        this.addMouseListener(this);
        this.addKeyListener(this);

        this.setSize(Constants.RES * Constants.CELL_SIDE + getInsets().left + getInsets().right,
                Constants.RES * Constants.CELL_SIDE + getInsets().top + getInsets().bottom);

        Draw.setScenery(this);
        this.addWall();
    }

    protected abstract void createLolo();
    protected abstract void createEntities();
    protected abstract void createHearts();
    protected abstract void createChest();
    protected abstract void createDoor();
    protected abstract void createEnemies();
    protected abstract void createScenery();

    private void createAllEntities() {
        createHearts();
        createDoor();
        createScenery();
        createChest();
        createLolo();
        createEntities();
        createEnemies();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = e.getY() / Constants.CELL_SIDE;
        int column = e.getX() / Constants.CELL_SIDE;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./Serialized"));

        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.FILES_ONLY) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream deserializer = new ObjectInputStream(fileInputStream);

                Entity entity = (Entity) deserializer.readObject();
                entity.setPosition(row, column);
                this.addEntity(entity);

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    private void addWall() {
        for (int i = 1; i < Constants.RES - 1; i++) {
            // top and bottom walls
            this.entities.add(new Top(0, i));
            this.entities.add(new Bottom(Constants.RES-1, i));

            // left and right walls
            this.entities.add(new Side(i, 0));
            this.entities.add(new Side(i, Constants.RES-2));
        }

        this.entities.add(new TopLeft());
        this.entities.add(new TopRight());

        this.entities.add(new BottomLeft());
        this.entities.add(new BottomRight());

        this.entities.add(this.door);
    }

    public Graphics getGraphicsBuffer(){
        return graphics;
    }

    public void draw(ArrayList<Entity> entities) {
        new ArrayList<>(entities).forEach(Entity::selfDraw);
    }

    public void onHitDetected() {
        livesListener.onLiveDecrease();
    }

    public void process(ArrayList<Entity> entities) {
        Entity entity;

        if (lolo.getPosition().isEqual(this.door.getPosition())) {
            stop();
            phaseCompleteListener.onPhaseComplete();
            return;
        }

        for(int i = 0; i < entities.size(); i++) {
            entity = entities.get(i);

            //Se a posição do Lolo for igual a uma entidade e a entidade for passavel e coletável, então ele coleta o coração e o remove
            if(lolo.getPosition().isEqual(entity.getPosition()) && entity.isPassable() && entity.isCollectible()) {
                if (entity instanceof Heart) {
                    this.decrementHearts();
                    this.power++;
                    this.entities.remove(entity);
                }

                if (entity instanceof OpenChest && hearts <= 0) {
                    this.entities.removeIf(entity_ -> entity_.getPosition().isEqual(this.door.getPosition())
                            && entity_ instanceof Top);
                    this.door.setImage(Constants.OPEN_DOOR_PNG_PATH);
                    this.entities.remove(entity);
                    setEnemiesAsDead();
                }
            }

            if(lolo.getPosition().isEqual(entity.getPosition())){
                if(entity instanceof GreenMonster){
                    ((GreenMonster) entity).setInert(true);
                }
            }
        }
        removeDeadEnemies();
    }

    private void removeDeadEnemies() {
        Predicate<Entity> isDead = entity -> (entity instanceof Enemy)
                && (entity.getTicker() >= Entity.getTIMER());
        this.entities.removeIf(isDead);
    }

    private void setEnemiesAsDead() {
        this.entities.forEach(entity -> {
            if (entity instanceof Enemy) {
                entity.disintegrationEntity();
                entity.setDead(true);
            }
        });
    }

    private void decrementHearts() {
        this.hearts--;

        if (hearts <= 0) onCollectedAllHearts();
    }

    protected void onCollectedAllHearts() {
        this.entities.removeIf(o -> o instanceof Chest);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2022 - Adventures of lolo");
        setAutoRequestFocus(true);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> this.lolo.move(Direction.TOP);
            case KeyEvent.VK_DOWN -> this.lolo.move(Direction.DOWN);
            case KeyEvent.VK_LEFT -> this.lolo.move(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> this.lolo.move(Direction.RIGHT);
            case KeyEvent.VK_Z -> {
                if (this.power >= 2) {
                    this.lolo.shot(this.lolo.getPosition().getLastMove());
                    this.power -= 2;
                }
            }

            case KeyEvent.VK_SHIFT -> {
                this.reset();
                return;
            }
            default -> {
            }
        }

        this.setTitle("Lives: " + this.lives + " -> Line: " + (this.lolo.getPosition().getRow()) + ", Col: "
                + (this.lolo.getPosition().getColumn()));
    }

    @Override
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        graphics = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        for (int i = 0; i < Constants.RES; i++) {
            for (int j = 0; j < Constants.RES-1; j++) {
                paintInPosition(i, j, Constants.BRICKS_PNG_PATH);
            }
        }

        for (int i = 0; i < Constants.RES; i++) {
            paintInPosition(i, (Constants.RES-1), Constants.BLACK_PNG_PATH);
        }

        paintInPosition(7, (Constants.RES-1), Constants.L_PNG_PATH);
        paintInPosition(8, (Constants.RES-1), Constants.O_PNG_PATH);
        paintInPosition(9, (Constants.RES-1), Constants.L_PNG_PATH);
        paintInPosition(10, (Constants.RES-1), Constants.O_PNG_PATH);

        paintLives();

        if (!this.entities.isEmpty() && this.lolo != null) {
            this.draw(entities);
            this.process(entities);
        }

        g.dispose();
        graphics.dispose();

        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    private void paintLives() {
        int constant = 1;
        for(int i=0; i < lives; i++) {
            paintInPosition(constant+i, (Constants.RES-1), Constants.LOLO_PNG_PATH);
        }
    }

    private void paintInPosition(int i, int j, String src) {
        try {
            Image newImage = Toolkit.getDefaultToolkit().
                    getImage(new java.io.File(".").getCanonicalPath() + Constants.PATH + src);
            graphics.drawImage(newImage,
                    j * Constants.CELL_SIDE, i * Constants.CELL_SIDE, Constants.CELL_SIDE, Constants.CELL_SIDE, null);

        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MoveManager getMoveManager() {
        return this.moveManager;
    }

    public ArrayList<Entity> getEntities() {
        return new ArrayList<>(entities);
    }

    public List<Entity> getEntitiesInRow(int row) {
        return this.getEntities().stream().filter(entity -> entity.getPosition().getRow() == row).toList();
    }

    public List<Entity> getEntitiesInColumn(int column) {
        return this.getEntities().stream().filter(entity -> entity.getPosition().getColumn() == column).toList();
    }

}
