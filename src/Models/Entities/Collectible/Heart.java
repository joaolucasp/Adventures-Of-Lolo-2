/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.Entities.Collectible;

import Global.Constants;
import Global.Position;
import Models.Entity;

/**
 *
 * @author Joel
 */
public class Heart extends Entity {
    public Heart(int row, int column) {
        super(new Position(row, column), Constants.HEART_PNG_PATH, true, true);
    }
}
