/**
 * 
 */
package Battleshipgame;

/**
 * @author nikol
 *
 */
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Parent {
    private VBox rows = new VBox();
    private boolean enemy = false;
    public int ships = 5;
    int carrier = 0;
    int battleship = 0;
    int cruiser  = 0;
    int submarine = 0;
    int destroyer = 0;
    int other_ship = 0;

    public Board(boolean enemy) {
        this.enemy = enemy;
        for (int y = 0; y <= 10; y++) {
            HBox row = new HBox();
            for (int x = 0; x <= 10; x++) {
            	
                Cell c = new Cell(x, y, this);
                if(y==0 && x!=0){
                	Label label = new Label(String.valueOf(x-1));
                	label.setPrefWidth(31);
                	label.setPrefHeight(30);
                	label.setAlignment(Pos.CENTER);
                	row.getChildren().add(label);
                }
                else if (y!=0 && x==0) {
                	Label label = new Label(String.valueOf(y-1));
                	label.setPrefWidth(30);
                	label.setPrefHeight(31);
                	label.setAlignment(Pos.CENTER);
                	row.getChildren().add(label);
                }
                else if (x==0 && y==0)
                {
                	Label label = new Label("");
                	label.setPrefWidth(31);
                	label.setPrefHeight(30);
                	label.setAlignment(Pos.CENTER);
                	row.getChildren().add(label);
                }
                else
                	row.getChildren().add(c);
            }
            
            rows.getChildren().add(row);
        }
        getChildren().add(rows);
    }

    public boolean placeShip(Ship ship, int x, int y) throws OversizeException, OverlapTilesException, AdjacentTilesException, InvalidCountException, InvalidException{
    	//System.out.println(x);
        //System.out.println(y);
    	try {
	    	if (canPlaceShip(ship, x, y)) {
	            int length = ship.health;
	            if (ship.vertical) {
	                for (int i = y; i < y + length; i++) {
	                    Cell cell = getCell(x, i);
	                    cell.ship = ship;
	                    if (!enemy) {
	                        cell.setFill(Color.WHITE);
	                        cell.setStroke(Color.GREEN);
	                    }
	                }
	            }
	            else {
	                for (int i = x; i < x + length; i++) {
	                    Cell cell = getCell(i, y);
	                    cell.ship = ship;
	                    if (!enemy) {
	                        cell.setFill(Color.WHITE);
	                        cell.setStroke(Color.GREEN);
	                    }
	                }
	            }
	
	            return true;
	    	}
	    	
        }catch(Exception e) {
        	throw e;
        }
    	
    	return false;
    }

    public Cell getCell(int x, int y) {
        return (Cell)((HBox)rows.getChildren().get(y)).getChildren().get(x);
    }

    private Cell[] getNeighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
                new Point2D(x, y + 1)
        };

        List<Cell> neighbors = new ArrayList<Cell>();

        for (Point2D p : points) {
            if (isValidPoint(p)) {
                neighbors.add(getCell((int)p.getX(), (int)p.getY()));
            }
        }

        return neighbors.toArray(new Cell[0]);
    }
    
    private boolean canPlaceShip(Ship ship, int x, int y) throws OversizeException, OverlapTilesException, AdjacentTilesException, InvalidCountException, InvalidException {
        int length = ship.health;
        if(ship.type == 1){
        	carrier = carrier + 1;
        }else if (ship.type == 2) {
        	battleship = battleship +1;
        }else if (ship.type == 3) {
        	cruiser = cruiser + 1;
        }else if(ship.type == 4) {
        	submarine = submarine +1;
        }
        else if(ship.type == 5) {
        	destroyer = destroyer +1;
        }
        else other_ship = other_ship +1;
        
        if (other_ship != 0 )
        	throw new InvalidException("You can choose only from five types of ships");
        if (carrier > 1 || battleship > 1 || cruiser > 1 || submarine > 1 || destroyer > 1) {
        	throw new InvalidCountException("You must give only one ship from each type");
        }
        if (ship.vertical) {
            for (int i = y; i < y + length; i++) {
                	if (!isValidPoint(x, i))
                		throw new OversizeException("Out of bounds");                
                Cell cell = getCell(x, i);
                if (cell.ship != null)
                	throw new OverlapTilesException("A Ship can't be placed over another ship");

                for (Cell neighbor : getNeighbors(x, i)) {
                    if (!isValidPoint(x, i))
                    	throw new OversizeException("Out of bounds");

                    if (neighbor.ship != null)
                        throw new AdjacentTilesException("Every ship has to be at least one cell away from each other");
                }
            }
        }
        else {
            for (int i = x; i < x + length; i++) {
                if (!isValidPoint(i, y))
                    throw new OversizeException("Out of bounds");

                Cell cell = getCell(i, y);
                if (cell.ship != null)
                    throw new OverlapTilesException("A Ship can't be placed over another ship");

                for (Cell neighbor : getNeighbors(i, y)) {
                    if (!isValidPoint(i, y))
                    	throw new OversizeException("Out of bounds");

                    if (neighbor.ship != null)
                    	throw new AdjacentTilesException("Every ship has to be at least one cell away from any other ship");
                }
            }
        }

        return true;
    }

    private boolean isValidPoint(Point2D point) {
        return isValidPoint(point.getX(), point.getY());
    }

    private boolean isValidPoint(double x, double y) {
        return x >= 1 && x <= 10 && y >= 1 && y <= 10;
    }

    public class Cell extends Rectangle {
        public int x, y;
        public Ship ship = null;
        public boolean wasShot = false;

        private Board board;

        public Cell(int x, int y, Board board) {
            super(30, 30);
            this.x = x;
            this.y = y;
            this.board = board;
            setFill(Color.BLUE);
            setStroke(Color.BLACK);
        }

        public boolean shoot() {
            wasShot = true;
            setFill(Color.BLACK);

            if (ship != null) {
                ship.hit();
                setFill(Color.RED);
                if (!ship.isAlive()) {
                    board.ships--;
                }
                return true;
            }

            return false;
        }
    }
    public List<Point2D> possible_neighbors(int x, int y) {
        Point2D[] points = new Point2D[] {
                new Point2D(x - 1, y),
                new Point2D(x, y + 1),
                new Point2D(x + 1, y),
                new Point2D(x, y - 1),
        };

        List<Point2D> validPoints = new ArrayList<Point2D>();

        for(Point2D point : points) {
            if(isValidPoint(point)) {
                validPoints.add(point);
            }
        }

        return validPoints;
    }
}