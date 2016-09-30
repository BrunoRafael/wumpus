package scenario;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import components.Component;
import components.Danger;
import components.Gold;
import components.Hole;
import components.Hunter;
import components.Point;
import components.Wumpus;
import main.State;

public class ScenarioManager {
	
	private Scenario sc;
	private List<Point> movimentsToGold;
	
	public ScenarioManager(boolean useFirstDiagonal){
		this.sc = new Scenario();
		generateScenario();
		this.movimentsToGold = getMovimentsToGold(useFirstDiagonal);
	}
	
	private void generateScenario(){
		try {
			
			this.sc.addComponent(new Hunter(new Point(0, 0)));
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
		
	private Point generatePosition(ComponentName component){
		
		Random rn = new Random();
		
		Point pos = new Point();
		pos.setX(rn.nextInt(sc.getWidth()));
		pos.setY(rn.nextInt(sc.getHeight()));
		
		while(!sc.isAvailable(component, pos)){
			pos.setX(rn.nextInt(sc.getWidth()));
			pos.setY(rn.nextInt(sc.getHeight()));
		};
		
		return pos;
	}
	
	private void addAlertTo(Danger cTarget){
		try {
			Point pos = new Point();
			if(cTarget.getX() + 1 <= this.sc.getWidth() - 1){
				pos.setX(cTarget.getX() + 1);
				pos.setY(cTarget.getY());
				this.sc.addComponent(cTarget.createAlert(pos));
			}
			
			if(cTarget.getY() + 1 <= this.sc.getHeight() - 1){
				pos = new Point(cTarget.getX(), cTarget.getY() + 1);
				this.sc.addComponent(cTarget.createAlert(pos));
			}
	
			if(cTarget.getX() - 1 >= 0){
				pos = new Point(cTarget.getX() - 1, cTarget.getY());
				this.sc.addComponent(cTarget.createAlert(pos));
			}
			
			if(cTarget.getY() - 1 >= 0){
				pos = new Point(cTarget.getX(),  cTarget.getY() - 1);

				this.sc.addComponent(cTarget.createAlert(pos));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public State explore(Point newCoord) throws Exception{
		return this.sc.moveComponent(ComponentName.HUNTER, 
				this.sc.getHunterPosition(), newCoord);
	}
	
	public List<Point> getHunterAvailablePositions(){
		return sc.getAvailablePositionsToNext();
	}
	
	private List<Point> getMovimentsToGold(boolean useFirstDiagonal){
		
		Point gPosition = this.sc.getGoldPosition();
		Point hPosition = this.sc.getHunterPosition();
		
		int hDistance = Math.abs(gPosition.getY() - hPosition.getY());
		int vDistance = Math.abs(gPosition.getX() - hPosition.getX());
		
		if(useFirstDiagonal){
			return incrementPositionDiagonal(hDistance, vDistance, 
					hPosition, gPosition);
		} else {
			return incrementPositionHorizontal(hDistance, vDistance, 
					hPosition, gPosition);
		}
	}
	
	private List<Point> incrementPositionDiagonal(int hDistance, int vDistance, 
			Point hunterPosition, Point goldPosition){
		
		List<Point> points = new LinkedList<Point>();
		Point actualPoint = this.sc.getHunterPosition();
		System.out.println(this.sc.toString());
		
		if(hDistance > vDistance){
			while(hDistance > vDistance){
				actualPoint = selectPoint(actualPoint, goldPosition);
				points.add(actualPoint);
				hDistance = Math.abs(actualPoint.getY() - goldPosition.getY());
				vDistance = Math.abs(actualPoint.getX() - goldPosition.getX());
			}
		} else {
			while(hDistance < vDistance){
				actualPoint = selectPoint(actualPoint, goldPosition);
				points.add(actualPoint);
				hDistance = Math.abs(actualPoint.getY() - goldPosition.getY());
				vDistance = Math.abs(actualPoint.getX() - goldPosition.getX());
			}
		}
		
		return points;
	};
	
	private List<Point> incrementPositionHorizontal(int hDistance, int vDistance, 
			Point hunterPosition, Point goldPosition){
		return null;
	};
	
	private Point selectPoint(Point actualPoint, Point goldPosition){
		Point newPoint = new Point(actualPoint.getX(), actualPoint.getY());
		if(actualPoint.getX() < goldPosition.getX()){
			newPoint.setX(actualPoint.getX() + 1);
		}
		
		if(actualPoint.getY() < goldPosition.getY()){
			newPoint.setY(actualPoint.getY() + 1);
		}
		
		return newPoint;
	}
	
	public List<Point> getMovimentsToGold() {
		return movimentsToGold;
	}
	
	@Override
	public String toString(){
		return this.sc.toString();
	}

}
