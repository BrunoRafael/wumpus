package main;

import heuristics.LinearHeuristic;

public class Main {

	public static void main(String[] args) {
		Game g = new Game(new LinearHeuristic(), true);
		g.execute();
		System.out.println(g.toString());
	}

}
