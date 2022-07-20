/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author junio
 */
public class Draw implements Serializable {
    static Phase scenery;
    
    public static void setScenery(Phase scenery_) {
        scenery = scenery_;
    }

    public static Phase getScenery() {
        return scenery;
    }

    public static Graphics getScreenGraphics() {
        return scenery.getGraphicsBuffer();
    }
    
    public static void draw(ImageIcon image, int column, int row) {
        Graphics screenGraphics = getScreenGraphics();
        image.paintIcon(scenery, screenGraphics,
                column * Constants.CELL_SIDE, row * Constants.CELL_SIDE);
    }
}
