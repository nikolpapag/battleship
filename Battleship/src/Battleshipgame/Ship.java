/**
 * 
 */
package Battleshipgame;

/**
 * @author nikol
 *
 */
import javafx.scene.Parent;

public class Ship extends Parent {
    public int type;
    public boolean vertical = true;

    public int health;

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        if (type==1)
        	health=5;
        else if (type==2)
        		health=4;
        else if (type==3 || type ==4)
        		health=3;
        else
        	health = 2;


    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}