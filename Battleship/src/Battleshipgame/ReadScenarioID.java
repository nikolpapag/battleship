package Battleshipgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.lang.reflect.Array;
import java.util.Scanner;

/**
 * @author nikol
 *
 */
public class ReadScenarioID {

	//public static Object listP;
	public static ArrayList<Integer> listP;
	public static ArrayList<Integer> listE;
	
	public ReadScenarioID (int x) throws FileNotFoundException {
	
		Scanner p = new Scanner(new File("medialab/player_"+ String.valueOf(x)+".txt")).useDelimiter("([\\n,]|(\\r\\n))+");
		listP = new ArrayList<Integer>();

		while (p.hasNextLine())
			listP.add(p.nextInt());
		Scanner e = new Scanner(new File("medialab/enemy_"+ String.valueOf(x)+".txt")).useDelimiter("([\\n,]|(\\r\\n))+");
		listE = new ArrayList<Integer>();
		
		while (e.hasNextLine())
			listE.add(e.nextInt());
		//System.out.println(listE);
	}
	
	
	public static ArrayList<Integer> getlistP() {
	    return listP;		
	}
	
	public static ArrayList<Integer> getlistE() {
	    return listE;		
	}
}