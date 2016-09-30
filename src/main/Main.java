package main;

import heuristics.LinearHeuristic;

public class Main {

	public static void main(String[] args) {
		for(int i = 1; i <= 8; i++){
			Game g = new Game(new LinearHeuristic(), true);
			g.execute();
			System.out.println(g.toString());
		}
	}

}
