package scenario;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import components.Component;
import components.Danger;
import components.Gold;
import components.Hole;
import components.Hunter;
import components.Wumpus;

public class ScenarioManager {
	
	private Scenario sc;
	
	public void generateScenario(){
		this.sc = new Scenario();
		
		this.sc.addComponent(new Hunter(0,0));
					
		Danger wumpus = new Wumpus(generatePosition(ComponentName.WUMPUS));
		sc.addComponent(wumpus);
		addAlertTo(wumpus);
		
		//add hole
		for(int i = 1; i <= 5; i++){
			Danger hole = new Hole(generatePosition(ComponentName.HOLE));
			sc.addComponent(hole);
			addAlertTo(hole);
		}
		
		Component gold = new Gold(generatePosition(ComponentName.GOLD));
		sc.addComponent(gold);
	}
	
	private List<Integer> generatePosition(ComponentName component){
		
		Random rn = new Random();
		
		List<Integer> pos = new LinkedList<Integer>();
		pos.add(rn.nextInt(sc.getWidth()));
		pos.add(rn.nextInt(sc.getHeight()));
		
		while(sc.isOccupied(pos)){
			pos.set(0, rn.nextInt(sc.getWidth()));
			pos.set(1, rn.nextInt(sc.getHeight()));
		};
		
		return pos;
	}
	
	private void addAlertTo(Danger cTarget){
		List<Integer> pos = new LinkedList<Integer>();
		if(cTarget.getX() + 1 <= this.sc.getWidth() - 1){
			pos.add(0, cTarget.getX() + 1);
			pos.add(1, cTarget.getY());
			this.sc.addComponent(cTarget.createAlert(pos));
		}
		
		if(cTarget.getY() + 1 <= this.sc.getHeight() - 1){
			pos = new LinkedList<Integer>();
			pos.add(0, cTarget.getX());
			pos.add(1, cTarget.getY() + 1);
			this.sc.addComponent(cTarget.createAlert(pos));
		}

		if(cTarget.getX() - 1 >= 0){
			pos = new LinkedList<Integer>();
			pos.add(cTarget.getX() - 1);
			pos.add(cTarget.getY());
			this.sc.addComponent(cTarget.createAlert(pos));
		}
		
		if(cTarget.getY() - 1 >= 0){
			pos = new LinkedList<Integer>();
			pos.add(0, cTarget.getX());
			pos.add(1, cTarget.getY() - 1);
			this.sc.addComponent(cTarget.createAlert(pos));
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		for(Component[] line : this.sc.getBoard()){
			for(Component c : line){
				if(c != null){
					s += " " + c.getSymbol() + " ";
				} else {
					s += " * ";
				}
				
			}
			s += "\n";
		}
		return s;
	}
	
	public String generateMinorRouteToGold(){
		return "";
	}

}
