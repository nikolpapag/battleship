/**
 * 
 */
package Battleshipgame;

import java.util.ArrayList;

/**
 * @author nikol
 *
 */

import java.util.Random;

import java.util.List;
import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import Battleshipgame.Board.Cell;

public class Battleshipmain extends Application {

    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;
    int i = 0 ;
    private boolean enemyTurn;
    private int playersmoves = 0;
    private int enemysmoves  = 0;
    private List<Point2D> possiblehits = new ArrayList<Point2D>();
    private Point2D new_possible = new Point2D(0,0);
    private Random random = new Random();
    private ReadScenarioID Scenario;
    private TextField x_coordinate, y_coordinate;
    private int previous_x = 0;
    private int previous_y = 0;
    private int first_shot = 5;
    //private boolean possible = false;
    private int first_x = 0;
    private int direction = 0; 
    private int first_y = 0;
    private int enemypercent = 0;
	private int playerpercent = 0;
    private int playershits = 0;
    private int enemyshits = 0;
    private int enemy_score = 0;
    private int player_score = 0;
    private HBox hbox;
    private int id;
    private int[] enemyships_status = {0, 0 , 0, 0, 0, 0};
    private Statistics st;
    boolean running = false;
    private List<Cell> enemy_shots = new ArrayList<Cell>();
    private List<Cell> player_shots = new ArrayList<Cell>();
    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }
    
    
    
    private boolean isValidPoint(double x, double y) {
        return x >= 1 && x <= 10 && y >= 1 && y <= 10;
    }
    
    
    private void initialize_board(){
    	//we have to initialize again the variables every time after the start button
    	st.change(true,5, 0, 0);
    	st.change(false, 5, 0, 0);
    	running = true;
    	enemyTurn = random.nextBoolean();
    	playersmoves = 0;
        enemysmoves  = 0;
    	previous_x = 0;
        previous_y = 0;
        first_shot = 5;
        //private boolean possible = false;
        first_x = 0;
        direction = 0; 
        first_y = 0;
        enemypercent = 0;
    	playerpercent = 0;
        playershits = 0;
        enemyshits = 0;
        enemy_score = 0;
        player_score = 0;
        enemyships_status = new int[6];
        enemyBoard = new Board(true);
        playerBoard = new Board(false);
        enemy_shots = new ArrayList<Cell>();
        player_shots = new ArrayList<Cell>();
        try {
	        Scenario = new ReadScenarioID(id);
	        ArrayList<Integer> PlayersList = Scenario.getlistP();
	        i = 0 ;
	        while(i<20) {
		        shipsToPlace = PlayersList.get(i);
		        boolean vertical = (! (PlayersList.get(i+3) == 1) );
		        int x = PlayersList.get(i+1);
		        int y = PlayersList.get(i+2);
		        if (playerBoard.placeShip(new Ship(shipsToPlace, vertical), y+1, x+1)) {
		            if (i == 16) {
		            	ArrayList<Integer> EnemyList = Scenario.getlistE();
		    	        i = 0 ;
		    	        //System.out.println(EnemyList);
		    	        while(i<20) {
		    		        shipsToPlace = EnemyList.get(i);
		    		        vertical = (! (EnemyList.get(i+3) == 1) );
		    		        x = EnemyList.get(i+1);
		    		        y = EnemyList.get(i+2);
		    		        //System.out.println(shipsToPlace);
		    		        if (enemyBoard.placeShip(new Ship(shipsToPlace, vertical), y+1, x+1)) {
		    		            
		    		        	if (i == 16) {
		    		                break;
		    		            }
		    		        }
		    		        i=i+4;
		    	        }
		            }
		        }
		        i = i + 4;
	        }
	        Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("FIRST MOVE");
        	alert.setHeaderText("FIRST MOVE ");
        	alert.setContentText(enemyTurn ? "COMPUTER":"YOU! MAY THE FORCE BE WITH YOU");
        	alert.showAndWait();
	        if (enemyTurn)
	        	enemyMove();
        }catch (Exception e) {
        	//System.out.println("not found");
        	running = false;
            enemyBoard = new Board(true);
            playerBoard = new Board(false);
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("ERROR");
        	alert.setHeaderText(e.getClass().getSimpleName());
        	alert.setContentText(e.getMessage());
        	alert.showAndWait();
        }
        finally {
        	hbox.getChildren().clear();
            hbox.getChildren().addAll(playerBoard, enemyBoard);
        }
    }
    
    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(1500, 700);
        
        st = new Statistics();
        root.getChildren().add(st);
        //Title for my board
        final Text text1 = new Text(520,170, "My Board");
        text1.setFill(Color.CHOCOLATE);
        text1.setFont(Font.font(java.awt.Font.SERIF, 25));
        root.getChildren().add(text1);
        //Title for enemy's board
        final Text text2 = new Text(880,170, "Enemy's Board");
        text2.setFill(Color.CHOCOLATE);
        text2.setFont(Font.font(java.awt.Font.SERIF, 25));
        root.getChildren().add(text2);
        
    	MenuBar mb = new MenuBar();
    	root.setTop(mb);
    	//first menu with sub items
    	Menu m = new Menu("Application");
    	MenuItem mi1 = new MenuItem("Start");
    	mi1.setOnAction(e ->{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Start");
    		alert.setHeaderText("Start with the same id");
    		alert.setContentText("Start the Game!");

    		alert.showAndWait();
    		initialize_board();
    		
    	} );
    	MenuItem mi2 = new MenuItem("Load");
    	mi2.setOnAction(e-> {
    		TextInputDialog dialog = new TextInputDialog("0");
    		dialog.setTitle("Load");
    		dialog.setHeaderText("Load Scenario-ID");
    		dialog.setContentText("Give the number");
    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    			id=Integer.parseInt(result.get());
    		    //System.out.println("Your name: " + result.get());
    			initialize_board();
    		}
    		
    	});
    	MenuItem mi3 = new MenuItem("Exit");
    	mi3.setOnAction(e-> {System.exit(0);} );
    	m.getItems().addAll(mi1,mi2,mi3);
    	//second menu with sub items
    	Menu m1 = new Menu("Details");
    	MenuItem r1 = new MenuItem("Enemy Ships");
    	r1.setOnAction(e ->{
    		Details.enemyships_function(enemyships_status);
    	});
    	MenuItem r2 = new MenuItem("Player Shots");
    	r2.setOnAction(e ->{
    		Shots.display_shots(player_shots);
    	});
    	m1.getItems().addAll(r1,r2);
    	MenuItem r3 = new MenuItem("Enemy Shots");
    	r3.setOnAction(e ->{
    		Shots.display_shots(enemy_shots);
    	});
    	m1.getItems().add(r3);
    	mb.getMenus().addAll(m, m1);
    	
       
    	//initialize_board();
        
    	enemyBoard = new Board(true);//allagh se true
        playerBoard = new Board(false); 
        hbox = new HBox(50, playerBoard, enemyBoard);
        Button enter = new Button("Shoot");
        x_coordinate = new TextField();
        x_coordinate.setPromptText("Select row");
        y_coordinate = new TextField();
        y_coordinate.setPromptText("Select column");
        HBox coordinatesbox = new HBox(17);
        coordinatesbox.getChildren().addAll(x_coordinate,y_coordinate,enter);
        coordinatesbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        VBox generalbox = new VBox(15,hbox,coordinatesbox);
        generalbox.setAlignment(Pos.CENTER); 
        enemyTurn = random.nextBoolean();
        if (enemyTurn && running) 
        	enemyMove();
        enter.setOnAction(e->{
        	//System.out.println(x_coordinate.getText()+" " +y_coordinate.getText());
        	if (running)
        		playerMove();
        	if (enemyTurn && running)
        		enemyMove();
        });
        root.setCenter(generalbox);
        
        return root;
    }
    
    private void playerMove() {
    	// System.out.println("hey");
        	if ((x_coordinate.getText().equals("")) && (y_coordinate.getText().equals("")))
        		return;
    		int x = Integer.parseInt(x_coordinate.getText())+1;
            int y = Integer.parseInt(y_coordinate.getText())+1;
            x_coordinate.clear();
            y_coordinate.clear();
            //System.out.println(x);
            //System.out.println(y);

            Cell cell = enemyBoard.getCell(y, x);
            if (cell.wasShot) {
            	return;
            }
             
            playersmoves = playersmoves + 1;
            enemyTurn = !cell.shoot();
            player_shots.add(0,cell);
            if(!enemyTurn) {
            	if(cell.ship.isAlive())
            		enemyships_status[cell.ship.type] = 1;
            	else
            		enemyships_status[cell.ship.type] = 2;
            	playershits = playershits + 1;
            	player_score = score(player_score, cell.ship);
            	//System.out.println(player_score);
            }    
            playerpercent = (playershits *100) / playersmoves;
            st.change(true, enemyBoard.ships, playerpercent, player_score);
            //System.out.println(playerTurn);
            if(playersmoves == 40) {//////////////////////////////////////////////////////
            	if(player_score > enemy_score) {
            		final_result("WINNER!");
            	}
            	else if (enemy_score> player_score)
            		final_result("YOU LOSE!");
            	else final_result("DRAW!");
            	running = false;
            }
            if (enemyBoard.ships == 0) {
            	final_result("WINNER!");
            	running = false;
            }
            
    }
    private void enemyMove() { 
    	int x = 0;
    	int y = 0;
    	int n = 0;
    	int dx = 0;
    	int dy = 0;
    	while(enemyTurn) {
    		n= 0;
        	if(possiblehits.isEmpty()) {
        		x = random.nextInt(10)+1;
        		previous_x = x;
                y = random.nextInt(10)+1;
                previous_y = y;
                first_shot = 0;
                direction = 0;
        	}
        	else
        	{
        		x = (int) possiblehits.get(n).getX();
                y = (int) possiblehits.get(n).getY();
                dx = x - previous_x;
                dy = y - previous_y;
                possiblehits.remove(n);
                //possible = true;
        	}
        	
            Cell cell = playerBoard.getCell(y, x);
            
            if (cell.wasShot) {
            	continue;
            }
                
            
            enemyTurn = cell.shoot();
            enemysmoves = enemysmoves + 1;
            enemy_shots.add(0,cell);
            if(enemyTurn) {
            	enemy_score = score(enemy_score, cell.ship);
            	enemyshits = enemyshits + 1;
            	previous_x = x;
            	previous_y = y;
            	if (first_shot==0) {
            		first_shot = 1;
            		first_x = x;
            		first_y = y;
            		direction = 0;
                	List<Point2D> new_possiblehits = (playerBoard.possible_neighbors(x, y));
                    possiblehits.addAll(new_possiblehits);
            	}
            	else {
            		if ((dx == -1) && (dy == 0)) {
                		possiblehits.removeAll(possiblehits);
                		new_possible = new Point2D((x+dx),(y+dy));
                		if (isValidPoint(new_possible)){
                			possiblehits.add(new_possible);
                    		direction = 1;
                		}
            		}
            		if ((dx == 0) && (dy == 1)) {
                		possiblehits.removeAll(possiblehits);
                		new_possible = new Point2D((x+dx),(y+dy));
                		if (isValidPoint(new_possible)){
                			possiblehits.add(new_possible);
                    		direction = 2;
                		}
            		}
            		if ((dx == 1) && (dy == 0)) {
                		possiblehits.removeAll(possiblehits);
                		new_possible = new Point2D((x+dx),(y+dy));
                		if (isValidPoint(new_possible)){
                			possiblehits.add(new_possible);
                    		direction = 3;
                		}
            		} 
            		if ((dx == 0) && (dy == -1)) {
                		possiblehits.removeAll(possiblehits);
                		new_possible = new Point2D((x+dx),(y+dy));
                		if (isValidPoint(new_possible)){
                			possiblehits.add(new_possible);
                    		direction = 4;
                		}
                		
                	} 	 	
            	}
            }
            else
            {
            	if ( direction == 1 )
            	{	
            		new_possible = new Point2D((first_x - 1), first_y);
            		possiblehits.add(new_possible);
            		previous_x = first_x;
            		previous_y = first_y;
            	}
            	if ( direction == 2 )
            	{
            		new_possible = new Point2D(first_x, (first_y - 1) );
            		possiblehits.add(new_possible);
            		previous_x = first_x;
            		previous_y = first_y;
            	}
            }
            enemypercent = (enemyshits *100) / enemysmoves;
            st.change(false, playerBoard.ships, enemypercent, enemy_score);
            if(enemysmoves==40) {
            	if(player_score > enemy_score)
            		final_result("WINNER!");
            	else if (enemy_score> player_score)
            		final_result("YOU LOSE!");
            	else final_result("DRAW!");
            	running=false;
            }
            
            if (playerBoard.ships == 0) {
            	final_result("YOU LOSE!");
            	running = false;
            }
    	}
    }
    
    public void final_result (String win) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("The End");
		alert.setHeaderText("Game is over");
		alert.setContentText(win);
		alert.showAndWait();
    }
    
    public int score(int the_score, Ship ship) {
        switch(ship.type) {
            case 5:
                the_score = the_score + 50;
                break;
            case 4:
            	the_score = the_score + 100;
                break;
            case 3:
            	the_score = the_score + 100;
                if (!ship.isAlive()) {
                	the_score = the_score + 250;
                }
                break;
            case 2:
            	the_score = the_score + 250;
                if (!ship.isAlive()) {
                	the_score = the_score + 500;
                }
                break;
            case 1:
            	the_score = the_score + 350;
                if (!ship.isAlive()) {
                	the_score = the_score + 1000;
                }
                break;
            default:
            	the_score = the_score + 0;
        }
        return  the_score;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        //scene.setFill(Color.BLACK);
        primaryStage.setTitle("MediaLab  Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

