package agent;

import java.util.List;

import components.Point;
import heuristics.Heuristic;
import heuristics.LinearHeuristic;
import main.State;
import scenario.ScenarioManager;
import scenario.Tree;

public class Agent {

	private Heuristic heuristic;
	private ScenarioManager sManager;
	
	private int victories, defeats;
	
	public static final int MAXIMUM_DISTANCE = 5;
	
	public Agent(Heuristic heuristic){
		this.heuristic = heuristic;
		restart();
	}
	
	public boolean moveNext() {
		State actualState = State.CONTINUE;
		System.out.println(sManager.toString());
		while(actualState == State.CONTINUE){
			List<Point> pts = sManager.getAvailablePointsToNextPosition(MAXIMUM_DISTANCE, false);
			if(pts.isEmpty()){
				pts = sManager.getAvailablePointsToNextPosition(MAXIMUM_DISTANCE, true);
			}
			boolean containsThreat = containsThreat(pts);
			Point pt = heuristic.evaluatePositions(pts, sManager.getActualPosition());
			try {
				if(pt != null){
					//Tree tree = new Tree(this.sManager.getActualPosition(), pt, this.sManager);
					actualState = sManager.saveMoveAndExplore(pt, containsThreat);
					System.out.println(sManager.toString());
				} else {
					System.out.print("Nenhum ponto avaliado!");
					return true;
				}
			} catch (Exception e) {
				// TODO Handle errors
				e.printStackTrace();
			}
		}
		
		if(actualState == State.GAME_OVER_HOLE){
			defeats++;
			System.out.println("Game over, hole found");
		} else if(actualState == State.GAME_OVER_WUMPUS){
			defeats++;
			System.out.println("Game over, wumpus found!");
		} else {
			victories++;
			System.out.println("Victoryyy :D");
		}
		
		return true;
	}
	
	private boolean containsThreat(List<Point> pts) {
		for(Point p : pts){
			List<Point> neighbors = this.sManager.getAllNeighbors(p, null, true);
			if(!neighbors.isEmpty()){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString(){
		return sManager.toString();
	}

	public int getDefeats() {
		return defeats;
	}
	
	public int getVictories(){
		return victories;
	}

	public void restart() {
		this.sManager = ScenarioManager.newInstance();
		if(this.heuristic instanceof LinearHeuristic){
			((LinearHeuristic)this.heuristic).setPointsToGold(sManager.getMovimentsToGold());
		}
	}
}
