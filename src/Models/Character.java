/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Global.Position;

import java.io.Serializable;

/**
 *
 * @author Junio
 */
public abstract class Character extends Movable implements Serializable {
    protected boolean mortal;

    protected Character(Position position, String imageSrc, boolean passable, boolean mortal) {
        super(position, imageSrc, passable);
        setMortal(mortal);
    }

    public boolean isMortal() {
        return mortal;
    }

    public void setMortal(boolean mortal) {
        this.mortal = mortal;
    }

}
