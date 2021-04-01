package Battleshipgame;

import java.util.List;

import Battleshipgame.Board.Cell;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Thie class contains exclusively of methods that create a pop up window
 * to help user with same additional information
 * @author nikol
 *
 */
 
 
public class Shots {
/*
 * This Method shows some information about player's/enemy'sthe last 5 shots.
 * Shows the coordinates, if the shot was a "miss" or a "hit" and if it was a "hit" 
 * shows the name of the ship that it was hit.
 *  
 * @param c  		the cell that hit the enemy/player.Through the cell we can find
 * 						all the information we want about the shot (coordinates,hit/miss,type)	
 */

	public static void display_shots( List<Cell> c ) {
		int length = c.size();
		if (length > 5)		
			length = 5;
		Stage window = new Stage();
		//window.initModality(Modality.APPLICATION_MODAL);
		VBox vbox =new VBox(10);
		for (int i=0; i<length; i++)
		{
			HBox hbox = new HBox();
			Cell cell = c.get(i);
			String name = "("+ (cell.y-1 )+","+(cell.x-1) +")";
			Label l = new Label(name);
			l.setPrefWidth(80);
	    	l.setPrefHeight(30);
	    	l.setAlignment(Pos.CENTER);
	    	hbox.getChildren().add(l);
	    	if (cell.ship!=null)
	    		name = "hit";
	    	else name= "miss";
	    	Label l1 = new Label(name);
			l1.setPrefWidth(80);
	    	l1.setPrefHeight(30);
	    	l1.setAlignment(Pos.CENTER);
	    	hbox.getChildren().add(l1);
	    	if (cell.ship!=null)
	    	{
	    		switch(cell.ship.type) {
	            case 1:
	                name = "Currier";
	                break;
	            case 2:
	            	name = "Battleship";
	                break;
	            case 3:
	            	name = "Cruiser";
	                break;
	            case 4:
	            	name = "Submarine";
	                break;
	            case 5:
	            	name = "Destroyer";
	                break;
	            default:
	            	name = "";
	    		}
	    	}else name= "";
			
			Label l2 = new Label(name);
			l2.setPrefWidth(80);
	    	l2.setPrefHeight(30);
	    	l2.setAlignment(Pos.CENTER);
	    	hbox.getChildren().add(l2);
		

		vbox.getChildren().add(hbox);
		}
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
