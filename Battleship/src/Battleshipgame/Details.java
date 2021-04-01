package Battleshipgame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * 
 * @author nikol
 *
 */
//  for the pop up window Details->Enemyships
public class Details {
	 
	public static void enemyships_function (int [] r) {
		Stage window = new Stage();
		//window.initModality(Modality.APPLICATION_MODAL);
		VBox vbox =new VBox(10);
		for (int i=1; i<=5; i++)
		{
			HBox hbox = new HBox();
			String name;
			switch(i) {
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
			Label l = new Label(name);
			l.setPrefWidth(80);
	    	l.setPrefHeight(30);
	    	l.setAlignment(Pos.CENTER);
	    	hbox.getChildren().add(l);
		switch(r[i]) {
		  case 0:
          	name = "Intact";
              break;
          case 1:
          	name = "Hit";
              break;
          case 2:
          	name = "Sunk";
              break;
          default:
          	name = "";
		}
		Label l1 = new Label(name);
		l1.setPrefWidth(80);
    	l1.setPrefHeight(30);
    	l1.setAlignment(Pos.CENTER);
    	hbox.getChildren().add(l1);
		vbox.getChildren().add(hbox);
		}
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		
	}
}
