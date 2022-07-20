/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Controller.Phases.*;
import Global.EndGame;
import Global.LostGame;
import Global.Phase;

import java.util.ArrayList;
import java.util.List;

public class Game implements PhaseCompleteListener, LivesListener {
    private final List<Phase> phases;
    private short lives;
    private short level;

    private final EndGame endGame;

    private final LostGame lostGame;

    public Game() {
        this.phases = new ArrayList<>();
        this.phases.add(new SnakeGarden(this, this));
        this.phases.add(new SkeletalLabyrinth(this, this));
        this.phases.add(new ArmadilloEscape(this, this));
        this.phases.add(new RocksAndShots(this, this));
        this.phases.add(new GreenSwarm(this, this));
        this.endGame = new EndGame();
        this.lostGame = new LostGame();

        this.level = 1;
        this.lives = 5;

        phases.forEach(phase -> phase.setLives(lives));
    }
    public Phase getPhase() {
        return this.phases.get(this.level-1);
    }

    public void next() {
        this.incrementLevel();
    }

    private void incrementLevel() {
        if (this.level >= this.phases.size()) {
            this.startEndGame();
        } else {
            this.level++;
            this.start();
        }
    }
    private void decrementLives() {
        this.getPhase().stop();
        if(this.lives <= 0) {
            this.startLostGame();
        } else {
            Phase phase = getPhaseBasedInLevel();
            phases.set(level-1, null);
            phases.set(level-1, phase);

            this.lives--;

            phases.forEach(phase_ -> phase_.setLives(lives));

            this.start();
        }
    }

    private Phase getPhaseBasedInLevel() {
        Phase phase;

        switch (level) {
            case 1 -> phase = new SnakeGarden(this, this);
            case 2 -> phase = new SkeletalLabyrinth(this, this);
            case 3 -> phase = new ArmadilloEscape(this, this);
            case 4 -> phase = new RocksAndShots(this, this);
            case 5 -> phase = new GreenSwarm(this, this);
            default -> phase = null;
        }
        return phase;
    }

    @Override
    public void onPhaseComplete() {
        next();
    }

    public void start() {
        Phase phase = this.getPhase();
        java.awt.EventQueue.invokeLater(phase::start);
    }

    public void startEndGame() {
        java.awt.EventQueue.invokeLater(endGame::start);
    }

    public void startLostGame() {
        java.awt.EventQueue.invokeLater(lostGame::start);
    }

    @Override
    public void onLiveDecrease() {
        decrementLives();
    }
}
