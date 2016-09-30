package main;

import agent.Agent;
import heuristics.Heuristic;

public class Game {

	private Agent agent;
	
	public Game(Heuristic heuristic, boolean useFirstDiagonal){
		this.agent = new Agent(heuristic, useFirstDiagonal);
	}
	
	public void execute(){
		while(!agent.finalized()){
			agent.nextMove();
		}
	}
	
	@Override
	public String toString(){
		return agent.toString();
	}
}
