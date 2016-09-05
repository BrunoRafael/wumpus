package main;

import scenario.ScenarioManager;

public class Main {

	public static void main(String[] args) {
		ScenarioManager sc = new ScenarioManager();
		sc.generateScenario();
		System.out.println(sc.toString());
	}

}
