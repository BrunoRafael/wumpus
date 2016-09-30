package main;

import agent.Agent;
import heuristics.Heuristic;

public class Game {

	private Agent agent;
	
	public Game(Heuristic heuristic, boolean useFirstDiagonal){
		this.agent = new Agent(heuristic, useFirstDiagonal);
	}
	
	public void execute(){
		boolean finalized = agent.nextMove(); 
		while(!finalized){
			finalized = agent.nextMove();
		}
	}
	
	@Override
	public String toString(){
		return agent.toString();
	}
}
