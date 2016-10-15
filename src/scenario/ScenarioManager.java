package scenario;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import components.Component;
import components.Danger;
import components.Gold;
import components.Hole;
import components.Hunter;
import components.Movimentation;
import components.Point;
import components.Wumpus;
import main.State;
import utils.Direction;

public class ScenarioManager {
	
	private Scenario sc;
	private List<Point> movimentsToGold;
	
	private static ScenarioManager sm;
	
	private ScenarioManager(){
		this.sc = new Scenario();
		generateScenario();
		this.movimentsToGold = generateMovimentsToGold();
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
			explore(this.sc.getHunterPosition());
		} catch (Exception e) {
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
			e.printStackTrace();
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public State saveMoveAndExplore(Point newCoord, boolean containsThreat) throws Exception{
		Point p = this.sc.getHunterPosition();
		State st = explore(newCoord);
		
		Movimentation mov = new Movimentation();
		mov.setLocalization(newCoord.getX() + "," + newCoord.getY());
		mov.setPerception(containsThreat ? 1 : 0);
		
		Direction d = getDirection(p, newCoord);
		if(d != null){
			mov.setMovimentation(d.getCode());
		
			if(st == State.GAME_OVER_HOLE || st == State.GAME_OVER_WUMPUS){
				mov.setExit(0);
			} else {
				mov.setExit(1);
			}
			
			
			this.sc.saveMovimentation(mov);
		}
		return st;
	}
	
	private State explore(Point newCoord) throws Exception {
		this.sc.saveAvailablePointsToNextPosition(newCoord);
		return this.sc.moveComponent(ComponentName.HUNTER, 
				this.sc.getHunterPosition(), newCoord);
	}

	public List<Point> getAllNeighbors(Point p, List<Point> neighborsActual, boolean insertExplored){
		return this.sc.getAvailableNeighbors(p, neighborsActual, insertExplored);
	}
	
	public List<Point> getAllNeighbors(Point p){
		return this.sc.getAvailableNeighbors(p);
	}
	
	private List<Point> generateMovimentsToGold(){
		
		Point gPosition = this.sc.getGoldPosition();
		Point hPosition = this.sc.getHunterPosition();
		
		int hDistance = Math.abs(gPosition.getY() - hPosition.getY());
		int vDistance = Math.abs(gPosition.getX() - hPosition.getX());
		
		return incrementPositionDiagonal(hDistance, vDistance, 
				hPosition, gPosition);
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
			if(hDistance == vDistance){
				System.out.println("equals");
			}
			while(true){
				actualPoint = selectPoint(actualPoint, goldPosition);
				points.add(actualPoint);
				hDistance = Math.abs(actualPoint.getY() - goldPosition.getY());
				vDistance = Math.abs(actualPoint.getX() - goldPosition.getX());
				if(hDistance == 0 && vDistance == 0){
					break;
				}
			}
		}
		
		return points;
	};
	
	private Point selectPoint(Point actualPoint, Point goldPosition){
		Point newPoint = new Point(actualPoint.getX(), actualPoint.getY());
		if(actualPoint.getX() < goldPosition.getX() && 
				actualPoint.getX() < this.sc.getWidth()){
			newPoint.setX(actualPoint.getX() + 1);
		}
		
		if(actualPoint.getY() < goldPosition.getY() && 
				actualPoint.getY() < this.sc.getHeight()){
			newPoint.setY(actualPoint.getY() + 1);
		}
		
		return newPoint;
	}
	
	public List<Point> getMovimentsToGold() {
		return movimentsToGold;
	}

	public List<Point> getAvailablePointsToNextPosition(double maximumDistance, boolean insertExplored) {
		List<Point> points = new LinkedList<Point>();
		for(Point p : this.sc.getAvailablePointsToNextPosition()){
			double distance = calculateDistance(this.sc.getHunterPosition(), p);
			if(insertExplored){
				if(p.isExplored() && distance <= maximumDistance){
					points.add(p);
				}
			} else {
				if(!p.isExplored() && distance <= maximumDistance){
					points.add(p);
				}
			}
		}
		return points;
	}
	
	public Point getActualPosition(){
		return this.sc.getHunterPosition();
	}

	@Override
	public String toString(){
		return this.sc.toString();
	}

	public double calculateDistance(Point actualPos, Point evaluatePos) {
		double xDiff = Math.abs(actualPos.getX() - evaluatePos.getX());
		double yDiff = Math.abs(actualPos.getY() - evaluatePos.getY());

		return xDiff + yDiff;
	}
	
	public Direction getDirection(Point origin, Point destination){
		int diffX = destination.getX() - origin.getX();
		int diffY = destination.getY() - origin.getY();
		if(diffX == diffY){
			return null;
		}
		if(diffX > diffY){
			if(diffX > 0){
				return Direction.RIGHT;
			} else {
				return Direction.LEFT;
			}
		} else {
			if(diffY > 0){
				return Direction.DOWN;
			} else {
				return Direction.UP;
			}
		}
	}
	
	public static ScenarioManager newInstance(){
		sm = new ScenarioManager();
		return sm;
	}

	public static ScenarioManager getInstance() {
		if(sm == null){
			sm = new ScenarioManager();
		}
		return sm;
	}
	
}
