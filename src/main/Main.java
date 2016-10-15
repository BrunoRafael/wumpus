package main;

import java.text.ParseException;

import heuristics.EvaluatePositionHeuristic;
import heuristics.LinearHeuristic;

public class Main {
	public static void main(String[] args) throws ParseException {
		Game g = new Game(new LinearHeuristic());
		g.execute(40);
		
		System.out.println("Derrotas : " + g.getDefeats());
		System.out.println("Vitórias : " + g.getVictories());
	}
}
