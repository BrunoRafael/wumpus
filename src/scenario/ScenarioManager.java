package scenario;

import java.util.Random;

import components.Wumpus;
import components.WumpusComponent;

public class ScenarioManager {
	
	private Scenario sc;
	
	public void generateScenario(){
		this.sc = new Scenario();
		
		int[] wumpusCordinates = generatePosition();
		
		//add hole
		for(int i = 1; i <= 5; i++){
			
			WumpusComponent h = new Wumpus(generatePosition(), ComponentName.HOLE);
			
			sc.addComponent(h);
			
			for(int j = 1; j <= 4; j++){
				int[] badSmellCoordinates = {};
			}
		}
		
		int[] hunterCoordinates = generatePosition();
		
		int[] breezeCoordinates = generatePosition();
		
	}
	
	private int[] generatePosition(){
		Random rn = new Random();
		int[] pos = new int[2];
		do{
			pos[0] = rn.nextInt(sc.getWidth()) + 1;
			pos[1] = rn.nextInt(sc.getHeight()) + 1;
		}while(sc.isOccupied(pos));
		
		return pos;
	}

}
