package agent;

import java.util.List;

import components.Point;
import heuristics.Heuristic;
import heuristics.LinearHeuristic;
import main.State;
import scenario.ScenarioManager;

public class Agent {

	private Heuristic heuristic;
	private ScenarioManager sManager;
	
	public Agent(Heuristic heuristic, boolean useFirstDiagonal){
		this.heuristic = heuristic;
		this.sManager = new ScenarioManager(useFirstDiagonal);
		if(this.heuristic instanceof LinearHeuristic){
			((LinearHeuristic)this.heuristic).setPointsToGold(sManager.getMovimentsToGold());
		}
	}
	
	public void nextMove() {
		State actualState = State.CONTINUE;
		System.out.println(sManager.toString());
		while(actualState == State.CONTINUE){
			List<Point> pts = sManager.getHunterAvailablePositions();
			Point pt = heuristic.evaluatePositions(pts);
			try {
				actualState = sManager.explore(pt);
				System.out.println(sManager.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(actualState == State.GAME_OVER_HOLE){
			System.out.println("Game over, hole found");
		} else if(actualState == State.GAME_OVER_WUMPUS){
			System.out.println("Game over, wumpus found!");
		} else {
			System.out.println("Victoryyy :D");
		}
	}
	
	public boolean finalized() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString(){
		return sManager.toString();
	}
		
}
