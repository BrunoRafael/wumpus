package components;

import java.util.List;

import scenario.ComponentName;

public class Wumpus extends Danger{
	
	public Wumpus(List<Integer> coord) {
		super(coord, ComponentName.WUMPUS);
	}

	@Override
	public Alert createAlert(List<Integer> position) {
		return new Alert(position, ComponentName.BED_SMELL);
	}

	@Override
	public char getSymbol() {
		return ComponentName.WUMPUS.getSymbol();
	}
	
	

}
