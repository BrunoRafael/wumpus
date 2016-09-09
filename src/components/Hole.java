package components;

import java.util.List;

import scenario.ComponentName;

public class Hole extends Danger{

	public Hole(List<Integer> coord) {
		super(coord, ComponentName.HOLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Alert createAlert(List<Integer> position) {
		// TODO Auto-generated method stub
		return new Alert(position, ComponentName.BREEZE);
	}

	@Override
	public char getSymbol() {
		return ComponentName.HOLE.getSymbol();
	}

}
