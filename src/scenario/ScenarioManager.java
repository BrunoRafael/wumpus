package scenario;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import components.Component;
import components.Danger;
import components.Gold;
import components.Hole;
import components.Hunter;
import components.Wumpus;
import utils.Movimentation;

public class ScenarioManager {
	
	private Scenario sc;
	
	public void generateScenario(){
		this.sc = new Scenario();
		try {
			
			List<Integer> pos = new LinkedList<Integer>();
			pos.add(0);
			pos.add(0);
			this.sc.addComponent(new Hunter(pos));
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
					
		
	}
	
	private List<Integer> generatePosition(ComponentName component){
		
		Random rn = new Random();
		
		List<Integer> pos = new LinkedList<Integer>();
		pos.add(rn.nextInt(sc.getWidth()));
		pos.add(rn.nextInt(sc.getHeight()));
		
		while(!sc.isAvailable(component, pos)){
			pos.set(0, rn.nextInt(sc.getWidth()));
			pos.set(1, rn.nextInt(sc.getHeight()));
		};
		
		return pos;
	}
	
	private void addAlertTo(Danger cTarget){
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Movimentation> getMovimentsToGold(){
		List<Integer> gPosition = this.sc.getGoldPosition();
		List<Integer> hPosition = this.sc.getHunterPosition();
		
		int hDistance = Math.abs(gPosition.get(0) - hPosition.get(0));
		int vDistance = Math.abs(gPosition.get(1) - hPosition.get(1));
		
		if(hDistance > vDistance){
			while(hDistance != vDistance){
				
			}
		} else {
			while(hDistance != vDistance){
				
			}
		}
	}
	
	@Override
	public String toString(){
		String s = "";
		for(List<Map<ComponentName, Component>> line : this.sc.getBoard()){
			for(Map<ComponentName, Component> m : line){
				if(m.size() == 0){
					s += " * ";
				} else {
					for(ComponentName component : m.keySet()){
						if(component == null){
							s += " * ";
						} else {
							if(m.values().size() > 1){
								s += component.getSymbol();
							} else {
								s += component.toString();								
							}
						}
					}
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
