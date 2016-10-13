package main;

import java.text.ParseException;

import heuristics.EvaluatePositionHeuristic;

public class Main {
	public static void main(String[] args) throws ParseException {
		Game g = new Game(new EvaluatePositionHeuristic());
		g.execute(100);
		
		System.out.println("Derrotas : " + g.getDefeats());
		System.out.println("Vitórias : " + g.getVictories());
	}
}
