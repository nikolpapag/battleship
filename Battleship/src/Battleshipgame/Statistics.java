package Battleshipgame;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//fuction to display the information at the top of the window(active ships, total score, hit percentage)
public class Statistics extends Parent {
	private VBox rows = new VBox();
	
    
	public Statistics () {
		rows.setPadding(new Insets(20,20,20,570));
		HBox r = new HBox();
		
		Label label = new Label("");
    	label.setPrefWidth(80);
    	label.setPrefHeight(30);
    	label.setAlignment(Pos.CENTER);
    	r.getChildren().add(label);
    	Label label1 = new Label("Active Ships");
    	label1.setPrefWidth(80);
    	label1.setPrefHeight(30);
    	label1.setAlignment(Pos.CENTER);
    	r.getChildren().add(label1);
    	Label label2 = new Label("Total Score");
    	label2.setPrefWidth(80);
    	label2.setPrefHeight(30);
    	label2.setAlignment(Pos.CENTER);
    	r.getChildren().add(label2);
    	Label label3 = new Label("Hit Percentage");
    	label3.setPrefWidth(80);
    	label3.setPrefHeight(30);
    	label3.setAlignment(Pos.CENTER);
    	r.getChildren().add(label3);
    	rows.getChildren().add(r);
    	
    	
    	r = new HBox();
		label = new Label("Player");
    	label.setPrefWidth(80);
    	label.setPrefHeight(30);
    	label.setAlignment(Pos.CENTER);
    	r.getChildren().add(label);
    	label1 = new Label(String.valueOf(5));
    	label1.setPrefWidth(80);
    	label1.setPrefHeight(30);
    	label1.setAlignment(Pos.CENTER);
    	r.getChildren().add(label1);
    	label2 = new Label(String.valueOf(0));
    	label2.setPrefWidth(80);
    	label2.setPrefHeight(30);
    	label2.setAlignment(Pos.CENTER);
    	r.getChildren().add(label2);
    	label3 = new Label(String.valueOf(0));
    	label3.setPrefWidth(80);
    	label3.setPrefHeight(30);
    	label3.setAlignment(Pos.CENTER);
    	r.getChildren().add(label3);
    	rows.getChildren().add(r);
    	
    	r = new HBox();
    	label = new Label("Enemy");
    	label.setPrefWidth(80);
    	label.setPrefHeight(30);
    	label.setAlignment(Pos.CENTER);
    	r.getChildren().add(label);
    	label1 = new Label("5");
    	label1.setPrefWidth(80);
    	label1.setPrefHeight(30);
    	label1.setAlignment(Pos.CENTER);
    	r.getChildren().add(label1);
    	label2 = new Label("0");
    	label2.setPrefWidth(80);
    	label2.setPrefHeight(30);
    	label2.setAlignment(Pos.CENTER);
    	r.getChildren().add(label2);
    	label3 = new Label("0");
    	label3.setPrefWidth(80);
    	label3.setPrefHeight(30);
    	label3.setAlignment(Pos.CENTER);
    	r.getChildren().add(label3);
    	
    	rows.getChildren().add(r);
    	getChildren().add(rows);
	}
	
	
	public Label getLabel(int x, int y) {
        return (Label)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

	public void change(boolean player, int ships, int per, int score) {
		if (player) {
			Label l = getLabel(1,2);
			l.setText(String.valueOf(ships));
			l = getLabel(2,1);
			l.setText(String.valueOf(score));
			l = getLabel(3,1);
			l.setText(String.valueOf(per));
		}
		else {
			Label l = getLabel(1,1);
			l.setText(String.valueOf(ships));
			l = getLabel(2,2);
			l.setText(String.valueOf(score));
			l = getLabel(3,2);
			l.setText(String.valueOf(per));
		}
		
	}

}