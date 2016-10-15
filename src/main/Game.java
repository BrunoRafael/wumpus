package main;

import agent.Agent;
import heuristics.Heuristic;

public class Game {

	private Agent agent;
		
	public Game(Heuristic heuristic){
		this.agent = new Agent(heuristic);
	}
	
	public void execute(int repeat){
		for(int i = 1; i <= repeat; i++){
			boolean finalized = agent.moveNext(); 
			while(!finalized){
				finalized = agent.moveNext();
			}
			agent.restart();
		}
	}
	
	public int getDefeats(){
		return agent.getDefeats();
	}
	
	public int getVictories(){
		return agent.getVictories();
	}
	
	@Override
	public String toString(){
		return agent.toString();
	}
}
